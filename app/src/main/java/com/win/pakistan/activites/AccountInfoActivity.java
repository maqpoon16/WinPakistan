package com.win.pakistan.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
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
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.win.pakistan.MVC.Implementers.AccountInfoScreenImplementer;
import com.win.pakistan.MVC.Presentors.AccountInfoScreenPresenter;
import com.win.pakistan.MVC.Views.AccountInfoScreenView;
import com.win.pakistan.Models.ProfileUpdateModel;
import com.win.pakistan.Models.UserData;
import com.win.pakistan.Models.authResponse;
import com.win.pakistan.R;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.win.pakistan.Common.Methods.bitmapToBase64;
import static com.win.pakistan.Common.Methods.getAutoLogin;
import static com.win.pakistan.Common.Methods.showSnackbar;

public class AccountInfoActivity extends AppCompatActivity implements AccountInfoScreenView {
    private ConstraintLayout layout;
    private authResponse response;
    private UserData userData;
    private EditText fullname, username, password, email, mobile, address, age;
    private TextView dateOfBirth,txBase64;
    private ProgressBar progressBar;
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
        showSnackbar(layout,10000,AccountInfoActivity.this, exception );
    }

    @Override
    public void ShowFailureMessage(String failureReason) {
        showSnackbar(layout,10000,AccountInfoActivity.this, failureReason );

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

        userData = onlineProfile;
        fullname.setText(onlineProfile.getFullName());
        username.setText(onlineProfile.getUsername());
        password.setText(onlineProfile.getPassword());
        email.setText(onlineProfile.getEmail());
        mobile.setText(onlineProfile.getMobileNumber());
        address.setText(onlineProfile.getAddress());
        age.setText(onlineProfile.getAge());
        dateOfBirth.setText(onlineProfile.getDob());
        Picasso.get().load(getResources().getString(R.string.ServerBaseUrlImage)+onlineProfile.getProfileImage()).into(profile_image);
        if(response.getUser().getGender().equals(getString(R.string.male))){
            RadioButton button = (RadioButton) findViewById(R.id.rdmale);
            button.setChecked(true);
        }else {
            RadioButton button = (RadioButton) findViewById(R.id.rdfemale);
            button.setChecked(true);
        } }

    @Override
    public void onOnlineProfileUpdated(authResponse response) {
        progressBar.setVisibility(View.GONE);
        if(response.isStatus()) {
            showSnackbar(layout,10000,AccountInfoActivity.this, response.getMessage());
            startActivity(new Intent(AccountInfoActivity.this, ProfileActivity.class));
            finish();
        }else {
            showSnackbar(layout,10000,AccountInfoActivity.this, response.getMessage() );
        }
    }

    void initializeObjects(){
        response = getAutoLogin(AccountInfoActivity.this);
        accountInfoScreenPresenter = new AccountInfoScreenImplementer(this);
        accountInfoScreenPresenter.getOnlineProfile(AccountInfoActivity.this,response.getUser().getId());
        layout = findViewById(R.id.layout);
        profile_image = (CircleImageView) findViewById(R.id.profile_image);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        fullname = (EditText) findViewById(R.id.edtname);
        username = (EditText) findViewById(R.id.edtusername);
        password = (EditText) findViewById(R.id.edtpassword);
        email = (EditText) findViewById(R.id.edtemail);
        mobile = (EditText) findViewById(R.id.edtcontact);
        address = (EditText) findViewById(R.id.edtaddress);
        age = (EditText) findViewById(R.id.edtage);
        rdgender = (RadioGroup) findViewById(R.id.rdgender);
        dateOfBirth = (TextView) findViewById(R.id.dtdob);
        txBase64 = (TextView) findViewById(R.id.txBase64);
    }
    void setObjectsValues(){
        fullname.setText(response.getUser().getFullName());
        username.setText(response.getUser().getUsername());
        password.setText(response.getUser().getPassword());
        email.setText(response.getUser().getEmail());
        mobile.setText(response.getUser().getMobileNumber());
        address.setText(response.getUser().getAddress());
        age.setText(response.getUser().getAge());
        dateOfBirth.setText(response.getUser().getDob());
        Picasso.get().load(getResources().getString(R.string.ServerBaseUrlImage)+response.getUser().getProfileImage()).into(profile_image);
        if(response.getUser().getGender().equals(getString(R.string.male))){
            RadioButton button = (RadioButton) findViewById(R.id.rdmale);
            button.setChecked(true);
        }else {
            RadioButton button = (RadioButton) findViewById(R.id.rdfemale);
            button.setChecked(true);
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK)
            if (requestCode == PICK_IMAGE) {
                Uri selectedImage = data.getData();
                Picasso.get().load(selectedImage).into(profile_image);
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                    txBase64.setText(bitmapToBase64(bitmap));
                    Log.d("Img_base64",txBase64.getText().toString());
                } catch (IOException e) {
                    showSnackbar(layout,10000,AccountInfoActivity.this, e.getLocalizedMessage() );
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

    public void onUpdateButtonClicked(View view) {
        progressBar.setVisibility(View.VISIBLE);
        if(userData!=null){
            ProfileUpdateModel profile = new ProfileUpdateModel();
            profile.setId(userData.getId());
            profile.setProfileImage(userData.getProfileImage());
            profile.setFullName(fullname.getText().toString());
            profile.setUsername(username.getText().toString());
            profile.setEmail(email.getText().toString());
            profile.setMobileNumber(mobile.getText().toString());
            RadioButton radioButton = (RadioButton) findViewById(rdgender.getCheckedRadioButtonId());
            final String value =
                    radioButton
                            .getText().toString();
            profile.setGender(value);
            profile.setAge(age.getText().toString());
            profile.setDob(dateOfBirth.getText().toString());
            profile.setAddress(address.getText().toString());
            profile.setLatitude(userData.getLatitude());
            profile.setLongitude(userData.getLongitude());
            profile.setProfileImg(txBase64.getText().toString());
            accountInfoScreenPresenter.updateProfileOnline(AccountInfoActivity.this,profile);
        return;
        }
        showSnackbar(layout,10000,AccountInfoActivity.this, "User ID not found!" );
    }
}