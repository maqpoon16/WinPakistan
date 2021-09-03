package com.win.pakistan.Common.Recievers;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import com.win.pakistan.R;
import static com.win.pakistan.Common.Methods.isNetworkConnected;

public class ConnectivityReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, final Intent intent) {
        boolean status = isNetworkConnected((Activity)context);
        String statusMessage = context.getResources().getString(R.string.no_internet_Message);
        Log.d("network", String.valueOf(status));
        if(status) {
            statusMessage="Internet Connected!";
        }
        Toast.makeText(context, statusMessage, Toast.LENGTH_LONG).show();
    }
}