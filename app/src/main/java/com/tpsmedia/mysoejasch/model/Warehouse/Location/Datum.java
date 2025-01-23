package com.tpsmedia.mysoejasch.model.Warehouse.Location;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class Datum {

    @SerializedName("UCode_lokasi")
    @Expose
    private String UCode_lokasi;

    @SerializedName("Kode_lokasi")
    @Expose
    private String Kode_lokasi;

    @SerializedName("Nama_lokasi")
    @Expose
    private String Nama_lokasi;

    @SerializedName("Capacity")
    @Expose
    private String Capacity;

    @SerializedName("Total")
    @Expose
    private Double Total;

    @SerializedName("TotalKG")
    @Expose
    private String TotalKG;


    public String getUCode_lokasi(){ return UCode_lokasi; }

    public String getKode_lokasi(){ return Kode_lokasi; }

    public String getNama_lokasi(){ return Nama_lokasi; }

    public String getCapacity(){ return Capacity; }

    public Double getTotal(){ return Total; }

    public String getTotalKG(){ return TotalKG; }




}
