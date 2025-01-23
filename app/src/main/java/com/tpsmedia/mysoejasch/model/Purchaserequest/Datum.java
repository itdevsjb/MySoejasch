package com.tpsmedia.mysoejasch.model.Purchaserequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Datum {

    public Datum(String UCode_PPB, String No_PPB,  String Tgl_PPB, String Ket, String foto,  String Nama_Dept, String remarks, String nama_karyawan, String approval_1, String approval_2, String approval_3, String approval_4, String no_approval_1, String no_approval_2, String no_approval_3, String no_approval_4, String cek_approval_1, String cek_approval_2, String cek_approval_3, String cek_approval_4) {
        this.UCodePPB = UCode_PPB;
        this.NoPPB = No_PPB;
        this.TglPPB = Tgl_PPB;
        this.Ket = Ket;
        this.foto = foto;
        this.Nama_Dept = Nama_Dept;
        this.remarks = remarks;
        this.nama_karyawan = nama_karyawan;

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


    }


    @SerializedName("UCode_PPB")
    @Expose
    public String UCodePPB;
    @SerializedName("No_PPB")
    @Expose
    private String NoPPB;
    @SerializedName("Ket")
    @Expose
    private String Ket;

    @SerializedName("Tgl_PPB")
    @Expose
    private String TglPPB;

    @SerializedName("status_approval")
    @Expose
    private String status_approval;

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




    @SerializedName("request_approval")
    @Expose
    private String request_approval;

    @SerializedName("Nama_Dept")
    @Expose
    private String Nama_Dept;

    @SerializedName("remarks")
    @Expose
    private String remarks;

    @SerializedName("nama_karyawan")
    @Expose
    private String nama_karyawan;

    @SerializedName("foto")
    @Expose
    private String foto;

    @SerializedName("jenis")
    @Expose
    private String jenis;


    public String getUcodePPB() {
        return UCodePPB;
    }
    public String getNoPPB() {
        return NoPPB;
    }
    public String getKet() {
        return Ket;
    }
    public String getTglPBB() {
        return TglPPB;
    }

    public String getStatus() {
        return status_approval;
    }

    public String getRequestor() {
        return request_approval;
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






    public String getNama_Dept() {
        return Nama_Dept;
    }

    public String getRemarks() {
        return remarks;
    }

    public String getNama_karyawan() {
        return nama_karyawan;
    }

    public String getFoto() {
        return foto;
    }

    public String getTglPPB() {
        return TglPPB;
    }

    public String getJenis() {
        return jenis;
    }




}
