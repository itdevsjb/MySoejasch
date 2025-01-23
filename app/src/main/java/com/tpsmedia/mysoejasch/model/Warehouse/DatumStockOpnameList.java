package com.tpsmedia.mysoejasch.model.Warehouse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DatumStockOpnameList {

    @SerializedName("ucode_brg")
    @Expose
    private String ucode_brg;

    @SerializedName("Kode_brg")
    @Expose
    private String Kode_brg;

    @SerializedName("Nama_brg")
    @Expose
    private String Nama_brg;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("qty_berjalan")
    @Expose
    private String qty_berjalan;

    @SerializedName("qty_berjalan_std")
    @Expose
    private String qty_berjalan_std;


    public String getUcode_brg() {
        return ucode_brg;
    }

    public String getKode_brg() {
        return Kode_brg;
    }

    public String getNama_brg() {
        return Nama_brg;
    }

    public String getStatus() {
        return status;
    }
    public String getQty_berjalan() {
        return qty_berjalan;
    }

    public String getQty_berjalan_std() {
        return qty_berjalan_std;
    }



}
