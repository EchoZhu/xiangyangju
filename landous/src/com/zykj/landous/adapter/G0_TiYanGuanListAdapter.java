package com.zykj.landous.adapter;

import java.util.ArrayList;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.BeeFramework.adapter.BeeBaseAdapter;
import com.zykj.xiangyangju.R;

public class G0_TiYanGuanListAdapter extends BeeBaseAdapter {
	private ArrayList<Map<String, String>> dataList;
	private Context c;
	private LayoutInflater listContainer;
	private LinearLayout ll_spcar;
	private SharedPreferences shared;
	private SharedPreferences.Editor editor;

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
			cellView = listContainer.inflate(R.layout.g0_tiyanguanlistitem, null);
		}
		TextView tv_activity_title = (TextView) cellView.findViewById(R.id.tv_activity_title);
		TextView tv_activity_content = (TextView) cellView.findViewById(R.id.tv_activity_content);
		ImageView iv_activity_pic = (ImageView) cellView.findViewById(R.id.iv_activity_pic);
		ImageView phone = (ImageView) cellView.findViewById(R.id.phone);
		phone.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(c, "您即将给"+position+"拨打电话", 500).show();
			}
		});
//		String url = dataList.get(position).get("goods_image") + "";
//		ImageLoader.getInstance().displayImage(url, iv_activity_pic,
//				BeeFrameworkApp.options_head);
		return cellView;

	}
	
	




}
