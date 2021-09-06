package com.win.pakistan.activites;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.win.pakistan.MVC.Implementers.SignUpImplementer;
import com.win.pakistan.MVC.Presentors.SignUpPresenter;
import com.win.pakistan.MVC.Views.SignUpView;
import com.win.pakistan.Models.UserData;
import com.win.pakistan.R;

import java.util.Calendar;

import static com.win.pakistan.Common.Methods.showSnackbar;

public class SignUpActivity extends AppCompatActivity implements SignUpView {
    private ConstraintLayout layout;
    private SignUpPresenter signUpPresenter;
    private EditText fullname, username, password, email, mobile, address, age;
    private TextView dateOfBirth;
    private RadioGroup rdgender;
    private int year, month, day;
    private Calendar calendar;

    private LocationManager locationManager;
    private  Location location;
    private String provider;
    private MyLocationListener mylistener;
    private Criteria criteria;
    private static final int REQUEST_LOCATION = 1;
    private LottieAnimationView loading;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        signUpPresenter = new SignUpImplementer(this, SignUpActivity.this);
        GetLocation();
        layout = findViewById(R.id.layout);
        fullname = (EditText) findViewById(R.id.edtname);
        username = (EditText) findViewById(R.id.edtusername);
        password = (EditText) findViewById(R.id.edtpassword);
        email = (EditText) findViewById(R.id.edtemail);
        mobile = (EditText) findViewById(R.id.edtcontact);
        address = (EditText) findViewById(R.id.edtaddress);
        age = (EditText) findViewById(R.id.edtage);
        rdgender = (RadioGroup) findViewById(R.id.rdgender);
        dateOfBirth = (TextView) findViewById(R.id.dtdob);
        loading = findViewById(R.id.animatedLoading);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year - 20, month + 1, day);

    }

    public void alreadyaccount(View view) {

        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void ShowValidationError(String error) {
        loading.setVisibility(View.GONE);
        showSnackbar(layout, 6000, SignUpActivity.this, error);

    }

    @Override
    public void ShowException(String exception) {
        loading.setVisibility(View.GONE);
        showSnackbar(layout, 6000, SignUpActivity.this, exception);

    }

    @Override
    public void OnSignUpSuccessfull() {
        loading.setVisibility(View.GONE);
        showSnackbar(layout, 3000, SignUpActivity.this, getResources().getString(R.string.snackbarLoginSuccessfull));
        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    public void OnSignUpFailed(String loginFailMessage) {
        loading.setVisibility(View.GONE);
        showSnackbar(layout, 6000, SignUpActivity.this, loginFailMessage);

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    showDate(arg1, arg2 + 1, arg3);
                    getAge(arg1, arg2 + 1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        dateOfBirth.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

    public void SetDOB(View view) {
        showDialog(999);
    }

    private void getAge(int year, int month, int day) {
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.set(year, month, day);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }

        Integer ageInt = new Integer(age);
        String ageS = ageInt.toString();
        this.age.setText(ageS);
    }

    public void OnClickedCreateAccount(View view) {
        loading.setVisibility(View.VISIBLE);
        UserData model = new UserData();
        model.setFullName(fullname.getText().toString());
        model.setPassword(password.getText().toString());
        model.setMobileNumber(mobile.getText().toString());
        model.setEmail(email.getText().toString());
        model.setDob(dateOfBirth.getText().toString());
        model.setAddress(address.getText().toString());
        if (location != null) {
            model.setLatitude(String.valueOf(location.getLatitude()));
            model.setLongitude(String.valueOf(location.getLongitude()));
        }
//        RadioButton radioButton = (RadioButton) findViewById(rdgender.getCheckedRadioButtonId());
//        final String value =
//                radioButton
//                        .getText().toString();
//        model.setGender(value);
//        model.setAge(age.getText().toString());
        signUpPresenter.SignUpProcess(model);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    void GetLocation() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // Define the criteria how to select the location provider
        criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);   //default

        // user defines the criteria

        criteria.setCostAllowed(false);
        // get the best provider depending on the criteria
        provider = locationManager.getBestProvider(criteria, false);

        // the last known location of this provider
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.checkSelfPermission(
                    SignUpActivity.this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    SignUpActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
            }
            return;
        }
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            OnGPS();
            return;
        }
        location = locationManager.getLastKnownLocation(provider);

        mylistener = new MyLocationListener();

        if (location != null) {
            mylistener.onLocationChanged(location);
        }

        // location updates: at least 1 meter and 200millsecs change
        locationManager.requestLocationUpdates(provider, 200, 1, mylistener);
        String a= null;
        if (location != null) {
            a = ""+location.getLatitude();
        }
    }
    class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            // Initialize the location fields



            Toast.makeText(SignUpActivity.this,  ""+location.getLatitude()+location.getLongitude(),
                    Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Toast.makeText(SignUpActivity.this, provider + "'s status changed to "+status +"!",
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onProviderEnabled(String provider) {
            Toast.makeText(SignUpActivity.this, "Provider " + provider + " enabled!",
                    Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onProviderDisabled(String provider) {
            Toast.makeText(SignUpActivity.this, "Provider " + provider + " disabled!",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void OnGPS() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("Yes", new  DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
