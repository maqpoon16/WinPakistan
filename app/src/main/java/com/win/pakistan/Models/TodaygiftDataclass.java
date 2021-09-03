package com.win.pakistan.Models;

public class TodaygiftDataclass {
    String giftname,luckytitle,giftImage,gifttime;
    int getGiftfees,getGiftcoins;

    public TodaygiftDataclass(String giftname, String giftImage, int getGiftfees, int getGiftcoins, String gifttime) {
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

    public String getGiftImage() {
        return giftImage;
    }

    public void setGiftImage(String giftImage) {
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

    public String getGifttime() {
        return gifttime;
    }

    public void setGifttime(String gifttime) {
        this.gifttime = gifttime;
    }
}
