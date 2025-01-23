package com.tpsmedia.appmanager;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u001d\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\r\u001a\u0004\u0018\u00010\n\u00a2\u0006\u0002\u0010\u000eJ\'\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\r\u001a\u0004\u0018\u00010\n2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u00a2\u0006\u0002\u0010\u0011J\u001f\u0010\u0012\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\r\u001a\u0004\u0018\u00010\n\u00a2\u0006\u0002\u0010\u0013J)\u0010\u0012\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\r\u001a\u0004\u0018\u00010\n2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u00a2\u0006\u0002\u0010\u0014J\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\b0\u00162\u0006\u0010\u0005\u001a\u00020\u0006J\u001c\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\b0\u00162\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0010J\u001e\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\b0\u00162\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010J&\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\b0\u00162\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0017\u001a\u00020\u0010J\u001c\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\b0\u0016J\u0016\u0010\u001b\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b\u00a8\u0006\u001c"}, d2 = {"Lcom/tpsmedia/appmanager/PostManager;", "", "()V", "addPost", "", "context", "Landroid/content/Context;", "postModel", "Lcom/tpsmedia/appmanager/model/PostModel;", "position", "", "deletePost", "", "post_id", "(Landroid/content/Context;Ljava/lang/Integer;)Z", "post_type", "", "(Landroid/content/Context;Ljava/lang/Integer;Ljava/lang/String;)Z", "getPostById", "(Landroid/content/Context;Ljava/lang/Integer;)Lcom/tpsmedia/appmanager/model/PostModel;", "(Landroid/content/Context;Ljava/lang/Integer;Ljava/lang/String;)Lcom/tpsmedia/appmanager/model/PostModel;", "getPosts", "", "filter", "getPostsType", "setPosts", "posts", "updatePost", "AppManager_debug"})
public final class PostManager {
    
    public PostManager() {
        super();
    }
    
    public final void setPosts(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    java.util.List<com.tpsmedia.appmanager.model.PostModel> posts) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.tpsmedia.appmanager.model.PostModel> getPosts(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.tpsmedia.appmanager.model.PostModel> getPosts(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    java.lang.String filter) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.tpsmedia.appmanager.model.PostModel> getPostsType(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.Nullable
    java.lang.String post_type) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.tpsmedia.appmanager.model.PostModel> getPostsType(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.Nullable
    java.lang.String post_type, @org.jetbrains.annotations.NotNull
    java.lang.String filter) {
        return null;
    }
    
    public final void addPost(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    com.tpsmedia.appmanager.model.PostModel postModel, int position) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.tpsmedia.appmanager.model.PostModel getPostById(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.Nullable
    java.lang.Integer post_id) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.tpsmedia.appmanager.model.PostModel getPostById(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.Nullable
    java.lang.Integer post_id, @org.jetbrains.annotations.Nullable
    java.lang.String post_type) {
        return null;
    }
    
    public final boolean updatePost(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    com.tpsmedia.appmanager.model.PostModel postModel) {
        return false;
    }
    
    public final boolean deletePost(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    com.tpsmedia.appmanager.model.PostModel postModel) {
        return false;
    }
    
    public final boolean deletePost(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.Nullable
    java.lang.Integer post_id) {
        return false;
    }
    
    public final boolean deletePost(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.Nullable
    java.lang.Integer post_id, @org.jetbrains.annotations.Nullable
    java.lang.String post_type) {
        return false;
    }
}