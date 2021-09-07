package com.example.myapplication.service;

import com.example.myapplication.model.Message;
import com.example.myapplication.model.Product;
import com.example.myapplication.model.Token;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ServiceAPI {
    String BASE_Service = "https://hoccungminh.dinhnt.com/api/";

    @GET("token")
    Observable<Token> GetToken();

    @GET("all-product")
    Observable<ArrayList<Product>> GetAllProduct(@Header("Authorization") String token);

    @GET("detail-product")
    Observable<Product> GetDetailProduct(@Header("Authorization") String token, @Query("id") int id);

    @POST("add-product")
    Observable<Message> AddProduct(@Header("Authorization") String token, @Body Product product);
}
