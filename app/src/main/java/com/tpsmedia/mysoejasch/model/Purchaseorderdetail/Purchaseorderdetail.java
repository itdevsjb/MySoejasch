package com.tpsmedia.mysoejasch.model.Purchaseorderdetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Purchaseorderdetail {

    @SerializedName("success")
    @Expose
    private com.tpsmedia.mysoejasch.model.Purchaseorderdetail.Success success;

    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

    public List<com.tpsmedia.mysoejasch.model.Purchaseorderdetail.Datum> getData() {
        return data;
    }

    public com.tpsmedia.mysoejasch.model.Purchaseorderdetail.Success getSuccess() {
        return success;
    }

    public void setSuccess(Success success) {
        this.success = success;
    }

}
