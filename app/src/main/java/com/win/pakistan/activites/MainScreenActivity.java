package com.win.pakistan.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
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

import com.win.pakistan.Adapters.AdapetrGifttoday;
import com.win.pakistan.Adapters.AdapetrMenu;
import com.win.pakistan.Adapters.AdapetrParticepant;
import com.win.pakistan.MVC.Implementers.MainScreenImplementer;
import com.win.pakistan.MVC.Presentors.MainScreenPresenter;
import com.win.pakistan.MVC.Views.MainScreenView;
import com.win.pakistan.Models.MenuDataclass;
import com.win.pakistan.Models.TodaygiftDataclass;
import com.win.pakistan.Models.authResponse;
import com.win.pakistan.R;

import java.util.ArrayList;

import static com.win.pakistan.Common.Methods.getAutoLogin;
import static com.win.pakistan.Common.Methods.isNetworkConnected;
import static com.win.pakistan.Common.Methods.newUserReward;
import static com.win.pakistan.Common.Methods.setNewUserReward;
import static com.win.pakistan.Common.Methods.showSnackbar;

public class MainScreenActivity extends AppCompatActivity implements MainScreenView {
    private ConstraintLayout layout;
    RecyclerView recyclerViewpize,recyclerViewmenu;
    AdapetrGifttoday adapetrGifttoday;
    AdapetrMenu adapetrMenu;
    ArrayList<MenuDataclass> arrayListmenu;
    ArrayList<TodaygiftDataclass> todaygiftArrayList;

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
        setContentView(R.layout.activity_mainscreencopy2);
        response = getAutoLogin(MainScreenActivity.this);
        mainScreenPresenter = new MainScreenImplementer(this);
        layout = findViewById(R.id.layout);
        //this will check user gets their on sigup reward or not!
      IsNewUser();
        arrayListmenu=new ArrayList<>();
        todaygiftArrayList=new ArrayList<>();
        handler = new Handler();
        particpentimage = new int[]{R.mipmap.person1};



        recyclerViewpize = findViewById(R.id.giftrecyclerview);
       recyclerViewmenu = findViewById(R.id.menurecyclerview);
        clockwise = findViewById(R.id.clockwise);
        txtname = findViewById(R.id.txtname);
        txtname.setText(response.getUser().getFullName());
       if (isNetworkConnected(MainScreenActivity.this)) {
            mainScreenPresenter.TimerProcess(MainScreenActivity.this);

        }
       else {
            showSnackbar(layout, 10000, MainScreenActivity.this, getString(R.string.no_internet_Message));
        }


        /******* menu recyclerview*********/
        arrayListmenu.add(new MenuDataclass("Grand Prize",R.mipmap.grandpize));

        arrayListmenu.add(new MenuDataclass("Refer To Friend",R.mipmap.refertofriends));
        arrayListmenu.add(new MenuDataclass("Refer To Friend",R.mipmap.refertofriends));
        arrayListmenu.add(new MenuDataclass("Refer To Friend",R.mipmap.refertofriends));

        adapetrMenu = new AdapetrMenu(MainScreenActivity.this, arrayListmenu);
        recyclerViewmenu.setAdapter(adapetrMenu);

        /******* gift recyclerview*********/


        todaygiftArrayList.add(new TodaygiftDataclass("Honda CD 70",R.mipmap.cd70,1000,200,2000));


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
    public void LuckyDrawWinner(String remainingTime) {

    }
    public void  gotoprofile(View v){

        Intent intent=new Intent(MainScreenActivity.this,ProfileActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}