package com.win.pakistan.Models;

public class TodaygiftDataclass {
    String giftname,luckytitle;
    int giftImage,getGiftfees,getGiftcoins,gifttime;

    public TodaygiftDataclass(String giftname, int giftImage, int getGiftfees, int getGiftcoins, int gifttime) {
        this.giftname = giftname;
        this.giftImage = giftImage;
        this.getGiftfees = getGiftfees;
        this.getGiftcoins = getGiftcoins;
        this.gifttime = gifttime;
    }

    public String getGiftname() {
        return giftname;
    }

    public void setGiftname(String giftname) {
        this.giftname = giftname;
    }

    public int getGiftImage() {
        return giftImage;
    }

    public void setGiftImage(int giftImage) {
        this.giftImage = giftImage;
    }

    public int getGetGiftfees() {
        return getGiftfees;
    }

    public void setGetGiftfees(int getGiftfees) {
        this.getGiftfees = getGiftfees;
    }

    public int getGetGiftcoins() {
        return getGiftcoins;
    }

    public void setGetGiftcoins(int getGiftcoins) {
        this.getGiftcoins = getGiftcoins;
    }

    public int getGifttime() {
        return gifttime;
    }

    public void setGifttime(int gifttime) {
        this.gifttime = gifttime;
    }
}
