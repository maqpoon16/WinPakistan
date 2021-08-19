package com.win.pakistan.MVC.Presentors;

import com.win.pakistan.Models.UserData;
import com.win.pakistan.Models.UserSignup;

public interface SignUpPresenter {
    //this fucntion called on login button and that class / activity implement this interface.
    void SignUpProcess(UserSignup data);
}
