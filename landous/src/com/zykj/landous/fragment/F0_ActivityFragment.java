package com.zykj.landous.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;

import com.external.maxwin.view.XListView.IXListViewListener;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.zykj.landous.LandousAppConst;
import com.zykj.landous.Tools.HttpUtils;
import com.zykj.landous.activity.F0_huodong;
import com.zykj.landous.adapter.F0_ActivityListAdapter;
import com.zykj.landous.view.MyListView;
import com.zykj.xiangyangju.R;

/**
 * 活动
 * 
 * @author zyk
 * 
 */
public class F0_ActivityFragment extends Fragment implements OnClickListener, IXListViewListener {

	private MyListView listView;
	private View view;
	ArrayList<Map<String, String>> data = new ArrayList<Map<String, String>>();
	F0_ActivityListAdapter adapter;
    Context mContext;
    ImageView iv_tiyanguan;
	private ProgressDialog loadingPDialog = null;
	// 轮播图
//	private SlideShowView slideshowView_f0;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext=getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.f0_activity_fragment, null);
		listView = (MyListView) view.findViewById(R.id.activity_list);
		iv_tiyanguan = (ImageView) view.findViewById(R.id.iv_tiyanguan);
		listView.setPullLoadEnable(false);
		listView.setPullRefreshEnable(true);
		listView.setXListViewListener(this, 0);
		listView.setAdapter(adapter);
		adapter = new F0_ActivityListAdapter(mContext,data);
		listView.setAdapter(adapter);
		loadingPDialog = new ProgressDialog(getActivity());
		loadingPDialog.setMessage("正在加载....");
		loadingPDialog.setCancelable(false);
		loadingPDialog.show();
        HttpUtils.getActList(res_getActList);
		return view;

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRefresh(int id) {
		// TODO Auto-generated method stub
		loadingPDialog.show();
        HttpUtils.getActList(res_getActList);
	}

	@Override
	public void onLoadMore(int id) {
		// TODO Auto-generated method stub
		loadingPDialog.show();
        HttpUtils.getActList(res_getActList);
	}
	JsonHttpResponseHandler res_getActList = new JsonHttpResponseHandler() {
		public void onSuccess(int statusCode, org.apache.http.Header[] headers, JSONObject response) {
			super.onSuccess(statusCode, headers, response);
			Log.e("活动", response+"");
			int result = 0;
			try {
				result = Integer.valueOf(response.getString("result"));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (result == 1 && statusCode == 200) {
				try {
					data.clear();
					JSONArray array = response.getJSONArray("list");
					for (int i = 0; i < array.length(); i++) {
						JSONObject jsonItem = array.getJSONObject(i);
						Map<String, String> map = new HashMap();
						map.put("act_id", jsonItem.getString("act_id"));
						map.put("act_title", jsonItem.getString("act_title"));
						map.put("act_content", jsonItem.getString("act_content"));
						map.put("act_addtime", jsonItem.getString("act_addtime"));
						map.put("act_img", LandousAppConst.act_img_head+jsonItem.getString("act_img"));
						data.add(map);
					}
					adapter.notifyDataSetChanged();

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			loadingPDialog.dismiss();
		};
		
	};

		


}
