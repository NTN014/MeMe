package com.example.meme;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;


public class LoginActivity extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;
    private Button datebutton;
    private EditText Email, Name, phone;
    public static final String EXTRA_EMAIL = "extra_email";
    public static final String EXTRA_USERNAME = "extra_username";
    public static final String EXTRA_PHONE_NUMBER = "extra_phone_number";
    public static final String EXTRA_DATE_OF_BIRTH = "extra_date_of_birth";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initDatePicker();
        datebutton = findViewById(R.id.ageselect);
        datebutton.setText(getTodaysDate());
        Email = findViewById(R.id.email);
        Name = findViewById(R.id.name);
        phone = findViewById(R.id.phonenumber);
        Button signin = findViewById(R.id.login);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Email.getText().toString();
                String phoneNumber = phone.getText().toString();
                String name = Name.getText().toString();
                String dateOfBirth = datebutton.getText().toString();
                
                if (!isValidEmail(email)) {
                    // Show an error message indicating that the email is not valid
                    Email.setError("Invalid email");
                    return;
                }

                if (name.length() > 25) {
                    // Show an error message indicating that the name is too long
                    Name.setError("Name should not exceed 25 characters");
                    return;
                }

                if (!isValidPhoneNumber(phoneNumber)) {
                    // Show an error message indicating that the phone number is not valid
                    phone.setError("Invalid phone number");
                    return;
                }
                
                sendUsernameToProfile(email, name, phoneNumber, dateOfBirth);
            }
        });
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        String regexStr = "^[0-9]{10}$";
        return phoneNumber.matches(regexStr);
    }

    private boolean isValidEmail(String email) {
        String regexPattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        return email.matches(regexPattern);
    }


    private void sendUsernameToProfile(String email, String name, String phoneNumber, String dateOfBirth) {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtra(EXTRA_USERNAME, name);
        intent.putExtra(EXTRA_PHONE_NUMBER, phoneNumber);
        intent.putExtra(EXTRA_EMAIL, email);
        intent.putExtra(EXTRA_DATE_OF_BIRTH, dateOfBirth);
        setResult(RESULT_OK, intent);
        finish();
    }

    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month) {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";


        return "JAN";
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                datebutton.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);

    }

    public void OnDatePicker(View view){

        datePickerDialog.show();
    }

}