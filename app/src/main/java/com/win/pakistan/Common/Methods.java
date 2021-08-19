package com.win.pakistan.Common;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.snackbar.Snackbar;
import com.win.pakistan.R;

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
}
