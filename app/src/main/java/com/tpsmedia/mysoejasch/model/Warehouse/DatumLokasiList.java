package com.tpsmedia.mysoejasch.model.Warehouse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DatumLokasiList {

    @SerializedName("ucode_lokasi")
    @Expose
    private String ucode_lokasi;

    public String getLokasi() {
        return ucode_lokasi;
    }


}
