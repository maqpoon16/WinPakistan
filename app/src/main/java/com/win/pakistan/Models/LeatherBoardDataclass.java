package com.win.pakistan.Models;

public class LeatherBoardDataclass {
   String leadername,leaderamount,leaderdate,leaderprize;
   int leaderimage;

    public LeatherBoardDataclass(String leadername, String leaderamount, String leaderdate, String leaderprize, int leaderimage) {
        this.leadername = leadername;
        this.leaderamount = leaderamount;
        this.leaderdate = leaderdate;
        this.leaderprize = leaderprize;
        this.leaderimage = leaderimage;

    }

    public String getLeadername() {
        return leadername;
    }

    public void setLeadername(String leadername) {
        this.leadername = leadername;
    }

    public String getLeaderamount() {
        return leaderamount;
    }

    public void setLeaderamount(String leaderamount) {
        this.leaderamount = leaderamount;
    }

    public String getLeaderdate() {
        return leaderdate;
    }

    public void setLeaderdate(String leaderdate) {
        this.leaderdate = leaderdate;
    }

    public String getLeaderprize() {
        return leaderprize;
    }

    public void setLeaderprize(String leaderprize) {
        this.leaderprize = leaderprize;
    }

    public int getLeaderimage() {
        return leaderimage;
    }

    public void setLeaderimage(int leaderimage) {
        this.leaderimage = leaderimage;
    }
}
