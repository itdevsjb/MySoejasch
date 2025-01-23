package com.tpsmedia.mysoejasch.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiResponse {


    private Integer total;
    public Integer getTotal(){
        return total;
    }

    private Integer sisa;
    public Integer getSisa(){
        return sisa;
    }

    private Integer no_ct;
    public Integer getNo_ct(){
        return no_ct;
    }

    private String ucode_lokasi;
    public String getUcode_lokasi(){
        return ucode_lokasi;
    }

    private String ucode_gdg;
    public String getUcode_gdg(){
        return ucode_gdg;
    }

    private String ucode_inbound;
    public String getUcode_inbound(){
        return ucode_inbound;
    }

    private String ucode_brg;
    public String getUcode_brg(){
        return ucode_brg;
    }

    private String UCode_Brg;
    public String getUcode_brg2(){
        return UCode_Brg;
    }

    private String ucode_sat;
    public String getUcode_sat(){
        return ucode_sat;
    }

    private String UCode_Sat;
    public String getUCode_Sat2(){
        return UCode_Sat;
    }

    private String ucode_sat_std;
    public String getUcode_sat_std(){
        return ucode_sat_std;
    }

    private String UCode_Sat_Std;
    public String getUCode_Sat_Std2(){
        return UCode_Sat_Std;
    }



    private String batch_no;
    public String getBatch_no(){
        return batch_no;
    }

    private String Batch_No;
    public String getBatch_no2(){
        return Batch_No;
    }

    private String tgl_expired;
    public String getTgl_expired(){
        return tgl_expired;
    }

    private String Tgl_Expired;
    public String getTgl_expired2(){
        return Tgl_Expired;
    }

    private String qty;
    public String getQty(){
        return qty;
    }

    private String Qty;
    public String getQty2(){
        return Qty;
    }

    private String qty_std;
    public String getQty_std(){
        return qty_std;
    }

    private String Qty_Std;
    public String getQty_std2(){
        return Qty_Std;
    }





    private String serahterima;
    public String getSerahterima(){
        return serahterima;
    }

    private String no_box;
    public String getNo_box(){
        return no_box;
    }

    private String No_Karton;
    public String getNo_Karton(){
        return No_Karton;
    }

    private String UCode_Pallet;
    public String getUCode_Pallet(){
        return UCode_Pallet;
    }

    private String qr_value;
    public String getQr_value(){
        return qr_value;
    }

















    private List<String> choices;

    public List<String> getChoices() {
        return choices;
    }



    public void setChoices(List<String> choices) {
        this.choices = choices;
    }

}
