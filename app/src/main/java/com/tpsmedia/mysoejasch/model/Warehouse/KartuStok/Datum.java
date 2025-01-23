package com.tpsmedia.mysoejasch.model.Warehouse.KartuStok;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("Tanggal")
    @Expose
    private String Tanggal;

    @SerializedName("Type")
    @Expose
    private String Type;

    @SerializedName("Keterangan")
    @Expose
    private String Keterangan;

    @SerializedName("Qty")
    @Expose
    private String Qty;

    @SerializedName("Qty_std")
    @Expose
    private String Qty_std;

    public String getTanggal() {
        return Tanggal;
    }

    public String getType() {
        return Type;
    }

    public String getKeterangan() {
        return Keterangan;
    }

    public String getQty() {
        return Qty;
    }

    public String getQty_std() {
        return Qty_std;
    }




}
