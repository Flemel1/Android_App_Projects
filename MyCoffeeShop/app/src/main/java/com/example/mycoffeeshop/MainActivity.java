package com.example.mycoffeeshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    LinearLayout menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        menu = (LinearLayout) findViewById(R.id.parentMenu);
        menu();
    }

    private void menu(){
        for (int i = 0; i<menu.getChildCount();i++){
            CardView menuItem = (CardView) menu.getChildAt(i);
            if (i == 0){
                menuItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), list_order.class);
                        startActivity(intent);
                    }
                });
            }
            else if(i == 1){
                menuItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), list_barang.class);
                        startActivity(intent);
                    }
                });
            }
            else if(i == 2){
                menuItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "Cardview ke-3", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }
}