package com.win.pakistan.Models;

public class WalletHistoryDataclass {
   String historyluckydate,historyluckyname,historypaidby,historyamount_or_coins;
   int historyimage;

    public WalletHistoryDataclass(String historyluckydate, String historyluckyname, String historypaidby, String historyamount_or_coins, int historyimage) {
        this.historyluckydate = historyluckydate;
        this.historyluckyname = historyluckyname;
        this.historypaidby = historypaidby;
        this.historyamount_or_coins = historyamount_or_coins;
        this.historyimage = historyimage;
    }

    public String getHistoryluckydate() {
        return historyluckydate;
    }

    public void setHistoryluckydate(String historyluckydate) {
        this.historyluckydate = historyluckydate;
    }

    public String getHistoryluckyname() {
        return historyluckyname;
    }

    public void setHistoryluckyname(String historyluckyname) {
        this.historyluckyname = historyluckyname;
    }

    public String getHistorypaidby() {
        return historypaidby;
    }

    public void setHistorypaidby(String historypaidby) {
        this.historypaidby = historypaidby;
    }

    public String getHistoryamount_or_coins() {
        return historyamount_or_coins;
    }

    public void setHistoryamount_or_coins(String historyamount_or_coins) {
        this.historyamount_or_coins = historyamount_or_coins;
    }

    public int getHistoryimage() {
        return historyimage;
    }

    public void setHistoryimage(int historyimage) {
        this.historyimage = historyimage;
    }
}
