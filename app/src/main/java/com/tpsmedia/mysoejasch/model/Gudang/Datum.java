package com.tpsmedia.mysoejasch.model.Gudang;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("UCode_gdg")
    @Expose
    private String UCode_gdg;

    @SerializedName("Kode_gdg")
    @Expose
    private String Kode_gdg;

    @SerializedName("Nama_gdg")
    @Expose
    private String Nama_gdg;

    @SerializedName("text")
    @Expose
    private String text;

    @SerializedName("value")
    @Expose
    private String value;


    public String getUcodegdg() {
        return UCode_gdg;
    }

    public String getKodegdg() {
        return Kode_gdg;
    }

    public String getNamagdg() {
        return Nama_gdg;
    }

    public String getText() {
        return text;
    }

    public String getValue() {
        return value;
    }



}
