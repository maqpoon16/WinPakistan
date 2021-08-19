package com.win.pakistan.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
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
import android.widget.EditText;
import android.widget.LinearLayout;
import com.google.android.material.snackbar.Snackbar;
import com.win.pakistan.MVC.Implementers.LoginImplementer;
import com.win.pakistan.MVC.Presentors.LoginPresenter;
import com.win.pakistan.MVC.Views.LoginView;
import com.win.pakistan.R;

import java.util.ArrayList;

import static com.win.pakistan.Common.FinalStrings.snackbarLoginSuccessfull;
import static com.win.pakistan.Common.FinalStrings.snackbarOK;
import static com.win.pakistan.Common.Methods.showSnackbar;

public class LoginActivity extends AppCompatActivity implements LoginView {
    private ConstraintLayout layout;
    private LoginPresenter loginPresenter;
    private ArrayList<EditText> editTexts;
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
        editTexts = new ArrayList<>();
        loginPresenter = new LoginImplementer(this);
        layout = findViewById(R.id.layout);
        editTexts.add(findViewById(R.id.edtemail));
        editTexts.add(findViewById(R.id.edtpassword));
        LinearLayout loginlayout;
        loginlayout=findViewById(R.id.loginlayout);
        Animation slide_up = AnimationUtils.loadAnimation(LoginActivity.this,
                R.anim.slide_out_top);
        loginlayout.startAnimation(slide_up);

    }

    //Login Button
    public void homescreen(View view) {
        loginPresenter.LoginProcess(editTexts.get(0).getText().toString(),editTexts.get(1).getText().toString(),LoginActivity.this);
        //opndialogbox();


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

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void ShowValidationError(String error) {
        showSnackbar(layout,10000,LoginActivity.this,error);

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void ShowException(String exception) {
        showSnackbar(layout,10000,LoginActivity.this, exception );

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void OnloginSuccessfull() {
        showSnackbar(layout,3000,LoginActivity.this, snackbarLoginSuccessfull );
        Intent intent = new Intent(LoginActivity.this, MainScreenActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void OnloginFailed(String loginFailMessage) {
        showSnackbar(layout,10000,LoginActivity.this,loginFailMessage );

    }
}