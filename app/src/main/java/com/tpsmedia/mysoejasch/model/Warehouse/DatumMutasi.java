package com.tpsmedia.mysoejasch.model.Warehouse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DatumMutasi {

    @SerializedName("UCode_MTB")
    @Expose
    private String UCode_MTB;

    @SerializedName("No_MTB")
    @Expose
    private String No_MTB;

    @SerializedName("Gudang")
    @Expose
    private String Gudang;

    @SerializedName("Tgl_MTB")
    @Expose
    private String Tgl_MTB;

    @SerializedName("TotalItem")
    @Expose
    private String TotalItem;

    public String getUCodeMTB() {
        return UCode_MTB;
    }

    public String getTglMTB() {
        return Tgl_MTB;
    }

    public String getNoMTB() {
        return No_MTB;
    }

    public String getGudangMTB() {
        return Gudang;
    }

    public String getTotalItemMTB() {
        return TotalItem;
    }


}
