package com.tpsmedia.mysoejasch.service;

import android.content.Context;
import android.content.SharedPreferences;

public class ServiceSetData {

    private SharedPreferences myPrefs;
    private static final String PREF_NAME = "data_mysoejasch_form";
    private static final String KEY_NAMA = "itempo";

    public ServiceSetData(Context context) {
        myPrefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public boolean setItemPO(String itemPO) {
//        SharedPreferences.Editor editor = myPrefs.edit();
//        editor.putString("itempo", itemPO);
//        editor.apply();
        SharedPreferences.Editor editor = myPrefs.edit();
        editor.putString("itempo", itemPO);
        editor.apply();
        return editor.commit();
    }

    public String getItemPO() {
        return myPrefs.getString(KEY_NAMA, ""); // Mengambil data nama atau default kosong jika tidak ditemukan
    }

}
