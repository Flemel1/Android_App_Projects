package com.example.recipeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class DetailRecipeActivity extends AppCompatActivity {
    ImageView image;
    TextView tv_recipe_name_detail;
    TextView tv_created_detail;
    TextView tv_ingredients;
    TextView tv_steps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_recipe);
        image = (ImageView) findViewById(R.id.image_food);
        tv_recipe_name_detail = (TextView) findViewById(R.id.tv_recipe_name_detail);
        tv_created_detail = (TextView) findViewById(R.id.tv_create_by_detail);
        tv_ingredients = (TextView) findViewById(R.id.tv_ingredients);
        tv_steps = (TextView) findViewById(R.id.tv_steps);
        showDetailRecipe();
    }

    private void showDetailRecipe() {
        String recipe_name = getIntent().getStringExtra("RECIPE_NAME");
        String created_by = getIntent().getStringExtra("CREATED");
        String ingredients = getIntent().getStringExtra("INGREDIENTS");
        String steps = getIntent().getStringExtra("STEPS");
        String image_food = getIntent().getStringExtra("IMAGE");
        Picasso.with(this).load(image_food).into(image);
        tv_recipe_name_detail.setText(recipe_name + " Recipe");
        tv_created_detail.setText("Created by " + created_by);
        tv_ingredients.setText(ingredients);
        tv_steps.setText(steps);
    }
}