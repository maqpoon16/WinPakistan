package com.win.pakistan.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import com.win.pakistan.Adapters.PrizDataclass;
import com.win.pakistan.Adapters.GrandPrizeAdapter;
import com.win.pakistan.Models.LuckDrawModels.LuckyDrawModel;
import com.win.pakistan.Models.LuckDrawModels.Prize;
import com.win.pakistan.Models.TodaygiftDataclass;
import com.win.pakistan.Models.authResponse;
import com.win.pakistan.R;

import java.util.ArrayList;
import java.util.List;

import static com.win.pakistan.Common.Methods.getAutoLogin;
import static com.win.pakistan.Common.Methods.getLuckyDrawData;

public class PrizeActivity extends AppCompatActivity {
ArrayList<PrizDataclass> prizearraylist;
GrandPrizeAdapter grandPrizeAdapter;
GridView gridView;
    private authResponse response;
    private LuckyDrawModel luckyDrawModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prize);
        response = getAutoLogin(PrizeActivity.this);
        luckyDrawModel = getLuckyDrawData(PrizeActivity.this);
        prizearraylist=new ArrayList<>();
        gridView=findViewById(R.id.gridprize);

        List<Prize> gifts = luckyDrawModel.getData().getLuckydraw().getPrizeList();
        String img = "https://cdn4.vectorstock.com/i/1000x1000/57/83/win-prizes-3d-gold-badge-with-red-ribbon-vector-16325783.jpg";
        for(Prize prize  : gifts){
            int coins = Integer.parseInt(luckyDrawModel.getData().getLuckydraw().getCoinsRequired());
            if(prize.getImage()!=null){
                img = prize.getImage().toString();
            }
            String title = prize.getTitle();
            prizearraylist.add(new PrizDataclass(title,coins,img));
        }
        if(gifts.size()<=0){
            prizearraylist.clear();
            prizearraylist.add(new PrizDataclass("V8",1000000, img));
            prizearraylist.add(new PrizDataclass("Mehran",30000, img));
            prizearraylist.add(new PrizDataclass("CD 70 ",20000, img));
            prizearraylist.add(new PrizDataclass("Glaxy C7",10000,img));
            prizearraylist.add(new PrizDataclass("Note8i ",80000, img));

        }
        grandPrizeAdapter =new GrandPrizeAdapter(PrizeActivity.this,prizearraylist);
        gridView.setAdapter(grandPrizeAdapter);


    }
}