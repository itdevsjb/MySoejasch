package com.tpsmedia.mysoejasch.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VersionResponse {
    @SerializedName("status")
    @Expose
    private Boolean status;

    @SerializedName("versionCode")
    @Expose
    private Integer versionCode;

    @SerializedName("apkUrl")
    @Expose
    private String apkUrl;

    public Boolean getStatus() {
        return status;
    }
    public Integer getVersionCode() { return versionCode; }
    public String getApkUrl() { return apkUrl; }

    public void setSuccess(Boolean success) {
        this.status = status;
    }
}
