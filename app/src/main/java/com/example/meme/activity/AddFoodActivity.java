package com.example.meme.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.meme.R;
import com.example.meme.model.Food;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddFoodActivity extends AppCompatActivity {

    EditText etxtImagePath, etxtIDFood, etxtNameFood, etxtPriceFood, etxtQuantity, etxtDescription;
    Button btnAddFood;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);
        initUI();

        btnAddFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(etxtIDFood.getText().toString().trim());
                int price = Integer.parseInt(etxtPriceFood.getText().toString().trim());
                int quantity = Integer.parseInt(etxtQuantity.getText().toString().trim());
                String imagePath = etxtImagePath.getText().toString().trim();
                String name = etxtNameFood.getText().toString().trim();
                String description = etxtDescription.getText().toString().trim();

                Food food = new Food(id, name, price, imagePath, quantity, description);

                onClickPushData(food);
            }
        });


    }

    private void onClickPushData(Food food) {
        // Write a message to the database
        DatabaseReference myRef = database.getReference("food_shop");
        String pathObject = String.valueOf(food.getId());
        myRef.child(pathObject).setValue(food, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                Toast.makeText(AddFoodActivity.this, "Push data successfull", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initUI() {
        etxtDescription = findViewById(R.id.etxtDescriptionFood);
        etxtPriceFood = findViewById(R.id.etxtPriceFood);
        etxtIDFood = findViewById(R.id.etxtIDFood);
        etxtQuantity = findViewById(R.id.etxtQuantityFood);
        etxtNameFood = findViewById(R.id.etxtNameFood);
        etxtImagePath = findViewById(R.id.etxtImagePath);
        btnAddFood = findViewById(R.id.btnAddFood);
    }
}