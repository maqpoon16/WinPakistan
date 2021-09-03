package com.win.pakistan.Common;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.util.Base64;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.win.pakistan.Models.LuckDrawModels.LuckyDrawModel;
import com.win.pakistan.Models.authResponse;
import com.win.pakistan.R;

import java.io.ByteArrayOutputStream;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class Methods {

    public static void showSnackbar(ConstraintLayout coordinatorLayout, int duration, Activity context, String contextString ) {
        final Snackbar snackbar = Snackbar.make(coordinatorLayout, "", duration);
        View customSnackView = context.getLayoutInflater().inflate(R.layout.snackbar_layout, null);
        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbar.getView();
        snackbarLayout.setPadding(20, 20 , 20, 20);
        TextView bGotoWebsite = customSnackView.findViewById(R.id.btnOK);
        TextView txtMessage = customSnackView.findViewById(R.id.message);
        txtMessage.setText(contextString);
        bGotoWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Redirecting to Website", Toast.LENGTH_SHORT).show();
                snackbar.dismiss();
            }
        });
        snackbarLayout.addView(customSnackView, 0);
        snackbar.show();
    }

    //SharedPreference Methods
    public  static void setAutoLogin(Activity context, authResponse authResponse){
        SharedPreferences sharedpreferences = context.getSharedPreferences(context.getResources().getString(R.string.preferance_name), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putBoolean(context.getResources().getString(R.string.preferance_key_isEnable), authResponse.isStatus());
        Gson gson = new Gson();
        String json = gson.toJson(authResponse);
        editor.putString(context.getResources().getString(R.string.preferance_key_UserData), json);
        editor.apply();
    }

    public  static authResponse getAutoLogin(Activity context){
        authResponse Object = null;
        SharedPreferences sharedpreferences = context.getSharedPreferences(context.getResources().getString(R.string.preferance_name), Context.MODE_PRIVATE);
        if(sharedpreferences.getBoolean(context.getResources().getString(R.string.preferance_key_isEnable),false)){
            Gson gson = new Gson();
            String json = sharedpreferences.getString(context.getResources().getString(R.string.preferance_key_UserData),null);
            if(json!=null){
                Object = gson.fromJson(json, authResponse.class);
            }
        }
        return Object;
    }

    public  static boolean newUserReward(Activity context){
        SharedPreferences sharedpreferences = context.getSharedPreferences(context.getResources().getString(R.string.preferance_name), Context.MODE_PRIVATE);
        return sharedpreferences.getBoolean(context.getResources().getString(R.string.preferance_key_NewUser), false);
    }

    public  static void setNewUserReward(Activity context,boolean setValue){
        SharedPreferences sharedpreferences = context.getSharedPreferences(context.getResources().getString(R.string.preferance_name), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putBoolean(context.getResources().getString(R.string.preferance_key_NewUser), setValue);
        editor.apply();
    }

    public  static boolean getWelcomeScreenStatus(Activity context){
        SharedPreferences sharedpreferences = context.getSharedPreferences(context.getResources().getString(R.string.preferance_name), Context.MODE_PRIVATE);
        return sharedpreferences.getBoolean(context.getResources().getString(R.string.preferance_key_WelcomeScreen), true);
    }

    public  static void setWelcomeScreenOff(Activity context,boolean setValue){
        SharedPreferences sharedpreferences = context.getSharedPreferences(context.getResources().getString(R.string.preferance_name), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putBoolean(context.getResources().getString(R.string.preferance_key_WelcomeScreen), setValue);
        editor.apply();
    }

    public  static void setLuckyDrawData(Activity context, LuckyDrawModel luckyDrawModel){
        SharedPreferences sharedpreferences = context.getSharedPreferences(context.getResources().getString(R.string.preferance_name), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putBoolean(context.getResources().getString(R.string.preferance_luckyDraw_data_availble), luckyDrawModel.isStatus());
        Gson gson = new Gson();
        String json = gson.toJson(luckyDrawModel);
        editor.putString(context.getResources().getString(R.string.preferance_luckyDraw), json);
        editor.apply();
    }
    public  static LuckyDrawModel getLuckyDrawData(Activity context){
        LuckyDrawModel Object = null;
        SharedPreferences sharedpreferences = context.getSharedPreferences(context.getResources().getString(R.string.preferance_name), Context.MODE_PRIVATE);
        if(sharedpreferences.getBoolean(context.getResources().getString(R.string.preferance_luckyDraw_data_availble),false)){
            Gson gson = new Gson();
            String json = sharedpreferences.getString(context.getResources().getString(R.string.preferance_luckyDraw),null);
            if(json!=null){
                Object = gson.fromJson(json, LuckyDrawModel.class);
            }
        }
        return Object;
    }

    //SharedPreference Method Close

    public  static void closeKeyboard(Activity context){
        View view = context.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public  static boolean isNetworkConnected(Activity context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean isConnected = cm.getActiveNetworkInfo()  != null;
        if(isConnected) {
            try {
                String command = "ping -c 1 google.com";
                return (Runtime.getRuntime().exec(command).waitFor() == 0);
            } catch (Exception e) {
                return false;
            }
        }
        return  false;
    }

    public  static String bitmapToBase64(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream .toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

    public  static Bitmap base64ToBitmap(String b64) {
        byte[] imageAsBytes = Base64.decode(b64.getBytes(), Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
    }

}
