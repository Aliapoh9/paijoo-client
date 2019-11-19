package com.example.paijoov1;

import java.util.*;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.*;

public interface PaijooService {

    @Headers({
            "'Content-Type': 'application/json'",
            "Content-Type: application/json"
    })
    @GET("/messages/{id}")
    Call<ArrayList<Conversation>> getMes(@Path("id") int id);

    @POST("/messages/post")
    Call<requestBody<TextContentRequestBody>> postMessage(@Body requestBody<TextContentRequestBody> rb);


    @GET("users/{id}/friends")
    Call<ArrayList<Users>> getFriendList(@Path("id") int id);

    @POST("users/create")
    Call<Users> register(@Body Users users);

}
