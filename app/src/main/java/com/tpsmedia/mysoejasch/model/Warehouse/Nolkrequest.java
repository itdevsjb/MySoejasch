package com.tpsmedia.mysoejasch.model.Warehouse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Nolkrequest {

    @SerializedName("success")
    @Expose
    private Success success;

    @SerializedName("nomor")
    @Expose
    private String nomor;


    public String getNomor() {
        return nomor;
    }

    public com.tpsmedia.mysoejasch.model.Warehouse.Success getSuccess() {
        return success;
    }



}
