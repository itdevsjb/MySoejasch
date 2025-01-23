package com.tpsmedia.mysoejasch.model.Warehouse.StokKeluar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tpsmedia.mysoejasch.model.Warehouse.StokKeluar.Datum;

import java.util.List;

public class StokKeluar {
    @SerializedName("success")
    @Expose
    private Boolean success;

    @SerializedName("data")
    @Expose
    private List<Datum> data = null;


    public List<Datum> getData() {
        return data;
    }

    public Boolean getSuccess() {
        return success;
    }

}
