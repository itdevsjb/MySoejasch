package com.tpsmedia.mysoejasch.model.Warehouse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DatumBarangList {

    @SerializedName("Nama_Brg")
    @Expose
    private String Nama_Brg;

    @SerializedName("qty")
    @Expose
    private String qty;

    @SerializedName("qty_std")
    @Expose
    private String qty_std;

    @SerializedName("batch_no")
    @Expose
    private String batch_no;

    @SerializedName("tgl_expired")
    @Expose
    private String tgl_expired;

    @SerializedName("Kode_lokasi")
    @Expose
    private String Kode_lokasi;

    public String getNama_Brg() {
        return Nama_Brg;
    }

    public String getqty() {
        return qty;
    }

    public String getqtystd() {
        return qty_std;
    }

    public String getbatchno() {
        return batch_no;
    }

    public String gettgl_expired() {
        return tgl_expired;
    }

    public String getKode_lokasi() { return Kode_lokasi; }

}
