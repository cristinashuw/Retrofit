package com.example.retrofit.service;

import com.example.retrofit.model.*;
import com.example.retrofit.model.User;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.POST;

public interface PostService {
    @POST(value = "/api/users")
    Observable<CreateUserResponse> postAllUsers();



//
//    @POST("/api/users")
//    Call<User> doGetListResources(@Field("firstName") String firstName, @Field("lastName") String lastName, @Field("email") String email);
}
