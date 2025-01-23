package com.tpsmedia.mysoejasch.model.Auth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Notifikasi {

    @SerializedName("success")
    @Expose
    private Boolean success;

    @SerializedName("data")
    @Expose
    private String data;

    public Boolean getSuccess() {
        return success;
    }



}
