package com.tpsmedia.appmanager.room;

import java.lang.System;

@androidx.room.Entity(tableName = "table_posts")
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010%\u001a\u00020&J\u000e\u0010\'\u001a\u00020(2\u0006\u0010)\u001a\u00020&R\"\u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR \u0010\n\u001a\u0004\u0018\u00010\u000b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR \u0010\u0010\u001a\u0004\u0018\u00010\u000b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\r\"\u0004\b\u0012\u0010\u000fR\"\u0010\u0013\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\u0014\u0010\u0006\"\u0004\b\u0015\u0010\bR \u0010\u0016\u001a\u0004\u0018\u00010\u000b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\r\"\u0004\b\u0018\u0010\u000fR\"\u0010\u0019\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\u001a\u0010\u0006\"\u0004\b\u001b\u0010\bR \u0010\u001c\u001a\u0004\u0018\u00010\u000b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\r\"\u0004\b\u001e\u0010\u000fR \u0010\u001f\u001a\u0004\u0018\u00010\u000b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b \u0010\r\"\u0004\b!\u0010\u000fR \u0010\"\u001a\u0004\u0018\u00010\u000b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\r\"\u0004\b$\u0010\u000f\u00a8\u0006*"}, d2 = {"Lcom/tpsmedia/appmanager/room/TablePosts;", "Ljava/io/Serializable;", "()V", "id", "", "getId", "()Ljava/lang/Integer;", "setId", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "post_content", "", "getPost_content", "()Ljava/lang/String;", "setPost_content", "(Ljava/lang/String;)V", "post_date", "getPost_date", "setPost_date", "post_id", "getPost_id", "setPost_id", "post_image", "getPost_image", "setPost_image", "post_parent", "getPost_parent", "setPost_parent", "post_status", "getPost_status", "setPost_status", "post_title", "getPost_title", "setPost_title", "post_type", "getPost_type", "setPost_type", "getPost", "Lcom/tpsmedia/appmanager/model/PostModel;", "setPost", "", "postModel", "AppManager_debug"})
public final class TablePosts implements java.io.Serializable {
    @org.jetbrains.annotations.Nullable
    @androidx.room.PrimaryKey(autoGenerate = true)
    private java.lang.Integer id;
    @org.jetbrains.annotations.Nullable
    @androidx.room.ColumnInfo(name = "post_id")
    private java.lang.Integer post_id;
    @org.jetbrains.annotations.Nullable
    @androidx.room.ColumnInfo(name = "post_date")
    private java.lang.String post_date;
    @org.jetbrains.annotations.Nullable
    @androidx.room.ColumnInfo(name = "post_title")
    private java.lang.String post_title;
    @org.jetbrains.annotations.Nullable
    @androidx.room.ColumnInfo(name = "post_content")
    private java.lang.String post_content;
    @org.jetbrains.annotations.Nullable
    @androidx.room.ColumnInfo(name = "post_image")
    private java.lang.String post_image;
    @org.jetbrains.annotations.Nullable
    @androidx.room.ColumnInfo(name = "post_status")
    private java.lang.String post_status;
    @org.jetbrains.annotations.Nullable
    @androidx.room.ColumnInfo(name = "post_type")
    private java.lang.String post_type;
    @org.jetbrains.annotations.Nullable
    @androidx.room.ColumnInfo(name = "post_parent")
    private java.lang.Integer post_parent;
    
    public TablePosts() {
        super();
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
    
    @org.jetbrains.annotations.NotNull
    public final com.tpsmedia.appmanager.model.PostModel getPost() {
        return null;
    }
    
    public final void setPost(@org.jetbrains.annotations.NotNull
    com.tpsmedia.appmanager.model.PostModel postModel) {
    }
}