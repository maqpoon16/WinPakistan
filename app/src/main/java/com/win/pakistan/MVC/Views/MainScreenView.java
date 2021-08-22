package com.win.pakistan.MVC.Views;

public interface MainScreenView {
    //This class only created for layout to implement it where they get data / errors with these methods
    void ShowException(String exception); // on any exception we get
    void SetTimer(String remainingTime); // on any exception we get
    void LuckyDrawWinner(String remainingTime); // on any exception we get
}
