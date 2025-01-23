package com.tpsmedia.mysoejasch.model.Warehouse.StokMasuk;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tpsmedia.mysoejasch.model.Warehouse.StokMasuk.Datum;
import com.tpsmedia.mysoejasch.model.Warehouse.StokMasuk.Success;

import java.util.List;

public class StokMasuk {
    @SerializedName("success")
    @Expose
    private Boolean success;

    @SerializedName("data")
    @Expose
    private List<com.tpsmedia.mysoejasch.model.Warehouse.StokMasuk.Datum> data = null;

    @SerializedName("result")
    @Expose
    private List<com.tpsmedia.mysoejasch.model.Warehouse.StokMasuk.Datum> result = null;


    public List<Datum> getData() {
        return data;
    }

    public List<Datum> getResult() {
        return result;
    }

    public Boolean getSuccess() {
        return success;
    }


}
