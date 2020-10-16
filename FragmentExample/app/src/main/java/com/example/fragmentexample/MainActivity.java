package com.example.fragmentexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.fragmentexample.Fragment.FragmentA;
import com.example.fragmentexample.Fragment.FragmentB;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dl = (DrawerLayout)findViewById(R.id.drawer);
        t = new ActionBarDrawerToggle(this, dl, R.string.Open, R.string.Close);

        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nv = (NavigationView)findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch(id)
                {
                    case R.id.account:
                        FragmentManager fm = getSupportFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        FragmentA fragmentA = FragmentA.newInstance("Passing data from Activity to Framgment A");
                        ft.replace(R.id.frame, fragmentA);
                        ft.commit();
                        break;
                    case R.id.settings:
                        FragmentManager fm_B = getSupportFragmentManager();
                        FragmentTransaction ft_B = fm_B.beginTransaction();
                        FragmentB fragmentB = FragmentB.newInstance("Passing data from Activity to Fragment B");
                        ft_B.replace(R.id.frame, fragmentB);
                        ft_B.commit();
                        break;
                    case R.id.mycart:
                        Toast.makeText(MainActivity.this, "My Cart", Toast.LENGTH_SHORT).show();break;
                    default:
                        return true;
                }


                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(t.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }


//        public void Clicked(View view) {
//        int id = view.getId();
//        switch (id) {
//            case R.id.btn_A :
//                FragmentManager fm = getSupportFragmentManager();
//                FragmentTransaction ft = fm.beginTransaction();
//                FragmentA fragmentA = FragmentA.newInstance("Passing data from Activity to Framgment A");
//                ft.replace(R.id.frame, fragmentA);
//                ft.commit();
//                break;
//            case R.id.btn_B :
//                FragmentManager fm_B = getSupportFragmentManager();
//                FragmentTransaction ft_B = fm_B.beginTransaction();
//                FragmentB fragmentB = FragmentB.newInstance("Passing data from Activity to Fragment B");
//                ft_B.replace(R.id.frame, fragmentB);
//                ft_B.commit();
//                break;
//        }
//    }
}