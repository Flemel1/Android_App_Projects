package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import Adapter.ListViewAdapter;
import ConnectionDB.DatabaseAccess;
import Model.Income;

public class IncomeAcitivity extends AppCompatActivity {
    ListView listMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_acitivity);
        listMonth = (ListView)findViewById(R.id.listMonth);
        loadMonth();
        eventListClicked();
    }

    private void eventListClicked() {
        listMonth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String getMonth = parent.getItemAtPosition(position).toString();
                Intent intent = new Intent(getApplicationContext(),DetailOutcomeActivity.class);
                intent.putExtra("Month", getMonth);
                startActivity(intent);
            }
        });
    }

    private void loadMonth() {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        List<String> month = databaseAccess.getMounth();
        databaseAccess.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, month);
        listMonth.setAdapter(adapter);
    }

}
