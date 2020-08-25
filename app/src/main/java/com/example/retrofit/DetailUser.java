package com.example.retrofit;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofit.model.ListUserResponse;
import com.example.retrofit.model.User;
import com.example.retrofit.service.ApiClient;
import com.example.retrofit.service.GetService;
import com.example.retrofit.service.SingleService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class DetailUser extends AppCompatActivity {
    TextView responseText;
    SingleService apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);

        responseText = findViewById(R.id.responseText3);
        apiInterface = ApiClient.getRetrofitInstance(getApplication()).create(SingleService.class);

        SingleService service = ApiClient.getRetrofitInstance(getApplicationContext())
                .create(SingleService.class);

        service.getDetailUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<ListUserResponse>() {
                    @Override
                    public void onSuccess(ListUserResponse listUserResponse) {
                        generateDataList(listUserResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(DetailUser.this, "No internet connection. Please try again!", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void generateDataList(ListUserResponse response) {
        RecyclerView recyclerView = findViewById(R.id.customDetailUser);
        CustomAdapterUser adapterUser = new CustomAdapterUser(this, response.getData()); // Penting di sini harus diperhatikan
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DetailUser.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterUser);
    }
}

