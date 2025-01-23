package com.tpsmedia.mysoejasch.model.Warehouse.Pallet;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tpsmedia.mysoejasch.model.Warehouse.Pallet.Datum;
import com.tpsmedia.mysoejasch.model.Warehouse.Pallet.Success;

import java.util.List;

public class PalletList {
    @SerializedName("success")
    @Expose
    private Boolean success;

    @SerializedName("data")
    @Expose
    private List<com.tpsmedia.mysoejasch.model.Warehouse.Pallet.Datum> data = null;

    public List<Datum> getData() {
        return data;
    }

    public Boolean getSuccess() {
        return success;
    }


}
