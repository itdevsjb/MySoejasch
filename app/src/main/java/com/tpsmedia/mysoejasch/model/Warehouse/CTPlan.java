package com.tpsmedia.mysoejasch.model.Warehouse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CTPlan {

    @SerializedName("total")
    @Expose
    private Integer total;

    @SerializedName("cek")
    @Expose
    private Integer cek;

    @SerializedName("ucode_ct")
    @Expose
    private String ucode_ct;

    @SerializedName("no_ct")
    @Expose
    private String no_ct;

    @SerializedName("ucode_gdg_asl")
    @Expose
    private String ucode_gdg_asl;

    @SerializedName("keterangan")
    @Expose
    private String keterangan;




    public Integer getTotal() {
        return total;
    }

    public Integer getCek() {
        return cek;
    }

    public String getUcode_ct() {
        return ucode_ct;
    }

    public  String getUcode_gdg_asl(){
        return ucode_gdg_asl;
    }

    public String getNo_ct() {
        return no_ct;
    }

    public String getKeterangan() {
        return keterangan;
    }


}
