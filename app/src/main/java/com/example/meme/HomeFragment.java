package com.example.meme;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    ViewFlipper viewFlipper;
    Animation slideInRight, slideOutRight;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        viewFlipper = view.findViewById(R.id.homeViewFlipper);

        actionViewFlipper();
        return view;
    }

    private void actionViewFlipper() {

        ArrayList<String> advertisementArray = new ArrayList<>();
        advertisementArray.add("https://png.pngtree.com/background/20211215/original/pngtree-small-fresh-anime-scene-background-picture-picture-image_1499416.jpg");
        advertisementArray.add("https://png.pngtree.com/background/20211215/original/pngtree-anime-scene-fresh-anime-picture-image_1499869.jpg");
        advertisementArray.add("https://png.pngtree.com/thumb_back/fh260/background/20210907/pngtree-anime-scene-beautiful-cherry-blossom-image_811998.jpg");
        advertisementArray.add("https://bizweb.dktcdn.net/100/330/208/files/hinh-nen-anime-mau-hong-cute-1.jpg?v=1649317596706");
        advertisementArray.add("https://i.pinimg.com/originals/24/90/18/2490186c015c32c784421be816f71de6.jpg");

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