package com.win.pakistan.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class authResponse {

    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName(value = "user" , alternate={"data"})
    @Expose
    private UserData user;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserData getUser() {
        return user;
    }

    public void setUser(UserData user) {
        this.user = user;
    }

}
