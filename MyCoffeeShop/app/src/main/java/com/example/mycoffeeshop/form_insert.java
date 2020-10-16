package com.example.mycoffeeshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mycoffeeshop.connection.DBAccess;

public class form_insert extends AppCompatActivity {
    EditText name;
    EditText price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_insert);
        if (savedInstanceState == null) { //pertama kali menjalankan activity
            name = (EditText) findViewById(R.id.ed_name);
            price = (EditText) findViewById(R.id.ed_price);
        }
        else { // baris ini akan dijalankan ketika state dari aplikasi berubah atau di rotated
            name = (EditText) findViewById(R.id.ed_name);
            price = (EditText) findViewById(R.id.ed_price);
            String temp_name = savedInstanceState.getString("coffee_name");
            String temp_price = savedInstanceState.getString("coffee_prices");
            name.setText(temp_name);
            price.setText(temp_price);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putString("coffee_name", name.getText().toString());
        outState.putString("coffee_prices", price.getText().toString());
    }

    public void insertItem(View view) {
        DBAccess db = DBAccess.getInstance(this);
        db.open();
        String coffee_name = name.getText().toString().trim();
        String temp_price = price.getText().toString().trim();
        if (coffee_name.isEmpty()){
            Toast.makeText(getApplicationContext(), "Fill Coffee Name", Toast.LENGTH_SHORT).show();
        }
        else if(temp_price.isEmpty()){
            Toast.makeText(getApplicationContext(), "Fill Coffee Price", Toast.LENGTH_SHORT).show();
        }
        else {
            int coffee_price = Integer.parseInt(temp_price);
            db.insert(coffee_name,coffee_price);
            Toast.makeText(getApplicationContext(),"Data Berhasil Masuk", Toast.LENGTH_SHORT).show();
            name.setText("");
            price.setText("");
            db.close();
        }
    }
}