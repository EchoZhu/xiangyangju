package com.zykj.landous.fragment;

import java.util.ArrayList;
import java.util.Map;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ListView;

import com.external.maxwin.view.XListView.IXListViewListener;
import com.zykj.landous.adapter.F0_ActivityListAdapter;
import com.zykj.landous.view.MyListView;
import com.zykj.landous.view.SlideShowView;
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
//		slideshowView_f0 = (SlideShowView) view.findViewById(R.id.slideshowView_f0);
//		LayoutParams lp = slideshowView_f0.getLayoutParams();
//		DisplayMetrics metric = new DisplayMetrics();
//		getActivity().getWindowManager().getDefaultDisplay().getMetrics(metric);
//		int width = metric.widthPixels; // 屏幕宽度（像素）
//		lp.height = width/5*2;
//		slideshowView_f0.setLayoutParams(lp);
//        HttpUtils.//访问活动接口
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


//	JsonHttpResponseHandler res = new JsonHttpResponseHandler() {
//
//		@Override
//		public void onSuccess(int statusCode, Header[] headers,
//				JSONObject response) {
//			super.onSuccess(statusCode, headers, response);
//
//			int result = 0;
//
//			try {
//				result = Integer.valueOf(response.getString("result"));
//
//			} catch (NumberFormatException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (JSONException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			if (result == 1 && statusCode == 200) {
//
//				loadingPDialog.dismiss();
//
//				AlertDialog.Builder builder = new Builder(getActivity());
//				builder.setTitle("签到成功");
//
//				builder.setNegativeButton("确认",
//						new DialogInterface.OnClickListener() {
//
//							public void onClick(DialogInterface dialog,
//									int which) {
//								profile_check
//										.setImageResource(R.drawable.profile_checked);
//								HttpUtils.refreshUserInfo(res_refresh);
//							}
//
//						});
//				builder.create().show();
//
//			} else {
//				loadingPDialog.dismiss();
//				String message = "";
//				try {
//					message = response.getString("message");
//				} catch (JSONException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				AlertDialog.Builder builder = new Builder(getActivity());
//				builder.setTitle(message);
//
//				builder.setNegativeButton("确认",
//						new DialogInterface.OnClickListener() {
//
//							public void onClick(DialogInterface dialog,
//									int which) {
//
//							}
//
//						});
//				builder.create().show();
//				Log.i("check_fail", response.toString());
//
//			}
//		}
//
//		@Override
//		public void onFailure(int statusCode, Header[] headers,
//				Throwable throwable, JSONObject errorResponse) {
//			// TODO Auto-generated method stub
//			Toast.makeText(getActivity(), "网络连接超时", Toast.LENGTH_LONG).show();
//			super.onFailure(statusCode, headers, throwable, errorResponse);
//			loadingPDialog.dismiss();
//		}
//	};

}
