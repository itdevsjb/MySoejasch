package com.tpsmedia.appmanager.model;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b)\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010?\u001a\u00020@2\u0006\u0010A\u001a\u00020BR.\u0010\u0003\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004j\n\u0012\u0004\u0012\u00020\u0005\u0018\u0001`\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001e\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0086\u000e\u00a2\u0006\u0010\n\u0002\u0010\u0011\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R.\u0010\u0012\u001a\u0016\u0012\u0004\u0012\u00020\u0000\u0018\u00010\u0004j\n\u0012\u0004\u0012\u00020\u0000\u0018\u0001`\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\b\"\u0004\b\u0014\u0010\nR\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u0016X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0018\"\u0004\b\u001d\u0010\u001aR\u001c\u0010\u001e\u001a\u0004\u0018\u00010\u0016X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0018\"\u0004\b \u0010\u001aR\u001c\u0010!\u001a\u0004\u0018\u00010\u0016X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0018\"\u0004\b#\u0010\u001aR\u001c\u0010$\u001a\u0004\u0018\u00010\u0016X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0018\"\u0004\b&\u0010\u001aR\u001e\u0010\'\u001a\u0004\u0018\u00010\fX\u0086\u000e\u00a2\u0006\u0010\n\u0002\u0010\u0011\u001a\u0004\b(\u0010\u000e\"\u0004\b)\u0010\u0010R\u001c\u0010*\u001a\u0004\u0018\u00010\u0016X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\u0018\"\u0004\b,\u0010\u001aR.\u0010-\u001a\u0016\u0012\u0004\u0012\u00020\u0000\u0018\u00010\u0004j\n\u0012\u0004\u0012\u00020\u0000\u0018\u0001`\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\b\"\u0004\b/\u0010\nR\u001e\u00100\u001a\u0004\u0018\u00010\fX\u0086\u000e\u00a2\u0006\u0010\n\u0002\u0010\u0011\u001a\u0004\b1\u0010\u000e\"\u0004\b2\u0010\u0010R\u001c\u00103\u001a\u0004\u0018\u00010\u0016X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\u0018\"\u0004\b5\u0010\u001aR\u001c\u00106\u001a\u0004\u0018\u00010\u0016X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b7\u0010\u0018\"\u0004\b8\u0010\u001aR\u001c\u00109\u001a\u0004\u0018\u00010\u0016X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b:\u0010\u0018\"\u0004\b;\u0010\u001aR\u001c\u0010<\u001a\u0004\u0018\u00010\u0016X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b=\u0010\u0018\"\u0004\b>\u0010\u001a\u00a8\u0006C"}, d2 = {"Lcom/tpsmedia/appmanager/model/PostModel;", "Ljava/io/Serializable;", "()V", "categories", "Ljava/util/ArrayList;", "Lcom/tpsmedia/appmanager/model/Category;", "Lkotlin/collections/ArrayList;", "getCategories", "()Ljava/util/ArrayList;", "setCategories", "(Ljava/util/ArrayList;)V", "id", "", "getId", "()Ljava/lang/Integer;", "setId", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "item", "getItem", "setItem", "key", "", "getKey", "()Ljava/lang/String;", "setKey", "(Ljava/lang/String;)V", "post_asset", "getPost_asset", "setPost_asset", "post_audio", "getPost_audio", "setPost_audio", "post_content", "getPost_content", "setPost_content", "post_date", "getPost_date", "setPost_date", "post_id", "getPost_id", "setPost_id", "post_image", "getPost_image", "setPost_image", "post_item", "getPost_item", "setPost_item", "post_parent", "getPost_parent", "setPost_parent", "post_status", "getPost_status", "setPost_status", "post_title", "getPost_title", "setPost_title", "post_type", "getPost_type", "setPost_type", "post_video", "getPost_video", "setPost_video", "showPostImage", "", "imageView", "Landroid/widget/ImageView;", "AppManager_release"})
public final class PostModel implements java.io.Serializable {
    @org.jetbrains.annotations.Nullable
    private java.lang.String key;
    @org.jetbrains.annotations.Nullable
    private java.lang.Integer id;
    @org.jetbrains.annotations.Nullable
    private java.lang.Integer post_id;
    @org.jetbrains.annotations.Nullable
    private java.lang.String post_date;
    @org.jetbrains.annotations.Nullable
    private java.lang.String post_title;
    @org.jetbrains.annotations.Nullable
    private java.lang.String post_content;
    @org.jetbrains.annotations.Nullable
    private java.lang.String post_image;
    @org.jetbrains.annotations.Nullable
    private java.lang.String post_video;
    @org.jetbrains.annotations.Nullable
    private java.lang.String post_audio;
    @org.jetbrains.annotations.Nullable
    private java.lang.String post_asset;
    @org.jetbrains.annotations.Nullable
    private java.lang.String post_status;
    @org.jetbrains.annotations.Nullable
    private java.lang.String post_type;
    @org.jetbrains.annotations.Nullable
    private java.lang.Integer post_parent;
    @org.jetbrains.annotations.Nullable
    private java.util.ArrayList<com.tpsmedia.appmanager.model.PostModel> item;
    @org.jetbrains.annotations.Nullable
    private java.util.ArrayList<com.tpsmedia.appmanager.model.PostModel> post_item;
    @org.jetbrains.annotations.Nullable
    private java.util.ArrayList<com.tpsmedia.appmanager.model.Category> categories;
    
    public PostModel() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getKey() {
        return null;
    }
    
    public final void setKey(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer getId() {
        return null;
    }
    
    public final void setId(@org.jetbrains.annotations.Nullable
    java.lang.Integer p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer getPost_id() {
        return null;
    }
    
    public final void setPost_id(@org.jetbrains.annotations.Nullable
    java.lang.Integer p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getPost_date() {
        return null;
    }
    
    public final void setPost_date(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getPost_title() {
        return null;
    }
    
    public final void setPost_title(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getPost_content() {
        return null;
    }
    
    public final void setPost_content(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getPost_image() {
        return null;
    }
    
    public final void setPost_image(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getPost_video() {
        return null;
    }
    
    public final void setPost_video(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getPost_audio() {
        return null;
    }
    
    public final void setPost_audio(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getPost_asset() {
        return null;
    }
    
    public final void setPost_asset(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getPost_status() {
        return null;
    }
    
    public final void setPost_status(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getPost_type() {
        return null;
    }
    
    public final void setPost_type(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer getPost_parent() {
        return null;
    }
    
    public final void setPost_parent(@org.jetbrains.annotations.Nullable
    java.lang.Integer p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.ArrayList<com.tpsmedia.appmanager.model.PostModel> getItem() {
        return null;
    }
    
    public final void setItem(@org.jetbrains.annotations.Nullable
    java.util.ArrayList<com.tpsmedia.appmanager.model.PostModel> p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.ArrayList<com.tpsmedia.appmanager.model.PostModel> getPost_item() {
        return null;
    }
    
    public final void setPost_item(@org.jetbrains.annotations.Nullable
    java.util.ArrayList<com.tpsmedia.appmanager.model.PostModel> p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.ArrayList<com.tpsmedia.appmanager.model.Category> getCategories() {
        return null;
    }
    
    public final void setCategories(@org.jetbrains.annotations.Nullable
    java.util.ArrayList<com.tpsmedia.appmanager.model.Category> p0) {
    }
    
    public final void showPostImage(@org.jetbrains.annotations.NotNull
    android.widget.ImageView imageView) {
    }
}