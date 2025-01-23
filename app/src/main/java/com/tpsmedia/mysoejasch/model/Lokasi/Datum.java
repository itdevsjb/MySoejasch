package com.tpsmedia.mysoejasch.model.Lokasi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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

    @SerializedName("Ket")
    @Expose
    private String Ket;

    @SerializedName("text")
    @Expose
    private String text;

    @SerializedName("value")
    @Expose
    private String value;

    public String getText() {
        return text;
    }

    public String getValue() {
        return value;
    }


    public String getUcodelokasi() {
        return UCode_lokasi;
    }

    public String getKodelokasi() {
        return Kode_lokasi;
    }

    public String getNamalokasi() {
        return Nama_lokasi;
    }

    public String getKetlokasi() {
        return Ket;
    }

}
