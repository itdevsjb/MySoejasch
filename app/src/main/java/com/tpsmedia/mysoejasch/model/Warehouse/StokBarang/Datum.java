package com.tpsmedia.mysoejasch.model.Warehouse.StokBarang;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("Nama_Brg")
    @Expose
    private String Nama_Brg;

    @SerializedName("Nama_barang")
    @Expose
    private String Nama_barang;

    @SerializedName("Batch_No")
    @Expose
    private String Batch_No;

    @SerializedName("Tgl_Expired")
    @Expose
    private String Tgl_Expired;

    @SerializedName("Tanggal_Exp")
    @Expose
    private String Tanggal_Exp;

    @SerializedName("Kode_Pallet")
    @Expose
    private String Kode_Pallet;

    @SerializedName("Qty_Std")
    @Expose
    private String Qty_Std;

    @SerializedName("Stock")
    @Expose
    private String Stock;

    @SerializedName("satuan")
    @Expose
    private String satuan;

    @SerializedName("Stock_std")
    @Expose
    private String Stock_std;

    @SerializedName("Karton")
    @Expose
    private String Karton;

    @SerializedName("TotalKG")
    @Expose
    private String TotalKG;



    public String getNama_Brg() {
        return Nama_Brg;
    }

    public String getNama_barang() {
        return Nama_barang;
    }

    public String getBatch_No() {
        return Batch_No;
    }

    public String getSatuan() {
        return satuan;
    }

    public String getTgl_Expired() {
        return Tgl_Expired;
    }

    public String getStock() {
        return Stock;
    }

    public String getStock_std() {
        return Stock_std;
    }

    public String getTanggal_Exp() {
        return Tanggal_Exp;
    }

    public String getKode_Pallet() {
        return Kode_Pallet;
    }

    public String getQty_Std() {
        return Qty_Std;
    }

    public String getKarton() {
        return Karton;
    }


    public String getTotalKG(){
        return TotalKG;
    }








}
