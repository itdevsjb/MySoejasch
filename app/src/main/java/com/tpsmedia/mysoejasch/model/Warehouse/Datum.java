package com.tpsmedia.mysoejasch.model.Warehouse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("UCode_LK")
    @Expose
    private String UCode_LK;

    @SerializedName("No_LK")
    @Expose
    private String No_LK;

    @SerializedName("Tgl_LK")
    @Expose
    private String Tgl_LK;

    @SerializedName("Tgl_Periode")
    @Expose
    private String Tgl_Periode;

    @SerializedName("Pic")
    @Expose
    private String Pic;

    @SerializedName("Stat_Dok")
    @Expose
    private String Stat_Dok;

    @SerializedName("Kode_lokasi")
    @Expose
    private String Kode_lokasi;


    public String getUcodeLK() {
        return UCode_LK;
    }

    public String getNoLK() {
        return No_LK;
    }

    public String getTglLK() {
        return Tgl_LK;
    }

    public String getTglPeriode() {
        return Tgl_Periode;
    }

    public String getPIC() {
        return Pic;
    }

    public String getStatDok() {
        return Stat_Dok;
    }

    public String getKodeLokasi() {
        return Kode_lokasi;
    }



}
