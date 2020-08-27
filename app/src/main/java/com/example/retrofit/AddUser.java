package com.example.retrofit;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofit.model.ListUserResponse;
import com.example.retrofit.service.ApiClient;
import com.example.retrofit.service.GetService;
import com.example.retrofit.service.PostService;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static com.example.retrofit.R.id.responseText2;

public class AddUser extends AppCompatActivity {
    TextView responseText;
    GetService apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        responseText = findViewById(responseText2);
        apiInterface = ApiClient.getRetrofitInstance(getApplication()).create(GetService.class);

        PostService service = ApiClient.getRetrofitInstance(getApplicationContext())
                .create(PostService.class);

        service.postAllUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ListUserResponse>() {
                    @Override
                    public void onNext(ListUserResponse listUserResponse) {
                        generateDataList(listUserResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(AddUser.this, "No internet connection. Please try again!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    private void generateDataList(ListUserResponse response) {
        RecyclerView recyclerView = findViewById(R.id.customAddUser);
        CustomAdapterUser adapterUser = new CustomAdapterUser(this, response.postData()); // Penting di sini harus diperhatikan
    }


}