package com.tpsmedia.mysoejasch.model.Divisi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("Nama_Div")
    @Expose
    private String Nama_Div;

    @SerializedName("UCode_Div")
    @Expose
    private String UCode_Div;

    @SerializedName("text")
    @Expose
    private String text;

    @SerializedName("value")
    @Expose
    private String value;


    public String getText() {
        return text;
    }

    public String getValue() {
        return value;
    }


    public String getNama_Div(){
        return Nama_Div;
    }

    public String getUCode_Div(){
        return UCode_Div;
    }

}
