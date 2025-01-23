package com.tpsmedia.mysoejasch.model.Auth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datauser {

    @SerializedName("id_karyawan")
    @Expose
    private String id_karyawan;

    @SerializedName("ucode_karyawan")
    @Expose
    private String ucode_karyawan;

    @SerializedName("nama_karyawan")
    @Expose
    private String nama_karyawan;

    @SerializedName("jabatan")
    @Expose
    private String jabatan;

    @SerializedName("department")
    @Expose
    private String department;

    @SerializedName("tempat_lahir")
    @Expose
    private String tempat_lahir;

    @SerializedName("tanggal_lahir")
    @Expose
    private String tanggal_lahir;

    @SerializedName("alamat")
    @Expose
    private String alamat;

    @SerializedName("telepon")
    @Expose
    private String telepon;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("device")
    @Expose
    private String device;

    @SerializedName("nik")
    @Expose
    private String nik;

    @SerializedName("stat_aktif")
    @Expose
    private String stat_aktif;

    @SerializedName("sisintal_level")
    @Expose
    private String sisintal_level;

    @SerializedName("procurement_pr_level")
    @Expose
    private String procurement_pr_level;

    @SerializedName("procurement_po_level")
    @Expose
    private String procurement_po_level;

    @SerializedName("nama_jabatan")
    @Expose
    private String nama_jabatan;


    public String GetUcodeKaryawan() {
        return ucode_karyawan;
    }
    public String getNamaKaryawan() {
        return nama_karyawan;
    }



}
