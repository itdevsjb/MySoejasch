package com.tpsmedia.mysoejasch.model.Lokasi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tpsmedia.mysoejasch.model.Lokasi.Datum;
import com.tpsmedia.mysoejasch.model.Lokasi.Success;

import java.util.List;

public class Lokasirequest {


    @SerializedName("success")
    @Expose
    private com.tpsmedia.mysoejasch.model.Lokasi.Success success;

    @SerializedName("data")
    @Expose
    private List<com.tpsmedia.mysoejasch.model.Lokasi.Datum> data = null;

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


    public com.tpsmedia.mysoejasch.model.Lokasi.Success getSuccess() {
        return success;
    }

    public void setSuccess(Success success) {
        this.success = success;
    }

}
