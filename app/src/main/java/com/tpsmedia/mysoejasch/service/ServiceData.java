package com.tpsmedia.mysoejasch.service;

import android.content.Context;
import android.content.SharedPreferences;

public class ServiceData {

    private SharedPreferences myPrefs;
    private String itempo;
    private String Lat;
    private String Long;
    private String Device;

    private  String start_date;
    private  String end_date;
    private  String select_date;
    private  String select_type;

    private  String ucode_gdg;
    private  String ucode_lokasi;
    private  String ucode_div_tujuan;

    private  String factory_pilihan;
    private  String lokasi_pilihan;

    private  String ucode_inbound;
    private  String ucode_outbound;





    public ServiceData(Context context) {
        myPrefs = context.getSharedPreferences("data_mysoejasch_form", Context.MODE_PRIVATE);
        itempo = myPrefs.getString("itempo", "");
        Lat = myPrefs.getString("Lat", "");
        Long = myPrefs.getString("Long", "");
        Device = myPrefs.getString("Device", "");

        start_date = myPrefs.getString("start_date", "");
        end_date = myPrefs.getString("end_date", "");
        select_date = myPrefs.getString("select_date", "");

        ucode_gdg = myPrefs.getString("ucode_gdg", "");
        ucode_lokasi = myPrefs.getString("ucode_lokasi", "");

        ucode_div_tujuan = myPrefs.getString("ucode_div_tujuan", "");

        factory_pilihan = myPrefs.getString("factory_pilihan", "");
        lokasi_pilihan = myPrefs.getString("lokasi_pilihan", "");
        ucode_inbound = myPrefs.getString("ucode_inbound", "");
        ucode_outbound = myPrefs.getString("ucode_outbound", "");



    }

    public String getItempo() {
        return itempo;
    }

    public String getLat() { return Lat;}
    public String getLong() { return Long;}
    public String getDevice() { return Device;}

    public String getStart_date() { return start_date;}
    public String getEnd_date() { return end_date; }
    public String getSelect_date() { return select_date; }
    public String getSelect_type() { return select_type; }

    public String getUcode_gdg() { return ucode_gdg; }

    public String getUcode_lokasi() { return ucode_lokasi; }

    public String getUcode_div_tujuan() { return ucode_div_tujuan; }

    public String getFactory_pilihan() { return factory_pilihan; }

    public String getLokasi_pilihan() { return lokasi_pilihan; }

    public  String getUcode_inbound() { return ucode_inbound; }

    public  String getUcode_outbound() { return ucode_outbound; }


}
