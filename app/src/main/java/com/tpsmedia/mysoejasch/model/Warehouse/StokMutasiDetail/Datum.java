package com.tpsmedia.mysoejasch.model.Warehouse.StokMutasiDetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {
    @SerializedName("Nama_Brg")
    @Expose
    private String Nama_Brg;


    public String getNama_Brg() {
        return Nama_Brg;
    }
}
