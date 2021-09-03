package com.win.pakistan.Adapters;

public class PrizDataclass {
String prizename,prizeimage;
int requiredcoins;

    public PrizDataclass(String prizename,int requiredcoins, String prizeimage) {
        this.prizename = prizename;
        this.requiredcoins = requiredcoins;
        this.prizeimage = prizeimage;
    }

    public String getPrizename() {
        return prizename;
    }

    public void setPrizename(String prizename) {
        this.prizename = prizename;
    }

    public int getRequiredcoins() {
        return requiredcoins;
    }

    public void setRequiredcoins(int requiredcoins) {
        this.requiredcoins = requiredcoins;
    }

    public String getPrizeimage() {
        return prizeimage;
    }

    public void setPrizeimage(String prizeimage) {
        this.prizeimage = prizeimage;
    }
}

