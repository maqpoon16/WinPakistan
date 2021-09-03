package com.win.pakistan.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.win.pakistan.Adapters.AdapetrGifttoday;
import com.win.pakistan.Adapters.AdapetrMenu;
import com.win.pakistan.Common.Recievers.ConnectivityReceiver;
import com.win.pakistan.MVC.Implementers.MainScreenImplementer;
import com.win.pakistan.MVC.Presentors.MainScreenPresenter;
import com.win.pakistan.MVC.Views.MainScreenView;
import com.win.pakistan.Models.LuckDrawModels.LuckyDrawModel;
import com.win.pakistan.Models.LuckDrawModels.Luckydraw;
import com.win.pakistan.Models.LuckDrawModels.Prize;
import com.win.pakistan.Models.MenuDataclass;
import com.win.pakistan.Models.TodaygiftDataclass;
import com.win.pakistan.Models.authResponse;
import com.win.pakistan.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.win.pakistan.Common.Methods.getAutoLogin;
import static com.win.pakistan.Common.Methods.getLuckyDrawData;
import static com.win.pakistan.Common.Methods.isNetworkConnected;
import static com.win.pakistan.Common.Methods.newUserReward;
import static com.win.pakistan.Common.Methods.setLuckyDrawData;
import static com.win.pakistan.Common.Methods.setNewUserReward;
import static com.win.pakistan.Common.Methods.showSnackbar;

public class MainScreenActivity extends AppCompatActivity implements MainScreenView {
    private ConstraintLayout layout;
    RecyclerView recyclerViewpize,recyclerViewmenu;
    AdapetrGifttoday adapetrGifttoday;
    AdapetrMenu adapetrMenu;
    ArrayList<MenuDataclass> arrayListmenu;
    ArrayList<TodaygiftDataclass> todaygiftArrayList;

    private MainScreenPresenter mainScreenPresenter;
    private  TextView clockwise,txtname,txtCoinsRequired,txtCashRequired;
    private CircleImageView profile_image;
    private authResponse response;
    private LuckyDrawModel luckyDrawModel;
    private BroadcastReceiver myReceiver = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainscreencopy2);
        objectInitialization();
        broadcastIntent();
        //this will check user gets their on sigup reward or not!
        IsNewUser();

        if (isNetworkConnected(MainScreenActivity.this)) {
            mainScreenPresenter.GetLuckyDrawData(MainScreenActivity.this);
       }
       else {
            showSnackbar(layout, 10000, MainScreenActivity.this, getString(R.string.no_internet_Message));
        }

        objectAssignment();
    }

    void objectInitialization(){
        myReceiver = new ConnectivityReceiver();
        response = getAutoLogin(MainScreenActivity.this);
        luckyDrawModel = getLuckyDrawData(MainScreenActivity.this);
        if(luckyDrawModel!=null){
            mainScreenPresenter = new MainScreenImplementer(this,luckyDrawModel.getData().getLuckydraw().getDateTime());
        }else {
            mainScreenPresenter = new MainScreenImplementer(this,null);
        }
        layout = findViewById(R.id.layout);
        arrayListmenu=new ArrayList<>();
        todaygiftArrayList=new ArrayList<>();
        recyclerViewpize = findViewById(R.id.giftrecyclerview);
        recyclerViewmenu = findViewById(R.id.menurecyclerview);
        clockwise = findViewById(R.id.clockwise);
        txtname = findViewById(R.id.txtname);
        txtCashRequired = findViewById(R.id.txt_cash);
        txtCoinsRequired = findViewById(R.id.txt_coins);
        profile_image = (CircleImageView) findViewById(R.id.imageviewuser);

    }
    void objectAssignment(){
        Picasso.get().load(getResources().getString(R.string.ServerBaseUrlImage)+response.getUser().getProfileImage()).into(profile_image);
        clockwise.setText("00:00:00");
        txtname.setText(response.getUser().getFullName());
        /******* menu recyclerview*********/
        arrayListmenu.add(new MenuDataclass("Grand Prize",R.mipmap.grandpize));
        arrayListmenu.add(new MenuDataclass("Refer To Friend",R.mipmap.refertofriends));
        adapetrMenu = new AdapetrMenu(MainScreenActivity.this, arrayListmenu);
        recyclerViewmenu.setAdapter(adapetrMenu);

        /******* gift recyclerview*********/
        if(luckyDrawModel!=null){
            txtCashRequired.setText(luckyDrawModel.getData().getLuckydraw().getCashReqiured());
            txtCoinsRequired.setText(luckyDrawModel.getData().getLuckydraw().getCoinsRequired());
          todaygiftArrayList.clear();
            List<Prize> gifts = luckyDrawModel.getData().getLuckydraw().getPrizeList();
            for(Prize prize  : gifts){
                int coins = Integer.parseInt(luckyDrawModel.getData().getLuckydraw().getCoinsRequired());
                int cash = Integer.parseInt(luckyDrawModel.getData().getLuckydraw().getCashReqiured());
                String img = "https://cdn4.vectorstock.com/i/1000x1000/57/83/win-prizes-3d-gold-badge-with-red-ribbon-vector-16325783.jpg";
                if(prize.getImage()!=null){
                    img = prize.getImage().toString();
                }
                String dateTime = luckyDrawModel.getData().getLuckydraw().getDateTime();
                String title = prize.getTitle();
                todaygiftArrayList.add(new TodaygiftDataclass(title,img ,cash,coins,dateTime));
            }
        }
        adapetrGifttoday = new AdapetrGifttoday(MainScreenActivity.this, todaygiftArrayList);
        recyclerViewpize.setAdapter(adapetrGifttoday);

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
    public void onlineLuckyDrawData(LuckyDrawModel luckyDrawModel) {
       // gift recyclerview
        if(luckyDrawModel!=null){
            mainScreenPresenter.TimerProcess(MainScreenActivity.this);
            setLuckyDrawData(MainScreenActivity.this,luckyDrawModel);
            this.luckyDrawModel = getLuckyDrawData(MainScreenActivity.this);
            txtCashRequired.setText(this.luckyDrawModel.getData().getLuckydraw().getCashReqiured());
            txtCoinsRequired.setText(this.luckyDrawModel.getData().getLuckydraw().getCoinsRequired());
            todaygiftArrayList.clear();
            List<Prize> gifts = this.luckyDrawModel.getData().getLuckydraw().getPrizeList();
            for(Prize prize  : gifts){
                int coins = Integer.parseInt(this.luckyDrawModel.getData().getLuckydraw().getCoinsRequired());
                int cash = Integer.parseInt(this.luckyDrawModel.getData().getLuckydraw().getCashReqiured());
                String img = "https://cdn4.vectorstock.com/i/1000x1000/57/83/win-prizes-3d-gold-badge-with-red-ribbon-vector-16325783.jpg";
                if(prize.getImage()!=null){
                    img = prize.getImage().toString();
                }
                String dateTime = this.luckyDrawModel.getData().getLuckydraw().getDateTime();
                String title = prize.getTitle();
                todaygiftArrayList.add(new TodaygiftDataclass(title,img ,cash,coins,dateTime));
            }
        }
        adapetrGifttoday = new AdapetrGifttoday(MainScreenActivity.this, todaygiftArrayList);
        recyclerViewpize.setAdapter(adapetrGifttoday);
    }

    @Override
    public void LuckyDrawWinner(String remainingTime) {

    }

    public void  gotoprofile(View v){

        Intent intent=new Intent(MainScreenActivity.this,ProfileActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void broadcastIntent() {
        registerReceiver(myReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(myReceiver);
    }
    @Override
    protected void onResume() {
        super.onResume();
        broadcastIntent();
    }
}