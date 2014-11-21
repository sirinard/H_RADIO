package com.truelife.mobile.android.hradio;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.truelife.mobile.android.hradio.adapter.SpringboardListAdapter;
import com.truelife.mobile.android.hradio.api.Api;
import com.truelife.mobile.android.hradio.dataset.SBEntry;
import com.truelife.mobile.android.hradio.jsonparser.SpringBoardParser;

import org.json.JSONObject;

import java.util.ArrayList;

public class HomeActivity extends CoreActivity{
	
	private DrawerLayout mDrawerLayout;
	private FrameLayout content_frame;
    private ListView mDrawerList;
    private RelativeLayout left_drawer;
	
    private Api api;
    private AQuery aq;
    private SpringBoardParser springBoardParser;
    private SpringboardListAdapter adapter;
    private AlertDialog.Builder builder = null;
    ProgressDialog dialog;
    Handler handler = new Handler();

    private ArrayList<SBEntry> arrData = new ArrayList<SBEntry>();
    int failedCount = 0;

    String TAG = getClass().getSimpleName();

    Runnable operDrawer = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			mDrawerLayout.openDrawer(left_drawer);
		}
	};

	Runnable closeDrawer = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			mDrawerLayout.closeDrawer(left_drawer);
		}
	};

    @Override
    protected void onResume() {
    	// TODO Auto-generated method stub
    	super.onResume();

	    mDrawerLayout.closeDrawer(left_drawer);

    }

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homeactivity);

		api = new Api(this);
		aq = new AQuery(this);
		springBoardParser = new SpringBoardParser();
//		dialog = new ProgressDialog(this);
//		dialog.setMessage(getString(R.string.loading_msg));
//		dialog.setTitle("");

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		content_frame = (FrameLayout) findViewById(R.id.content_frame);
		left_drawer = (RelativeLayout) findViewById(R.id.left_drawer);
		mDrawerList = (ListView) findViewById(R.id.list);

		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
//        mDrawerLayout.setScrimColor(getResources().getColor(android.R.color.white));

		callSpringBoard();

	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if(keyCode == KeyEvent.KEYCODE_BACK) {
            if (mDrawerLayout.isDrawerOpen(left_drawer)) {
                mDrawerLayout.closeDrawer(left_drawer);
            }else{
                showAlertExitDialog();
            }
		}
		return false;
	}

	private void callSpringBoard(){
//		dialog.show();
		aq.ajax(this, api.getApiSpringBoard(), JSONObject.class, new AjaxCallback<JSONObject>(){
			@Override
			public void callback(String url, JSONObject json, AjaxStatus status) {
				if (json != null) {
					try {
						arrData = springBoardParser.getData(json);
						adapter = new SpringboardListAdapter(HomeActivity.this, arrData);
						mDrawerList.setAdapter(adapter);
						mDrawerList.setOnItemClickListener(new OnItemClickListener() {

							@Override
							public void onItemClick(AdapterView<?> parent,
									View view, int position, long id) {

								selectItem(position);

							}
						});

						selectItem(0);

					} catch (Exception e) {
						status.invalidate();
						Log.e(TAG + ".callSpringBoard", "Springboard Error ", e);
					}

				} else {
					status.invalidate();
					Log.e(TAG + ".callSpringBoard", "springBoardCallback : Fail!!!");
				}
//				dialog.dismiss();
				if(arrData.size() == 0){
	                 status.invalidate();

			         if(failedCount < 5){
			             callSpringBoard();

			         }else{
			             Toast.makeText(HomeActivity.this, "Get SpringBoard Fail!", Toast.LENGTH_SHORT).show();
			             finish();
			         }
	                 failedCount++;
	             }else{
	                 failedCount = 0;
	             }
			}
		});
	}

	private void selectItem(int position) {

		SBEntry entry = arrData.get(position);

		Fragment fragment = null;

		if(entry.getTemplate().equalsIgnoreCase("radio_hit")){
			fragment = (Fragment) new RadioHomeActivity();
//			CoreActivity.trackingStat(this, "Home", "A", entry.getTitle());
		}else if(entry.getTemplate().equalsIgnoreCase("radio_station")){


		}else if(entry.getTemplate().equalsIgnoreCase("radio_local")){


		}else if(entry.getTemplate().equalsIgnoreCase("radio_drama")){


		}else if(entry.getTemplate().equalsIgnoreCase("radio_variety")){


		}else if(entry.getTemplate().equalsIgnoreCase("myfavorite")){


		}else if(entry.getType().equalsIgnoreCase("webview")){


		}

		if(fragment!=null){
			showFragment(fragment, R.id.content_frame);
			mDrawerList.setItemChecked(position, true);
		}

		mDrawerLayout.closeDrawer(left_drawer);
	}

	private void showAlertExitDialog() {

		Builder builder = new AlertDialog.Builder(this);
		builder.setIcon(R.drawable.ic_launcher);
		builder.setTitle(this.getString(R.string.app_displayname));
		builder.setMessage(R.string.exit_alert);
		builder.setPositiveButton(R.string.button_ok, new DialogInterface.OnClickListener(){
			//
			public void onClick(DialogInterface dialog, int id) {
				finish();
			}
		});
		builder.setNegativeButton(R.string.button_cancel, null);
		builder.show();
	}

	public void toggleLeftMenu() {
		if (mDrawerLayout.isDrawerOpen(left_drawer)) {
			mDrawerLayout.closeDrawer(left_drawer);
		}else{
			mDrawerLayout.openDrawer(left_drawer);
		}
	}

}
