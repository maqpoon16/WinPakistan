package com.win.pakistan.Adapters;

public class PrizDataclass {
String prizename;
int requiredcoins,prizeimage;

    public PrizDataclass(String prizename,int requiredcoins, int prizeimage) {
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

    public int getPrizeimage() {
        return prizeimage;
    }

    public void setPrizeimage(int prizeimage) {
        this.prizeimage = prizeimage;
    }
}

