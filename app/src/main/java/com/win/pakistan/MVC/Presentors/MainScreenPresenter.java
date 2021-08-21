package com.win.pakistan.MVC.Presentors;

import android.app.Activity;
import android.widget.TextView;

public interface MainScreenPresenter {
    //this fucntion called on login button and that class / activity implement this interface.
    void TimerProcess(Activity context, TextView timerLabel);
}
