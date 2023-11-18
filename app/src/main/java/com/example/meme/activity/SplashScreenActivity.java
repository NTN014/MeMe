package com.example.meme.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.meme.R;

public class SplashScreenActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 3500;
    Animation pushUpAnim, dropDownAnim;

    ImageView logo;
    TextView appName, slogan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT < 27) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        setContentView(R.layout.activity_splash_screen);

        pushUpAnim = AnimationUtils.loadAnimation(this, R.anim.push_up_anim);
        dropDownAnim = AnimationUtils.loadAnimation(this, R.anim.drop_down_anim);
        logo = findViewById(R.id.logo_wibu);
        appName = findViewById(R.id.app_name);
        slogan = findViewById(R.id.slogan);

        logo.setAnimation(dropDownAnim);
        appName.setAnimation(pushUpAnim);
        slogan.setAnimation(pushUpAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN);
    }
}