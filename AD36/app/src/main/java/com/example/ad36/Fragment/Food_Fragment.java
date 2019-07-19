package com.example.ad36.Fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ad36.Foods.Food;
import com.example.ad36.Foods.FoodAdapter;
import com.example.ad36.MainActivity;
import com.example.ad36.MyService;
import com.example.ad36.R;

import java.util.ArrayList;
import java.util.List;


public class Food_Fragment extends Fragment {

    Food f;
    RecyclerView recyclerView;


    public static Food_Fragment newInstant(){
        return new Food_Fragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_food, container, false);
        recyclerView = view.findViewById(R.id.rvFood);

        List<Food> foodList = new ArrayList<>();

        f = new Food(1, "Gà rán", "3 miếng", "foods", R.drawable.garan3, 99000); foodList.add(f);
        f = new Food(2, "Gà rán", "6 miếng", "foods", R.drawable.garan6, 99000); foodList.add(f);
        f = new Food(3, "Gà rán", "9 miếng", "foods", R.drawable.garan9, 99000); foodList.add(f);
        f = new Food(4, "Gà rán", "12 miếng", "foods", R.drawable.garan12, 99000); foodList.add(f);
        f = new Food(5, "Gà quay", "1 miếng", "foods", R.drawable.gaquay, 99000); foodList.add(f);
        f = new Food(5, "Burger", "Zinger", "foods", R.drawable.zinger, 69000); foodList.add(f);

        FoodAdapter foodAdapter = new FoodAdapter(foodList);
        recyclerView.setAdapter(foodAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        return view;
    }

}
