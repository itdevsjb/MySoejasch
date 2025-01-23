package com.tpsmedia.mysoejasch.model.Purchaseorder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.Date;

public class Datum {


    public Datum(String UCode_SPB, String No_SPB, String Ket, String Tgl_SPB, String nama_karyawan, String foto, String nama_Supp, String approval_1, String approval_2, String approval_3, String approval_4, String no_approval_1, String no_approval_2, String no_approval_3, String no_approval_4, String cek_approval_1, String cek_approval_2, String cek_approval_3, String cek_approval_4, String jenis, String lampiran) {
        this.UCode_SPB = UCode_SPB;
        this.NoSPB = No_SPB;
        this.Tgl_SPB = Tgl_SPB;
        this.Ket = Ket;
        this.nama_karyawan = nama_karyawan;
        this.foto = foto;
        this.Nama_Supp = nama_Supp;

        this.approval_1 = approval_1;
        this.approval_2 = approval_2;
        this.approval_3 = approval_3;
        this.approval_4 = approval_4;

        this.no_approval_1 = no_approval_1;
        this.no_approval_2 = no_approval_2;
        this.no_approval_3 = no_approval_3;
        this.no_approval_4 = no_approval_4;

        this.cek_approval_1 = cek_approval_1;
        this.cek_approval_2 = cek_approval_2;
        this.cek_approval_3 = cek_approval_3;
        this.cek_approval_4 = cek_approval_4;

        this.jenis = jenis;
        this.lampiran = lampiran;

    }


    @SerializedName("UCode_SPB")
    @Expose
    private String UCode_SPB;
    @SerializedName("No_SPB")
    @Expose
    private String NoSPB;
    @SerializedName("Ket")
    @Expose
    private String Ket;

    @SerializedName("Tgl_SPB")
    @Expose
    private String Tgl_SPB;

    @SerializedName("status_approval")
    @Expose
    private String status_approval;

    @SerializedName("request_approval")
    @Expose
    private String request_approval;


    @SerializedName("no_approval")
    @Expose
    private String no_approval;

    @SerializedName("remarks")
    @Expose
    private String remarks;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("no_approval_1")
    @Expose
    private String no_approval_1;
    @SerializedName("no_approval_2")
    @Expose
    private String no_approval_2;

    @SerializedName("no_approval_3")
    @Expose
    private String no_approval_3;

    @SerializedName("no_approval_4")
    @Expose
    private String no_approval_4;

    @SerializedName("no_approval_5")
    @Expose
    private String no_approval_5;


    @SerializedName("cek_approval_1")
    @Expose
    private String cek_approval_1;
    @SerializedName("cek_approval_2")
    @Expose
    private String cek_approval_2;

    @SerializedName("cek_approval_3")
    @Expose
    private String cek_approval_3;

    @SerializedName("cek_approval_4")
    @Expose
    private String cek_approval_4;

    @SerializedName("cek_approval_5")
    @Expose
    private String cek_approval_5;


    @SerializedName("approval_1")
    @Expose
    private String approval_1;

    @SerializedName("approval_2")
    @Expose
    private String approval_2;

    @SerializedName("approval_3")
    @Expose
    private String approval_3;

    @SerializedName("approval_4")
    @Expose
    private String approval_4;

    @SerializedName("approval_5")
    @Expose
    private String approval_5;



    @SerializedName("Kode_MU")
    @Expose
    private String Kode_MU;


    @SerializedName("Grand_Tot_MU")
    @Expose
    private BigDecimal Grand_Tot_MU;

    @SerializedName("Pjk_MU")
    @Expose
    private BigDecimal Pjk_MU;

    @SerializedName("Grand_Tot_Net_MU")
    @Expose
    private BigDecimal Grand_Tot_Net_MU;

    @SerializedName("Nama_Supp")
    @Expose
    private String Nama_Supp;

    @SerializedName("lampiran")
    @Expose
    private String lampiran;

    @SerializedName("nama_karyawan")
    @Expose
    private String nama_karyawan;


    @SerializedName("foto")
    @Expose
    private String foto;

    @SerializedName("jenis")
    @Expose
    private String jenis;


    public String getUcodeSPB() {
        return UCode_SPB;
    }
    public String getNoSPB() {
        return NoSPB;
    }
    public String getMatauang() {
        return Kode_MU;
    }

    public String getKet() {
        return Ket;
    }

    public String getTglSPB() {
        return Tgl_SPB;
    }

    public String getNama_karyawan() {
        return nama_karyawan;
    }

    public String getFoto() {
        return foto;
    }

    public String getStatus() {
        return status_approval;
    }
    public String getRequestor() {
        return request_approval;
    }
    public String getAprovalPo() {
        return no_approval;
    }

    public String getRemarks() {
        return remarks;
    }

    public String getApproval1() {
        return approval_1;
    }

    public String getApproval2() {
        return approval_2;
    }

    public String getApproval3() {
        return approval_3;
    }

    public String getApproval4() { return approval_4; }

    public String getApproval5() { return approval_5; }


    public String getNoApproval1() {
        return no_approval_1;
    }

    public String getNoApproval2() {
        return no_approval_2;
    }

    public String getNoApproval3() {
        return no_approval_3;
    }

    public String getNoApproval4() {
        return no_approval_4;
    }

    public String getNoApproval5() {
        return no_approval_5;
    }


    public String getCekApproval1() {
        return cek_approval_1;
    }

    public String getCekApproval2() {
        return cek_approval_2;
    }

    public String getCekApproval3() {
        return cek_approval_3;
    }

    public String getCekApproval4() {
        return cek_approval_4;
    }

    public String getCekApproval5() {
        return cek_approval_5;
    }



    public BigDecimal getGrandTotal() {
        return Grand_Tot_MU;
    }
    public BigDecimal getPajak() {
        return Pjk_MU;
    }

    public String getNamaSupp() {
        return Nama_Supp;
    }
    public BigDecimal getGrandTotalNet() {
        return Grand_Tot_Net_MU;
    }

    public String getLampiran() {
        return lampiran;
    }

    public String getStatusUser() {
        return status;
    }

    public String getJenis() {
        return jenis;
    }
}
