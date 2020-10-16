package com.example.recipeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import Adapter.AdapaterRecipe;
import Network.ApiHelper;
import Network.InitRetrofit;
import Response.ResponseRecipe;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView rc_list_recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rc_list_recipe = (RecyclerView) findViewById(R.id.rc_list_recipe);
        rc_list_recipe.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        getAllRecipe();
    }

    private void getAllRecipe() {
        ApiHelper apiHelper = InitRetrofit.getRetrofit();
        Call<List<ResponseRecipe>> call = apiHelper.getAllRecipe();
        call.enqueue(new Callback<List<ResponseRecipe>>() {
            @Override
            public void onResponse(Call<List<ResponseRecipe>> call, Response<List<ResponseRecipe>> response) {
                if (response.isSuccessful()){
                    List<ResponseRecipe> list_recipe = response.body();
                    AdapaterRecipe adapter = new AdapaterRecipe(MainActivity.this, list_recipe);
                    rc_list_recipe.setAdapter(adapter);
                }
                else {
                    Log.e("Error: ", response.message());
                    Toast.makeText(getApplicationContext(), "Gagal mengambil data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ResponseRecipe>> call, Throwable t) {
                Log.e("Error: ", t.getMessage());
                Toast.makeText(getApplicationContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}