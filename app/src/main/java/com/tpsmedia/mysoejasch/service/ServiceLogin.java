package com.tpsmedia.mysoejasch.service;

import android.content.Context;
import android.content.SharedPreferences;

public class ServiceLogin {

    private SharedPreferences myPrefs;
    private String nama_karyawan;
    private String ucode_karyawan;
    private String polevel;
    private String prlevel;
    private String token;
    private String downloaddata;

    public ServiceLogin(Context context) {
        myPrefs = context.getSharedPreferences("data_mysoejasch", Context.MODE_PRIVATE);
        nama_karyawan = myPrefs.getString("infouser", "");
        ucode_karyawan = myPrefs.getString("ucodeuser", "");
        prlevel = myPrefs.getString("prLevel", "");
        polevel = myPrefs.getString("poLevel", "");
        token = myPrefs.getString("token", "");
        downloaddata = myPrefs.getString("downloaddata", "");
    }

    public String getLoginName() {
        return nama_karyawan;
    }

    public String getDownloaddata() {
        return downloaddata;
    }

    public String getLoginId() {
        return ucode_karyawan;
    }

    public String getprlevel() {
        return prlevel;
    }

    public String getpolevel() {
        return polevel;
    }

    public String getToken() {
        return token;
    }
}
