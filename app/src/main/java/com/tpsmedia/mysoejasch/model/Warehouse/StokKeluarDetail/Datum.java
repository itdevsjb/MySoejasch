package com.tpsmedia.mysoejasch.model.Warehouse.StokKeluarDetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {
    @SerializedName("Nama_Brg")
    @Expose
    private String Nama_Brg;

    @SerializedName("batch_no")
    @Expose
    private String batch_no;


    @SerializedName("tgl_expired")
    @Expose
    private String tgl_expired;

    @SerializedName("Kode_Pallet")
    @Expose
    private String Kode_Pallet;


    public String getKode_Pallet() {
        return Kode_Pallet;
    }

    public String getNama_Brg() {
        return Nama_Brg;
    }

    public String getBatch_no(){ return batch_no; }

    public String getTgl_expired(){ return tgl_expired; }



}
