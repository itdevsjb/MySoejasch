package com.tpsmedia.mysoejasch.model.Warehouse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponeKode {

    @SerializedName("status")
    @Expose
    private Boolean status;

    @SerializedName("last_kode")
    @Expose
    private String last_kode;

    @SerializedName("header")
    @Expose
    private String header;


    public Boolean getStatus() {
        return status;
    }

    public String getLast_kode() {
        return last_kode;
    }

    public String getLHeader() {
        return header;
    }

}
