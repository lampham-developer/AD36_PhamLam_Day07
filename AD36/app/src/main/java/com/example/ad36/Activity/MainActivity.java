package com.example.ad36.Activity;


import android.os.Bundle;

import com.example.ad36.Data.DataHandler;
import com.example.ad36.Fragment.Drink_Fragment;
import com.example.ad36.Fragment.Food_Fragment;
import com.example.ad36.Fragment.SideDish_Fragment;
import com.example.ad36.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.MenuItem;



public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    DataHandler dataHandler;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_food:
                    getFragment(Food_Fragment.newInstant());
                    return true;
                case R.id.navigation_drink:
                    getFragment(Drink_Fragment.newInstant());
                    return true;
                case R.id.navigation_sidedish:
                    getFragment(SideDish_Fragment.newInstant());
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Foods store");
        BottomNavigationView navView = findViewById(R.id.nav);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        dataHandler = new DataHandler(getBaseContext());
        getFragment(Food_Fragment.newInstant());
    }

    public void getFragment(Fragment fragment) {
        try {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "getFragment: " + e.getMessage());
        }
    }


}
