package com.tpsmedia.mysoejasch.model.Notifikasidetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tpsmedia.mysoejasch.model.Notifikasidetail.Datum;
import com.tpsmedia.mysoejasch.model.Notifikasidetail.Success;

import java.util.List;

public class Notifikasidetail {

    @SerializedName("success")
    @Expose
    private com.tpsmedia.mysoejasch.model.Notifikasidetail.Success success;

    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

    public List<com.tpsmedia.mysoejasch.model.Notifikasidetail.Datum> getData() {
        return data;
    }

    public com.tpsmedia.mysoejasch.model.Notifikasidetail.Success getSuccess() {
        return success;
    }

    public void setSuccess(Success success) {
        this.success = success;
    }

}