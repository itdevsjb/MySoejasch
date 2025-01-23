package com.tpsmedia.mysoejasch.model.Approval;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("no_approval")
    @Expose
    private String no_approval;

    @SerializedName("approval_tahap")
    @Expose
    private String approval_tahap;

    @SerializedName("nama_karyawan")
    @Expose
    private String nama_karyawan;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("user_agent")
    @Expose
    private String user_agent;

    @SerializedName("latitude")
    @Expose
    private String latitude;

    @SerializedName("longitude")
    @Expose
    private String longitude;

    @SerializedName("remarks")
    @Expose
    private String remarks;

    @SerializedName("tanggal")
    @Expose
    private String tanggal;

    @SerializedName("jam")
    @Expose
    private String jam;

    public Datum(String no_approval, String approval_tahap, String nama_karyawan, String status, String user_agent, String latitude, String longitude, String remarks, String tanggal, String jam) {
        this.no_approval =no_approval;
        this.approval_tahap = approval_tahap;
        this.nama_karyawan = nama_karyawan;
        this.status = status;
        this.user_agent = user_agent;
        this.latitude = latitude;
        this.longitude = longitude;
        this.remarks = remarks;
        this.tanggal = tanggal;
        this.jam = jam;
    }


    public String getNo_approval() {
        return no_approval;
    }
    public String getApproval_tahap() {
        return approval_tahap;
    }
    public String getNama_karyawan() {
        return nama_karyawan;
    }
    public String getStatus() {
        return status;
    }
    public String getUser_agent() {
        return user_agent;
    }
    public String getLatitude() {
        return latitude;
    }
    public String getLongitude() {
        return longitude;
    }
    public String getRemarks() {
        return remarks;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getJam() {
        return jam;
    }





}
