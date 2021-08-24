package com.win.pakistan.Models;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TimeSpan {

    @SerializedName("abbreviation")
    @Expose
    private String abbreviation;
    @SerializedName("client_ip")
    @Expose
    private String clientIp;
    @SerializedName("datetime")
    @Expose
    private String datetime;
    @SerializedName("day_of_week")
    @Expose
    private int dayOfWeek;
    @SerializedName("day_of_year")
    @Expose
    private int dayOfYear;
    @SerializedName("dst")
    @Expose
    private boolean dst;
    @SerializedName("dst_from")
    @Expose
    private Object dstFrom;
    @SerializedName("dst_offset")
    @Expose
    private int dstOffset;
    @SerializedName("dst_until")
    @Expose
    private Object dstUntil;
    @SerializedName("raw_offset")
    @Expose
    private int rawOffset;
    @SerializedName("timezone")
    @Expose
    private String timezone;
    @SerializedName("unixtime")
    @Expose
    private int unixtime;
    @SerializedName("utc_datetime")
    @Expose
    private String utcDatetime;
    @SerializedName("utc_offset")
    @Expose
    private String utcOffset;
    @SerializedName("week_number")
    @Expose
    private int weekNumber;

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getDayOfYear() {
        return dayOfYear;
    }

    public void setDayOfYear(int dayOfYear) {
        this.dayOfYear = dayOfYear;
    }

    public boolean isDst() {
        return dst;
    }

    public void setDst(boolean dst) {
        this.dst = dst;
    }

    public Object getDstFrom() {
        return dstFrom;
    }

    public void setDstFrom(Object dstFrom) {
        this.dstFrom = dstFrom;
    }

    public int getDstOffset() {
        return dstOffset;
    }

    public void setDstOffset(int dstOffset) {
        this.dstOffset = dstOffset;
    }

    public Object getDstUntil() {
        return dstUntil;
    }

    public void setDstUntil(Object dstUntil) {
        this.dstUntil = dstUntil;
    }

    public int getRawOffset() {
        return rawOffset;
    }

    public void setRawOffset(int rawOffset) {
        this.rawOffset = rawOffset;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public int getUnixtime() {
        return unixtime;
    }

    public void setUnixtime(int unixtime) {
        this.unixtime = unixtime;
    }

    public String getUtcDatetime() {
        return utcDatetime;
    }

    public void setUtcDatetime(String utcDatetime) {
        this.utcDatetime = utcDatetime;
    }

    public String getUtcOffset() {
        return utcOffset;
    }

    public void setUtcOffset(String utcOffset) {
        this.utcOffset = utcOffset;
    }

    public int getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(int weekNumber) {
        this.weekNumber = weekNumber;
    }

}