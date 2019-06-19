package com.sanga.schoolwash;

import com.google.gson.annotations.SerializedName;

public class FormData {

    @SerializedName("id")
    private int id;
    @SerializedName("schoolName")
    private String schoolName;
    @SerializedName("headName")
    private String headName;
    @SerializedName("sFemale")
    private String sFemale;
    @SerializedName("sMale")
    private String sMale;
    @SerializedName("mToilets")
    private String mToilets;
    @SerializedName("sToilets")
    private String sToilets;
    @SerializedName("noStaff")
    private String noStaff;
    @SerializedName("fToilets")
    private String fToilets;
    @SerializedName("taps")
    private String taps;
    @SerializedName("dustBins")
    private String dustBins;
    @SerializedName("response")
    private String response;

    public FormData(int id, String schoolName, String headName, String sFemale, String sMale, String mToilets, String sToilets, String noStaff, String fToilets, String taps, String dustBins, String response) {
        this.id = id;
        this.schoolName = schoolName;
        this.headName = headName;
        this.sFemale = sFemale;
        this.sMale = sMale;
        this.mToilets = mToilets;
        this.sToilets = sToilets;
        this.noStaff = noStaff;
        this.fToilets = fToilets;
        this.taps = taps;
        this.dustBins = dustBins;
        this.response = response;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getHeadName() {
        return headName;
    }

    public void setHeadName(String headName) {
        this.headName = headName;
    }

    public String getsFemale() {
        return sFemale;
    }

    public void setsFemale(String sFemale) {
        this.sFemale = sFemale;
    }

    public String getsMale() {
        return sMale;
    }

    public void setsMale(String sMale) {
        this.sMale = sMale;
    }

    public String getmToilets() {
        return mToilets;
    }

    public void setmToilets(String mToilets) {
        this.mToilets = mToilets;
    }

    public String getsToilets() {
        return sToilets;
    }

    public void setsToilets(String sToilets) {
        this.sToilets = sToilets;
    }

    public String getNoStaff() {
        return noStaff;
    }

    public void setNoStaff(String noStaff) {
        this.noStaff = noStaff;
    }

    public String getfToilets() {
        return fToilets;
    }

    public void setfToilets(String fToilets) {
        this.fToilets = fToilets;
    }

    public String getTaps() {
        return taps;
    }

    public void setTaps(String taps) {
        this.taps = taps;
    }

    public String getDustBins() {
        return dustBins;
    }

    public void setDustBins(String dustBins) {
        this.dustBins = dustBins;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
