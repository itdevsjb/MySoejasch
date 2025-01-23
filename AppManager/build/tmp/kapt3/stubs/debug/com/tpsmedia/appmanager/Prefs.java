package com.tpsmedia.appmanager;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R$\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR$\u0010\f\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\r\u0010\t\"\u0004\b\u000e\u0010\u000bR$\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u0010\u0010\t\"\u0004\b\u0011\u0010\u000bR$\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u0013\u0010\t\"\u0004\b\u0014\u0010\u000bR$\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0005\u001a\u00020\u00158F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR$\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u001c\u0010\t\"\u0004\b\u001d\u0010\u000bR0\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e2\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u0011\u0010%\u001a\u00020&\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010(R$\u0010*\u001a\u00020)2\u0006\u0010\u0005\u001a\u00020)8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R0\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00060\u001e2\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u001e8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b0\u0010\"\"\u0004\b1\u0010$R$\u00103\u001a\u0002022\u0006\u0010\u0005\u001a\u0002028F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b4\u00105\"\u0004\b6\u00107\u00a8\u00068"}, d2 = {"Lcom/tpsmedia/appmanager/Prefs;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "value", "", "API_KEY", "getAPI_KEY", "()Ljava/lang/String;", "setAPI_KEY", "(Ljava/lang/String;)V", "BASE_URL", "getBASE_URL", "setBASE_URL", "FIREBASE_KEY", "getFIREBASE_KEY", "setFIREBASE_KEY", "FIREBASE_URL", "getFIREBASE_URL", "setFIREBASE_URL", "Lcom/tpsmedia/appmanager/model/ItemModel;", "ITEM_MODEL", "getITEM_MODEL", "()Lcom/tpsmedia/appmanager/model/ItemModel;", "setITEM_MODEL", "(Lcom/tpsmedia/appmanager/model/ItemModel;)V", "ITEM_RESPONSE", "getITEM_RESPONSE", "setITEM_RESPONSE", "", "Lcom/tpsmedia/appmanager/model/PostModel;", "post_model_array_list", "getPost_model_array_list", "()Ljava/util/List;", "setPost_model_array_list", "(Ljava/util/List;)V", "prefs", "Landroid/content/SharedPreferences;", "getPrefs", "()Landroid/content/SharedPreferences;", "", "privacy_policy", "getPrivacy_policy", "()Z", "setPrivacy_policy", "(Z)V", "serach_array_list", "getSerach_array_list", "setSerach_array_list", "", "version_code", "getVersion_code", "()I", "setVersion_code", "(I)V", "AppManager_debug"})
public final class Prefs {
    @org.jetbrains.annotations.NotNull
    private final android.content.SharedPreferences prefs = null;
    
    public Prefs(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final android.content.SharedPreferences getPrefs() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getBASE_URL() {
        return null;
    }
    
    public final void setBASE_URL(@org.jetbrains.annotations.NotNull
    java.lang.String value) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getAPI_KEY() {
        return null;
    }
    
    public final void setAPI_KEY(@org.jetbrains.annotations.NotNull
    java.lang.String value) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getFIREBASE_URL() {
        return null;
    }
    
    public final void setFIREBASE_URL(@org.jetbrains.annotations.NotNull
    java.lang.String value) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getFIREBASE_KEY() {
        return null;
    }
    
    public final void setFIREBASE_KEY(@org.jetbrains.annotations.NotNull
    java.lang.String value) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getITEM_RESPONSE() {
        return null;
    }
    
    public final void setITEM_RESPONSE(@org.jetbrains.annotations.NotNull
    java.lang.String value) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.tpsmedia.appmanager.model.ItemModel getITEM_MODEL() {
        return null;
    }
    
    public final void setITEM_MODEL(@org.jetbrains.annotations.NotNull
    com.tpsmedia.appmanager.model.ItemModel value) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.tpsmedia.appmanager.model.PostModel> getPost_model_array_list() {
        return null;
    }
    
    public final void setPost_model_array_list(@org.jetbrains.annotations.NotNull
    java.util.List<com.tpsmedia.appmanager.model.PostModel> value) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<java.lang.String> getSerach_array_list() {
        return null;
    }
    
    public final void setSerach_array_list(@org.jetbrains.annotations.NotNull
    java.util.List<java.lang.String> value) {
    }
    
    public final int getVersion_code() {
        return 0;
    }
    
    public final void setVersion_code(int value) {
    }
    
    public final boolean getPrivacy_policy() {
        return false;
    }
    
    public final void setPrivacy_policy(boolean value) {
    }
}