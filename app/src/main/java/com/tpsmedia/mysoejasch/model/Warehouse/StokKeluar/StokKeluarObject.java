package com.tpsmedia.mysoejasch.model.Warehouse.StokKeluar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tpsmedia.mysoejasch.model.Warehouse.StokKeluar.Datum;

import java.util.List;

public class StokKeluarObject {
    @SerializedName("success")
    @Expose
    private Boolean success;

    @SerializedName("data")
    @Expose
    private Datum data = null;


    public Datum getData() {
        return data;
    }

    public Boolean getSuccess() {
        return success;
    }

}
