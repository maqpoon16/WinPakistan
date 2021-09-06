package com.win.pakistan.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.win.pakistan.Adapters.AdapetrLeatherBoard;
import com.win.pakistan.Adapters.AdapetrWallethistory;
import com.win.pakistan.Models.LeatherBoardDataclass;
import com.win.pakistan.Models.WalletHistoryDataclass;
import com.win.pakistan.R;

import java.util.ArrayList;

public class WalletActivity extends AppCompatActivity {
    ArrayList<WalletHistoryDataclass> arrayListwallet=new ArrayList<>();
    AdapetrWallethistory adapetrWallethistory;
    RecyclerView wallethistoryrecyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);




        wallethistoryrecyclerview=(RecyclerView)findViewById(R.id.wallethistoryrecyclerview);
        arrayListwallet.add(new WalletHistoryDataclass("20-Sep-2019","Azadi Lucky Draw","Paid by coins","400",R.mipmap.coins2));
        arrayListwallet.add(new WalletHistoryDataclass("06-Sep-2021","Azadi ki Jang lucky  Draw","Paid by coins","100",R.mipmap.coins2));
        arrayListwallet.add(new WalletHistoryDataclass("06-Sep-2021","Ramzan lucky  Draw","Paid by coins","100",R.mipmap.coins2));

        adapetrWallethistory = new AdapetrWallethistory(WalletActivity.this, arrayListwallet);
        wallethistoryrecyclerview.setAdapter(adapetrWallethistory);

    }
}