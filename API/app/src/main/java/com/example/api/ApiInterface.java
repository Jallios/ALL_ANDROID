package com.example.api;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("car")
    Call<ArrayList<Car>> getCarList();

    @GET("car/{id}")
    Call<Car> getCarById(@Path("id") Integer id);

    @POST("car")
    Call<Car> postCar(@Body Car car);

    @DELETE("car/{id}")
    Call<Car> deleteCar(@Path("id") Integer id);

    @GET("UserRoles")
    Call<ArrayList<UserRole>> getRoles();

}
