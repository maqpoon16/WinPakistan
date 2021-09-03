package com.win.pakistan.MVC.Views;

import com.win.pakistan.Models.UserData;
import com.win.pakistan.Models.authResponse;

public interface AccountInfoScreenView {
    //This class only created for layout to implement it where they get data / errors with these methods
    void ShowException(String exception); // on any exception we get
    void ShowFailureMessage(String failureReason); // on any exception we get
    void SetDOB(String selectedDOB); // on any exception we get
    void SetAge(String calculatedAge); // on any exception we get
    void SetOnlineProfile(UserData onlineProfile); // on any exception we get
    void onOnlineProfileUpdated(authResponse response); // on any exception we get
}
