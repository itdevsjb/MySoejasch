package com.tpsmedia.mysoejasch.model.Divisi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tpsmedia.mysoejasch.model.Divisi.Datum;
import com.tpsmedia.mysoejasch.model.Divisi.Success;

import java.util.List;

public class Divisirequest {

    @SerializedName("success")
    @Expose
    private com.tpsmedia.mysoejasch.model.Divisi.Success success;

    @SerializedName("data")
    @Expose
    private List<com.tpsmedia.mysoejasch.model.Divisi.Datum> data = null;


    public List<Datum> getData() {
        return data;
    }

    public com.tpsmedia.mysoejasch.model.Divisi.Success getSuccess() {
        return success;
    }

    public void setSuccess(Success success) {
        this.success = success;
    }




}
