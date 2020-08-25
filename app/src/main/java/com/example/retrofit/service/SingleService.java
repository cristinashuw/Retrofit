package com.example.retrofit.service;

import com.example.retrofit.model.ListUserResponse;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface SingleService {
    @GET("/api/users")
    Single<ListUserResponse> getDetailUsers();
}
