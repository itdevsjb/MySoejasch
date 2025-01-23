package com.tpsmedia.mysoejasch.model.Warehouse.StokKeluar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("ucode_outbound")
    @Expose
    private String ucode_outbound;

    @SerializedName("no_outbound")
    @Expose
    private String no_outbound;

    @SerializedName("keterangan")
    @Expose
    private String keterangan;

    @SerializedName("tgl_outbound")
    @Expose
    private String tgl_outbound;

    @SerializedName("warna")
    @Expose
    private String warna;

    @SerializedName("nama_karyawan")
    @Expose
    private String nama_karyawan;

    @SerializedName("ucode_lokasi")
    @Expose
    private String ucode_lokasi;

    @SerializedName("ucode_ct")
    @Expose
    private String ucode_ct;

    @SerializedName("jumlah_kg")
    @Expose
    private String jumlah_kg;

    @SerializedName("jumlah_box")
    @Expose
    private String jumlah_box;




    public String getUcode_outbound() {
        return ucode_outbound;
    }

    public String getNo_outbound() {
        return no_outbound;
    }

    public String getKeterangan(){ return  keterangan; }

    public String getTgl_outbound(){ return  tgl_outbound; }

    public String getWarna(){ return  warna; }

    public String getNama_karyawan(){ return  nama_karyawan; }

    public String getUcode_lokasi(){ return  ucode_lokasi; }

    public String getUcode_ct(){ return  ucode_ct; }

    public String getJumlah_box(){ return  jumlah_box; }

    public String getJumlah_kg(){ return  jumlah_kg; }



}
