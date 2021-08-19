package com.win.pakistan.MVC.Presentors;

import android.app.Activity;

public interface LoginPresenter {
    //this fucntion called on login button and that class / activity implement this interface.
    void LoginProcess(String user, String password, Activity context);
}
