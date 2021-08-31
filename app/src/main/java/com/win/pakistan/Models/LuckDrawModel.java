package com.win.pakistan.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class LuckDrawModel {

    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private Data data;

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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}

class Data {

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

class Luckydraw {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("luckydraw_name")
    @Expose
    private String luckydrawName;
    @SerializedName("date_time")
    @Expose
    private String dateTime;
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

class Prize {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("luckydraw_id")
    @Expose
    private String luckydrawId;
    @SerializedName("prize")
    @Expose
    private String prize;
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

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
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