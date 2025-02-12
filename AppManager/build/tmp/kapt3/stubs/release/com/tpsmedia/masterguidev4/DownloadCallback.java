package com.tpsmedia.masterguidev4;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0005H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH&\u00a8\u0006\u000b"}, d2 = {"Lcom/tpsmedia/masterguidev4/DownloadCallback;", "", "onDownloadComplete", "", "filePath", "", "onDownloadFailed", "errorMessage", "onProgressUpdate", "progress", "", "AppManager_release"})
public abstract interface DownloadCallback {
    
    public abstract void onProgressUpdate(int progress);
    
    public abstract void onDownloadComplete(@org.jetbrains.annotations.NotNull
    java.lang.String filePath);
    
    public abstract void onDownloadFailed(@org.jetbrains.annotations.NotNull
    java.lang.String errorMessage);
}