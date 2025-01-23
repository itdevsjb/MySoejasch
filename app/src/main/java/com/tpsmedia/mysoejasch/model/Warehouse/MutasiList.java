package com.tpsmedia.mysoejasch.model.Warehouse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import java.util.List;

public class MutasiList {

    @SerializedName("success")
    @Expose
    private Success success;

    @SerializedName("data")
    @Expose
    private List<DatumMutasi> data = null;

    public List<com.tpsmedia.mysoejasch.model.Warehouse.DatumMutasi> getData() {
        return data;
    }

    public com.tpsmedia.mysoejasch.model.Warehouse.Success getSuccess() {
        return success;
    }

    public void setSuccess(com.tpsmedia.mysoejasch.model.Warehouse.Success success) {
        this.success = success;
    }


}
