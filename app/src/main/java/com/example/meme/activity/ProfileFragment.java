    package com.example.meme.activity;

    import android.content.Context;
    import android.content.Intent;
    import android.content.SharedPreferences;
    import android.net.Uri;
    import android.os.Bundle;

    import androidx.fragment.app.Fragment;

    import android.provider.MediaStore;
    import android.text.TextUtils;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.Button;
    import android.widget.ImageView;
    import android.widget.TextView;

    import com.bumptech.glide.Glide;
    import com.example.meme.R;

    public class ProfileFragment extends Fragment {
        private static final String KEY_EMAIL = "email";
        private static final String KEY_NAME = "name";
        private static final String KEY_PHONE_NUMBER = "phoneNumber";
        private static final String KEY_DATE_OF_BIRTH = "dateOfBirth";
        private static final int LOGIN_REQUEST_CODE = 123;
        private static final int IMAGE_PICKER_REQUEST_CODE = 456;
        private TextView emailtv, phone, Uname, dob;
        private String email = "";
        private String name = "";
        private String phoneNumber = "";
        private String dateOfBirth = "";
        private boolean isViewCreated = false;
        private SharedPreferences sharedPreferences;
        private ImageView imageView;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_profile, container, false);

            Button loginButton = view.findViewById(R.id.loginButton);
            emailtv = view.findViewById(R.id.email);
            phone = view.findViewById(R.id.phoneNumberTextView);
            Uname = view.findViewById(R.id.name);
            dob = view.findViewById(R.id.dateofbirth);
            imageView = view.findViewById(R.id.ProfilePicture);
            sharedPreferences = requireContext().getSharedPreferences("ProfileData", Context.MODE_PRIVATE);
            Button logoutButton = view.findViewById(R.id.logoutButton);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openImagePicker();
                }
            });
            logoutButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    logout();
                }
            });


            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openLoginScreen();
                }
            });

            if (savedInstanceState != null) {
                email = savedInstanceState.getString(KEY_EMAIL);
                name = savedInstanceState.getString(KEY_NAME);
                phoneNumber = savedInstanceState.getString(KEY_PHONE_NUMBER);
                dateOfBirth = savedInstanceState.getString(KEY_DATE_OF_BIRTH);
            } else {
                loadProfileData();
            }
            isViewCreated = true;
            updateProfileData();
            return view;
        }

        private void openImagePicker() {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, IMAGE_PICKER_REQUEST_CODE);
        }


        private void logout() {
            // Clear the profile data
            email = "";
            name = "";
            phoneNumber = "";
            dateOfBirth = "";

            // Clear the shared preferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove(KEY_EMAIL);
            editor.remove(KEY_NAME);
            editor.remove(KEY_PHONE_NUMBER);
            editor.remove(KEY_DATE_OF_BIRTH);
            editor.apply();

            // Update the profile data immediately
            updateProfileData();

            // Restart the activity
            requireActivity().recreate();

        }


        private void openLoginScreen() {
            Intent intent = new Intent(requireContext(), LoginActivity.class);
            startActivityForResult(intent, LOGIN_REQUEST_CODE);
        }



        private void loadProfileData() {
            email = sharedPreferences.getString(KEY_EMAIL, "");  // Provide default value as empty string
            name = sharedPreferences.getString(KEY_NAME, "");
            phoneNumber = sharedPreferences.getString(KEY_PHONE_NUMBER, "");
            dateOfBirth = sharedPreferences.getString(KEY_DATE_OF_BIRTH, "");

        }

        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == LOGIN_REQUEST_CODE && resultCode == LoginActivity.RESULT_OK && data != null) {
                email = data.getStringExtra(LoginActivity.EXTRA_EMAIL);
                name = data.getStringExtra(LoginActivity.EXTRA_USERNAME);
                phoneNumber = data.getStringExtra(LoginActivity.EXTRA_PHONE_NUMBER);
                dateOfBirth = data.getStringExtra(LoginActivity.EXTRA_DATE_OF_BIRTH);


                saveProfileData();
                updateProfileData();

            } else if(requestCode == IMAGE_PICKER_REQUEST_CODE && resultCode == getActivity().RESULT_OK && data != null) {
                Uri selectedImageUri = data.getData();
                updateProfilePicture(selectedImageUri);

            }
        }

        private void updateProfilePicture(Uri selectedImageUri) {
            Glide.with(imageView.getContext())
                    .load(selectedImageUri)
                    .circleCrop()
                    .into(imageView);

        }

        private void saveProfileData() {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(KEY_EMAIL, email);
            editor.putString(KEY_NAME, name);
            editor.putString(KEY_PHONE_NUMBER, phoneNumber);
            editor.putString(KEY_DATE_OF_BIRTH, dateOfBirth);
            editor.apply();
        }
        private void updateProfileData() {
            if (isViewCreated && getView() != null && emailtv != null && Uname != null && phone != null && dob != null) {
                if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(name) && !TextUtils.isEmpty(phoneNumber) && !TextUtils.isEmpty(dateOfBirth)) {
                    Uname.setText("Name:  " + name);
                    emailtv.setText("Email:  " + email);
                    phone.setText("Phone:  "+ phoneNumber);
                    dob.setText("Date of birth:  " +dateOfBirth);
                    Button loginButton = getView().findViewById(R.id.loginButton);
                    loginButton.setVisibility(View.GONE); // Hide the login button

                    Button logoutButton = getView().findViewById(R.id.logoutButton);
                    logoutButton.setVisibility(View.VISIBLE); // Show the logout button
                } else {
                    Button loginButton = getView().findViewById(R.id.loginButton);
                    loginButton.setVisibility(View.VISIBLE); // Show the login button

                    Button logoutButton = getView().findViewById(R.id.logoutButton);
                    logoutButton.setVisibility(View.GONE); // Hide the logout button
                }
            }
        }

        @Override
        public void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
            outState.putString(KEY_EMAIL, email);
            outState.putString(KEY_NAME, name);
            outState.putString(KEY_PHONE_NUMBER, phoneNumber);
            outState.putString(KEY_DATE_OF_BIRTH, dateOfBirth);
        }
        @Override
        public void onResume() {
            super.onResume();
            loadProfileData();
            updateProfileData();
        }
    }