package com.tpsmedia.mysoejasch.model.Warehouse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DatumStockoutList {

    @SerializedName("ucode_stock_out")
    @Expose
    private String ucode_stock_out;

    @SerializedName("no_dok_stock_out")
    @Expose
    private String no_dok_stock_out;

    @SerializedName("no_referensi")
    @Expose
    private String no_referensi;

    @SerializedName("keterangan")
    @Expose
    private String keterangan;

    @SerializedName("nama_karyawan")
    @Expose
    private String nama_karyawan;

    @SerializedName("total_item")
    @Expose
    private String total_item;

    @SerializedName("lokasi_asal")
    @Expose
    private String lokasi_asal;

    @SerializedName("lokasi_tujuan")
    @Expose
    private String lokasi_tujuan;

    public String getUcode_stock_out() {
        return ucode_stock_out;
    }
    public String getNo_dok_stock_out() {
        return no_dok_stock_out;
    }
    public String getKeterangan() {
        return keterangan;
    }

    public String getLokasiAsal() {
        return lokasi_asal;
    }

    public String getLokasiTujuan() {
        return lokasi_tujuan;
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
