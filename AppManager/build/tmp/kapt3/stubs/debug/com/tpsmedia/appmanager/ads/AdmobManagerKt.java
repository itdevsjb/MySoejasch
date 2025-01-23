package com.tpsmedia.appmanager.ads;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 2, d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\"*\u0010\u0000\u001a\u0012\u0012\u0004\u0012\u00020\u00020\u0001j\b\u0012\u0004\u0012\u00020\u0002`\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007\"\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r\"\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013\"\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000\"\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001b\"\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001e"}, d2 = {"ADMOB_TEST_DEVICE_ID", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "getADMOB_TEST_DEVICE_ID", "()Ljava/util/ArrayList;", "setADMOB_TEST_DEVICE_ID", "(Ljava/util/ArrayList;)V", "admobInterstitial", "Lcom/google/android/gms/ads/interstitial/InterstitialAd;", "getAdmobInterstitial", "()Lcom/google/android/gms/ads/interstitial/InterstitialAd;", "setAdmobInterstitial", "(Lcom/google/android/gms/ads/interstitial/InterstitialAd;)V", "admobRewardedAd", "Lcom/google/android/gms/ads/rewarded/RewardedAd;", "getAdmobRewardedAd", "()Lcom/google/android/gms/ads/rewarded/RewardedAd;", "setAdmobRewardedAd", "(Lcom/google/android/gms/ads/rewarded/RewardedAd;)V", "appOpenAd", "Lcom/google/android/gms/ads/appopen/AppOpenAd;", "consentInformation", "Lcom/google/android/ump/ConsentInformation;", "getConsentInformation", "()Lcom/google/android/ump/ConsentInformation;", "setConsentInformation", "(Lcom/google/android/ump/ConsentInformation;)V", "isShowingOpenAd", "", "AppManager_debug"})
public final class AdmobManagerKt {
    @org.jetbrains.annotations.Nullable
    private static com.google.android.gms.ads.rewarded.RewardedAd admobRewardedAd;
    @org.jetbrains.annotations.Nullable
    private static com.google.android.gms.ads.interstitial.InterstitialAd admobInterstitial;
    private static com.google.android.gms.ads.appopen.AppOpenAd appOpenAd;
    private static boolean isShowingOpenAd = false;
    @org.jetbrains.annotations.Nullable
    private static com.google.android.ump.ConsentInformation consentInformation;
    @org.jetbrains.annotations.NotNull
    private static java.util.ArrayList<java.lang.String> ADMOB_TEST_DEVICE_ID;
    
    @org.jetbrains.annotations.Nullable
    public static final com.google.android.gms.ads.rewarded.RewardedAd getAdmobRewardedAd() {
        return null;
    }
    
    public static final void setAdmobRewardedAd(@org.jetbrains.annotations.Nullable
    com.google.android.gms.ads.rewarded.RewardedAd p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public static final com.google.android.gms.ads.interstitial.InterstitialAd getAdmobInterstitial() {
        return null;
    }
    
    public static final void setAdmobInterstitial(@org.jetbrains.annotations.Nullable
    com.google.android.gms.ads.interstitial.InterstitialAd p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public static final com.google.android.ump.ConsentInformation getConsentInformation() {
        return null;
    }
    
    public static final void setConsentInformation(@org.jetbrains.annotations.Nullable
    com.google.android.ump.ConsentInformation p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public static final java.util.ArrayList<java.lang.String> getADMOB_TEST_DEVICE_ID() {
        return null;
    }
    
    public static final void setADMOB_TEST_DEVICE_ID(@org.jetbrains.annotations.NotNull
    java.util.ArrayList<java.lang.String> p0) {
    }
}