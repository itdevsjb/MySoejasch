package com.tpsmedia.mysoejasch.model.Warehouse.Location;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tpsmedia.mysoejasch.model.Warehouse.Location.Datum;
import com.tpsmedia.mysoejasch.model.Warehouse.Location.Success;

import java.util.List;

public class LocationList {
    @SerializedName("success")
    @Expose
    private Boolean success;

    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

    public List<Datum> getData() {
        return data;
    }

    public Boolean getSuccess() {
        return success;
    }


}
