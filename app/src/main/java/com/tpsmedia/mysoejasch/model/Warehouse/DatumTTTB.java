package com.tpsmedia.mysoejasch.model.Warehouse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DatumTTTB {

    @SerializedName("UCode_TTTB")
    @Expose
    private String UCode_TTTB;

    @SerializedName("No_TTTB")
    @Expose
    private String No_TTTB;

    @SerializedName("Tgl_TTTB")
    @Expose
    private String Tgl_TTTB;

    @SerializedName("Gudang")
    @Expose
    private String Gudang;

    @SerializedName("No_SJTB")
    @Expose
    private String No_SJTB;

    @SerializedName("Stat_Dok")
    @Expose
    private String Stat_Dok;

    public String getUCode_TTTB() {
        return UCode_TTTB;
    }
    public String getNo_TTTB() {
        return No_TTTB;
    }
    public String getTgl_TTTB() {
        return Tgl_TTTB;
    }
    public String getGudangTTTB() {
        return Gudang;
    }
    public String getNoSJTBTTTB() {
        return No_SJTB;
    }
    public String getStatDok_TTTB() {
        return Stat_Dok;
    }

}
