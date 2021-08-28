package com.win.pakistan.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.win.pakistan.Models.authResponse;
import com.win.pakistan.R;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.win.pakistan.Common.Methods.getAutoLogin;
import static com.win.pakistan.Common.Methods.setAutoLogin;

public class ProfileActivity extends AppCompatActivity {
    private static final int PICK_IMAGE = 1;
    private CircleImageView profile_image;
    private authResponse response;
    private TextView tx_user,ex_mobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        response = getAutoLogin(ProfileActivity.this);
        tx_user = (TextView) findViewById(R.id.textuser);
        profile_image = (CircleImageView) findViewById(R.id.profile_image);
        tx_user.setText(response.getUser().getEmail());
        ex_mobile = (TextView) findViewById(R.id.textcontact);
        ex_mobile.setText(response.getUser().getMobileNumber());

    }
    private  void checkGalleryPermission(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PICK_IMAGE);
            return;
        }
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, PICK_IMAGE);
    }
    public void  gotoaccountinfo(View v){
        Intent intent=new Intent(ProfileActivity.this,AccountInfoActivity.class);
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
    
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK)
            if (requestCode == PICK_IMAGE) {
                Uri selectedImage = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                    profile_image.setImageBitmap(bitmap);
                } catch (IOException e) {
                    Log.i("TAG", "Some exception " + e);
                }
            }
    }

    public void onPictureClicked(View view) {
        checkGalleryPermission();
    }
}