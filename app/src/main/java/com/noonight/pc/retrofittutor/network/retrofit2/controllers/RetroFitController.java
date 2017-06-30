package com.noonight.pc.retrofittutor.network.retrofit2.controllers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.noonight.pc.retrofittutor.Constants.PreferenceKey;
import com.noonight.pc.retrofittutor.DebugHelper.DebugHelper;
import com.noonight.pc.retrofittutor.network.retrofit2.ServiceGenerator;

/**
 * Created by ayur on 30.06.2017.
 */

public class RetroFitController {

    private Context mContext;

    private SharedPreferences preferences;

    public RetroFitController(Context mContext) {
        this.mContext = mContext;
    }

    public String getData(Class mAPIService, Class mClass) {

        return new String("some text here");
    }

    private void changeUrl() {
        preferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        String newUrl = preferences.getString(PreferenceKey.KEY_ADDRESS, "");
        try {
            ServiceGenerator.changeApiBaseUrl(newUrl);
        }catch (Exception e) {
            DebugHelper.LogD(getClass(), "some error: " + e);
        }
    }
}
