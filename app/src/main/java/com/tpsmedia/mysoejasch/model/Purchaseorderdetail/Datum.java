package com.tpsmedia.mysoejasch.model.Purchaseorderdetail;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class Datum {

    @SerializedName("UCode_Brg")
    @Expose
    private String UCode_Brg;

    @SerializedName("UCode_SPB")
    @Expose
    private String UCode_SPB;

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

    @SerializedName("Sub_Tot_Disc_MU")
    @Expose
    private String Sub_Tot_Disc_MU;

    @SerializedName("Hrg_Unit_MU")
    @Expose
    private String Hrg_Unit_MU;

    @SerializedName("Sub_Tot_MU")
    @Expose
    private String Sub_Tot_MU;

    @SerializedName("Prop_Pjk_MU")
    @Expose
    private String Prop_Pjk_MU;

    public Datum(String uCodeSpb, String noUrut, String uCodeBrg, String kodeBrg, String namaBrg, String qty, String qtyStd, String namaSat, String namaSatStd, String ket, String approvalCek, String Harga, String Discount, String Sub_Total, String Pajak) {
        this.UCode_SPB = uCodeSpb;
        this.No_Urut = noUrut;
        this.UCode_Brg = uCodeBrg;
        this.Kode_Brg = kodeBrg;
        this.Nama_Brg = namaBrg;
        this.Qty = qty;
        this.Qty_Std = qtyStd;
        this.Nama_sat = namaSat;
        this.Nama_sat_std = namaSatStd;
        this.Ket = ket;
        this.Approval_cek = approvalCek;
        this.Hrg_Unit_MU = Harga;
        this.Sub_Tot_Disc_MU = Discount;
        this.Sub_Tot_MU = Sub_Total;
        this.Prop_Pjk_MU = Pajak;

    }

    public String getUcodeBrg() {
        return UCode_Brg;
    }

    public String getUCode_SPB() {
        return UCode_SPB;
    }

    public String getNo_Urut() {
        return No_Urut;
    }

    public String getKodeBrg() {
        return Kode_Brg;
    }
    public String getNamaBrg() {
        return Nama_Brg;
    }

    public String getApproveBrg() { return Approval_cek; }

    public String getJumlahBrg() { return Qty; }

    public String getJumlahBrgStd() { return Qty_Std; }

    public String getNamaSat() { return Nama_sat; }

    public String getNamaSatStd() { return Nama_sat_std; }

    public String getKet() { return Ket; }

    public String getHarga() { return Hrg_Unit_MU; }

    public String getDiscount() { return Sub_Tot_Disc_MU; }

    public String getSubtotal() { return Sub_Tot_MU; }

    public String getPajak() { return Prop_Pjk_MU; }




}
