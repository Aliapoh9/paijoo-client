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

}
