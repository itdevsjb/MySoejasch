package com.tpsmedia.mysoejasch.model.Notifikasidetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Datum {

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("deskripsi")
    @Expose
    private String deskripsi;

    @SerializedName("waktu")
    @Expose
    private String waktu;


    public String getTitle() {
        return title;
    }
    public String getDeskripsi() {
        return deskripsi;
    }

    public String getWaktu() { return waktu; }



}
