package com.tpsmedia.mysoejasch.model.Warehouse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DatumSJTB {

    @SerializedName("UCode_SJTB")
    @Expose
    private String UCode_SJTB;

    @SerializedName("No_SJTB")
    @Expose
    private String No_SJTB;

    @SerializedName("Tgl_SJTB")
    @Expose
    private String Tgl_SJTB;

    @SerializedName("Gudang")
    @Expose
    private String Gudang;


    @SerializedName("No_SPC")
    @Expose
    private String No_SPC;

    @SerializedName("Stat_Dok")
    @Expose
    private String Stat_Dok;

    public String getUCode_SJTB() {
        return UCode_SJTB;
    }
    public String getNo_SJTB() {
        return No_SJTB;
    }
    public String getTgl_SJTB() {
        return Tgl_SJTB;
    }
    public String getGudangSJTB() {
        return Gudang;
    }
    public String getNoSPCSJTB() {
        return No_SPC;
    }
    public String getStatDok_SJTB() {
        return Stat_Dok;
    }

}
