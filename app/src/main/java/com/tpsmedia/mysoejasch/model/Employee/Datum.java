package com.tpsmedia.mysoejasch.model.Employee;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("ucode_karyawan")
    @Expose
    private String ucode_karyawan;

    @SerializedName("nama_karyawan")
    @Expose
    private String nama_karyawan;

    @SerializedName("jabatan")
    @Expose
    private String jabatan;

    @SerializedName("foto")
    @Expose
    private String foto;

    public Datum(String ucodeKaryawan, String namaKaryawan, String jabatan, String foto) {
        this.ucode_karyawan =ucodeKaryawan;
        this.nama_karyawan = namaKaryawan;
        this.jabatan = jabatan;
        this.foto = foto;
    }


    public String getUcodekaryawan() {
        return ucode_karyawan;
    }

    public String getNamakaryawan() {
        return nama_karyawan;
    }

    public String getJabatan() {
        return jabatan;
    }

    public String getPhoto() {
        return foto;
    }





}
