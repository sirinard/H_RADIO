package com.truelife.mobile.android.hradio;

import android.app.Activity;
import android.support.v4.app.Fragment;

/**
 * Created by Sirinard_cho on 21-Nov-14.
 */
public class CoreFragment extends Fragment {

    protected void toggleLeftMenu() {
        Activity activity = getActivity();
        if(activity instanceof HomeActivity){
            ((HomeActivity)activity).toggleLeftMenu();
        }
    }

    protected boolean isHomeActivity() {
        Activity activity = getActivity();
        if(activity instanceof HomeActivity){
            return true;
        }
        return false;
    }

}
