package com.example.ad36;


import android.os.Bundle;

import com.example.ad36.Data.DataHandler;
import com.example.ad36.Foods.Food;
import com.example.ad36.Fragment.Drink_Fragment;
import com.example.ad36.Fragment.Food_Fragment;
import com.example.ad36.Fragment.SideDish_Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

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
        dataHandler.createTable();
        addBaseFood();

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


    private void addBaseFood(){
        List<Food> foodList = new ArrayList<>();
        Food f;
        f = new Food(1, "Gà rán", "3 miếng", "foods", R.drawable.garan3, 99000); foodList.add(f);
        f = new Food(2, "Gà rán", "6 miếng", "foods", R.drawable.garan6, 195000); foodList.add(f);
        f = new Food(3, "Gà rán", "9 miếng", "foods", R.drawable.garan9, 289000); foodList.add(f);
        f = new Food(4, "Gà rán", "12 miếng", "foods", R.drawable.garan12, 379000); foodList.add(f);
        f = new Food(5, "Gà quay", "1 miếng", "foods", R.drawable.gaquay, 69000); foodList.add(f);
        f = new Food(5, "Burger", "Zinger", "foods", R.drawable.zinger, 79000); foodList.add(f);
        f = new Food(1, "Coca", "Size M", "drinks", R.drawable.coca, 19000);foodList.add(f);
        f = new Food(1, "Coca", "Size L", "drinks", R.drawable.coca, 29000);foodList.add(f);
        f = new Food(1, "Pepsi", "Size M", "drinks", R.drawable.pepsi, 19000);foodList.add(f);
        f = new Food(1, "Pepsi", "Size L", "drinks", R.drawable.pepsi, 29000);foodList.add(f);
        f = new Food(1, "Khoai tây", "Cỡ vừa", "sidedish", R.drawable.potato, 29000); foodList.add(f);
        f = new Food(1, "Khoai tây", "Cỡ lớn", "sidedish", R.drawable.potato, 39000);foodList.add(f);
        f = new Food(1, "Salad bắp cải", "Cỡ vừa", "sidedish", R.drawable.salad, 19000); foodList.add(f);
        f = new Food(1, "Salad bắp cải", "Cỡ lớn", "sidedish", R.drawable.salad, 29000);foodList.add(f);

        for (Food food: foodList) {
            dataHandler.addNewFood(food);
        }
    }
}
