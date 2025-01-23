package com.tpsmedia.mysoejasch.model.Warehouse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.List;

public class Getbarang {
    @SerializedName("success")
    @Expose
    private Success success;

    @SerializedName("status")
    @Expose
    private Boolean status;

    @SerializedName("nama_produk")
    @Expose
    private String nama_produk;

    @SerializedName("batch_no")
    @Expose
    private String batch_no;

    @SerializedName("exp_date")
    @Expose
    private String exp_date;

    @SerializedName("lokasi")
    @Expose
    private String lokasi;

    @SerializedName("UCode_LK")
    @Expose
    private String UCode_LK;

    @SerializedName("UCode_Brg")
    @Expose
    private String UCode_Brg;

    @SerializedName("UCode_Sat")
    @Expose
    private String UCode_Sat;

    @SerializedName("UCode_Sat_Std")
    @Expose
    private String UCode_Sat_Std;

    @SerializedName("UCode_Lok")
    @Expose
    private String UCode_Lok;



    @SerializedName("Konv")
    @Expose
    private String Konv;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("ucode_lokasi")
    @Expose
    private List<String> ucode_lokasi = null;

    public String getNamaProduk() {
        return nama_produk;
    }

    public Boolean getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getBatchNo() {
        return batch_no;
    }

    public String getExpDate() {
        return exp_date;
    }

    public String getLokasi() {
        return lokasi;
    }

    public String getUCodeLK() {
        return UCode_LK;
    }

    public String getUCodeBrg() {
        return UCode_Brg;
    }

    public String getUCodeSat() {
        return UCode_Sat;
    }

    public String getUCodeSatStd() {
        return UCode_Sat_Std;
    }

    public String getUCodeLokasi() {
        return UCode_Lok;
    }

    public String getKonv() {
        return Konv;
    }

    public com.tpsmedia.mysoejasch.model.Warehouse.Success getSuccess() {
        return success;
    }

    public List<String> getData() {
        return ucode_lokasi;
    }

}
