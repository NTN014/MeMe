package com.example.meme.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.meme.R;
import com.example.meme.adapter.FoodShopAdapter;
import com.example.meme.model.Food;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FoodShopActivity extends AppCompatActivity {

    ImageView imgFood;
    TextView txtFoodName, txtFoodPrice, txtFoodQuantity, txtFoodDescription;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    RecyclerView rcvFoodShop;
    FoodShopAdapter foodShopAdapter;

    List<Food> foodList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_shop);
        initUI();
        setupRecyclerView();
        getFoodData();

    }

    private void setupRecyclerView() {
        // ... Existing code ...

        foodShopAdapter.setOnFoodItemClickListener(position -> {
            Food selectedFood = foodList.get(position);
            Intent intent = new Intent(FoodShopActivity.this, FoodDetailsActivity.class);
            intent.putExtra("FOOD_DETAILS", String.valueOf(selectedFood));
            startActivity(intent);
        });
    }

    private void getFoodData() {
        // Read from the database
        DatabaseReference myRef = database.getReference("food_shop");
        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                foodList.clear(); // Clear the list before adding new data
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Food food = dataSnapshot1.getValue(Food.class);
                    foodList.add(food);
                }

                foodShopAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(FoodShopActivity.this, "Load Data Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void initUI() {
        imgFood = findViewById(R.id.imgFood);
        txtFoodName = findViewById(R.id.txtFoodName);
        txtFoodDescription = findViewById(R.id.txtDescriptionFood);
        txtFoodPrice = findViewById(R.id.txtPriceFood);
        txtFoodQuantity = findViewById(R.id.txtQuantityFood);
        rcvFoodShop = findViewById(R.id.rcvFoodShop);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvFoodShop.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcvFoodShop.addItemDecoration(dividerItemDecoration);

        foodList = new ArrayList<>();
        foodShopAdapter = new FoodShopAdapter(foodList);

        rcvFoodShop.setAdapter(foodShopAdapter);
    }

}