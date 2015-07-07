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
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.external.maxwin.view.XListView.IXListViewListener;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.zykj.landous.LandousAppConst;
import com.zykj.landous.Tools.HttpUtils;
import com.zykj.landous.activity.G0_Tiyanguan;
import com.zykj.landous.adapter.G0_TiYanGuanListAdapter;
import com.zykj.landous.view.MyListView;
import com.zykj.xiangyangju.R;

/**
 * 体验馆
 * 
 * @author zyk
 * 
 */
public class G0_TiYanGuanFragment extends Fragment implements OnClickListener, IXListViewListener {

	private MyListView listView;
	private View view;
	ArrayList<Map<String, String>> data = new ArrayList<Map<String, String>>();
	G0_TiYanGuanListAdapter adapter;
    Context mContext;
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
		view = inflater.inflate(R.layout.g0_tiyanguan_fragment, null);

		adapter = new G0_TiYanGuanListAdapter(mContext,data);
		listView = (MyListView) view.findViewById(R.id.tiyanguan_list);
		listView.setPullLoadEnable(false);
		listView.setPullRefreshEnable(true);
		listView.setXListViewListener(this, 0);
		listView.setAdapter(adapter);
//	    listView.setOnItemClickListener(new OnItemClickListener(){  
//			@Override
//			public void onItemClick(AdapterView<?> arg0, View arg1, int position,long id) {
//				// TODO Auto-generated method stub
////				Toast.makeText(mContext, "跳转到"+position, 500).show();
//				Intent intent = new Intent(mContext,G0_Tiyanguan.class);
//				//传一些参数过去
//				
//				startActivity(intent);
//			 }  
//       });  
//		slideshowView_f0 = (SlideShowView) view.findViewById(R.id.slideshowView_f0);
//		LayoutParams lp = slideshowView_f0.getLayoutParams();
//		DisplayMetrics metric = new DisplayMetrics();
//		getActivity().getWindowManager().getDefaultDisplay().getMetrics(metric);
//		int width = metric.widthPixels; // 屏幕宽度（像素）
//		lp.height = width/5*2;
//		slideshowView_f0.setLayoutParams(lp);
//      HttpUtils.//访问活动接口
	    loadingPDialog = new ProgressDialog(getActivity());
		loadingPDialog.setMessage("正在加载....");
		loadingPDialog.setCancelable(false);
		loadingPDialog.show();
        HttpUtils.getExpList(res_getExpList);
		return view;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRefresh(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLoadMore(int id) {
		// TODO Auto-generated method stub
		
	}


	JsonHttpResponseHandler res_getExpList = new JsonHttpResponseHandler() {
		public void onSuccess(int statusCode, org.apache.http.Header[] headers, JSONObject response) {
			super.onSuccess(statusCode, headers, response);
			Log.e("体验馆", response+"");
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
						map.put("expr_longi", jsonItem.getString("expr_longi"));
						map.put("expr_lati", jsonItem.getString("expr_lati"));
						map.put("expr_location", jsonItem.getString("expr_location"));
						map.put("expr_abstract", jsonItem.getString("expr_abstract"));
						map.put("expr_name", jsonItem.getString("expr_name"));
						map.put("expr_tel", jsonItem.getString("expr_tel"));
						map.put("expr_img", LandousAppConst.act_img_head+jsonItem.getString("expr_img"));
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
