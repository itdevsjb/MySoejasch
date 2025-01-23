package com.tpsmedia.mysoejasch.model.Purchaserequestdetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Purchaserequestdetail {

    @SerializedName("success")
    @Expose
    private Success success;

    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

    public List<com.tpsmedia.mysoejasch.model.Purchaserequestdetail.Datum> getData() {
        return data;
    }

    public Success getSuccess() {
        return success;
    }

    public void setSuccess(Success success) {
        this.success = success;
    }

}
