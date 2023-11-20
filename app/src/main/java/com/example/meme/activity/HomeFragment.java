package com.example.meme.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.meme.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    ViewFlipper viewFlipper;
    Animation slideInRight, slideOutRight;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        viewFlipper = view.findViewById(R.id.homeViewFlipper);
        ImageView shoppingImageView = view.findViewById(R.id.shopping);
        ImageView mapImageView = view.findViewById(R.id.travel);
        ImageView miniGameImageView = view.findViewById(R.id.miniGame);

        miniGameImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MiniGameActivity.class);
                startActivity(intent);
            }
        });
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
}