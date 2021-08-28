package com.win.pakistan.Common;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.win.pakistan.Models.authResponse;
import com.win.pakistan.R;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class Methods {
    public static void showSnackbar(ConstraintLayout coordinatorLayout, int duration, Activity context, String contextString ) { // Create the Snackbar
        // create an instance of the snackbar
        final Snackbar snackbar = Snackbar.make(coordinatorLayout, "", duration);

        // inflate the custom_snackbar_view created previously
        View customSnackView = context.getLayoutInflater().inflate(R.layout.snackbar_layout, null);

        // set the background of the default snackbar as transparent
        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);

        // now change the layout of the snackbar
        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbar.getView();

        // set padding of the all corners as 0
        snackbarLayout.setPadding(20, 20 , 20, 20);

        // register the button from the custom_snackbar_view layout file
        TextView bGotoWebsite = customSnackView.findViewById(R.id.btnOK);
        TextView txtMessage = customSnackView.findViewById(R.id.message);
        txtMessage.setText(contextString);
        // now handle the same button with onClickListener
        bGotoWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Redirecting to Website", Toast.LENGTH_SHORT).show();
                snackbar.dismiss();
            }
        });

        // add the custom snack bar layout to snackbar layout
        snackbarLayout.addView(customSnackView, 0);

        snackbar.show();
    }
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

    public  static void closeKeyboard(Activity context){
        View view = context.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
