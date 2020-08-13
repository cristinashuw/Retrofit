package com.example.retrofit.service;

import android.content.Context;
import android.text.TextUtils;

import com.example.retrofit.model.ListUserResponse;
import com.example.retrofit.utils.PrefUtils;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static Retrofit retrofit;
    private static OkHttpClient okHttpClient;

//    public static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    public static final String BASE_URL = "https://reqres.in";

    public static Retrofit getRetrofitInstance(Context context) {
        if (okHttpClient == null)
            initOkHttp(context);

        if (retrofit == null){
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    private static void initOkHttp(final Context context) {
        int REQUEST_TIMEOUT = 60;
        OkHttpClient.Builder httpClient = new OkHttpClient().newBuilder()
                .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(REQUEST_TIMEOUT,TimeUnit.SECONDS)
                .writeTimeout(REQUEST_TIMEOUT,TimeUnit.SECONDS);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        httpClient.addInterceptor(interceptor);

        httpClient.addInterceptor(chain -> {
            Request original = chain.request();
            Request.Builder requestBuilder = original.newBuilder()
                    .addHeader("Accept", "application/json")
                    .addHeader("Content-type", "application/json");

//                Adding Authorization token (API Key)
//                Requests will be denied without API Key
            if (!TextUtils.isEmpty(PrefUtils.getApiKey(context))) {
                requestBuilder.addHeader("Authorization", PrefUtils.getApiKey(context));
            }

            Request request = requestBuilder.build();
            return chain.proceed(request);
        });

        okHttpClient = httpClient.build();
    }



}
