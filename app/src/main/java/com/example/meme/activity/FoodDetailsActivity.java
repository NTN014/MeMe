package com.example.meme.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.meme.R;
import com.example.meme.model.Food;

public class FoodDetailsActivity extends AppCompatActivity {

    ImageView imgFoodDetails;
    TextView txtFoodDetailsName;
    Button btnBuy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);

        imgFoodDetails = findViewById(R.id.imgFoodDetails);
        txtFoodDetailsName = findViewById(R.id.txtFoodDetailsName);
        btnBuy = findViewById(R.id.btnBuy);

        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FoodDetailsActivity.this, "Bạn đã mua thành công, và bạn không có hàng", Toast.LENGTH_SHORT).show();
            }
        });

        Intent intent = getIntent();
        if (intent != null) {
            Food selectedFood = intent.getParcelableExtra("FOOD_DETAILS");
            if (selectedFood != null) {
                // Set details in the views
                txtFoodDetailsName.setText(selectedFood.getName());

                // Load image using Glide or Picasso as done in FoodShopAdapter
                Glide.with(this)
                        .load(selectedFood.getImage())
                        .into(imgFoodDetails);

                // Set other details similarly
            }
        }
    }
}
