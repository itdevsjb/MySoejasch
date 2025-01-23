package com.tpsmedia.mysoejasch.model.Warehouse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DatumSPC {

    @SerializedName("UCode_SPC")
    @Expose
    private String UCode_SPC;

    @SerializedName("No_SPC")
    @Expose
    private String No_SPC;

    @SerializedName("Tgl_SPC")
    @Expose
    private String Tgl_SPC;

    @SerializedName("Gudang")
    @Expose
    private String Gudang;

    @SerializedName("tgl_spc_akhir")
    @Expose
    private String tgl_spc_akhir;

    @SerializedName("Stat_Approve")
    @Expose
    private String Stat_Approve;

    @SerializedName("Stat_Dok")
    @Expose
    private String Stat_Dok;

    public String getUCode_SPC() {
        return UCode_SPC;
    }
    public String getNo_SPC() {
        return No_SPC;
    }
    public String getTgl_SPC() {
        return Tgl_SPC;
    }
    public String getGudangSPC() {
        return Gudang;
    }
    public String getTglSPCAkhir() {
        return tgl_spc_akhir;
    }
    public String getStaApproveSPC() {
        return Stat_Approve;
    }
    public String getStatDok_SPC() {
        return Stat_Dok;
    }


}
