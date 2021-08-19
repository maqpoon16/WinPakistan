package com.win.pakistan.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        response = getAutoLogin(Splash.this);
        delayEnter();
    }
    private void delayEnter() {

        new CountDownTimer(1200, 1200) {
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