package com.example.perpus;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.JsonReader;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import response.RegisterAPI;
import response.Response;
import response.Value;
import retrofit2.Call;

import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public static final String Url_Perpus = "http://192.168.1.6/";
    private ProgressDialog progressDialog;

    @BindView(R.id.ed_idBuku) EditText edID;
    @BindView(R.id.ed_namaBuku) EditText edNama;
    @BindView(R.id.ed_penulisBuku) EditText edPenulis;
    @BindView(R.id.ed_penerbitBuku) EditText edPenerbit;
    @BindView(R.id.ed_tahunTerbit) EditText edTahun;
    @BindView(R.id.btn_submit)
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //daftar();
    }

    @OnClick(R.id.btn_submit) void daftar() {
        //membuat progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        //mengambil data dari EditText
        String ID = edID.getText().toString();
        String judul = edNama.getText().toString();
        String penulis = edPenulis.getText().toString();
        String penerbit = edPenerbit.getText().toString();
        String tahun = edTahun.getText().toString();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Url_Perpus).addConverterFactory(GsonConverterFactory.create()).build();
        RegisterAPI api = retrofit.create(RegisterAPI.class);
        Call<Response> call = api.register(ID,judul,penulis,penerbit,tahun);

        call.enqueue(new Callback<Response>() {

            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                int value = response.body().getValue();
                String message = response.body().getMessage();
                progressDialog.dismiss();
                if (value == 1){
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                t.printStackTrace();
                progressDialog.dismiss();

                Toast.makeText(MainActivity.this, "String", Toast.LENGTH_SHORT).show();
            }
       });
    }
}