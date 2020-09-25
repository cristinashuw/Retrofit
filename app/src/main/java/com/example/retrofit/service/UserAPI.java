package com.example.retrofit.service;

import android.content.Context;

import com.example.retrofit.model.*;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

//public interface UserAPI {
//    @GET("/photos")
//    Call<List<PhotoData>> getAllPhotos();
//}

public interface UserAPI {

    @GET("/api/users")
    Observable<ListUserResponse> getAllUsers();

    @GET("/api/users/{id}")
    Observable<DetailUserResponse> getSingleUser(@Path("id") long id);

    @POST(value = "/api/users")
    Observable<CreateUserResponse> createUser(@Body CreateUser createUser);

    @PUT("/api/users/{id}")
    Observable<UpdateUserResponse> updateUser(@Path("id") long id, @Body UpdateUser updateUser);


//     Sekarang kita dah sesuaikan kelas buat converter Gson'nya, diganti ke situ.
    /* List<User> untuk response body dengan struktur JSON ===> [{User}, {User}, dst..]
    * Coba lihat response aslinya */

//    @FormUrlEncoded
//    @POST("/api/users?")
//    Call<User> doGetListResources(@Field("firstName") String firstName, @Field("lastName") String lastName, @Field("email") String email);
}
