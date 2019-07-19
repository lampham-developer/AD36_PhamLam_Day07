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
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ad36.Data.DataHandler;
import com.example.ad36.Foods.Food;
import com.example.ad36.Foods.FoodAdapter;
import com.example.ad36.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class Order_Fragment extends Fragment {
    RecyclerView recyclerView;
    TextView tvCount, tvTotal;
    Button btOK;
    RelativeLayout relativeLayout;

    private static final String total ="Tổng giá tiền : ";

    public static Order_Fragment newInstant() {
        // Required empty public constructor
        return new Order_Fragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_food, container, false);

        recyclerView = view.findViewById(R.id.rvFood);
        final DataHandler dataHandler = new DataHandler(getContext());
        HashMap<Food, Integer> orderList = dataHandler.getOrderList();
        tvCount = view.findViewById(R.id.tvCount);
        tvTotal = view.findViewById(R.id.tvTotal);
        btOK = view.findViewById(R.id.btOK);
        relativeLayout = view.findViewById(R.id.rlGrocery);

        btOK.setVisibility(View.VISIBLE);
        tvTotal.setVisibility(View.VISIBLE);
        tvCount.setText(String.valueOf(dataHandler.getCountOrder()));


        List<Food> foodList = editList(orderList);
        FoodAdapter foodAdapter = new FoodAdapter(foodList, getContext());
        recyclerView.setAdapter(foodAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));

        int totalPrice = 0;
        for (Food food: foodList) {
            totalPrice += food.getPrice();
        }

        tvTotal.setText(total + String.valueOf(totalPrice));
        btOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataHandler.clearOrderDataBase();
                Toast.makeText(getContext(), "Order thành công", Toast.LENGTH_LONG).show();
            }
        });

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, Food_Fragment.newInstant())
                            .commit()
                    ;
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        return view;
    }


    private List<Food> editList(HashMap<Food, Integer> orderList){
        List<Food> foodList = new ArrayList<>();
        for (Map.Entry<Food, Integer> entry : orderList.entrySet()){
            int quantity = entry.getValue();
            Food food = entry.getKey();

            food.setName(food.getName() + "( x"+ String.valueOf(quantity) +" )");
            food.setPrice(food.getPrice() * quantity);
            foodList.add(food);
        }
        return foodList;
    }
}
