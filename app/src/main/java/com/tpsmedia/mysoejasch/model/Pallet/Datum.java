package com.tpsmedia.mysoejasch.model.Pallet;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("UCode_pallet")
    @Expose
    private String UCode_pallet;

    @SerializedName("Kode_pallet")
    @Expose
    private String Kode_pallet;

    @SerializedName("Nama_pallet")
    @Expose
    private String Nama_pallet;

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

    public String getUCode_pallet() {
        return UCode_pallet;
    }

    public String getKode_pallet() {
        return Kode_pallet;
    }

    public String getNama_pallet() {
        return Nama_pallet;
    }

}
