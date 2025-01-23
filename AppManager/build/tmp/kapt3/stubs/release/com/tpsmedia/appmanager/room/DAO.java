package com.tpsmedia.appmanager.room;

import java.lang.System;

@androidx.room.Dao
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\'J\u0010\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u0003H\'J\u0010\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0003H\'J\u0010\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u0003H\'J\u0010\u0010\u000f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u0003H\'J\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\t0\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\'J\u001e\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\t0\u00112\u0006\u0010\r\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u0013H\'J\u001e\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\t0\u00112\u0006\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u0013H\'J\u0010\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u0013H\'J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\b\u001a\u00020\tH\'J\b\u0010\u001a\u001a\u00020\u0007H\'J\u0010\u0010\u001b\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u0013H\'J!\u0010\u001c\u001a\u00020\u00072\u0012\u0010\u001d\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\u001e\"\u00020\tH\'\u00a2\u0006\u0002\u0010\u001fR\u0014\u0010\u0002\u001a\u00020\u00038gX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005\u00a8\u0006 "}, d2 = {"Lcom/tpsmedia/appmanager/room/DAO;", "", "getPostsCount", "", "getGetPostsCount", "()I", "delete", "", "tablePosts", "Lcom/tpsmedia/appmanager/room/TablePosts;", "deletePost", "id", "deletePostId", "post_id", "getPost", "getPostId", "getPosts", "", "filter", "", "getPostsParent", "getPostsType", "post_type", "getPostsTypeCount", "insertPost", "", "removeAllPosts", "removeAllPostsType", "updatePost", "tablePost", "", "([Lcom/tpsmedia/appmanager/room/TablePosts;)V", "AppManager_release"})
public abstract interface DAO {
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "SELECT * FROM table_posts WHERE post_title LIKE  \'%\'||:filter||\'%\' OR post_content LIKE  \'%\'||:filter||\'%\'")
    public abstract java.util.List<com.tpsmedia.appmanager.room.TablePosts> getPosts(@org.jetbrains.annotations.NotNull
    java.lang.String filter);
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "SELECT * FROM table_posts WHERE post_type=:post_type AND (post_title LIKE  \'%\'||:filter||\'%\' OR post_content LIKE  \'%\'||:filter||\'%\')")
    public abstract java.util.List<com.tpsmedia.appmanager.room.TablePosts> getPostsType(@org.jetbrains.annotations.NotNull
    java.lang.String post_type, @org.jetbrains.annotations.NotNull
    java.lang.String filter);
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "SELECT * FROM table_posts WHERE post_parent=:post_id AND (post_title LIKE  \'%\'||:filter||\'%\' OR post_content LIKE  \'%\'||:filter||\'%\')")
    public abstract java.util.List<com.tpsmedia.appmanager.room.TablePosts> getPostsParent(int post_id, @org.jetbrains.annotations.NotNull
    java.lang.String filter);
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "SELECT * FROM table_posts WHERE id=:id")
    public abstract com.tpsmedia.appmanager.room.TablePosts getPost(int id);
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "SELECT * FROM table_posts WHERE post_id=:post_id")
    public abstract com.tpsmedia.appmanager.room.TablePosts getPostId(int post_id);
    
    @androidx.room.Query(value = "SELECT COUNT(id) FROM table_posts")
    public abstract int getGetPostsCount();
    
    @androidx.room.Query(value = "SELECT COUNT(id) FROM table_posts  WHERE post_type=:post_type ")
    public abstract int getPostsTypeCount(@org.jetbrains.annotations.NotNull
    java.lang.String post_type);
    
    @androidx.room.Insert(onConflict = 1)
    public abstract long insertPost(@org.jetbrains.annotations.NotNull
    com.tpsmedia.appmanager.room.TablePosts tablePosts);
    
    @androidx.room.Update
    public abstract void updatePost(@org.jetbrains.annotations.NotNull
    com.tpsmedia.appmanager.room.TablePosts... tablePost);
    
    @androidx.room.Query(value = "DELETE FROM table_posts")
    public abstract void removeAllPosts();
    
    @androidx.room.Query(value = "DELETE FROM table_posts WHERE post_type=:post_type ")
    public abstract void removeAllPostsType(@org.jetbrains.annotations.NotNull
    java.lang.String post_type);
    
    @androidx.room.Query(value = "DELETE FROM table_posts WHERE id = :id")
    public abstract void deletePost(int id);
    
    @androidx.room.Query(value = "DELETE FROM table_posts WHERE post_id = :post_id")
    public abstract void deletePostId(int post_id);
    
    @androidx.room.Delete
    public abstract void delete(@org.jetbrains.annotations.NotNull
    com.tpsmedia.appmanager.room.TablePosts tablePosts);
}