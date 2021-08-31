package com.win.pakistan.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.win.pakistan.Adapters.AdapetrMenu;
import com.win.pakistan.Adapters.AdapetrParticepant;
import com.win.pakistan.MVC.Implementers.MainScreenImplementer;
import com.win.pakistan.MVC.Presentors.MainScreenPresenter;
import com.win.pakistan.MVC.Views.MainScreenView;
import com.win.pakistan.Models.authResponse;
import com.win.pakistan.R;

import static com.win.pakistan.Common.Methods.getAutoLogin;
import static com.win.pakistan.Common.Methods.isNetworkConnected;
import static com.win.pakistan.Common.Methods.newUserReward;
import static com.win.pakistan.Common.Methods.setNewUserReward;
import static com.win.pakistan.Common.Methods.showSnackbar;

public class MainScreenActivity extends AppCompatActivity implements MainScreenView {
    private ConstraintLayout layout;
    ViewPager viewPagerparticpant,viewPagermenus;
    AdapetrParticepant particepantadpter;
    AdapetrMenu adapetrMenu;
    String[] textpartipantname,txtmenuname;

    int[] particpentimage,menuimage;
    private Handler handler;
    private int delay = 2000; //milliseconds
    private int page = 0;
    private MainScreenPresenter mainScreenPresenter;
    TextView clockwise,txtname;
    private authResponse response;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainscreencopy);
        response = getAutoLogin(MainScreenActivity.this);
        mainScreenPresenter = new MainScreenImplementer(this);
        layout = findViewById(R.id.layout);
        //this will check user gets their on sigup reward or not!
        IsNewUser();
        handler = new Handler();
        particpentimage = new int[] { R.mipmap.person1, R.mipmap.person2, R.mipmap.person3, R.mipmap.person2,R.mipmap.person3, R.mipmap.person1, R.mipmap.person2, R.mipmap.person3};

        textpartipantname = new String[] { "Asad", "Imran", "Arshad"," Asfand","Sohail", "Faisal", "Husain"," Inham"};

        viewPagerparticpant = findViewById(R.id.view_pager);
        viewPagermenus = findViewById(R.id.view_pagermenu);
        clockwise = findViewById(R.id.clockwise);
        txtname = findViewById(R.id.txtname);
        txtname.setText(response.getUser().getFullName());
        if(isNetworkConnected(MainScreenActivity.this)){
            mainScreenPresenter.TimerProcess(MainScreenActivity.this);
            return;
        }else {
            showSnackbar(layout,10000,MainScreenActivity.this, getString(R.string.no_internet_Message) );
        }
        particepantadpter = new AdapetrParticepant(MainScreenActivity.this,textpartipantname,particpentimage);

        viewPagerparticpant.setAdapter(particepantadpter);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (particepantadpter.getCount() == page) {
                    page = 0;
                } else {
                    page++;
                }
                viewPagerparticpant.setCurrentItem(page, true);
                handler.postDelayed(this, delay);
            }
        }, 1000);


        menuimage = new int[] {  R.mipmap.grandpize, R.mipmap.refertofriends};

        txtmenuname = new String[] { "Grand Prize", "Refer to friend"};

        adapetrMenu = new AdapetrMenu(MainScreenActivity.this,txtmenuname,menuimage);

        viewPagermenus.setAdapter(adapetrMenu);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (adapetrMenu.getCount() == page) {
                    page = 0;
                } else {
                    page++;
                }
                viewPagermenus.setCurrentItem(page, true);
                //handler.postDelayed(this, delay);
            }
        }, 1000);
    }
    void IsNewUser(){
        boolean userRewardEnable = newUserReward(MainScreenActivity.this);
        if(userRewardEnable){
            opndialogbox();
            setNewUserReward(MainScreenActivity.this,false);
        }
    }
    public void playbutton(View view) {

        particepatelottery();
    }
    private void opndialogbox() {
        Button btncontiue,btnwatchvideo;
        View promptsView = LayoutInflater.from(MainScreenActivity.this).inflate(R.layout.dialogcoustomafterlogin, null);
        final Dialog dialog = new Dialog(MainScreenActivity.this);
        dialog.requestWindowFeature(1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(-1));


        btncontiue = (Button) promptsView.findViewById(R.id.btncontinue);
        btnwatchvideo = (Button) promptsView.findViewById(R.id.btnwatchvideo);



        btncontiue.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnwatchvideo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Google Reward videos here
                dialog.dismiss();
            }
        });




        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = -1;
        lp.height = -1;
        dialog.setContentView(promptsView);
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
    }

    private void particepatelottery() {

        Button btncontiue,btnwatchvideo;
        View promptsView = LayoutInflater.from(MainScreenActivity.this).inflate(R.layout.particepatedialoge, null);
        final Dialog dialog = new Dialog(MainScreenActivity.this);
        dialog.requestWindowFeature(1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(-1));
        btncontiue = (Button) promptsView.findViewById(R.id.btncontinue);
        btnwatchvideo = (Button) promptsView.findViewById(R.id.btnwatchvideo);
        btncontiue.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnwatchvideo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Google Reward videos here
                dialog.dismiss();
            }
        });

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = -1;
        lp.height = -1;
        dialog.setContentView(promptsView);
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
    }
    @Override
    public void ShowException(String exception) {
        showSnackbar(layout,6000,MainScreenActivity.this, exception );

    }

    @Override
    public void ShowFailureMessage(String failureReason) {
        showSnackbar(layout,6000,MainScreenActivity.this, failureReason );

    }

    @Override
    public void SetTimer(String remainingTime) {
        clockwise.setText(remainingTime);

    }

    @Override
    public void LuckyDrawWinner(String remainingTime) {

    }
    public void  gotoprofile(View v){

        Intent intent=new Intent(MainScreenActivity.this,ProfileActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}