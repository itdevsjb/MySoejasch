package com.tpsmedia.mysoejasch.model.CTPlanData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tpsmedia.mysoejasch.model.CTPlanData.Datum;

import java.util.List;

public class Success {
    @SerializedName("success")
    @Expose
    private Boolean success;


    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("total")
    @Expose
    private Integer total;

    @SerializedName("no_ct")
    @Expose
    private String no_ct;

    @SerializedName("qty")
    @Expose
    private Integer qty;

    @SerializedName("ucode_outbound")
    @Expose
    private String ucode_outbound;

    @SerializedName("cek_submit")
    @Expose
    private Integer cek_submit;


    @SerializedName("count_sjtb")
    @Expose
    private Integer count_sjtb;

    @SerializedName("data")
    @Expose
    private List<Datum> data = null;


    public List<Datum> getData() {
        return data;
    }

    public  String getNo_ct(){
        return no_ct;
    }

    public Integer getQty(){
        return qty;
    }

    public Integer getCek_submit(){
        return cek_submit;
    }

    public Integer getTotal(){
        return total;
    }

    public String getMessage(){
        return message;
    }

    public  String getUcode_outbound(){
        return ucode_outbound;
    }

    public  Integer getCount_sjtb(){
        return count_sjtb;
    }


}
