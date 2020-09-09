package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofit.model.ListUserResponse;
import com.example.retrofit.service.ApiClient;
import com.example.retrofit.service.UserAPI;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    ProgressDialog progressDialog;

    TextView responseText;
    UserAPI apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        responseText = findViewById(R.id.responseText);
        apiInterface = ApiClient.getRetrofitInstance(getApplication()).create(UserAPI.class);

        UserAPI service = ApiClient.getRetrofitInstance(getApplicationContext())
                .create(UserAPI.class);

        service.getAllUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ListUserResponse>() {
                    @Override
                    public void onNext(ListUserResponse listUserResponse) {
                        generateDataList(listUserResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MainActivity.this, "No internet connection. Please try again!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                        progressDialog.dismiss();
                    }
                });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener((View view) -> openCreateUser());


//        /*Create handle for the RetrofitInstance interface*/
//        UserAPI service = ApiClient.getRetrofitInstance().create(UserAPI.class);
//        Call<List<PhotoData>> call = service.getAllPhotos();
//        call.enqueue(new Callback<List<PhotoData>>() {
//            @Override
//            public void onResponse(Call<List<PhotoData>> call, Response<List<PhotoData>> response) {
//                progressDialog.dismiss();
//                generateDataList(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<List<PhotoData>> call, Throwable t) {
//                progressDialog.dismiss();
//                Toast.makeText(MainActivity.this, "No internet connection. Please try again!", Toast.LENGTH_SHORT).show();
//            }
//        });

//        UserAPI service = ApiClient.getRetrofitInstance(getApplicationContext()).create(UserAPI.class);
//        Single<ListUserResponse> call = service.getAllUsers();
//        call.enqueue(new Callback<ListUserResponse>() {
//            @Override
//            public void onResponse(@NonNull Call<ListUserResponse> call, @NonNull Response<ListUserResponse> response) {
//                progressDialog.dismiss();
//                assert response.body() != null;
//                generateDataList(response.body());
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<ListUserResponse> call, @NonNull Throwable t) {
//                progressDialog.dismiss(); /* Ini namanya break point, cuma buat stop process ketika debugging, pakainya yang DEBUG, bukan RUN  */
//                Toast.makeText(MainActivity.this, "No Internet connection. Please try again!", Toast.LENGTH_SHORT).show();
//
//            }
//        });


    }

    private void openCreateUser() {
        Intent intent = new Intent(this, AddUserActivity.class);
        startActivity(intent);
    }

    private void getDetailUser(long id) {
        Intent intent = new Intent(this, DetailUserActivity.class);
        intent.putExtra(DetailUserActivity.EXTRA_UID, id);
        startActivity(intent);
    }

    private void generateDataList(ListUserResponse response) {
        RecyclerView recyclerView = findViewById(R.id.customRecycleView);
        //    private CustomAdapter adapter;
        CustomAdapterUser adapterUser = new CustomAdapterUser(this, response.getData(), (position, user) -> {
            Log.d("Get Clicked User ID", user.getId() +"");
            // todo: Open DetailUserActivity and pass user id
            getDetailUser(user.getId());

        }); // Penting di sini harus diperhatikan
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterUser);

    }

//    /*Method to generate List of data using RecyclerView with custom adapter*/
//    private void generateDataList(List<PhotoData> photoList) {
//        recyclerView = findViewById(R.id.customRecyclerView);
//        adapter = new CustomAdapter(this,photoList);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(adapter);
//    }

}
