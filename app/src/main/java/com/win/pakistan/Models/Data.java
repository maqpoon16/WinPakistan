package com.win.pakistan.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("date_time")
    @Expose
    private String dateTime;
    @SerializedName("luckydraw_name")
    @Expose
    private String luckydrawName;

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getLuckydrawName() {
        return luckydrawName;
    }

    public void setLuckydrawName(String luckydrawName) {
        this.luckydrawName = luckydrawName;
    }

}