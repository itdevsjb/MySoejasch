package com.tpsmedia.mysoejasch.model.Auth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Totalnotif {

    @SerializedName("success")
    @Expose
    private Boolean success;

    @SerializedName("totalpo")
    @Expose
    private String totalpo;

    @SerializedName("totalpr")
    @Expose
    private String totalpr;

    public Boolean getSuccess() {
        return success;
    }

    public String getTotalpo() {
        return totalpo;
    }
    public String getTotalpr() {
        return totalpr;
    }

}
