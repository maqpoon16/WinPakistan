package com.win.pakistan.Models.LuckDrawModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Prize {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("luckydraw_id")
    @Expose
    private String luckydrawId;
    @SerializedName("cash_prize")
    @Expose
    private String cashPrize;
    @SerializedName("image")
    @Expose
    private Object image;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLuckydrawId() {
        return luckydrawId;
    }

    public void setLuckydrawId(String luckydrawId) {
        this.luckydrawId = luckydrawId;
    }

    public String getCashPrize() {
        return cashPrize;
    }

    public void setCashPrize(String cashPrize) {
        this.cashPrize = cashPrize;
    }

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
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

}