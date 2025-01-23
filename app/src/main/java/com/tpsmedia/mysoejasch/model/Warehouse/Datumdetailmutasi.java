package com.tpsmedia.mysoejasch.model.Warehouse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datumdetailmutasi {

    @SerializedName("UCode_MTB")
    @Expose
    private String UCode_MTB;

    @SerializedName("Nama_brg")
    @Expose
    private String Nama_brg;

    @SerializedName("Kode_Brg")
    @Expose
    private String Kode_Brg;

    @SerializedName("batch_no")
    @Expose
    private String batch_no;

    @SerializedName("Qty")
    @Expose
    private String Qty;

    @SerializedName("Qty_std")
    @Expose
    private String Qty_std;

    @SerializedName("tgl_expired")
    @Expose
    private String Tgl_Expired;

    @SerializedName("Asal")
    @Expose
    private String Asal;

    @SerializedName("Tujuan")
    @Expose
    private String Tujuan;



    public String getNama_brg() {
        return Nama_brg;
    }

    public String getKode_Brg() {
        return Kode_Brg;
    }

    public String getBatch_No() {
        return batch_no;
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

    public String getAsal() {
        return Asal;
    }

    public String getTujuan() {
        return Tujuan;
    }

    public String getUCode_MTB() {
        return UCode_MTB;
    }




}
