package com.win.pakistan.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.widget.ProgressBar;

import com.win.pakistan.Models.authResponse;
import com.win.pakistan.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.win.pakistan.Common.Methods.getAutoLogin;

public class Splash extends AppCompatActivity {
    private authResponse response;
    ProgressBar progressBar;
    int progressBarValue = 0;
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        response = getAutoLogin(Splash.this);
        progressBar=findViewById(R.id.simpleProgressBar);
        delayEnter();
        new Thread(new Runnable() {
            @Override public void run() {
                while(progressBarValue < 100) {
                    progressBarValue++;
                    handler.post(new Runnable() {
                        @Override public void run() {
                            progressBar.setProgress(progressBarValue);

                        }
                    });
                    try { Thread.sleep(15);
                    }
                    catch (InterruptedException e) { e.printStackTrace();
                    }
                }
            }
        }).start();
    }










    private void delayEnter() {

        new CountDownTimer(1200, 1500) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                if(response!=null && response.isStatus()){
                    startActivity(new Intent(Splash.this, MainScreenActivity.class));
                    finish();
                    return;
                }
                startActivity(new Intent(Splash.this, WelcomeActivity.class));
                finish();
            }
        }.start();
    }
}