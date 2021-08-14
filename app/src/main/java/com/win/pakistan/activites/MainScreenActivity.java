package com.win.pakistan.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;

import com.win.pakistan.Adapters.AdapetrMenu;
import com.win.pakistan.Adapters.AdapetrParticepant;
import com.win.pakistan.R;

public class MainScreenActivity extends AppCompatActivity {
    ViewPager viewPagerparticpant,viewPagermenus;
    AdapetrParticepant particepantadpter;
    AdapetrMenu adapetrMenu;
    String[] textpartipantname,txtmenuname;

    int[] particpentimage,menuimage;
    private Handler handler;
    private int delay = 2000; //milliseconds
    private int page = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainscreencopy);
        handler = new Handler();
        particpentimage = new int[] { R.mipmap.person1, R.mipmap.person2, R.mipmap.person3, R.mipmap.person2,R.mipmap.person3, R.mipmap.person1, R.mipmap.person2, R.mipmap.person3};

        textpartipantname = new String[] { "Asad", "Imran", "Arshad"," Asfand","Sohail", "Faisal", "Husain"," Inham"};

        viewPagerparticpant = findViewById(R.id.view_pager);
        viewPagermenus = findViewById(R.id.view_pagermenu);

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


        menuimage = new int[] { R.mipmap.wallet, R.mipmap.grandpize, R.mipmap.refertofriends};

        txtmenuname = new String[] { "Wallet", "Grand Prize", "Refer to friend"};

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
}