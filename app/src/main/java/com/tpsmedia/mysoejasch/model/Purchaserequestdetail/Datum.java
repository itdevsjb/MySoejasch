package com.tpsmedia.mysoejasch.model.Purchaserequestdetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("UCode_PPB")
    @Expose
    private String UCodePPB;

    @SerializedName("UCode_Brg")
    @Expose
    private String UCode_Brg;

    @SerializedName("No_Urut")
    @Expose
    private String No_Urut;

    @SerializedName("Kode_Brg")
    @Expose
    private String Kode_Brg;

    @SerializedName("Nama_Brg")
    @Expose
    private String Nama_Brg;

    @SerializedName("Approval_cek")
    @Expose
    private String Approval_cek;

    @SerializedName("Qty")
    @Expose
    private String Qty;

    @SerializedName("Qty_Std")
    @Expose
    private String Qty_Std;

    @SerializedName("Nama_sat")
    @Expose
    private String Nama_sat;

    @SerializedName("Nama_sat_std")
    @Expose
    private String Nama_sat_std;

    @SerializedName("Ket")
    @Expose
    private String Ket;

    public Datum(String UCode_PPB, String No_Urut, String UCode_Brg, String Kode_Brg, String Nama_Brg, String Qty, String Qty_Std, String Nama_Sat, String Nama_Sat_Std, String Ket, String Approval_cek) {
        this.UCodePPB = UCode_PPB;
        this.No_Urut = No_Urut;
        this.UCode_Brg = UCode_Brg;
        this.Kode_Brg = Kode_Brg;
        this.Nama_Brg = Nama_Brg;
        this.Qty = Qty;
        this.Nama_sat = Nama_Sat;
        this.Qty_Std = Qty_Std;
        this.Nama_sat_std = Nama_Sat_Std;
        this.Ket = Ket;
        this.Approval_cek = Approval_cek;
    }


    public String getUcodeBrg() {
        return UCode_Brg;
    }

    public String getUCodePPB() {
        return UCodePPB;
    }

    public String getKodeBrg() {
        return Kode_Brg;
    }
    public String getNamaBrg() {
        return Nama_Brg;
    }

    public String getKet() {
        return Ket;
    }

    public String getApproveBrg() { return Approval_cek; }

    public String getJumlahBrg() { return Qty; }

    public String getNamaSat() { return Nama_sat; }

    public String getJumlahBrgStd() { return Qty_Std; }

    public String getNamaSatStd() { return Nama_sat_std; }

    public String getNo_Urut() { return No_Urut; }




}
