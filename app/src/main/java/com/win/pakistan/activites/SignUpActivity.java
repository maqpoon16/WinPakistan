package com.win.pakistan.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.win.pakistan.MVC.Implementers.SignUpImplementer;
import com.win.pakistan.MVC.Presentors.SignUpPresenter;
import com.win.pakistan.MVC.Views.SignUpView;
import com.win.pakistan.R;

import java.util.Calendar;

import static com.win.pakistan.Common.FinalStrings.snackbarLoginSuccessfull;
import static com.win.pakistan.Common.Methods.showSnackbar;

public class SignUpActivity extends AppCompatActivity implements SignUpView {
    private ConstraintLayout layout;
    private SignUpPresenter signUpPresenter;
    private EditText fullname,username,password,email,mobile,address,age;
    private TextView dateOfBirth;
    private int year, month, day;
    private Calendar calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        signUpPresenter = new SignUpImplementer(this,SignUpActivity.this);
        layout = findViewById(R.id.layout);
        fullname = (EditText)findViewById(R.id.edtname);
        username = (EditText)findViewById(R.id.edtusername);
        password = (EditText)findViewById(R.id.edtpassword);
        email = (EditText)findViewById(R.id.edtemail);
        mobile = (EditText)findViewById(R.id.edtcontact);
        address = (EditText)findViewById(R.id.edtaddress);
        age = (EditText)findViewById(R.id.edtage);
        dateOfBirth= (TextView)findViewById(R.id.dtdob);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year-20, month+1, day);
        dateOfBirth.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void alreadyaccount(View view) {

        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void ShowValidationError(String error) {

    }

    @Override
    public void ShowException(String exception) {

    }

    @Override
    public void OnSignUpSuccessfull() {
        showSnackbar(layout,3000,SignUpActivity.this, snackbarLoginSuccessfull );
        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void OnSignUpFailed(String loginFailMessage) {

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
                    showDate(arg1, arg2+1, arg3);
                    getAge(arg1,arg2+1,arg3);
                }
            };
    private void showDate(int year, int month, int day) {
        dateOfBirth.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

    public void SetDOB(View view) {
        showDialog(999);
    }
    private void getAge(int year, int month, int day){
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.set(year, month, day);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
            age--;
        }

        Integer ageInt = new Integer(age);
        String ageS = ageInt.toString();
        this.age.setText(ageS);
    }
}