package com.win.pakistan.MVC.Views;

public interface LoginView {
    //This class only created for layout to implement it where they get data / errors with these methods
    void ShowValidationError(String error); // this used for validation error
    void ShowException(String exception); // on any exception we get
    void OnloginSuccessfull(); // on login successfull
    void OnloginFailed(String loginFailMessage);//on login failed and returned message
}
