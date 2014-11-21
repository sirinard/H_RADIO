package com.truelife.mobile.android.hradio;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.truelife.mobile.android.hradio.util.Util;

/**
 * Created by Sirinard_cho on 21-Nov-14.
 */
public class RadioHomeActivity extends CoreFragment implements View.OnClickListener {

    private TextView header_title;
    private ImageView header_image_left;
    private String header = "";

    public RadioHomeActivity(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.radiohomeactivity, container, false);

        header_title = (TextView) view.findViewById(R.id.header_title);
        header_image_left = (ImageView) view.findViewById(R.id.header_image_left);

        header_title.setTypeface(Util.getTBoldFont(getActivity()));
        header_image_left.setOnClickListener(RadioHomeActivity.this);

        return view;

    }

    @Override
    public void onClick(View v) {
        if(v == header_image_left){
            toggleLeftMenu();
        }
    }
}
