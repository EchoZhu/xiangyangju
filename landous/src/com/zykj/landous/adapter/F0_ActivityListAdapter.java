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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.BeeFramework.BeeFrameworkApp;
import com.BeeFramework.activity.StartActivity;
import com.BeeFramework.adapter.BeeBaseAdapter;
import com.external.activeandroid.util.Log;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.zykj.landous.Tools.HttpUtils;
import com.zykj.landous.activity.B2_ProductdetailsActivity;
import com.zykj.landous.activity.E6_SigninActivity;
import com.zykj.landous.activity.F0_huodong;
import com.zykj.landous.adapter.D1_shopping_car_goods_adapter.CarClick;
import com.zykj.xiangyangju.R;

public class F0_ActivityListAdapter extends BeeBaseAdapter {
	private ArrayList<Map<String, String>> dataList;
	private Context c;
	private LinearLayout ll_spcar;
	private SharedPreferences shared;
	private SharedPreferences.Editor editor;
	private String title,content,date;

	public F0_ActivityListAdapter(Context c, ArrayList<Map<String, String>> dataList) {
		super(c, dataList);
		// TODO Auto-generated constructor stub
		this.dataList = dataList;
		this.c = c;
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
		return dataList.size();
//		return 5;
	}

	@Override
	public View createCellView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
        if (null == convertView)
        {
            viewHolder = new ViewHolder();
            LayoutInflater mInflater = LayoutInflater.from(c);
            convertView = mInflater.inflate(R.layout.f0_activitylistitem, null);

            viewHolder.tv_activity_title = (TextView) convertView.findViewById(R.id.tv_activity_title);
            viewHolder.tv_activity_content = (TextView) convertView.findViewById(R.id.tv_activity_content);
            viewHolder.iv_activity_pic = (ImageView) convertView.findViewById(R.id.iv_activity_pic);

            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

		String url = dataList.get(position).get("act_img") + "";
		ImageLoader.getInstance().displayImage(url, viewHolder.iv_activity_pic);
		title =dataList.get(position).get("act_title").toString();
		content = dataList.get(position).get("act_content").toString();
		date = dataList.get(position).get("act_addtime").toString();
		viewHolder.tv_activity_title.setText(title);
		viewHolder.tv_activity_content.setText(content);
		convertView.setOnClickListener(new CarClick(title,content,date));
//		convertView.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View arg0) {
//				// TODO Auto-generated method stub
//				Intent intent1 = new Intent(c,F0_huodong.class);
//				intent1.putExtra("title", title);
//				intent1.putExtra("content", content);
//				c.startActivity(intent1);
//			}
//		});
		return convertView;
	}
	
	private static class ViewHolder
    {
        TextView tv_activity_title;
        TextView tv_activity_content;
        ImageView iv_activity_pic;
    } 
	
	class CarClick implements View.OnClickListener {
		String title;
		String content;
		String date;
		public CarClick(String title,String content,String date) {
			this.title = title;
			this.content = content;
			this.date = date;
		}
		@Override
		public void onClick(View v) {
			Intent intent1 = new Intent(c,F0_huodong.class);
			intent1.putExtra("title", title);
			intent1.putExtra("content", content);
			intent1.putExtra("date", date);
			c.startActivity(intent1);
			
		}

	}
}
