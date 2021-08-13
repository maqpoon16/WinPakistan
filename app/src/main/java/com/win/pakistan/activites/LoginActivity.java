package com.win.pakistan.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

import com.win.pakistan.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        animate();
    }

    @Override
    protected void onResume() {
        animate();
        super.onResume();
    }

    public void animate(){

        LinearLayout loginlayout;
        loginlayout=findViewById(R.id.loginlayout);
        Animation slide_up = AnimationUtils.loadAnimation(LoginActivity.this,
                R.anim.slide_out_top);
        loginlayout.startAnimation(slide_up);

    }
    public void homescreen(View view) {
        opndialogbox();


    }
    public void opndialogbox() {
        Button btncontiue,btnwatchvideo;
        View promptsView = LayoutInflater.from(LoginActivity.this).inflate(R.layout.dialogcoustomafterlogin, null);
        final Dialog dialog = new Dialog(LoginActivity.this);
        dialog.requestWindowFeature(1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(-1));


        btncontiue = (Button) promptsView.findViewById(R.id.btncontinue);
        btnwatchvideo = (Button) promptsView.findViewById(R.id.btnwatchvideo);



        btncontiue.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                dialog.dismiss();
                Intent intent = new Intent(LoginActivity.this, MainScreenActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });
        btnwatchvideo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                dialog.dismiss();
                Intent intent = new Intent(LoginActivity.this, MainScreenActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
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

}