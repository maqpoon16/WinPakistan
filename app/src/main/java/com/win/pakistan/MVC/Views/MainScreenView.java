package com.win.pakistan.MVC.Views;

import com.win.pakistan.Models.LuckDrawModels.LuckyDrawModel;

public interface MainScreenView {
    //This class only created for layout to implement it where they get data / errors with these methods
    void ShowException(String exception); // on any exception we get
    void ShowFailureMessage(String failureReason); // on any exception we get
    void SetTimer(String remainingTime); // on any exception we get
    void onlineLuckyDrawData(LuckyDrawModel luckyDrawModel); // on any exception we get
    void LuckyDrawWinner(String remainingTime); // on any exception we get
}
