package com.tpsmedia.mysoejasch.model.Gudang;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tpsmedia.mysoejasch.model.Gudang.Success;
import com.tpsmedia.mysoejasch.model.Gudang.Datum;

import java.util.List;

public class Gudangrequest {

    @SerializedName("success")
    @Expose
    private com.tpsmedia.mysoejasch.model.Gudang.Success success;

    @SerializedName("data")
    @Expose
    private List<com.tpsmedia.mysoejasch.model.Gudang.Datum> data = null;

    private List<String> choices;

    public List<String> getChoices() {
        return choices;
    }

    public void setChoices(List<String> choices) {
        this.choices = choices;
    }


    public List<Datum> getData() {
        return data;
    }
//    public List<Datum> getData() {
//        return data;
//    }

    public com.tpsmedia.mysoejasch.model.Gudang.Success getSuccess() {
        return success;
    }

    public void setSuccess(Success success) {
        this.success = success;
    }


}
