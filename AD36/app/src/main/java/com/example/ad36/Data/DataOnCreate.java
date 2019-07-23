package com.example.ad36.Data;

import android.content.Context;

import com.example.ad36.Foods.Food;
import com.example.ad36.R;

import java.util.ArrayList;
import java.util.List;

public class DataOnCreate {
    public List<Food> baseFood(){
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

        return foodList;
    }
}
