package com.win.pakistan.Models.LuckDrawModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Luckydraw {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("luckydraw_name")
    @Expose
    private String luckydrawName;
    @SerializedName("date_time")
    @Expose
    private String dateTime;
    @SerializedName("coins_required")
    @Expose
    private String coinsRequired;
    @SerializedName("cash_reqiured")
    @Expose
    private String cashReqiured;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("prize_list")
    @Expose
    private List<Prize> prizeList = null;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLuckydrawName() {
        return luckydrawName;
    }

    public void setLuckydrawName(String luckydrawName) {
        this.luckydrawName = luckydrawName;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getCoinsRequired() {
        return coinsRequired;
    }

    public void setCoinsRequired(String coinsRequired) {
        this.coinsRequired = coinsRequired;
    }

    public String getCashReqiured() {
        return cashReqiured;
    }

    public void setCashReqiured(String cashReqiured) {
        this.cashReqiured = cashReqiured;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Prize> getPrizeList() {
        return prizeList;
    }

    public void setPrizeList(List<Prize> prizeList) {
        this.prizeList = prizeList;
    }

}