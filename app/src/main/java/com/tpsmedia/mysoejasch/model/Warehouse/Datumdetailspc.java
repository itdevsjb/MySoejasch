package com.tpsmedia.mysoejasch.model.Warehouse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datumdetailspc {


    @SerializedName("UCode_SPC")
    @Expose
    private String UCode_SPC;

    @SerializedName("Nama_brg")
    @Expose
    private String Nama_brg;

    @SerializedName("Kode_Brg")
    @Expose
    private String Kode_Brg;

    @SerializedName("Tgl_SPC")
    @Expose
    private String Tgl_SPC;

    @SerializedName("Qty")
    @Expose
    private String Qty;

    @SerializedName("Qty_std")
    @Expose
    private String Qty_std;

    @SerializedName("tgl_spc_akhir")
    @Expose
    private String tgl_spc_akhir;



    public String getNama_brg() {
        return Nama_brg;
    }

    public String getKode_Brg() {
        return Kode_Brg;
    }

    public String getQty() {
        return Qty;
    }

    public String getQty_std() {
        return Qty_std;
    }

    public String getTgl_SPC() {
        return Tgl_SPC;
    }

    public String getTgl_spc_akhir() {
        return tgl_spc_akhir;
    }




}
