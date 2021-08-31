package com.win.pakistan.MVC.Presentors;

import com.win.pakistan.Models.UserData;

public interface SignUpPresenter {
    //this fucntion called on login button and that class / activity implement this interface.
    void SignUpProcess(UserData data);
}
