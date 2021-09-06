package com.win.pakistan.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.win.pakistan.Adapters.AdapetrLeatherBoard;
import com.win.pakistan.Adapters.AdapetrWalletHistory;
import com.win.pakistan.Models.LeatherBoardDataclass;
import com.win.pakistan.Models.WalletHistoryDataclass;
import com.win.pakistan.R;

import java.util.ArrayList;

public class WalletActivity extends AppCompatActivity {
    private RecyclerView recyclerViewleadtherboad;
    private ArrayList<WalletHistoryDataclass> arrayWalletHistory=new ArrayList<>();
    private AdapetrWalletHistory adapetrWalletHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        recyclerViewleadtherboad=(RecyclerView) findViewById(R.id.recyclerviewleatherboard);
        arrayWalletHistory.add(new WalletHistoryDataclass("5th September 2021","Bumper Lucky Draw 2021","Paid By Cash","Rs.1000",R.drawable.googlepay_history));
        arrayWalletHistory.add(new WalletHistoryDataclass("5th September 2021","Bumper Lucky Draw 2021","Paid By Coins","5000\nCoins",R.drawable.coins_wallet));
        arrayWalletHistory.add(new WalletHistoryDataclass("4th September 2021","Special Lucky Draw 2021","Paid By Cash","Rs.500",R.drawable.googlepay_history));
        arrayWalletHistory.add(new WalletHistoryDataclass("3rd September 2021","Mega Lucky Draw 2021","Paid By Coins","1000\nCoins",R.drawable.coins_wallet));
        arrayWalletHistory.add(new WalletHistoryDataclass("2nd September 2021","Rs.10,000 Cash Prize Lucky Draw 2021","Paid By Coins","1000\nCoins",R.drawable.coins_wallet));
        adapetrWalletHistory = new AdapetrWalletHistory(WalletActivity.this, arrayWalletHistory);
        recyclerViewleadtherboad.setAdapter(adapetrWalletHistory);
    }
}