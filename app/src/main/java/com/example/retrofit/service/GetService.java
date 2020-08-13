package com.example.retrofit.service;

import android.content.Context;

import com.example.retrofit.model.*;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

//public interface GetService {
//    @GET("/photos")
//    Call<List<PhotoData>> getAllPhotos();
//}

public interface GetService {

    @GET("/api/users")
    Single<ListUserResponse> getAllUsers();



//     Sekarang kita dah sesuaikan kelas buat converter Gson'nya, diganti ke situ.
    /* List<User> untuk response body dengan struktur JSON ===> [{User}, {User}, dst..]
    * Coba lihat response aslinya */

//    @FormUrlEncoded
//    @POST("/api/users?")
//    Call<User> doGetListResources(@Field("firstName") String firstName, @Field("lastName") String lastName, @Field("email") String email);
}
