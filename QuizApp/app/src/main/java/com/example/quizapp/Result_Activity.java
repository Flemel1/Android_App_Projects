package com.example.quizapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Result_Activity extends AppCompatActivity implements View.OnClickListener {
    private static final String BUNDLE_SCORE = "SCORE";

    private RatingBar ratingBar;
    private TextView tv_Score;
    private TextView tv_grade;
    private Button btn_home;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_);
        tv_grade = (TextView) findViewById(R.id.grade);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        tv_Score = (TextView) findViewById(R.id.tv_score);
        btn_home = (Button) findViewById(R.id.btn_home);
        Bundle bundle = getIntent().getExtras();
        ratingBar.setNumStars(5);
        ratingBar.setStepSize((float) 0.5);
        score = bundle.getInt(BUNDLE_SCORE);
        setGrade(score);
        ratingBar.setRating((float) score / 10);
        tv_Score.setText("Score: " + score);
        btn_home.setOnClickListener(this);
    }

    private void setGrade(int score){
        int grade = score / 10;
        switch (grade) {
            case 1 : tv_grade.setText("So Bad");break;
            case 2 : tv_grade.setText("So Bad");break;
            case 3 : tv_grade.setText("Bad");break;
            case 4 : tv_grade.setText("Bad");break;
            case 5 : tv_grade.setText("Good");break;
            case 6 : tv_grade.setText("Good");break;
            case 7 : tv_grade.setText("Excellent");break;
            case 8 : tv_grade.setText("Excellent");break;
            case 9 : tv_grade.setText("Amazing");break;
            case 10 : tv_grade.setText("Amazing");break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Close App")
                .setMessage("Are you sure to close app?")
                .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.e("msg", "bye bye");
                        finish();
                    }
                })
                .setNegativeButton("Cancel", null).create();
        dialog.show();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_home :
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
        }
    }
}