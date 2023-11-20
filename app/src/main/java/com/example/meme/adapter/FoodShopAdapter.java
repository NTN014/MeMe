package com.example.meme.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.meme.R;
import com.example.meme.model.Food;

import java.util.List;

public class FoodShopAdapter extends RecyclerView.Adapter<FoodShopAdapter.ViewHolder> {

    List<Food> foodList;
    private OnFoodItemClickListener onFoodItemClickListener;

    public FoodShopAdapter(List<Food> foodList) {
        this.foodList = foodList;
    }

    public interface OnFoodItemClickListener {
        void onFoodItemClick(int position);
    }

    public void setOnFoodItemClickListener(OnFoodItemClickListener listener) {
        this.onFoodItemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_food, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Food food = foodList.get(position);
        if(food == null) {
            return;
        }
        holder.txtFoodName.setText(food.getName());
        holder.txtFoodDescription.setText(food.getDescription());
        holder.txtFoodPrice.setText(String.valueOf("gia: " + food.getPrice())); // Convert int to String
        holder.txtFoodQuantity.setText(String.valueOf("so luong: " + food.getQuantity())); // Convert int to String

        Glide.with(holder.itemView.getContext())
                .load(food.getImage())
                .into(holder.imgFood);

        holder.itemView.setOnClickListener(view -> {
            if (onFoodItemClickListener != null) {
                onFoodItemClickListener.onFoodItemClick(position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgFood;
        TextView txtFoodName, txtFoodPrice, txtFoodQuantity, txtFoodDescription;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgFood = itemView.findViewById(R.id.imgFood);
            txtFoodName = itemView.findViewById(R.id.txtFoodName);
            txtFoodDescription = itemView.findViewById(R.id.txtDescriptionFood);
            txtFoodPrice = itemView.findViewById(R.id.txtPriceFood);
            txtFoodQuantity = itemView.findViewById(R.id.txtQuantityFood);
        }
    }
}
