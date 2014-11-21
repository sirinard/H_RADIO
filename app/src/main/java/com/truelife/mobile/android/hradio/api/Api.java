package com.truelife.mobile.android.hradio.api;

import android.content.Context;
import android.util.Log;

import com.truelife.mobile.android.hradio.R;

/**
 * Created by Sirinard_cho on 20-Nov-14.
 */
public class Api {

    Context context;
    private String TAG = getClass().getSimpleName();

    public Api(Context context){
        this.context = context;
    }

    public String getApiSpringBoard() {
        StringBuilder br = new StringBuilder();

        br.append("http://widget3.truelife.com/truelifes/services/rest/?method=springboard");
        br.append("&appname=").append(context.getResources().getString(R.string.app_name));
        br.append("&appversion=").append(context.getResources().getString(R.string.app_version));
        br.append("&app_id=").append(context.getResources().getString(R.string.springboard_id));
        br.append("&device=").append("Android");
        br.append("&format=").append("json");
        br.append("&config_ver=").append(context.getResources().getString(R.string.app_version));
        Log.i(TAG, br.toString());
        return br.toString();
    }
}
