package com.tpsmedia.mysoejasch.model.Warehouse.Pallet;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("UCode_Pallet")
    @Expose
    private String UCode_Pallet;

    @SerializedName("Kode_Pallet")
    @Expose
    private String Kode_Pallet;

    @SerializedName("Nama_Pallet")
    @Expose
    private String Nama_Pallet;

    @SerializedName("Lokasi")
    @Expose
    private String Lokasi;

    @SerializedName("Divisi")
    @Expose
    private String Divisi;

    @SerializedName("Stat")
    @Expose
    private String Stat;

    @SerializedName("Ket")
    @Expose
    private String Ket;

    @SerializedName("Capacity")
    @Expose
    private String Capacity;

    @SerializedName("TypeCapacity")
    @Expose
    private String TypeCapacity;

    @SerializedName("Tgl_Jam_Buat")
    @Expose
    private String Tgl_Jam_Buat;

    @SerializedName("Pembuat")
    @Expose
    private String Pembuat;

    @SerializedName("Total")
    @Expose
    private Integer Total;

    @SerializedName("TotalKG")
    @Expose
    private String TotalKG;

    public Datum(String uCodePallet, String kodePallet, String namaPallet, String lokasi, String divisi, String pembuat, String stat, String ket, String capacity, String typeCapacity, String tglJamBuat, Integer Total, String TotalKG) {
        this.UCode_Pallet = uCodePallet;
        this.Kode_Pallet = kodePallet;
        this.Nama_Pallet = namaPallet;
        this.Lokasi = lokasi;
        this.Divisi = divisi;
        this.Pembuat = pembuat;
        this.Stat = stat;
        this.Ket = ket;
        this.Capacity = capacity;
        this.TypeCapacity = typeCapacity;
        this.Tgl_Jam_Buat = tglJamBuat;
        this.Total = Total;
        this.TotalKG = TotalKG;
    }

    public String getUCode_Pallet(){ return UCode_Pallet; }

    public  String getKode_Pallet(){ return Kode_Pallet; }

    public String getNama_Pallet(){ return Nama_Pallet; }

    public String getLokasi(){ return Lokasi; }

    public String getDivisi(){ return Divisi; }

    public String getStat(){ return Stat; }

    public String getKet(){ return Ket; }

    public String getCapacity() {  return Capacity; }

    public String getTypeCapacity(){ return TypeCapacity; }

    public String getPembuat(){ return Pembuat; }

    public String getTgl_Jam_Buat(){ return Tgl_Jam_Buat; }

    public Integer getTotal(){ return Total; }

    public String getTotalKG(){ return TotalKG; }


}
