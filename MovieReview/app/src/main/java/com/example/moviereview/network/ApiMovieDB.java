package com.example.moviereview.network;

import com.example.moviereview.model.ResponseNowPlayingMovie;
import com.example.moviereview.model.ResponsePopularMovie;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface ApiMovieDB {
    @GET("movie/now_playing")
    Call<ResponseNowPlayingMovie> getNowPlayingMovie(@QueryMap Map<String, String> fieldsNowPlayingMovie);

    @GET("movie/popular")
    Call<ResponsePopularMovie> getPopularMovie(@QueryMap Map<String, String> fieldsPopularMovie);
}
