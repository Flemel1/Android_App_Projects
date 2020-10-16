package com.example.fragmenttofragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.fragmenttofragment.Fragment.FragmentA;
import com.example.fragmenttofragment.Fragment.FragmentB;

public class MainActivity extends AppCompatActivity{
    FragmentA fragmentA;
    FragmentB fragmentB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentA = new FragmentA();
        fragmentB = new FragmentB();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.container_a, fragmentA);
        ft.replace(R.id.container_b, fragmentB);
        ft.commit();
    }
//    using implements interface in Fragment class to use function below
//    @Override
//    public void setInputA(String input) {
//        fragmentB.updateInput(input);
//    }
//
//    @Override
//    public void setInputB(String input) {
//        fragmentA.updateInput(input);
//    }
}