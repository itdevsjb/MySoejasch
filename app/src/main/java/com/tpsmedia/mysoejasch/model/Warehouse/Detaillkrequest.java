package com.tpsmedia.mysoejasch.model.Warehouse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Detaillkrequest {

    @SerializedName("success")
    @Expose
    private Success success;

    @SerializedName("nama_produk")
    @Expose
    private String nama_produk;

    @SerializedName("batch_no")
    @Expose
    private String batch_no;

    @SerializedName("exp_date")
    @Expose
    private String exp_date;


    public String getNamaProduk() {
        return nama_produk;
    }

    public String getBatchNo() {
        return batch_no;
    }

    public String getExpDate() {
        return exp_date;
    }

    public com.tpsmedia.mysoejasch.model.Warehouse.Success getSuccess() {
        return success;
    }

}
