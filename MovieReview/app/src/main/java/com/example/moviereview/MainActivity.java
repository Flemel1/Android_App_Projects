package com.example.moviereview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.moviereview.adapter.AdapterNowPlayingMovie;
import com.example.moviereview.adapter.AdapterPopularMovie;
import com.example.moviereview.model.NowPlayingMovie;
import com.example.moviereview.model.PopularMovie;
import com.example.moviereview.model.ResponseNowPlayingMovie;
import com.example.moviereview.model.ResponsePopularMovie;
import com.example.moviereview.network.ApiMovieDB;
import com.example.moviereview.utils.RightSpaceRecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.moviereview.variablemodel.Variable.API_KEY;
import static com.example.moviereview.variablemodel.Variable.LANGUAGE;
import static com.example.moviereview.variablemodel.Variable.PARAM_API_KEY;
import static com.example.moviereview.variablemodel.Variable.PARAM_LANGUAGE;
import static com.example.moviereview.variablemodel.Variable.PARAM_REGION;
import static com.example.moviereview.variablemodel.Variable.REGION;
import static com.example.moviereview.variablemodel.Variable.URL_API;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView_now_playing_movie;
    RecyclerView recyclerView_popular_movie;
    BottomNavigationView bottomNavigationView;
    LinearLayoutManager linearLayoutManager;
    LinearLayoutManager linearLayoutManagerPopularMovie;
    private Retrofit retrofit;
    private ApiMovieDB api;
    private AdapterNowPlayingMovie adapter;
    private AdapterPopularMovie adapterPopularMovie;
    private RightSpaceRecyclerView rightSpaceRecyclerView;
    private List<NowPlayingMovie> nowPlayingMovieList = new ArrayList<>();
    private List<PopularMovie> popularMovieList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        getNowPlayingMovie();
        getPopularMovie();
    }

    private void init() {
        recyclerView_now_playing_movie = findViewById(R.id.rc_now_playing_movie);
        recyclerView_popular_movie = findViewById(R.id.rc_popular_movie);
        bottomNavigationView = findViewById(R.id.bottom_navigation_bar);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        linearLayoutManagerPopularMovie = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        retrofit = new Retrofit.Builder().baseUrl(URL_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(ApiMovieDB.class);
    }

    private void getNowPlayingMovie() {
        Map<String, String> fields = new HashMap<>();
        fields.put(PARAM_API_KEY, API_KEY);
        fields.put(PARAM_LANGUAGE, LANGUAGE);
        fields.put(PARAM_REGION, REGION);
        Call<ResponseNowPlayingMovie> call = api.getNowPlayingMovie(fields);
        call.enqueue(new Callback<ResponseNowPlayingMovie>() {
            @Override
            public void onResponse(Call<ResponseNowPlayingMovie> call, Response<ResponseNowPlayingMovie> response) {
                if (response.isSuccessful()){
                    nowPlayingMovieList = response.body().getResults();
                    adapter = new AdapterNowPlayingMovie(getApplicationContext(), nowPlayingMovieList);
                    rightSpaceRecyclerView = new RightSpaceRecyclerView(45);
                    recyclerView_now_playing_movie.setLayoutManager(linearLayoutManager);
                    recyclerView_now_playing_movie.addItemDecoration(rightSpaceRecyclerView);
                    recyclerView_now_playing_movie.setAdapter(adapter);
                }
                else {
                    Log.e("msg", "Not connected to server cause " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ResponseNowPlayingMovie> call, Throwable t) {
                Log.e("msg", "Failed cause " + t.getMessage());
            }
        });
    }

    private void getPopularMovie() {
        Map<String, String> fields = new HashMap<>();
        fields.put(PARAM_API_KEY, API_KEY);
        fields.put(PARAM_LANGUAGE, LANGUAGE);
        fields.put(PARAM_REGION, REGION);
        Call<ResponsePopularMovie> call = api.getPopularMovie(fields);
        call.enqueue(new Callback<ResponsePopularMovie>() {
            @Override
            public void onResponse(Call<ResponsePopularMovie> call, Response<ResponsePopularMovie> response) {
                if (response.isSuccessful()){
                    popularMovieList = response.body().getResults();
                    adapterPopularMovie = new AdapterPopularMovie(getApplicationContext(), popularMovieList);
                    RightSpaceRecyclerView rightSpaceRecyclerView = new RightSpaceRecyclerView(45);
                    recyclerView_popular_movie.setLayoutManager(linearLayoutManagerPopularMovie);
                    recyclerView_popular_movie.addItemDecoration(rightSpaceRecyclerView);
                    recyclerView_popular_movie.setAdapter(adapterPopularMovie);
                }
                else {
                    Log.e("msg", "Not connected to server cause " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ResponsePopularMovie> call, Throwable t) {
                Log.e("msg", "Failed cause " + t.getMessage());
            }
        });
    }
}
