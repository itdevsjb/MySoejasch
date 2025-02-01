package com.tpsmedia.mysoejasch.model.Warehouse.StokOpname;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {
    @SerializedName("no_opname")
    @Expose
    private String no_opname;

    @SerializedName("ucode_opname")
    @Expose
    private String ucode_opname;

    @SerializedName("keterangan")
    @Expose
    private String keterangan;

    @SerializedName("tgl_opname")
    @Expose
    private String tgl_opname;

    @SerializedName("nama_karyawan")
    @Expose
    private String nama_karyawan;

    @SerializedName("Nama_Pallet")
    @Expose
    private String Nama_Pallet;

    @SerializedName("ucode_gdg")
    @Expose
    private String ucode_gdg;

    @SerializedName("ucode_lokasi")
    @Expose
    private String ucode_lokasi;

    @SerializedName("ucode_pallet")
    @Expose
    private String ucode_pallet;


    public String getNo_opname() {
        return no_opname;
    }

    public String getNama_karyawan() {
        return nama_karyawan;
    }

    public String getNama_Pallet() {
        return Nama_Pallet;
    }

    public String getUcode_gdg() {
        return ucode_gdg;
    }

    public String getUcode_lokasi() {
        return ucode_lokasi;
    }

    public String getUcode_pallet() {
        return ucode_pallet;
    }

    public String getUcode_opname(){ return  ucode_opname; }

    public String getKeterangan(){ return  keterangan; }

    public String getTgl_opname(){ return  tgl_opname; }




}
