package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofit.model.ListUserResponse;
import com.example.retrofit.model.PhotoData;
import com.example.retrofit.model.User;
import com.example.retrofit.service.ApiClient;
import com.example.retrofit.service.GetService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

//    private CustomAdapter adapter;
    private CustomAdapterUser adapterUser;
    private RecyclerView recyclerView;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();


//        /*Create handle for the RetrofitInstance interface*/
//        GetService service = ApiClient.getRetrofitInstance().create(GetService.class);
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

        GetService service = ApiClient.getRetrofitInstance().create(GetService.class);
        Call<ListUserResponse> call = service.getAllUsers();
        call.enqueue(new Callback<ListUserResponse>() {
            @Override
            public void onResponse(Call<ListUserResponse> call, Response<ListUserResponse> response) {
                progressDialog.dismiss();
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<ListUserResponse> call, Throwable t) {
                progressDialog.dismiss(); /* Ini namanya break point, cuma buat stop process ketika debugging, pakainya yang DEBUG, bukan RUN  */
                Toast.makeText(MainActivity.this, "No Internet connection. Please try again!", Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void generateDataList(ListUserResponse response) {
        recyclerView = findViewById(R.id.customRecyclerView);
        adapterUser = new CustomAdapterUser(this, response.getData()); // Penting di sini harus diperhatikan
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
