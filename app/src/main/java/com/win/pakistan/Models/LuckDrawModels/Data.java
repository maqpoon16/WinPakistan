package com.win.pakistan.Models.LuckDrawModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("server_datetime")
    @Expose
    private String serverDatetime;
    @SerializedName("luckydraw")
    @Expose
    private Luckydraw luckydraw;

    public String getServerDatetime() {
        return serverDatetime;
    }

    public void setServerDatetime(String serverDatetime) {
        this.serverDatetime = serverDatetime;
    }

    public Luckydraw getLuckydraw() {
        return luckydraw;
    }

    public void setLuckydraw(Luckydraw luckydraw) {
        this.luckydraw = luckydraw;
    }

}