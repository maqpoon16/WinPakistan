package com.win.pakistan.MVC.Presentors;

import android.app.Activity;

import com.win.pakistan.Models.ProfileUpdateModel;
import com.win.pakistan.Models.UserData;

public interface AccountInfoScreenPresenter {
    void openCalenderForDOB(Activity context,String dob);
    void getOnlineProfile(Activity context,int id);
    void updateProfileOnline(Activity context, ProfileUpdateModel profile);
}
