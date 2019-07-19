package com.example.ad36.Foods;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ad36.MainActivity;
import com.example.ad36.MyService;
import com.example.ad36.R;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.Viewhoder> {

    List<Food> foodList;

    public FoodAdapter(List<Food> foodList) {
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public Viewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_food, parent, false);
        Viewhoder viewhoder = new Viewhoder(view);

        return viewhoder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewhoder holder, int position) {
        final Food food =foodList.get(position);
        holder.ivIcon.setImageResource(food.getIconId());
        holder.tvName.setText(food.getName());
        holder.tvDes.setText(food.getDescrip());
        holder.tvPrice.setText(String.valueOf(food.getPrice()));
        holder.lnFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
            }
        });

    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public class Viewhoder extends RecyclerView.ViewHolder {
        TextView tvName, tvDes, tvPrice;
        ImageView ivIcon;
        LinearLayout lnFood;

        public Viewhoder(@NonNull View itemView) {
            super(itemView);

            tvName =itemView.findViewById(R.id.tvName);
            tvDes =itemView.findViewById(R.id.tvDes);
            tvPrice =itemView.findViewById(R.id.tvPrice);
            ivIcon = itemView.findViewById(R.id.ivIcon);
            lnFood = itemView.findViewById(R.id.lnFood);
        }
    }
}
