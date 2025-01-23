package com.tpsmedia.appmanager;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 2, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0005\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0014\u0010\u0004\u001a\u00020\u0001X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0003\"\u0014\u0010\u0006\u001a\u00020\u0001X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0003\"\u001a\u0010\b\u001a\u00020\tX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r\u00a8\u0006\u000e"}, d2 = {"DEFAULT_INTERSTITIAL_INTERVAL", "", "getDEFAULT_INTERSTITIAL_INTERVAL", "()I", "DEFAULT_NATIVE_INTERVAL", "getDEFAULT_NATIVE_INTERVAL", "DEFAULT_NATIVE_START", "getDEFAULT_NATIVE_START", "DEFAULT_PRIORITY", "", "getDEFAULT_PRIORITY", "()Ljava/lang/String;", "setDEFAULT_PRIORITY", "(Ljava/lang/String;)V", "AppManager_debug"})
public final class ServerManagerKt {
    private static final int DEFAULT_NATIVE_START = 2;
    private static final int DEFAULT_NATIVE_INTERVAL = 8;
    private static final int DEFAULT_INTERSTITIAL_INTERVAL = 0;
    @org.jetbrains.annotations.NotNull
    private static java.lang.String DEFAULT_PRIORITY = "0,1,2,3";
    
    public static final int getDEFAULT_NATIVE_START() {
        return 0;
    }
    
    public static final int getDEFAULT_NATIVE_INTERVAL() {
        return 0;
    }
    
    public static final int getDEFAULT_INTERSTITIAL_INTERVAL() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String getDEFAULT_PRIORITY() {
        return null;
    }
    
    public static final void setDEFAULT_PRIORITY(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
}