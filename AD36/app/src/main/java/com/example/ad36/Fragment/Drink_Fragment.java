package com.example.ad36.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ad36.Foods.Food;
import com.example.ad36.Foods.FoodAdapter;
import com.example.ad36.R;

import java.util.ArrayList;
import java.util.List;


public class Drink_Fragment extends Fragment {

    Food f;
    RecyclerView recyclerView;


    public static Drink_Fragment newInstant() {
        return new Drink_Fragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_food, container, false);
        recyclerView = view.findViewById(R.id.rvFood);

        List<Food> foodList = new ArrayList<>();

        f = new Food(1, "Coca", "Size M", "drinks", R.drawable.coca, 20000);
        foodList.add(f);
        f = new Food(1, "Coca", "Size L", "drinks", R.drawable.coca, 30000);
        foodList.add(f);
        f = new Food(1, "Pepsi", "Size M", "drinks", R.drawable.pepsi, 20000);
        foodList.add(f);
        f = new Food(1, "Pepsi", "Size L", "drinks", R.drawable.pepsi, 30000);
        foodList.add(f);


        FoodAdapter foodAdapter = new FoodAdapter(foodList);
        recyclerView.setAdapter(foodAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));


        return view;
    }

}
