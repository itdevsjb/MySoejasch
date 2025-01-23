package com.tpsmedia.mysoejasch.model.Warehouse.StokMutasi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tpsmedia.mysoejasch.model.Warehouse.StokMutasi.Datum;

import java.util.List;

public class StokMutasi {
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
