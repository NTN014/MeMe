package com.example.meme.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.meme.R;

public class SettingFragment extends Fragment {

    Button btnMoveToAddFood, btnMoveToAddProduct;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        btnMoveToAddFood = view.findViewById(R.id.btnMoveToAddFood);
        btnMoveToAddProduct = view.findViewById(R.id.btnMoveToAddProduct);

        btnMoveToAddFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddFoodActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}