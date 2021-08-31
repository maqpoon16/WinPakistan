package com.win.pakistan.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.win.pakistan.MVC.Implementers.LoginImplementer;
import com.win.pakistan.MVC.Presentors.LoginPresenter;
import com.win.pakistan.MVC.Views.LoginView;
import com.win.pakistan.R;
import java.util.ArrayList;

import static com.win.pakistan.Common.Methods.closeKeyboard;
import static com.win.pakistan.Common.Methods.isNetworkConnected;
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
        editTexts.add(findViewById(R.id.edtcontact));
        editTexts.add(findViewById(R.id.edtpassword));
        LinearLayout loginlayout;
        loginlayout=findViewById(R.id.loginlayout);
        Animation slide_up = AnimationUtils.loadAnimation(LoginActivity.this,
                R.anim.slide_out_top);
        loginlayout.startAnimation(slide_up);

    }

    //Login Button
    public void homescreen(View view) {
        closeKeyboard(LoginActivity.this);
        if(isNetworkConnected(LoginActivity.this)){
            loginPresenter.LoginProcess(editTexts.get(0).getText().toString(),editTexts.get(1).getText().toString(),LoginActivity.this);
            return;
        }
        showSnackbar(layout,10000,LoginActivity.this, getString(R.string.no_internet_Message) );
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
        showSnackbar(layout,3000,LoginActivity.this,  getResources().getString(R.string.snackbarLoginSuccessfull ) );
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