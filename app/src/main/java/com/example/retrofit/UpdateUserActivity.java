package com.example.retrofit;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.retrofit.model.DetailUserResponse;
import com.example.retrofit.model.UpdateUser;
import com.example.retrofit.service.ApiClient;
import com.example.retrofit.service.UserAPI;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class UpdateUserActivity extends AppCompatActivity {
    public static final String EXTRA_UID = "UserID";

    private long uid = 0;
    private CompositeDisposable compositeDisposable;

    ProgressDialog progressDialog;
    TextView responseText;
    UserAPI apiInterface;

    @Override
    protected void onDestroy() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        compositeDisposable = new CompositeDisposable();

        progressDialog = new ProgressDialog(UpdateUserActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        responseText = findViewById(R.id.responseText);
        apiInterface = ApiClient.getRetrofitInstance(getApplication()).create(UserAPI.class);

        Button submitButton = findViewById(R.id.editSubmitButton);
        submitButton.setOnClickListener((View view) -> submitButton());

        UserAPI service = ApiClient.getRetrofitInstance(getApplicationContext())
                .create(UserAPI.class);

        uid = getIntent().getLongExtra(EXTRA_UID, 0);
        service.getSingleUser(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<DetailUserResponse>() {
                    @Override
                    public void onNext(DetailUserResponse detailUserResponse) {
                        showUserUpdate(detailUserResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(UpdateUserActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                        progressDialog.dismiss();
                    }
                });
    }

    private void showUserUpdate(DetailUserResponse response) {
        EditText vName = findViewById(R.id.editName);
        EditText vJob = findViewById(R.id.editJob);

        String name = response.getData().getFirstName();
        String job = response.getData().getLastName();

        vName.setText(name);
        vJob.setText(job);
    }

    private void submitButton() {
        if (uid <= 0) {
            Toast.makeText(this, "UID tidak ditemukan", Toast.LENGTH_SHORT).show();
            return;
        }
        UserAPI service = ApiClient.getRetrofitInstance(getApplicationContext())
                .create(UserAPI.class);

        EditText nameField = findViewById(R.id.editName);
        String name = nameField.getText().toString();

        EditText jobField = findViewById(R.id.editJob);
        String job = jobField.getText().toString();

        UpdateUser updateUser = new UpdateUser();
        updateUser.setName(name);
        updateUser.setJob(job);

        Disposable d = service.updateUser(uid, updateUser)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                    progressDialog = new ProgressDialog(UpdateUserActivity.this);
                    progressDialog.setMessage("Loading....");
                    progressDialog.show();
                })
                .doAfterTerminate(() -> progressDialog.dismiss())
                .doOnError(throwable -> {

                })
                .subscribe(response -> {
                    Toast.makeText(UpdateUserActivity.this, "Success update user: " + response.getName(), Toast.LENGTH_SHORT).show();
                    finish();
                });

        compositeDisposable.add(d);
    }
}
