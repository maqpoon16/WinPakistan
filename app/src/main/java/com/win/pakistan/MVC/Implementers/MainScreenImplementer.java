package com.win.pakistan.MVC.Implementers;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.TextView;

import com.win.pakistan.MVC.Presentors.MainScreenPresenter;
import com.win.pakistan.MVC.Views.MainScreenView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainScreenImplementer implements MainScreenPresenter {
    private final MainScreenView mainScreenView;
    private String EVENT_DATE_TIME = "2021-12-31 10:30:00";
    private String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private Handler handler = new Handler();
    private Runnable runnable;
    public MainScreenImplementer(MainScreenView mainScreenView) {
        this.mainScreenView = mainScreenView;
    }


    @Override
    public void TimerProcess(Activity context, TextView timerLabel) {

        runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    handler.postDelayed(this, 1000);
                    @SuppressLint("SimpleDateFormat")
                    SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
                    Date event_date = dateFormat.parse(EVENT_DATE_TIME);
                    Date current_date = new Date();
                    if (!current_date.after(event_date)) {
                        long diff = event_date.getTime() - current_date.getTime();
                        long Days = diff / (24 * 60 * 60 * 1000);
                        long Hours = diff / (60 * 60 * 1000) % 24;
                        long Minutes = diff / (60 * 1000) % 60;
                        long Seconds = diff / 1000 % 60;
                        timerLabel.setText(String.format("%02d", Hours)+":"+String.format("%02d", Minutes)+":"+String.format("%02d", Seconds));
                    } else {
                        handler.removeCallbacks(runnable);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, 0);
    }
}