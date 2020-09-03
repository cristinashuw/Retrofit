package com.example.retrofit;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofit.model.CreateUser;
import com.example.retrofit.model.CreateUserResponse;
import com.example.retrofit.service.ApiClient;
import com.example.retrofit.service.PostService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.http.Body;

import static com.example.retrofit.R.id.responseText2;
import static com.example.retrofit.R.id.submitButton;

public class AddUser extends AppCompatActivity {
    ProgressDialog progressDialog;

    TextView responseText;
    PostService apiInterface;
    private CreateUser createUser;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        responseText = findViewById(responseText2);
        apiInterface = ApiClient.getRetrofitInstance(getApplication()).create(PostService.class);





        submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener((View view) -> submitButton());
    }


    private void submitButton() {
        PostService service = ApiClient.getRetrofitInstance(getApplicationContext())
                .create(PostService.class);

        EditText nameField = (EditText) findViewById(R.id.name);
        String name = nameField.getText().toString();

        EditText jobField = (EditText) findViewById(R.id.job);
        String job = jobField.getText().toString();

        createUser = new CreateUser();
        createUser.setName(name);
        createUser.setJob(job);
        service.postAllUsers(createUser)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                    progressDialog = new ProgressDialog(AddUser.this);
                    progressDialog.setMessage("Loading....");
                    progressDialog.show();
                })
                .doAfterTerminate(() -> progressDialog.dismiss())
                .subscribe(new DisposableObserver<CreateUserResponse>() {

                    @Override
                    public void onNext(CreateUserResponse createUserResponse) {
                        Toast.makeText(AddUser.this, "Success create user: " + createUserResponse.getName(), Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(AddUser.this, "No internet connection. Please try again!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                        progressDialog.dismiss();
                    }
                });
    }


}