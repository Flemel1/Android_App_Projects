package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class NoteDetailIncome extends AppCompatActivity {

    ListView listNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail_income);
        listNote = (ListView)findViewById(R.id.list_note);
    }


}