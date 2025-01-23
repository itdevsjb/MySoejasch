package com.tpsmedia.mysoejasch.model.Warehouse.Produksi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tpsmedia.mysoejasch.model.Warehouse.Produksi.Datum;
import com.tpsmedia.mysoejasch.model.Warehouse.Produksi.Success;

import java.util.List;

public class Produksi {
    @SerializedName("success")
    @Expose
    private Success success;

    @SerializedName("data")
    @Expose
    private List<Datum> data = null;


    public List<Datum> getData() {
        return data;
    }

    public Success getSuccess() {
        return success;
    }

    public void setSuccess(Success success) {
        this.success = success;
    }
}
