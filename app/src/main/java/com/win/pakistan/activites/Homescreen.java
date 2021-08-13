package com.win.pakistan.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.win.pakistan.Adapters.AdapetrHelthtips;
import com.win.pakistan.R;

import java.util.Calendar;

public class Homescreen extends AppCompatActivity {
    AdapetrHelthtips helthadpter,promotionadpter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);

animate();



    }

    @Override
    protected void onResume() {
        animate();
        super.onResume();

    }

    public void login(View view) {

        Intent intent = new Intent(Homescreen.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);



    }

    public void animate()

    {
        Animation slide_up = AnimationUtils.loadAnimation(Homescreen.this,
                R.anim.slide_out_top);
        ConstraintLayout bottomlayout;
        bottomlayout = findViewById(R.id.bottomlayout);
        bottomlayout.startAnimation(slide_up);
    }
    public void signup(View view)
    {
        Intent intent=new Intent(Homescreen.this,SignUpActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }
}