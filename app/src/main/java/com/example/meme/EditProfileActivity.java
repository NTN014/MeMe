package com.example.meme;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import java.util.Calendar;
import java.util.Locale;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;



public class EditProfileActivity extends AppCompatActivity {

    private EditText emailup, nameup, phoneup;
    private Button saveButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        emailup = findViewById(R.id.emailupdate);
        nameup = findViewById(R.id.nameupdate);
        phoneup = findViewById(R.id.phonenumberupdate);
        saveButton = findViewById(R.id.loginupdate);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProfileChanges();
            }
        });
    }






    private void saveProfileChanges() {
        // Get the updated profile information from the EditText fields
        String updatedName = emailup.getText().toString();
        String updatedEmail = nameup.getText().toString();
        String updatedPhone = phoneup.getText().toString();


        // Create an intent to pass the updated data back to the ProfileFragment
        Intent intent = new Intent();
        intent.putExtra("name", updatedName);
        intent.putExtra("email", updatedEmail);
        intent.putExtra("phone", updatedPhone);
        setResult(RESULT_OK, intent);
        finish();
    }
}