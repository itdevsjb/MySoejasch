package com.tpsmedia.mysoejasch.model.CTPlanData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("Nama_Brg")
    @Expose
    private String Nama_Brg;

    @SerializedName("batch_no")
    @Expose
    private String batch_no;



    @SerializedName("qty")
    @Expose
    private String qty;

    @SerializedName("qty_std")
    @Expose
    private String qty_std;

    @SerializedName("total_scan")
    @Expose
    private String total_scan;

    @SerializedName("total_scan_std")
    @Expose
    private String total_scan_std;

    @SerializedName("sjtb")
    @Expose
    private String sjtb;




    public String getNama_Brg() {
        return Nama_Brg;
    }

    public String getBatch_no(){ return batch_no; }

    public String getQty(){ return qty; }
    public String getQtyStd(){ return qty_std; }

    public String getTotal_scan(){
        return total_scan;
    }

    public String getTotal_scan_std(){
        return total_scan_std;
    }

    public String getSjtb(){
        return sjtb;
    }



}
