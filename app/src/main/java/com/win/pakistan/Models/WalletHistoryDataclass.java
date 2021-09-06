package com.win.pakistan.Models;

public class WalletHistoryDataclass {
   String historyTimedate,historyTitle,historyPaymentBy,historyCoinOrCash;
   int historyimage;

    public WalletHistoryDataclass(String historyTimedate, String historyTitle, String historyPaymentBy, String historyCoinOrCash, int historyimage) {
        this.historyTimedate = historyTimedate;
        this.historyTitle = historyTitle;
        this.historyPaymentBy = historyPaymentBy;
        this.historyCoinOrCash = historyCoinOrCash;
        this.historyimage = historyimage;
    }

    public String getHistoryTimedate() {
        return historyTimedate;
    }

    public void setHistoryTimedate(String historyTimedate) {
        this.historyTimedate = historyTimedate;
    }

    public String getHistoryTitle() {
        return historyTitle;
    }

    public void setHistoryTitle(String historyTitle) {
        this.historyTitle = historyTitle;
    }

    public String getHistoryPaymentBy() {
        return historyPaymentBy;
    }

    public void setHistoryPaymentBy(String historyPaymentBy) {
        this.historyPaymentBy = historyPaymentBy;
    }

    public String getHistoryCoinOrCash() {
        return historyCoinOrCash;
    }

    public void setHistoryCoinOrCash(String historyCoinOrCash) {
        this.historyCoinOrCash = historyCoinOrCash;
    }

    public int getHistoryimage() {
        return historyimage;
    }

    public void setHistoryimage(int historyimage) {
        this.historyimage = historyimage;
    }
}
