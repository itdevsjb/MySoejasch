package com.tpsmedia.mysoejasch.model.Purchaseorder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SingleDataPO {
    @SerializedName("data")
    @Expose
    private Datum data;

    public Datum getData() {
        return data;
    }

    public void setData(Datum data) {
        this.data = data;
    }
}
