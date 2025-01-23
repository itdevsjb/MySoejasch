package com.tpsmedia.appmanager;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J;\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2#\u0010\t\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u000b\u00a2\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u00040\nJ\u0016\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\bJ&\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0012\u001a\u00020\u00132\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u0014J\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0005\u001a\u00020\u0006J3\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062#\u0010\t\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\b\u00a2\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u00040\nJM\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b25\u0010\t\u001a1\u0012\'\u0012%\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u0019j\n\u0012\u0004\u0012\u00020\u001a\u0018\u0001`\u001b\u00a2\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u00040\nJ;\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2#\u0010\t\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u001d\u00a2\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u00040\nJ\u0018\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\bJ\u0016\u0010\"\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010#\u001a\u00020\bJ\u0016\u0010$\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010%\u001a\u00020\b\u00a8\u0006&"}, d2 = {"Lcom/tpsmedia/appmanager/FirebaseManager;", "", "()V", "getCategory", "", "context", "Landroid/content/Context;", "url", "", "function", "Lkotlin/Function1;", "Lcom/tpsmedia/appmanager/model/CategoryModel;", "Lkotlin/ParameterName;", "name", "response", "getItem", "key", "getItemDelay", "ms", "", "Lkotlin/Function0;", "getItemModel", "Lcom/tpsmedia/appmanager/model/ItemModel;", "getItemResponse", "getPosts", "Ljava/util/ArrayList;", "Lcom/tpsmedia/appmanager/model/PostModel;", "Lkotlin/collections/ArrayList;", "getStorage", "Lcom/tpsmedia/appmanager/model/StorageModel;", "getStorageModel", "json", "Lorg/json/JSONObject;", "folderKey", "setApiKey", "firebase_key", "setBaseUrl", "firebase_url", "AppManager_release"})
public final class FirebaseManager {
    
    public FirebaseManager() {
        super();
    }
    
    public final void setBaseUrl(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    java.lang.String firebase_url) {
    }
    
    public final void setApiKey(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    java.lang.String firebase_key) {
    }
    
    public final void getItemResponse(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> function) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getItem(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    java.lang.String key) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.tpsmedia.appmanager.model.ItemModel getItemModel(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        return null;
    }
    
    public final void getItemDelay(@org.jetbrains.annotations.NotNull
    android.content.Context context, long ms, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> function) {
    }
    
    public final void getPosts(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    java.lang.String url, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super java.util.ArrayList<com.tpsmedia.appmanager.model.PostModel>, kotlin.Unit> function) {
    }
    
    public final void getCategory(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    java.lang.String url, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super com.tpsmedia.appmanager.model.CategoryModel, kotlin.Unit> function) {
    }
    
    public final void getStorage(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    java.lang.String url, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super com.tpsmedia.appmanager.model.StorageModel, kotlin.Unit> function) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.tpsmedia.appmanager.model.StorageModel getStorageModel(@org.jetbrains.annotations.NotNull
    org.json.JSONObject json, @org.jetbrains.annotations.Nullable
    java.lang.String folderKey) {
        return null;
    }
}