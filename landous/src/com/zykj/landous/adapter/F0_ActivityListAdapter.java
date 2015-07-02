package com.zykj.landous.adapter;

import java.util.ArrayList;
import java.util.Map;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.BeeFramework.BeeFrameworkApp;
import com.BeeFramework.adapter.BeeBaseAdapter;
import com.external.activeandroid.util.Log;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.zykj.landous.Tools.HttpUtils;
import com.zykj.landous.activity.B2_ProductdetailsActivity;
import com.zykj.landous.activity.E6_SigninActivity;
import com.zykj.xiangyangju.R;

public class F0_ActivityListAdapter extends BeeBaseAdapter {
	private ArrayList<Map<String, String>> dataList;
	private Context c;
	private LayoutInflater listContainer;
	private LinearLayout ll_spcar;
	private SharedPreferences shared;
	private SharedPreferences.Editor editor;

	public F0_ActivityListAdapter(Context c, ArrayList<Map<String, String>> dataList) {
		super(c, dataList);
		// TODO Auto-generated constructor stub
		this.dataList = dataList;
		this.c = c;
		listContainer = LayoutInflater.from(c);
	}

	@Override
	protected BeeCellHolder createCellHolder(View cellView) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getViewTypeCount() {

		return 1000;
	}

	@Override
	protected View bindData(int position, View cellView, ViewGroup parent,
			BeeCellHolder h) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getItemViewType(int position) {
		return position;
	}

	public int getCount() {
		// TODO Auto-generated method stub
//		return dataList.size();
		return 5;
	}

	@Override
	public View createCellView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public View getView(final int position, View cellView, ViewGroup parent) {
		if (cellView == null) {
			cellView = listContainer.inflate(R.layout.f0_activitylistitem, null);
		}
		TextView tv_activity_title = (TextView) cellView.findViewById(R.id.tv_activity_title);
		TextView tv_activity_content = (TextView) cellView.findViewById(R.id.tv_activity_content);
		ImageView iv_activity_pic = (ImageView) cellView.findViewById(R.id.iv_activity_pic);
//		String url = dataList.get(position).get("goods_image") + "";
//		ImageLoader.getInstance().displayImage(url, iv_activity_pic,
//				BeeFrameworkApp.options_head);
		return cellView;

	}




}
