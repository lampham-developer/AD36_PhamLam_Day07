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


public class SideDish_Fragment extends Fragment {

    Food f;
    RecyclerView recyclerView;


    public static SideDish_Fragment newInstant(){
        return new SideDish_Fragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_food, container, false);
        recyclerView = view.findViewById(R.id.rvFood);

        List<Food> foodList = new ArrayList<>();

        f = new Food(1, "Khoai tây", "Cỡ vừa", "sidedish", R.drawable.potato, 20000); foodList.add(f);
        f = new Food(1, "Khoai tây", "Cỡ lớn", "sidedish", R.drawable.potato, 30000);foodList.add(f);
        f = new Food(1, "Salad bắp cải", "Cỡ vừa", "sidedish", R.drawable.salad, 15000); foodList.add(f);
        f = new Food(1, "Salad bắp cải", "Cỡ lớn", "sidedish", R.drawable.salad, 25000);foodList.add(f);


        FoodAdapter foodAdapter = new FoodAdapter(foodList);
        recyclerView.setAdapter(foodAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));


        return view;
    }

}
