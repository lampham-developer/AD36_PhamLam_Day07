package com.vpbank.recyclerviewdemo;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    BottomNavigationView navigationView;
    Contact contact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationView=findViewById(R.id.nav);
        contact=new Contact("Mr Hai","0936555999");

        //gọi fragment
        getFragment(Home_Fragment.newInstance(contact));


        //bắt sự kiện click vào menu
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.mnHome:
                        getFragment(Home_Fragment.newInstance(contact));
                        break;
                    case R.id.mnFragment1:
                        getFragment(Dashboard_Fragment.newInstance());
                        break;
                    case R.id.mnFragment2:
                        getFragment(Notification_Fragment.newInstance());
                        break;
                }

                return false;
            }
        });

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
