package com.tpsmedia.mysoejasch.model.Warehouse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datumdetailstockin {

    @SerializedName("Nama_brg")
    @Expose
    private String Nama_brg;

    @SerializedName("Kode_Brg")
    @Expose
    private String Kode_Brg;

    @SerializedName("batch_no")
    @Expose
    private String batch_no;

    @SerializedName("qty")
    @Expose
    private String qty;

    @SerializedName("qty_int")
    @Expose
    private String qty_int;

    @SerializedName("qty_std")
    @Expose
    private String qty_std;

    @SerializedName("tgl_expired")
    @Expose
    private String tgl_expired;

    @SerializedName("Kode_lokasi")
    @Expose
    private String Kode_lokasi;

//    @SerializedName("Kode_Pallete")
//    @Expose
//    private String Kode_Pallete;
//

    @SerializedName("created_at")
    @Expose
    private String created_at;

    @SerializedName("added_by")
    @Expose
    private String added_by;

    @SerializedName("id_detail_stock_in")
    @Expose
    private String id_detail_stock_in;

    @SerializedName("valid_oleh")
    @Expose
    private String valid_oleh;

    @SerializedName("invalid_oleh")
    @Expose
    private String invalid_oleh;



    public String getNama_brg() {
        return Nama_brg;
    }

    public String getKode_Brg() {
        return Kode_Brg;
    }

    public String getUcode_stock_in() {
        return id_detail_stock_in;
    }

    public String getBatch_No() {
        return batch_no;
    }

    public String getQty() {
        return qty;
    }
    public String getQty_int() {
        return qty_int;
    }

    public String getQty_std() {
        return qty_std;
    }

    public String getTgl_Expired() {
        return tgl_expired;
    }

    public String getCreate_at() {
        return created_at;
    }

    public String getCreate_by() {
        return added_by;
    }

    public String getKode_lokasi() {
        return Kode_lokasi;
    }

//    public String getKode_Pallete() {
//        return Kode_Pallete;
//    }

    public String getValid_oleh() {
        return valid_oleh;
    }

    public String getInvalid_oleh() {
        return invalid_oleh;
    }

}
