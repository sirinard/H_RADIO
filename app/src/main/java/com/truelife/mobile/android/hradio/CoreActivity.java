package com.truelife.mobile.android.hradio;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

/**
 * Created by Sirinard_cho on 20-Nov-14.
 */
public class CoreActivity extends FragmentActivity {

    protected void showFragment(Fragment fragment, int res_id) {
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(res_id, fragment).commit();
        }
    }

}
