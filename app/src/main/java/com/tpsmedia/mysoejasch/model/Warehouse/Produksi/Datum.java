package com.tpsmedia.mysoejasch.model.Warehouse.Produksi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("Nama_Brg")
    @Expose
    private String Nama_Brg;

    @SerializedName("Qty_Std")
    @Expose
    private String Qty_Std;

    @SerializedName("Remark")
    @Expose
    private String Remark;

    @SerializedName("No_SPC_Rekap")
    @Expose
    private String No_SPC_Rekap;

    @SerializedName("Hasil")
    @Expose
    private String Hasil;

    @SerializedName("Tgl_Produksi")
    @Expose
    private String Tgl_Produksi;

    @SerializedName("No_OP")
    @Expose
    private String No_OP;


    public String getNama_Brg() {
        return Nama_Brg;
    }
    public String getQty() {
        return Qty_Std;
    }

    public String getRemark() {
        return Remark;
    }

    public String getTanggalProduksi() {
        return Tgl_Produksi;
    }

    public String getNo_SPC_Rekap() {
        return No_SPC_Rekap;
    }

    public String getHasil() {
        return Hasil;
    }

    public String getNo_OP() {
        return No_OP;
    }


}
