package com.tpsmedia.mysoejasch.model.Auth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class UbahPassword {
    @SerializedName("success")
    @Expose
    private Boolean success;


    public Boolean getSuccess() {
        return success;
    }



}
