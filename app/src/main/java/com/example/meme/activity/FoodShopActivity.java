package com.example.meme.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.meme.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FoodShopActivity extends AppCompatActivity {

    TextView txtGetData;
    EditText etxtPushData;
    Button btnPushData, btnGetData, btnGetImage;
    ImageView imgGet;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_shop);

        txtGetData = findViewById(R.id.txtGetData);
        etxtPushData = findViewById(R.id.etxt1);
        btnPushData = findViewById(R.id.btnPushData);
        btnGetData = findViewById(R.id.btnGetData);
        btnGetImage = findViewById(R.id.btnGetImage);
        ImageView imgGet = findViewById(R.id.imgGet);

        getData();

        btnPushData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushData();
            }
        });

        btnGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });

        btnGetImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getImage();
            }
        });
    }

    private void getImage() {
        // Read from the database

        DatabaseReference myRef = database.getReference("img");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
    }

    private void getData() {
        // Read from the database
        DatabaseReference myRef = database.getReference("message");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                txtGetData.setText(value);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
    }

    private void pushData() {
        // Write a message to the database
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue(etxtPushData.getText().toString().trim());
    }


}