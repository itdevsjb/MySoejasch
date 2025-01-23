package com.tpsmedia.mysoejasch.model.Auth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Login {
    @SerializedName("success")
    @Expose
    private Boolean success;

    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("loginas")
    @Expose
    private String loginas;

    @SerializedName("ucodeas")
    @Expose
    private String ucodeas;

    @SerializedName("data")
    @Expose
    private List<Datauser> data;

    @SerializedName("menu")
    @Expose
    private Object menu;

    @SerializedName("pr_level")
    @Expose
    private String pr_level;

    @SerializedName("po_level")
    @Expose
    private String po_level;

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("warehouse_level")
    @Expose
    private String warehouse_level;



    public Boolean getSuccess() {
        return success;
    }

    public String getToken() {
        return token;
    }

    public Object getMenu() { return menu; }

    public List<Datauser> getData() {
        return data;
    }
    public String getNama() {
        return loginas;
    }
    public String getUcode() {
        return ucodeas;
    }
    public String getPoLevel() {
        return po_level;
    }
    public String getPrLevel() {
        return pr_level;
    }

    public String getWarehouseLevel() {
        return warehouse_level;
    }

    public String getUsername() {
        return username;
    }

}
