package com.tpsmedia.mysoejasch.model.Warehouse.StokOpnameDetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {
    @SerializedName("Nama_Brg")
    @Expose
    private String Nama_Brg;

    @SerializedName("batch_no")
    @Expose
    private String batch_no;


    @SerializedName("tgl_expired")
    @Expose
    private String tgl_expired;

    @SerializedName("Kode_Pallet")
    @Expose
    private String Kode_Pallet;

    @SerializedName("no_urut")
    @Expose
    private String no_urut;

    @SerializedName("qty_std")
    @Expose
    private String qty_std;

    @SerializedName("created_at")
    @Expose
    private String created_at;


    public String getKode_Pallet() {
        return Kode_Pallet;
    }

    public String getNo_urut(){
        return no_urut;
    }

    public String getNama_Brg() {
        return Nama_Brg;
    }

    public String getQty_std(){
        return qty_std;
    }

    public String getCreated_at(){
        return created_at;
    }

    public String getBatch_no(){ return batch_no; }

    public String getTgl_expired(){ return tgl_expired; }
}
