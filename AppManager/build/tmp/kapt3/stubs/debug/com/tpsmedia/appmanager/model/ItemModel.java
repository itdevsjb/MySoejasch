package com.tpsmedia.appmanager.model;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\bP\n\u0002\u0010\b\n\u0002\b5\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\bR\u001a\u0010\u000f\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR\u001a\u0010\u0012\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\bR\u001a\u0010\u0015\u001a\u00020\u0016X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0006\"\u0004\b\u001d\u0010\bR\u001a\u0010\u001e\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0006\"\u0004\b \u0010\bR\u001a\u0010!\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0006\"\u0004\b#\u0010\bR\u001a\u0010$\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0006\"\u0004\b&\u0010\bR\u001a\u0010\'\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0006\"\u0004\b)\u0010\bR\u001a\u0010*\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\u0006\"\u0004\b,\u0010\bR\u001a\u0010-\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\u0006\"\u0004\b/\u0010\bR\u001a\u00100\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b1\u0010\u0006\"\u0004\b2\u0010\bR\u001a\u00103\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\u0006\"\u0004\b5\u0010\bR\u001a\u00106\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b7\u0010\u0006\"\u0004\b8\u0010\bR\u001a\u00109\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b:\u0010\u0006\"\u0004\b;\u0010\bR\u001a\u0010<\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b=\u0010\u0006\"\u0004\b>\u0010\bR\u001a\u0010?\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b@\u0010\u0006\"\u0004\bA\u0010\bR\u001a\u0010B\u001a\u00020\u0016X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bC\u0010\u0018\"\u0004\bD\u0010\u001aR\u001a\u0010E\u001a\u00020\u0016X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bF\u0010\u0018\"\u0004\bG\u0010\u001aR\u001a\u0010H\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bI\u0010\u0006\"\u0004\bJ\u0010\bR\u001a\u0010K\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bL\u0010\u0006\"\u0004\bM\u0010\bR\u001a\u0010N\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bO\u0010\u0006\"\u0004\bP\u0010\bR\u001a\u0010Q\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bR\u0010\u0006\"\u0004\bS\u0010\bR\u001a\u0010T\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bU\u0010\u0006\"\u0004\bV\u0010\bR\u001a\u0010W\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bX\u0010\u0006\"\u0004\bY\u0010\bR\u001a\u0010Z\u001a\u00020\u0016X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b[\u0010\u0018\"\u0004\b\\\u0010\u001aR\u001a\u0010]\u001a\u00020\u0016X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b^\u0010\u0018\"\u0004\b_\u0010\u001aR\u001a\u0010`\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\ba\u0010\u0006\"\u0004\bb\u0010\bR\u001a\u0010c\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bd\u0010\u0006\"\u0004\be\u0010\bR\u001a\u0010f\u001a\u00020gX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bh\u0010i\"\u0004\bj\u0010kR\u001a\u0010l\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bm\u0010\u0006\"\u0004\bn\u0010\bR\u001a\u0010o\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bp\u0010\u0006\"\u0004\bq\u0010\bR\u001a\u0010r\u001a\u00020gX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bs\u0010i\"\u0004\bt\u0010kR\u001a\u0010u\u001a\u00020gX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bv\u0010i\"\u0004\bw\u0010kR\u001a\u0010x\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\by\u0010\u0006\"\u0004\bz\u0010\bR\u001a\u0010{\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b|\u0010\u0006\"\u0004\b}\u0010\bR\u001b\u0010~\u001a\u00020\u0016X\u0086\u000e\u00a2\u0006\u000f\n\u0000\u001a\u0004\b\u007f\u0010\u0018\"\u0005\b\u0080\u0001\u0010\u001aR\u001d\u0010\u0081\u0001\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u0082\u0001\u0010\u0006\"\u0005\b\u0083\u0001\u0010\bR\u001d\u0010\u0084\u0001\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u0085\u0001\u0010\u0006\"\u0005\b\u0086\u0001\u0010\bR\u001d\u0010\u0087\u0001\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u0088\u0001\u0010\u0006\"\u0005\b\u0089\u0001\u0010\bR\u001d\u0010\u008a\u0001\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u008b\u0001\u0010\u0006\"\u0005\b\u008c\u0001\u0010\bR\u001d\u0010\u008d\u0001\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u008e\u0001\u0010\u0006\"\u0005\b\u008f\u0001\u0010\bR\u001d\u0010\u0090\u0001\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u0091\u0001\u0010\u0006\"\u0005\b\u0092\u0001\u0010\bR\u001d\u0010\u0093\u0001\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u0094\u0001\u0010\u0006\"\u0005\b\u0095\u0001\u0010\bR\u001d\u0010\u0096\u0001\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u0097\u0001\u0010\u0006\"\u0005\b\u0098\u0001\u0010\bR\u001d\u0010\u0099\u0001\u001a\u00020\u0016X\u0086\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u009a\u0001\u0010\u0018\"\u0005\b\u009b\u0001\u0010\u001a\u00a8\u0006\u009c\u0001"}, d2 = {"Lcom/tpsmedia/appmanager/model/ItemModel;", "", "()V", "DEEPAI_API_KEY", "", "getDEEPAI_API_KEY", "()Ljava/lang/String;", "setDEEPAI_API_KEY", "(Ljava/lang/String;)V", "IMAGINE_API_KEY", "getIMAGINE_API_KEY", "setIMAGINE_API_KEY", "ONESIGNAL_APP_ID", "getONESIGNAL_APP_ID", "setONESIGNAL_APP_ID", "OPENAI_API_KEY", "getOPENAI_API_KEY", "setOPENAI_API_KEY", "admob_banner", "getAdmob_banner", "setAdmob_banner", "admob_gdpr", "", "getAdmob_gdpr", "()Z", "setAdmob_gdpr", "(Z)V", "admob_interstitial", "getAdmob_interstitial", "setAdmob_interstitial", "admob_native", "getAdmob_native", "setAdmob_native", "admob_open_ads", "getAdmob_open_ads", "setAdmob_open_ads", "admob_rewarded_ads", "getAdmob_rewarded_ads", "setAdmob_rewarded_ads", "applovin_banner", "getApplovin_banner", "setApplovin_banner", "applovin_interstitial", "getApplovin_interstitial", "setApplovin_interstitial", "applovin_native", "getApplovin_native", "setApplovin_native", "applovin_open_ads", "getApplovin_open_ads", "setApplovin_open_ads", "applovin_rewarded_ads", "getApplovin_rewarded_ads", "setApplovin_rewarded_ads", "applovin_sdk_key", "getApplovin_sdk_key", "setApplovin_sdk_key", "asset_folder", "getAsset_folder", "setAsset_folder", "asset_storage", "getAsset_storage", "setAsset_storage", "asset_url", "getAsset_url", "setAsset_url", "detail_banner", "getDetail_banner", "setDetail_banner", "detail_native", "getDetail_native", "setDetail_native", "detail_native_view", "getDetail_native_view", "setDetail_native_view", "detail_priority", "getDetail_priority", "setDetail_priority", "facebook_banner", "getFacebook_banner", "setFacebook_banner", "facebook_interstitial", "getFacebook_interstitial", "setFacebook_interstitial", "facebook_native", "getFacebook_native", "setFacebook_native", "facebook_rewarded_ads", "getFacebook_rewarded_ads", "setFacebook_rewarded_ads", "home_banner", "getHome_banner", "setHome_banner", "home_native", "getHome_native", "setHome_native", "home_native_view", "getHome_native_view", "setHome_native_view", "home_priority", "getHome_priority", "setHome_priority", "interstitial_interval", "", "getInterstitial_interval", "()I", "setInterstitial_interval", "(I)V", "interstitial_priority", "getInterstitial_priority", "setInterstitial_priority", "more_app", "getMore_app", "setMore_app", "native_interval", "getNative_interval", "setNative_interval", "native_start", "getNative_start", "setNative_start", "privacy_policy", "getPrivacy_policy", "setPrivacy_policy", "redirect_button", "getRedirect_button", "setRedirect_button", "redirect_cancelable", "getRedirect_cancelable", "setRedirect_cancelable", "redirect_content", "getRedirect_content", "setRedirect_content", "redirect_image_url", "getRedirect_image_url", "setRedirect_image_url", "redirect_title", "getRedirect_title", "setRedirect_title", "redirect_url", "getRedirect_url", "setRedirect_url", "unity_banner", "getUnity_banner", "setUnity_banner", "unity_game_id", "getUnity_game_id", "setUnity_game_id", "unity_interstitial", "getUnity_interstitial", "setUnity_interstitial", "unity_rewarded_ads", "getUnity_rewarded_ads", "setUnity_rewarded_ads", "unity_test_mode", "getUnity_test_mode", "setUnity_test_mode", "AppManager_debug"})
public final class ItemModel {
    @org.jetbrains.annotations.NotNull
    private java.lang.String more_app = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String privacy_policy = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String asset_folder = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String asset_url = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String asset_storage = "";
    private int interstitial_interval;
    private int native_start;
    private int native_interval;
    @org.jetbrains.annotations.NotNull
    private java.lang.String interstitial_priority;
    private boolean home_banner = true;
    private boolean home_native = true;
    @org.jetbrains.annotations.NotNull
    private java.lang.String home_native_view = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String home_priority;
    private boolean detail_banner = true;
    private boolean detail_native = true;
    @org.jetbrains.annotations.NotNull
    private java.lang.String detail_native_view = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String detail_priority;
    @org.jetbrains.annotations.NotNull
    private java.lang.String admob_banner = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String admob_interstitial = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String admob_native = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String admob_open_ads = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String admob_rewarded_ads = "";
    private boolean admob_gdpr = false;
    @org.jetbrains.annotations.NotNull
    private java.lang.String facebook_banner = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String facebook_interstitial = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String facebook_native = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String facebook_rewarded_ads = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String unity_game_id = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String unity_banner = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String unity_interstitial = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String unity_rewarded_ads = "";
    private boolean unity_test_mode = false;
    @org.jetbrains.annotations.NotNull
    private java.lang.String applovin_sdk_key = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String applovin_banner = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String applovin_interstitial = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String applovin_native = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String applovin_rewarded_ads = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String applovin_open_ads = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String redirect_content = "Please update the application to the latest version to get additional features.";
    @org.jetbrains.annotations.NotNull
    private java.lang.String redirect_title = "Update";
    @org.jetbrains.annotations.NotNull
    private java.lang.String redirect_button = "Update";
    @org.jetbrains.annotations.NotNull
    private java.lang.String redirect_image_url = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String redirect_url = "";
    private boolean redirect_cancelable = true;
    @org.jetbrains.annotations.NotNull
    private java.lang.String ONESIGNAL_APP_ID = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String DEEPAI_API_KEY = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String OPENAI_API_KEY = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String IMAGINE_API_KEY = "";
    
    public ItemModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getMore_app() {
        return null;
    }
    
    public final void setMore_app(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getPrivacy_policy() {
        return null;
    }
    
    public final void setPrivacy_policy(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getAsset_folder() {
        return null;
    }
    
    public final void setAsset_folder(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getAsset_url() {
        return null;
    }
    
    public final void setAsset_url(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getAsset_storage() {
        return null;
    }
    
    public final void setAsset_storage(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    public final int getInterstitial_interval() {
        return 0;
    }
    
    public final void setInterstitial_interval(int p0) {
    }
    
    public final int getNative_start() {
        return 0;
    }
    
    public final void setNative_start(int p0) {
    }
    
    public final int getNative_interval() {
        return 0;
    }
    
    public final void setNative_interval(int p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getInterstitial_priority() {
        return null;
    }
    
    public final void setInterstitial_priority(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    public final boolean getHome_banner() {
        return false;
    }
    
    public final void setHome_banner(boolean p0) {
    }
    
    public final boolean getHome_native() {
        return false;
    }
    
    public final void setHome_native(boolean p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getHome_native_view() {
        return null;
    }
    
    public final void setHome_native_view(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getHome_priority() {
        return null;
    }
    
    public final void setHome_priority(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    public final boolean getDetail_banner() {
        return false;
    }
    
    public final void setDetail_banner(boolean p0) {
    }
    
    public final boolean getDetail_native() {
        return false;
    }
    
    public final void setDetail_native(boolean p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getDetail_native_view() {
        return null;
    }
    
    public final void setDetail_native_view(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getDetail_priority() {
        return null;
    }
    
    public final void setDetail_priority(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getAdmob_banner() {
        return null;
    }
    
    public final void setAdmob_banner(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getAdmob_interstitial() {
        return null;
    }
    
    public final void setAdmob_interstitial(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getAdmob_native() {
        return null;
    }
    
    public final void setAdmob_native(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getAdmob_open_ads() {
        return null;
    }
    
    public final void setAdmob_open_ads(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getAdmob_rewarded_ads() {
        return null;
    }
    
    public final void setAdmob_rewarded_ads(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    public final boolean getAdmob_gdpr() {
        return false;
    }
    
    public final void setAdmob_gdpr(boolean p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getFacebook_banner() {
        return null;
    }
    
    public final void setFacebook_banner(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getFacebook_interstitial() {
        return null;
    }
    
    public final void setFacebook_interstitial(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getFacebook_native() {
        return null;
    }
    
    public final void setFacebook_native(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getFacebook_rewarded_ads() {
        return null;
    }
    
    public final void setFacebook_rewarded_ads(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getUnity_game_id() {
        return null;
    }
    
    public final void setUnity_game_id(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getUnity_banner() {
        return null;
    }
    
    public final void setUnity_banner(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getUnity_interstitial() {
        return null;
    }
    
    public final void setUnity_interstitial(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getUnity_rewarded_ads() {
        return null;
    }
    
    public final void setUnity_rewarded_ads(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    public final boolean getUnity_test_mode() {
        return false;
    }
    
    public final void setUnity_test_mode(boolean p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getApplovin_sdk_key() {
        return null;
    }
    
    public final void setApplovin_sdk_key(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getApplovin_banner() {
        return null;
    }
    
    public final void setApplovin_banner(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getApplovin_interstitial() {
        return null;
    }
    
    public final void setApplovin_interstitial(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getApplovin_native() {
        return null;
    }
    
    public final void setApplovin_native(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getApplovin_rewarded_ads() {
        return null;
    }
    
    public final void setApplovin_rewarded_ads(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getApplovin_open_ads() {
        return null;
    }
    
    public final void setApplovin_open_ads(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getRedirect_content() {
        return null;
    }
    
    public final void setRedirect_content(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getRedirect_title() {
        return null;
    }
    
    public final void setRedirect_title(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getRedirect_button() {
        return null;
    }
    
    public final void setRedirect_button(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getRedirect_image_url() {
        return null;
    }
    
    public final void setRedirect_image_url(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getRedirect_url() {
        return null;
    }
    
    public final void setRedirect_url(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    public final boolean getRedirect_cancelable() {
        return false;
    }
    
    public final void setRedirect_cancelable(boolean p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getONESIGNAL_APP_ID() {
        return null;
    }
    
    public final void setONESIGNAL_APP_ID(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getDEEPAI_API_KEY() {
        return null;
    }
    
    public final void setDEEPAI_API_KEY(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getOPENAI_API_KEY() {
        return null;
    }
    
    public final void setOPENAI_API_KEY(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getIMAGINE_API_KEY() {
        return null;
    }
    
    public final void setIMAGINE_API_KEY(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
}