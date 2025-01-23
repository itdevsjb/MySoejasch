package com.tpsmedia.mysoejasch.model.Warehouse.StokMutasi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("ucode_mutasi")
    @Expose
    private String ucode_mutasi;

    @SerializedName("no_mutasi")
    @Expose
    private String no_mutasi;

    @SerializedName("keterangan")
    @Expose
    private String keterangan;

    @SerializedName("tgl_mutasi")
    @Expose
    private String tgl_mutasi;


    public String getUcode_mutasi(){ return ucode_mutasi; }

    public String getNo_mutasi() {
        return no_mutasi;
    }

    public String getKeterangan(){ return  keterangan; }

    public String getTgl_mutasi(){ return tgl_mutasi; }

}
