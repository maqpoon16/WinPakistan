package com.win.pakistan.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.win.pakistan.Adapters.AdapetrParticepant;
import com.win.pakistan.R;

public class Homescreen extends AppCompatActivity {
    AdapetrParticepant helthadpter,promotionadpter;
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