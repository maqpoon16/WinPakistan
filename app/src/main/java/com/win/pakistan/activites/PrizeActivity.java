package com.win.pakistan.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import com.win.pakistan.Adapters.PrizDataclass;
import com.win.pakistan.Adapters.GrandPrizeAdapter;
import com.win.pakistan.R;

import java.util.ArrayList;

public class PrizeActivity extends AppCompatActivity {
ArrayList<PrizDataclass> prizearraylist;
GrandPrizeAdapter grandPrizeAdapter;
GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prize);
        prizearraylist=new ArrayList<>();
        gridView=findViewById(R.id.gridprize);
        prizearraylist.add(new PrizDataclass("V8",1000000, R.mipmap.car2));
        prizearraylist.add(new PrizDataclass("Mehran",30000, R.mipmap.car1));
        prizearraylist.add(new PrizDataclass("CD 70 ",20000, R.mipmap.cd70));
        prizearraylist.add(new PrizDataclass("Glaxy C7",10000, R.mipmap.mobile1));
        prizearraylist.add(new PrizDataclass("Note8i ",80000, R.mipmap.mobile2));
        grandPrizeAdapter =new GrandPrizeAdapter(PrizeActivity.this,prizearraylist);
        gridView.setAdapter(grandPrizeAdapter);


    }
}