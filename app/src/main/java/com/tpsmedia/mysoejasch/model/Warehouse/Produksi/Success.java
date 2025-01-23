package com.tpsmedia.mysoejasch.model.Warehouse.Produksi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tpsmedia.mysoejasch.model.Warehouse.Produksi.Datum;

import java.util.List;

public class Success {
    @SerializedName("data")
    @Expose
    private List<com.tpsmedia.mysoejasch.model.Warehouse.Produksi.Datum> data = null;

    public List<com.tpsmedia.mysoejasch.model.Warehouse.Produksi.Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }
}
