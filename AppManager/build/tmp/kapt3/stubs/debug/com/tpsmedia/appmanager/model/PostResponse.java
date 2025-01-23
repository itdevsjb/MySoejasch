package com.tpsmedia.appmanager.model;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002R.\u0010\u0003\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004j\n\u0012\u0004\u0012\u00020\u0005\u0018\u0001`\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR.\u0010\u000b\u001a\u0016\u0012\u0004\u0012\u00020\f\u0018\u00010\u0004j\n\u0012\u0004\u0012\u00020\f\u0018\u0001`\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\b\"\u0004\b\u000e\u0010\nR.\u0010\u000f\u001a\u0016\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u0004j\n\u0012\u0004\u0012\u00020\u0010\u0018\u0001`\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\b\"\u0004\b\u0012\u0010\nR8\u0010\u0013\u001a \u0012\u0004\u0012\u00020\u0010\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00100\u0004j\b\u0012\u0004\u0012\u00020\u0010`\u0006\u0018\u00010\u0014X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001c\u0010\u0019\u001a\u0004\u0018\u00010\u0010X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001c\u0010\u001e\u001a\u0004\u0018\u00010\fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001e\u0010#\u001a\u0004\u0018\u00010$X\u0086\u000e\u00a2\u0006\u0010\n\u0002\u0010)\u001a\u0004\b%\u0010&\"\u0004\b\'\u0010(\u00a8\u0006*"}, d2 = {"Lcom/tpsmedia/appmanager/model/PostResponse;", "Ljava/io/Serializable;", "()V", "data", "Ljava/util/ArrayList;", "Lcom/tpsmedia/appmanager/model/PROnlineModel;", "Lkotlin/collections/ArrayList;", "getData", "()Ljava/util/ArrayList;", "setData", "(Ljava/util/ArrayList;)V", "datareal", "Lcom/tpsmedia/appmanager/model/PostModel;", "getDatareal", "setDatareal", "files", "", "getFiles", "setFiles", "folders", "", "getFolders", "()Ljava/util/Map;", "setFolders", "(Ljava/util/Map;)V", "message", "getMessage", "()Ljava/lang/String;", "setMessage", "(Ljava/lang/String;)V", "post", "getPost", "()Lcom/tpsmedia/appmanager/model/PostModel;", "setPost", "(Lcom/tpsmedia/appmanager/model/PostModel;)V", "status", "", "getStatus", "()Ljava/lang/Boolean;", "setStatus", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "AppManager_debug"})
public final class PostResponse implements java.io.Serializable {
    @org.jetbrains.annotations.Nullable
    private java.lang.Boolean status = false;
    @org.jetbrains.annotations.Nullable
    private java.lang.String message;
    @org.jetbrains.annotations.Nullable
    private java.util.ArrayList<com.tpsmedia.appmanager.model.PROnlineModel> data;
    @org.jetbrains.annotations.Nullable
    private java.util.ArrayList<com.tpsmedia.appmanager.model.PostModel> datareal;
    @org.jetbrains.annotations.Nullable
    private com.tpsmedia.appmanager.model.PostModel post;
    @org.jetbrains.annotations.Nullable
    private java.util.ArrayList<java.lang.String> files;
    @org.jetbrains.annotations.Nullable
    private java.util.Map<java.lang.String, ? extends java.util.ArrayList<java.lang.String>> folders;
    
    public PostResponse() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Boolean getStatus() {
        return null;
    }
    
    public final void setStatus(@org.jetbrains.annotations.Nullable
    java.lang.Boolean p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getMessage() {
        return null;
    }
    
    public final void setMessage(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.ArrayList<com.tpsmedia.appmanager.model.PROnlineModel> getData() {
        return null;
    }
    
    public final void setData(@org.jetbrains.annotations.Nullable
    java.util.ArrayList<com.tpsmedia.appmanager.model.PROnlineModel> p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.ArrayList<com.tpsmedia.appmanager.model.PostModel> getDatareal() {
        return null;
    }
    
    public final void setDatareal(@org.jetbrains.annotations.Nullable
    java.util.ArrayList<com.tpsmedia.appmanager.model.PostModel> p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.tpsmedia.appmanager.model.PostModel getPost() {
        return null;
    }
    
    public final void setPost(@org.jetbrains.annotations.Nullable
    com.tpsmedia.appmanager.model.PostModel p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.ArrayList<java.lang.String> getFiles() {
        return null;
    }
    
    public final void setFiles(@org.jetbrains.annotations.Nullable
    java.util.ArrayList<java.lang.String> p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.Map<java.lang.String, java.util.ArrayList<java.lang.String>> getFolders() {
        return null;
    }
    
    public final void setFolders(@org.jetbrains.annotations.Nullable
    java.util.Map<java.lang.String, ? extends java.util.ArrayList<java.lang.String>> p0) {
    }
}