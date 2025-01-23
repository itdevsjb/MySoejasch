package com.tpsmedia.mysoejasch.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetReponseSuccess {
    @SerializedName("success")
    @Expose
    private Boolean success;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("kode")
    @Expose
    private String kode;


    public Boolean getSuccess() {
        return success;
    }
    public String getMessage() { return message; }

    public String getKode() { return kode; }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
