package com.newdegitaldunia.AndroidTorch;

import android.content.Context;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;


/**
 * Created by Atechnos on 11/23/2016.
 */

public class Adds_file {

    static InterstitialAd mInterstitialAd;
    static int counter = 0;


    public static void showInterStitialAd(Context ctx){
        counter ++;
        if(counter % 1 == 0){
            mInterstitialAd = new InterstitialAd(ctx);
            mInterstitialAd.setAdUnitId(ctx.getString(R.string.interstitial_ad_id));
            AdRequest adRequest = new AdRequest.Builder()
                    .build();


            mInterstitialAd.loadAd(adRequest);
            mInterstitialAd.setAdListener(new AdListener() {
                public void onAdLoaded() {
                    showInterstitial();
                }
            });
        }
    }

    private static void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }
}

