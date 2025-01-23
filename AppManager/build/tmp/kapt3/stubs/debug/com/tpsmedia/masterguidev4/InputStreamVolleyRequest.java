package com.tpsmedia.masterguidev4;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B/\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u00a2\u0006\u0002\u0010\u000bJ\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0002H\u0014J\b\u0010\u000f\u001a\u00020\u0006H\u0016J\u0014\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0011H\u0016J\u0016\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00020\u00132\u0006\u0010\u000e\u001a\u00020\u0014H\u0014R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/tpsmedia/masterguidev4/InputStreamVolleyRequest;", "Lcom/android/volley/Request;", "", "method", "", "url", "", "listener", "Lcom/android/volley/Response$Listener;", "errorListener", "Lcom/android/volley/Response$ErrorListener;", "(ILjava/lang/String;Lcom/android/volley/Response$Listener;Lcom/android/volley/Response$ErrorListener;)V", "deliverResponse", "", "response", "getBodyContentType", "getHeaders", "", "parseNetworkResponse", "Lcom/android/volley/Response;", "Lcom/android/volley/NetworkResponse;", "AppManager_debug"})
public final class InputStreamVolleyRequest extends com.android.volley.Request<byte[]> {
    private final com.android.volley.Response.Listener<byte[]> listener = null;
    
    public InputStreamVolleyRequest(int method, @org.jetbrains.annotations.NotNull
    java.lang.String url, @org.jetbrains.annotations.NotNull
    com.android.volley.Response.Listener<byte[]> listener, @org.jetbrains.annotations.Nullable
    com.android.volley.Response.ErrorListener errorListener) {
        super(null, null);
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    protected com.android.volley.Response<byte[]> parseNetworkResponse(@org.jetbrains.annotations.NotNull
    com.android.volley.NetworkResponse response) {
        return null;
    }
    
    @java.lang.Override
    protected void deliverResponse(@org.jetbrains.annotations.NotNull
    byte[] response) {
    }
    
    @org.jetbrains.annotations.NotNull
    @kotlin.jvm.Throws(exceptionClasses = {com.android.volley.AuthFailureError.class})
    @java.lang.Override
    public java.util.Map<java.lang.String, java.lang.String> getHeaders() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public java.lang.String getBodyContentType() {
        return null;
    }
}