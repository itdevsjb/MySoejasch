package com.tpsmedia.mysoejasch.model.Warehouse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DatumStockinList {

    @SerializedName("ucode_stock_in")
    @Expose
    private String ucode_stock_in;

    @SerializedName("no_dok_stock_in")
    @Expose
    private String no_dok_stock_in;

    @SerializedName("nama_karyawan")
    @Expose
    private String nama_karyawan;

    @SerializedName("total_item")
    @Expose
    private String total_item;

    @SerializedName("no_referensi")
    @Expose
    private String no_referensi;

    @SerializedName("keterangan")
    @Expose
    private String keterangan;

    public String getUcode_stock_in() {
        return ucode_stock_in;
    }
    public String getNo_dok_stock_in() {
        return no_dok_stock_in;
    }
    public String getKeterangan() {
        return keterangan;
    }
    public String getNo_referensi() {
        return no_referensi;
    }
    public String getNama_karyawan() {
        return nama_karyawan;
    }
    public String getTotal_item() {
        return total_item;
    }




}
