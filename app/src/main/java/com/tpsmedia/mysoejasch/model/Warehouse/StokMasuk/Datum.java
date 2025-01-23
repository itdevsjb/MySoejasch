package com.tpsmedia.mysoejasch.model.Warehouse.StokMasuk;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {
    @SerializedName("no_inbound")
    @Expose
    private String no_inbound;

    @SerializedName("ucode_inbound")
    @Expose
    private String ucode_inbound;

    @SerializedName("keterangan")
    @Expose
    private String keterangan;

    @SerializedName("tgl_inbound")
    @Expose
    private String tgl_inbound;

    @SerializedName("warna")
    @Expose
    private String warna;

    @SerializedName("nama_karyawan")
    @Expose
    private String nama_karyawan;

    @SerializedName("ucode_lokasi")
    @Expose
    private String ucode_lokasi;


    @SerializedName("jumlah_box")
    @Expose
    private String jumlah_box;

    @SerializedName("jumlah_kg")
    @Expose
    private String jumlah_kg;

    @SerializedName("ucode_jenis_terima")
    @Expose
    private String ucode_jenis_terima;





    public String getNo_inbound() {
        return no_inbound;
    }

    public String getUcode_inbound(){ return  ucode_inbound; }

    public String getUcode_jenis_terima(){ return  ucode_jenis_terima; }

    public String getKeterangan(){ return  keterangan; }

    public String getTgl_inbound(){ return  tgl_inbound; }

    public String getWarna(){ return  warna; }

    public String getNama_karyawan(){ return  nama_karyawan; }

    public String getUcode_lokasi(){ return  ucode_lokasi; }


    public String getJumlah_box(){ return  jumlah_box; }

    public String getJumlah_kg(){ return  jumlah_kg; }



}
