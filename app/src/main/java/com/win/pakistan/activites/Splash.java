package com.win.pakistan.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.widget.ProgressBar;

import com.win.pakistan.MVC.Implementers.AccountInfoScreenImplementer;
import com.win.pakistan.MVC.Implementers.LoginImplementer;
import com.win.pakistan.MVC.Implementers.MainScreenImplementer;
import com.win.pakistan.MVC.Presentors.AccountInfoScreenPresenter;
import com.win.pakistan.MVC.Presentors.LoginPresenter;
import com.win.pakistan.MVC.Presentors.MainScreenPresenter;
import com.win.pakistan.MVC.Views.AccountInfoScreenView;
import com.win.pakistan.MVC.Views.MainScreenView;
import com.win.pakistan.Models.LuckDrawModels.LuckyDrawModel;
import com.win.pakistan.Models.UserData;
import com.win.pakistan.Models.authResponse;
import com.win.pakistan.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.win.pakistan.Common.Methods.getAutoLogin;
import static com.win.pakistan.Common.Methods.isNetworkConnected;
import static com.win.pakistan.Common.Methods.setLuckyDrawData;

public class Splash extends AppCompatActivity implements AccountInfoScreenView, MainScreenView{
    private authResponse response;
    ProgressBar progressBar;
    int progressBarValue = 0;
    Handler handler = new Handler();
    MainScreenPresenter  mainScreenPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        response = getAutoLogin(Splash.this);
        AccountInfoScreenPresenter accountInfoScreenPresenter = new AccountInfoScreenImplementer(this);
        mainScreenPresenter = new MainScreenImplementer(this,null);
        progressBar=findViewById(R.id.simpleProgressBar);

        if (isNetworkConnected(Splash.this)) {
            if(response!=null){
                accountInfoScreenPresenter.getOnlineProfile(Splash.this,response.getUser().getId());
            }else {
                mainScreenPresenter.GetLuckyDrawData(Splash.this);
            }
        }

        new Thread(new Runnable() {
            @Override public void run() {
                while(progressBarValue < 100) {
                    progressBarValue++;
                    handler.post(new Runnable() {
                        @Override public void run() {
                            progressBar.setProgress(progressBarValue);

                        }
                    });
                    try {
                        Thread.sleep(100);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(progressBarValue==100){
                    if(response!=null && response.isStatus()){
                        startActivity(new Intent(Splash.this, MainScreenActivity.class));
                        finish();
                    }else {
                        startActivity(new Intent(Splash.this, WelcomeActivity.class));
                        finish();
                    }
                }
            }
        }).start();
    }

    /*private void delayEnter() {

        new CountDownTimer(1200, 1500) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                if(response!=null && response.isStatus()){
                    startActivity(new Intent(Splash.this, MainScreenActivity.class));
                    finish();
                }else {
                    startActivity(new Intent(Splash.this, WelcomeActivity.class));
                    finish();
                }

            }
        }.start();
    }*/

    @Override
    public void ShowException(String exception) {
        // no use here
    }

    @Override
    public void ShowFailureMessage(String failureReason) {
        // no use here

    }

    @Override
    public void SetTimer(String remainingTime) {
        // no use here

    }

    @Override
    public void onlineLuckyDrawData(LuckyDrawModel luckyDrawModel) {
        if(luckyDrawModel !=null ){
            setLuckyDrawData(Splash.this,luckyDrawModel);
        }
    }

    @Override
    public void LuckyDrawWinner(String remainingTime) {
        // no use here

    }

    @Override
    public void SetDOB(String selectedDOB) {
        // no use here

    }

    @Override
    public void SetAge(String calculatedAge) {
        // no use here

    }

    @Override
    public void SetOnlineProfile(UserData onlineProfile) {
        Log.d("Data_status","100% Fetched!");
        mainScreenPresenter.GetLuckyDrawData(Splash.this);
    }

    @Override
    public void onOnlineProfileUpdated(authResponse response) {
        // no use here

    }
}