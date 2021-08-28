package com.win.pakistan.MVC.Presentors;

import android.app.Activity;

public interface AccountInfoScreenPresenter {
    void openCalenderForDOB(Activity context,String dob);
    void getOnlineProfile(Activity context,int id);
}
