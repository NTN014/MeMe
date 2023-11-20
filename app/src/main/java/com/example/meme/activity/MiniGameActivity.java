package com.example.meme.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meme.R;
import com.example.meme.ultil.CheckConnection;

import java.util.Random;

public class MiniGameActivity extends AppCompatActivity {

    TextView txtPoint;
    ImageButton ibtnPlay;
    CheckBox cbDog, cbChick, cbCat, cbOwl, cbTiger;
    SeekBar skDog, skChick, skCat, skOwl, skTiger;
    int point = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mini_game);
        AnhXa();
        disableSeekBar(skDog);
        disableSeekBar(skChick);
        disableSeekBar(skCat);
        disableSeekBar(skOwl);
        disableSeekBar(skTiger);
        CountDownTimer countDownTimer = new CountDownTimer(60000, 300) {
            @Override
            public void onTick(long millisUntilFinished) {
                int rdNum = 3;
                Random random = new Random();
                int dogNum = random.nextInt(rdNum);
                int chickNum = random.nextInt(rdNum);
                int catNum = random.nextInt(rdNum);
                int owlNum = random.nextInt(rdNum);
                int tigerNum = random.nextInt(rdNum);

                checkWin(skDog, "Dog", cbDog);
                checkWin(skChick, "Chick", cbChick);
                checkWin(skCat, "Cat", cbCat);
                checkWin(skOwl, "Owl", cbOwl);
                checkWin(skTiger, "Tiger", cbTiger);

                skDog.setProgress(skDog.getProgress() + dogNum);
                skChick.setProgress(skChick.getProgress() + chickNum);
                skCat.setProgress(skCat.getProgress() + catNum);
                skOwl.setProgress(skOwl.getProgress() + owlNum);
                skTiger.setProgress(skTiger.getProgress() + tigerNum);
            }

            private void checkWin(SeekBar seekBar, String animal, CheckBox checkBox) {
                if(seekBar.getProgress() >= seekBar.getMax()) {
//                    if(checkBox.isChecked()){
//                        checkBox.setChecked(false);
//                    }
                    ibtnPlay.setVisibility(View.VISIBLE);
                    this.cancel();
                    Toast.makeText(MiniGameActivity.this, animal+ " Win", Toast.LENGTH_SHORT).show();
                    calculatePoint(checkBox);
                }
            }

            private void calculatePoint(CheckBox checkBox) {
                if(checkBox.isChecked()) {
                    point += 10;
                    Toast.makeText(MiniGameActivity.this, "You win", Toast.LENGTH_SHORT).show();
                }else {
                    point -= 10;
                    Toast.makeText(MiniGameActivity.this, "You lose", Toast.LENGTH_SHORT).show();
                }
                txtPoint.setText(point + "");
                EnableCheckBox();
            }

            private void EnableCheckBox() {
                cbDog.setEnabled(true);
                cbChick.setEnabled(true);
                cbCat.setEnabled(true);
                cbOwl.setEnabled(true);
                cbTiger.setEnabled(true);

            }

            @Override
            public void onFinish() {

            }
        };

        ibtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbDog.isChecked() || cbChick.isChecked() || cbCat.isChecked()
                        || cbOwl.isChecked() || cbTiger.isChecked()) {
                    replayGame(skDog);
                    replayGame(skChick);
                    replayGame(skCat);
                    replayGame(skOwl);
                    replayGame(skTiger);

                    ibtnPlay.setVisibility(View.INVISIBLE);
                    countDownTimer.start();
                    disableCheckBox();
                }else {
                    Toast.makeText(MiniGameActivity.this, "Please bet before play", Toast.LENGTH_SHORT).show();
                }

            }

            private void disableCheckBox() {
                cbDog.setEnabled(false);
                cbChick.setEnabled(false);
                cbCat.setEnabled(false);
                cbOwl.setEnabled(false);
                cbTiger.setEnabled(false);
            }

            public void replayGame(SeekBar seekBar) {
                seekBar.setProgress(0);
            }
        });

        cbDog.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    checkCheckBox(cbChick, cbCat, cbOwl, cbTiger);
                }
            }
        });

        cbChick.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    checkCheckBox(cbDog, cbCat, cbOwl, cbTiger);
                }
            }
        });

        cbCat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    checkCheckBox(cbDog, cbChick, cbOwl, cbTiger);
                }
            }
        });

        cbOwl.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    checkCheckBox(cbDog, cbChick, cbCat, cbTiger);
                }
            }
        });

        cbTiger.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    checkCheckBox(cbDog, cbChick, cbOwl, cbCat);
                }
            }
        });
    }

    public void checkCheckBox(CheckBox checkBox1, CheckBox checkBox2, CheckBox checkBox3, CheckBox checkBox4) {
        checkBox1.setChecked(false);
        checkBox2.setChecked(false);
        checkBox3.setChecked(false);
        checkBox4.setChecked(false);
    }
    public void AnhXa() {
        txtPoint = findViewById(R.id.txtPoint);
        ibtnPlay = findViewById(R.id.btnPlay);
        cbDog = findViewById(R.id.checkboxAnimal1);
        cbChick = findViewById(R.id.checkboxAnimal2);
        cbCat = findViewById(R.id.checkboxAnimal3);
        cbOwl = findViewById(R.id.checkboxAnimal4);
        cbTiger = findViewById(R.id.checkboxAnimal5);
        skDog = findViewById(R.id.animal1);
        skChick = findViewById(R.id.animal2);
        skCat = findViewById(R.id.animal3);
        skOwl = findViewById(R.id.animal4);
        skTiger = findViewById(R.id.animal5);
    }

    public void disableSeekBar(SeekBar seekBar){
        seekBar.setEnabled(false);
    }

}