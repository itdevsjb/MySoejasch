package com.tpsmedia.mysoejasch.model.Warehouse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datumdetailsjtb {

    @SerializedName("UCode_SJTB")
    @Expose
    private String UCode_SJTB;

    @SerializedName("Nama_brg")
    @Expose
    private String Nama_brg;

    @SerializedName("Kode_Brg")
    @Expose
    private String Kode_Brg;

    @SerializedName("Batch_No")
    @Expose
    private String Batch_No;

    @SerializedName("Qty")
    @Expose
    private String Qty;

    @SerializedName("Qty_std")
    @Expose
    private String Qty_std;

    @SerializedName("Tgl_Expired")
    @Expose
    private String Tgl_Expired;

    @SerializedName("Kode_Rak")
    @Expose
    private String Kode_Rak;

    @SerializedName("Kode_Pallete")
    @Expose
    private String Kode_Pallete;



    public String getNama_brg() {
        return Nama_brg;
    }

    public String getKode_Brg() {
        return Kode_Brg;
    }

    public String getBatch_No() {
        return Batch_No;
    }

    public String getQty() {
        return Qty;
    }

    public String getQty_std() {
        return Qty_std;
    }

    public String getTgl_Expired() {
        return Tgl_Expired;
    }

    public String getKode_Rak() {
        return Kode_Rak;
    }

    public String getKode_Pallete() {
        return Kode_Pallete;
    }

    public String getUCode_SJTB() {
        return UCode_SJTB;
    }


}
