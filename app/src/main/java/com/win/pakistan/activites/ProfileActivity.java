package com.win.pakistan.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.win.pakistan.Models.authResponse;
import com.win.pakistan.R;

import static com.win.pakistan.Common.Methods.getAutoLogin;
import static com.win.pakistan.Common.Methods.setAutoLogin;

public class ProfileActivity extends AppCompatActivity {
    private authResponse response;
    private TextView tx_email,ex_mobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        response = getAutoLogin(ProfileActivity.this);
        tx_email = (TextView) findViewById(R.id.textemail);
        tx_email.setText(response.getUser().getEmail());
        ex_mobile = (TextView) findViewById(R.id.textcontact);
        ex_mobile.setText(response.getUser().getMobileNumber());

    }
    public void  gotoaccountinfo(View v){
        Intent intent=new Intent(ProfileActivity.this,ProfileActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void onLogOutClicked(View view) {
        authResponse authResponse = new authResponse();
        authResponse.setStatus(false);
        setAutoLogin(ProfileActivity.this,authResponse);
        Intent intent=new Intent(ProfileActivity.this,LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}