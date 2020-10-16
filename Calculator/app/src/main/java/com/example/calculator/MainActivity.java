package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    GridLayout mainGridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainGridLayout = (GridLayout)findViewById(R.id.mainGridLayout);

        setEventGridClicked(mainGridLayout);
    }

    private void setEventGridClicked(final GridLayout mainGridLayout) {
        for (int i = 0; i<mainGridLayout.getChildCount();i++){
            final CardView cardView = (CardView) mainGridLayout.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (finalI == 0){
                        Intent intent = new Intent(getApplicationContext(),IncomeAcitivity.class);
                        startActivity(intent);
                    }
                    else if(finalI == 1){
                        Intent intent = new Intent(getApplicationContext(),NoteDetailIncome.class);
                        startActivity(intent);
                    }
                    else if(finalI == 2){

                    }
                    else if(finalI == 3){

                    }
                    else if(finalI == 4){

                    }
                    else if(finalI == 5){

                    }

                }
            });
        }

    }
}
