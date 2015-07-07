package com.zykj.landous.adapter;

import java.util.ArrayList;
import java.util.Map;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.BeeFramework.adapter.BeeBaseAdapter;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.zykj.landous.activity.F0_huodong;
import com.zykj.landous.activity.G0_Tiyanguan;
import com.zykj.landous.adapter.F0_ActivityListAdapter.CarClick;
import com.zykj.xiangyangju.R;

public class G0_TiYanGuanListAdapter extends BeeBaseAdapter {
	private ArrayList<Map<String, String>> dataList;
	private Context c;
	private LayoutInflater listContainer;
	private LinearLayout ll_spcar;
	private SharedPreferences shared;
	private SharedPreferences.Editor editor;
	String expr_longi,expr_lati,expr_location,url,title,content,tel;

	public G0_TiYanGuanListAdapter(Context c, ArrayList<Map<String, String>> dataList) {
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
		return dataList.size();
//		return 5;
	}

	@Override
	public View createCellView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public View getView(final int position, View cellView, ViewGroup parent) {
		if (cellView == null) {
			cellView = listContainer.inflate(R.layout.g0_tiyanguanlistitem, null);
		}
		TextView tv_activity_title = (TextView) cellView.findViewById(R.id.tv_activity_title);
		TextView tv_activity_content = (TextView) cellView.findViewById(R.id.tv_activity_content);
		ImageView iv_activity_pic = (ImageView) cellView.findViewById(R.id.iv_activity_pic);
		ImageView phone = (ImageView) cellView.findViewById(R.id.phone);
		String phone_number = dataList.get(position).get("expr_tel").toString().trim();
		phone.setOnClickListener(new Call(phone_number));//拨打电话
		
		url = dataList.get(position).get("expr_img").toString().trim();
		title = dataList.get(position).get("expr_name").toString().trim();
		content = dataList.get(position).get("expr_abstract").toString().trim();
	    expr_longi = dataList.get(position).get("expr_longi").toString().trim();
		expr_lati = dataList.get(position).get("expr_lati").toString().trim();
		expr_location = dataList.get(position).get("expr_location").toString().trim();
		tel = dataList.get(position).get("expr_tel").toString().trim();
		cellView.setOnClickListener(new ToDetail(title,content,expr_longi,expr_lati,expr_location,tel));//拨打电话
		ImageLoader.getInstance().displayImage(url, iv_activity_pic);
		tv_activity_title.setText(title);
		tv_activity_content.setText(content);

		
		return cellView;

	}
	class ToDetail implements View.OnClickListener {
		String title,content,expr_longi,expr_lati,expr_location,tel ;
		public ToDetail(String title,String content,String expr_longi,String expr_lati,String expr_location,String tel) {
			this.title = title;
			this.content = content;
			this.expr_longi = expr_longi;
			this.expr_lati = expr_lati;
			this.expr_location = expr_location;
			this.tel = tel;
		}
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(mContext,G0_Tiyanguan.class);
//			//传一些参数过去
			intent.putExtra("title", title);
			intent.putExtra("content", content);
			intent.putExtra("expr_longi", expr_longi);
			intent.putExtra("expr_lati", expr_lati);
			intent.putExtra("expr_location", expr_location);
			intent.putExtra("tel", tel);
			c.startActivity(intent);
		}

	}
	class  Call implements View.OnClickListener {
		String phone_number;
		public Call(String phone_number) {
			this.phone_number = phone_number;
		}
		@Override
		public void onClick(View v) {
			AlertDialog.Builder builder1 = new Builder(c);
			builder1.setTitle("呼叫客服:"+phone_number);
			builder1.setPositiveButton("确定", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO Auto-generated method stub
					// 传入服务， parse（）解析号码
					Intent intent = new Intent(Intent.ACTION_CALL, Uri
							.parse("tel:" + phone_number));
					// 通知activtity处理传入的call服务
					c.startActivity(intent);
				}
			}).setNegativeButton("取消", null);
			builder1.create().show();
			
		}
		
	}

}
