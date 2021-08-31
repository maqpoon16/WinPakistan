package com.win.pakistan.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.win.pakistan.MVC.Implementers.AccountInfoScreenImplementer;
import com.win.pakistan.MVC.Presentors.AccountInfoScreenPresenter;
import com.win.pakistan.MVC.Views.AccountInfoScreenView;
import com.win.pakistan.Models.UserData;
import com.win.pakistan.Models.authResponse;
import com.win.pakistan.R;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.win.pakistan.Common.Methods.getAutoLogin;

public class AccountInfoActivity extends AppCompatActivity implements AccountInfoScreenView {
    private authResponse response;
    private EditText fullname, username, password, email, mobile, address, age;
    private TextView dateOfBirth;
    private RadioGroup rdgender;
    private AccountInfoScreenPresenter accountInfoScreenPresenter;
    private static final int PICK_IMAGE = 1;
    private CircleImageView profile_image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_info);
        initializeObjects();
        setObjectsValues();
    }
    public void SetDOB(View view) {
        accountInfoScreenPresenter.openCalenderForDOB(AccountInfoActivity.this,response.getUser().getDob());
    }

    //MVC Section
    @SuppressLint("ShowToast")
    @Override
    public void ShowException(String exception) {
        Toast.makeText(this,exception,Toast.LENGTH_LONG);
    }

    @Override
    public void ShowFailureMessage(String failureReason) {
        Toast.makeText(this,failureReason,Toast.LENGTH_LONG);

    }

    @Override
    public void SetDOB(String selectedDOB) {
        dateOfBirth.setText(selectedDOB);
    }

    @Override
    public void SetAge(String calculatedAge) {
        this.age.setText(calculatedAge);

    }

    @Override
    public void SetOnlineProfile(UserData onlineProfile) {
        fullname.setText(onlineProfile.getFullName());
        username.setText(onlineProfile.getUsername());
        email.setText(onlineProfile.getEmail());
        mobile.setText(onlineProfile.getMobileNumber());
        address.setText(onlineProfile.getAddress());
        age.setText(onlineProfile.getAge());
        dateOfBirth.setText(onlineProfile.getDob());
    }

    void initializeObjects(){
        response = getAutoLogin(AccountInfoActivity.this);
        accountInfoScreenPresenter = new AccountInfoScreenImplementer(this);
        accountInfoScreenPresenter.getOnlineProfile(AccountInfoActivity.this,response.getUser().getId());
        profile_image = (CircleImageView) findViewById(R.id.profile_image);
        fullname = (EditText) findViewById(R.id.edtname);
        username = (EditText) findViewById(R.id.edtusername);
        password = (EditText) findViewById(R.id.edtpassword);
        email = (EditText) findViewById(R.id.edtemail);
        mobile = (EditText) findViewById(R.id.edtcontact);
        address = (EditText) findViewById(R.id.edtaddress);
        age = (EditText) findViewById(R.id.edtage);
        rdgender = (RadioGroup) findViewById(R.id.rdgender);
        dateOfBirth = (TextView) findViewById(R.id.dtdob);
    }
    void setObjectsValues(){
        fullname.setText(response.getUser().getFullName());
        username.setText(response.getUser().getUsername());
        email.setText(response.getUser().getEmail());
        mobile.setText(response.getUser().getMobileNumber());
        address.setText(response.getUser().getAddress());
        age.setText(response.getUser().getAge());
        dateOfBirth.setText(response.getUser().getDob());
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

    private  void checkGalleryPermission(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PICK_IMAGE);
            return;
        }
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, PICK_IMAGE);
    }

    public void onClickedBackButton(View view) {
        startActivity(new Intent(AccountInfoActivity.this, ProfileActivity.class));
        finish();
    }
}