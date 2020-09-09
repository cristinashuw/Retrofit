package com.example.retrofit;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.retrofit.model.DetailUserResponse;
import com.example.retrofit.service.ApiClient;
import com.example.retrofit.service.UserAPI;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class DetailUserActivity extends AppCompatActivity {
    public static final String EXTRA_UID = "UserID";

    ProgressDialog progressDialog;
    TextView responseText;
    UserAPI apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);

        progressDialog = new ProgressDialog(DetailUserActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        responseText = findViewById(R.id.responseText3);
        apiInterface = ApiClient.getRetrofitInstance(getApplication()).create(UserAPI.class);

        UserAPI service = ApiClient.getRetrofitInstance(getApplicationContext())
                .create(UserAPI.class);

        // todo: Ganti 0 dengan id yang dikirim dari MainActivity.class
        // Get data from Activity Intent
        long uid = getIntent().getLongExtra(EXTRA_UID, 0);
        service.getSingleUser(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<DetailUserResponse>() {
                    @Override
                    public void onNext(DetailUserResponse detailUserResponse) {
                        showUserDetail(detailUserResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(DetailUserActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                        progressDialog.dismiss();
                    }
                });

    }

    private void showUserDetail(DetailUserResponse response) {
        TextView vFirstName = findViewById(R.id.firstName);
        TextView vLastName = findViewById(R.id.lastName);
        TextView vEmail = findViewById(R.id.email);

        // optional: todo bikin variable buat nampung value dari response api
        String firstName = response.getData().getFirstName();
        String lastName = response.getData().getLastName();
        String email = response.getData().getEmail();

        // set value to view component
        vFirstName.setText(firstName);
        vLastName.setText(lastName);
        vEmail.setText(email);
    }


}

