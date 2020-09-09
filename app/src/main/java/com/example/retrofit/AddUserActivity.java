package com.example.retrofit;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.retrofit.model.CreateUser;
import com.example.retrofit.model.CreateUserResponse;
import com.example.retrofit.service.ApiClient;
import com.example.retrofit.service.UserAPI;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static com.example.retrofit.R.id.responseText2;

public class AddUserActivity extends AppCompatActivity {
    ProgressDialog progressDialog;

    TextView responseText;
    UserAPI apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        responseText = findViewById(responseText2);
        apiInterface = ApiClient.getRetrofitInstance(getApplication()).create(UserAPI.class);

        Button submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener((View view) -> submitButton());
    }


    private void submitButton() {
        UserAPI service = ApiClient.getRetrofitInstance(getApplicationContext())
                .create(UserAPI.class);

        EditText nameField = findViewById(R.id.name);
        String name = nameField.getText().toString();

        EditText jobField = findViewById(R.id.job);
        String job = jobField.getText().toString();

        CreateUser createUser = new CreateUser();
        createUser.setName(name);
        createUser.setJob(job);
        service.createUser(createUser)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                    progressDialog = new ProgressDialog(AddUserActivity.this);
                    progressDialog.setMessage("Loading....");
                    progressDialog.show();
                })
                .doAfterTerminate(() -> progressDialog.dismiss())
                .subscribe(new DisposableObserver<CreateUserResponse>() {

                    @Override
                    public void onNext(CreateUserResponse createUserResponse) {
                        Toast.makeText(AddUserActivity.this, "Success create user: " + createUserResponse.getName(), Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(AddUserActivity.this, "No internet connection. Please try again!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                        progressDialog.dismiss();
                    }
                });
    }


}