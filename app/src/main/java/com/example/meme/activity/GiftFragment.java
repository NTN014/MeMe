package com.example.meme.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meme.DailyRewardService;
import com.example.meme.ultil.CustomAlertDialog;
import com.example.meme.R;

import java.util.Calendar;

public class GiftFragment extends Fragment {

    static TextView pointTV;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_gift, container, false);
        pointTV = view.findViewById(R.id.point);
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("PREFS",0);
        getParentFragmentManager().setFragmentResultListener("Data from home_fragment", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String data = result.getString("Points");
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Points", data);
                editor.apply();
                pointTV.setText(data);
            }
        });

        // Schedule the daily reward alarm
        scheduleDailyRewardAlarm();

        return view;
    }

    private void scheduleDailyRewardAlarm() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);


        Intent intent = new Intent(getActivity(), DailyRewardService.class);
        PendingIntent pendingIntent = PendingIntent.getService(getActivity(), 0, intent, 0);

        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        if (alarmManager != null) {
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        }
    }


}