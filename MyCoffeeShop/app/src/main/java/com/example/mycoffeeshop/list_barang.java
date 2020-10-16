package com.example.mycoffeeshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.mycoffeeshop.adapter.BarangAdapter;
import com.example.mycoffeeshop.connection.DBAccess;
import com.example.mycoffeeshop.connection.DBHelper;
import com.example.mycoffeeshop.model.Barang;

import java.util.ArrayList;
import java.util.List;

public class list_barang extends AppCompatActivity {
    RecyclerView list;
    List<Barang> list_barang;
    private BarangAdapter adapter;
    private LinearLayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_barang);
        list = (RecyclerView) findViewById(R.id.rc_barang);
        layoutManager = new LinearLayoutManager(com.example.mycoffeeshop.list_barang.this);
        list.setLayoutManager(layoutManager);
        loadBarang();
    }

    private void loadBarang(){
        DBAccess db = DBAccess.getInstance(this);
        db.open();
        list_barang = db.getBarang();
        adapter = new BarangAdapter(getApplicationContext(),list_barang);
        list.setAdapter(adapter);
        db.close();
    }

    public void GoToInsertForm(View view) {
        Intent intent = new Intent(getApplicationContext(), form_insert.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadBarang();
    }
}