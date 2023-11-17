package com.example.meme;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;



public class ProfileFragment extends Fragment {

    private static final int LOGIN_REQUEST_CODE = 123 ;
    private TextView email, phone, Uname, dob;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        Button logginButton = view.findViewById(R.id.loginButton);
        email = view.findViewById(R.id.email);
        phone = view.findViewById(R.id.phoneNumberTextView);
        Uname = view.findViewById(R.id.name);
        dob = view.findViewById(R.id.dateofbirth);

        logginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openLoginScreen();
            }
        });
        return view;
    }



    private void openLoginScreen() {
        Intent intent = new Intent(requireContext(), LoginActivity.class);
        startActivityForResult(intent, LOGIN_REQUEST_CODE);
    }



    //for userdata
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LOGIN_REQUEST_CODE && resultCode == LoginActivity.RESULT_OK && data != null) {
            String emailValue = data.getStringExtra(LoginActivity.EXTRA_EMAIL);
            String name = data.getStringExtra(LoginActivity.EXTRA_USERNAME);
            String phoneNumber = data.getStringExtra(LoginActivity.EXTRA_PHONE_NUMBER);
            String dateOfBirth = data.getStringExtra(LoginActivity.EXTRA_DATE_OF_BIRTH);

            displayUsername(name);
            phone.setText(phoneNumber);
            email.setText(emailValue);
            displayDateOfBirth(dateOfBirth);

            TextView emailLabel = getView().findViewById(R.id.emailLabel);
            TextView nameLabel = getView().findViewById(R.id.nameLabel);
            TextView phoneLabel = getView().findViewById(R.id.phoneLabel);
            TextView dobLabel = getView().findViewById(R.id.doblable);
            emailLabel.setText("Email:  ");
            nameLabel.setText("Name:  ");
            phoneLabel.setText("Phone:  ");
            dobLabel.setText("Birth year:  ");
            if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(phoneNumber) && !TextUtils.isEmpty(emailValue)) {
                Button logginButton = requireView().findViewById(R.id.loginButton);
                logginButton.setVisibility(View.GONE); // Hide the login button
            }
        }
    }

    private void displayUsername(String name) {
        Uname.setText(name);
    }
    private void displayDateOfBirth(String dateOfBirth) {
        dob.setText(dateOfBirth);
    }

}