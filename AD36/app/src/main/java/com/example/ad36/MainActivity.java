package com.example.ad36;

import android.os.Bundle;

import com.example.ad36.Foods.Food;
import com.example.ad36.Fragment.Drink_Fragment;
import com.example.ad36.Fragment.Food_Fragment;
import com.example.ad36.Fragment.SideDish_Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    TextView mTextMessage;
    TextView tvCount;
    FrameLayout layout_circle;
    HashMap<Integer, Integer> orderList = new HashMap<>();

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
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

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

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.localgrocery);
        FrameLayout rootView =(FrameLayout)item.getActionView();
        layout_circle = (FrameLayout)rootView.findViewById(R.id.layout_circle);
        tvCount = (TextView)rootView.findViewById(R.id.tvCount);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.custom_menu, menu);
        return true;
    }
}
