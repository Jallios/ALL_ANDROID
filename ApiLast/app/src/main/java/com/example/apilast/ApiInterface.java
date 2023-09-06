package com.example.apilast;

import com.example.apilast.models.Film;
import com.example.apilast.models.InfoFilm;
import com.example.apilast.models.Root;
import com.example.apilast.models.RootTrailer;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {
    @Headers({"X-API-KEY: 81131402-e400-4772-89d9-f71f0a34b7a9"})
    @GET("films/{id}")
    Call<InfoFilm> getFilmById(@Path("id") Integer id);

    @Headers({"X-API-KEY: 81131402-e400-4772-89d9-f71f0a34b7a9"})
    @GET("films/top")
    Call<Root> getFilmListTop();

    @Headers({"X-API-KEY: 81131402-e400-4772-89d9-f71f0a34b7a9"})
    @GET("films/top?type=TOP_AWAIT_FILMS&page=1")
    Call<Root> getFilmListPremieres();

    @Headers({"X-API-KEY: 81131402-e400-4772-89d9-f71f0a34b7a9"})
    @GET("films/{id}/videos")
    Call<RootTrailer> getFilmTrailer(@Path("id") Integer id);

}

