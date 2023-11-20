package com.example.meme.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.meme.R;
import com.example.meme.ultil.CustomAlertDialog;

import java.util.ArrayList;
import java.util.Calendar;

public class HomeFragment extends Fragment {
    int point = 0;

    static TextView pointTV;
    ViewFlipper viewFlipper;
    Animation slideInRight, slideOutRight;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        viewFlipper = view.findViewById(R.id.homeViewFlipper);
        ImageView shoppingImageView = view.findViewById(R.id.shopping);
        ImageView mapImageView = view.findViewById(R.id.travel);
        pointTV = view.findViewById(R.id.point);
        shoppingImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click event for shopping ImageView
                Intent intent = new Intent(getContext(), ShoppingActivity.class);
                startActivity(intent);
            }
        });
        mapImageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Handle the click event for travel ImageView
                Intent intent = new Intent(getContext(), MapsActivity.class);
                startActivity(intent);
            }
        });
        actionViewFlipper();

        dailyReward();
        updatePoint();

        return view;
    }

    private void actionViewFlipper() {

        ArrayList<String> advertisementArray = new ArrayList<>();
        advertisementArray.add("https://png.pngtree.com/template/20220330/ourmid/pngtree-queen-s-day-spring-women-s-shoes-promotion-poster-bannar-image_904008.jpg");
        advertisementArray.add("https://marketingai.mediacdn.vn/wp-content/uploads/2018/06/Mini_banner_986abc1d537506626cf8febce59dd06e0fe9a607_1526019604704.jpg");
        advertisementArray.add("https://chiasetainguyen.com/upload-file/banner-quang-cao-valentine-2019-35c480c896b9d3.jpg");
        advertisementArray.add("https://intphcm.com/data/upload/banner-my-pham-ngot-ngao.jpg");
        advertisementArray.add("https://png.pngtree.com/thumb_back/fw800/background/20190220/ourmid/pngtree-womens-clothing-sales-pink-background-literary-image_8484.jpg");

        for (int i = 0; i<advertisementArray.size(); i++) {
            ImageView imageView = new ImageView(getActivity().getApplicationContext());
            Glide.with(getActivity().getApplicationContext()).load(advertisementArray.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }

        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);

        slideInRight = AnimationUtils.loadAnimation(getContext().getApplicationContext(), R.anim.slide_in_right);
        slideOutRight = AnimationUtils.loadAnimation(getContext().getApplicationContext(), R.anim.slide_out_right);
        viewFlipper.setInAnimation(slideInRight);
        viewFlipper.setOutAnimation(slideOutRight);
    }
    public void updatePoint(){
        SharedPreferences sh = this.getActivity().getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sh.edit();
        int pointText = sh.getInt("point", point);
        pointTV.setText(String.valueOf(pointText));
        editor.putString("pointView", pointTV.getText().toString());
        editor.apply();
//        Bundle res = new Bundle();
//        res.putString("Points", pointTV.getText().toString());
//        getParentFragmentManager().setFragmentResult("Data from home_fragment",res);

    }

    public void dailyReward(){

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        String todayString = day + "" + month + "" + year;
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("PREFS", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        boolean currentDay = sharedPreferences.getBoolean(todayString, false);
        point = sharedPreferences.getInt("point", 0);
        if(!currentDay) {
            point += 100;
            CustomAlertDialog ccd = new CustomAlertDialog();
            ccd.showDialog(this.getActivity(), "You received daily rewards!");
            editor.putBoolean(todayString, true);
            editor.putInt("point", point);
            editor.apply();
        }


    }

    @Override
    public void onPause() {
        super.onPause();

    }
}