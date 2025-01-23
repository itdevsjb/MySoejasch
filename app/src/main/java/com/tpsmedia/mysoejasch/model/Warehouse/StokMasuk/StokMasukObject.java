package com.tpsmedia.mysoejasch.model.Warehouse.StokMasuk;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tpsmedia.mysoejasch.model.Warehouse.StokMasuk.Datum;
import com.tpsmedia.mysoejasch.model.Warehouse.StokMasuk.Success;

import java.util.List;

public class StokMasukObject {
    @SerializedName("success")
    @Expose
    private Boolean success;

    @SerializedName("data")
    @Expose
    private Datum data = null;

    @SerializedName("result")
    @Expose
    private Datum result = null;


    public Datum getData() {
        return data;
    }

    public Datum getResult() {
        return result;
    }

    public Boolean getSuccess() {
        return success;
    }


}
