package com.example.ad36.Fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ad36.Data.DataHandler;
import com.example.ad36.Foods.ClickFood;
import com.example.ad36.Foods.Food;
import com.example.ad36.Foods.FoodAdapter;
import com.example.ad36.R;

import java.util.List;


public class Food_Fragment extends Fragment {

    RecyclerView recyclerView;
    TextView tvCount;
    RelativeLayout relativeLayout;

    public static Food_Fragment newInstant() {
        return new Food_Fragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_food, container, false);
        recyclerView = view.findViewById(R.id.rvFood);
        DataHandler dataHandler = new DataHandler(getContext());
        List<Food> foodList = dataHandler.getFoodList("foods");
        tvCount = view.findViewById(R.id.tvCount);
        tvCount.setText(String.valueOf(dataHandler.getCountOrder()));
        relativeLayout = view.findViewById(R.id.rlGrocery);

        FoodAdapter foodAdapter = new FoodAdapter(foodList, getContext());
        recyclerView.setAdapter(foodAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        foodAdapter.setClickFood(new ClickFood() {
            @Override
            public void onClick(int count) {
                    tvCount.setText(String.valueOf(count));
            }
        });


        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, Order_Fragment.newInstant())
                            .commit()
                    ;
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        return view;
    }

}
