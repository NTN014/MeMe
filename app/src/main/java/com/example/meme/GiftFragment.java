package com.example.meme;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class GiftFragment extends Fragment {
    int point = 0;

    static TextView pointTV;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_gift, container, false);

        ImageView dailySignin = view.findViewById(R.id.dailyImage);
        pointTV = view.findViewById(R.id.point);

        dailySignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dailyReward();
            }
        });


        return view;
    }

    public void updatePoint(){
        String pointText = String.format("%,d", point);
        pointTV.setText(pointText);
    }

    public void dailyReward(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String todayString = day + "" + month + "" + year;

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("PREFS", 0);
        boolean currentDay = sharedPreferences.getBoolean(todayString, false);

        if(!currentDay){
            point += 100;
            updatePoint();
            CustomAlertDialog ccd = new CustomAlertDialog();
            ccd.showDialog(this.getActivity(), "You received daily rewards!");
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(todayString, true);
            editor.apply();
        }else {
            Toast.makeText(getActivity(), "You already signed in", Toast.LENGTH_SHORT).show();
        }
    }
}