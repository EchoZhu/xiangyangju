package com.zykj.landous.adapter;

import java.util.ArrayList;
import java.util.Map;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.BeeFramework.BeeFrameworkApp;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.zykj.landous.Data.BaseData;
import com.zykj.landous.Tools.HttpUtils;
import com.zykj.landous.Tools.MathTools;
import com.zykj.landous.activity.B2_ProductdetailsActivity;
import com.zykj.landous.activity.MyDialogActivity;
import com.zykj.landous.alipay.Fiap;
import com.zykj.xiangyangju.R;

/**
 * @author 作者 zyk
 * @version 创建时间：2015年1月12日 下午4:33:28 类说明
 */
public class E1_PaymentAdapter extends BaseAdapter {
	private Activity context;
	ArrayList<Map<String, String>> data;
	private LayoutInflater listContainer;
	LinearLayout ll_gone;

	public E1_PaymentAdapter(Activity context,
			ArrayList<Map<String, String>> data) {
		this.context = context;
		this.data = data;
		listContainer = LayoutInflater.from(context);

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (data == null)
			return 0;
		else
			return data.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(final int position, View view, ViewGroup viewGroup) {
		ViewHolder holder = null;
		if (view == null) {
			view = listContainer.inflate(R.layout.e1_item_payment, null);
			holder = new ViewHolder();
			holder.tv_postage = (TextView) view.findViewById(R.id.tv_postage);
			holder.tv_discount = (TextView) view.findViewById(R.id.tv_discount);
			holder.tv_shop_name = (TextView) view
					.findViewById(R.id.tv_shop_name);
			holder.tv_goods_name = (TextView) view
					.findViewById(R.id.tv_goods_name);
			holder.tv_price = (TextView) view.findViewById(R.id.tv_price);
			holder.tv_num = (TextView) view.findViewById(R.id.tv_num);
			holder.tv_all_price = (TextView) view
					.findViewById(R.id.tv_all_price);
			holder.ll_shop_name = (LinearLayout) view
					.findViewById(R.id.ll_shop_name);
			holder.tv_all_num = (TextView) view.findViewById(R.id.tv_all_num);
			holder.iv_goods_image = (ImageView) view
					.findViewById(R.id.iv_goods_image);
			holder.iv_topay = (ImageView) view.findViewById(R.id.iv_topay);
			holder.btn_cancel = (Button) view.findViewById(R.id.btn_cancel);

			view.setTag(holder);// 绑定ViewHolder对象
		} else {
			holder = (ViewHolder) view.getTag();// 取出ViewHolder对象
		}

		holder.tv_discount.setText("在线支付满" + BaseData.min_total_price + "元优惠"
				+ BaseData.online_pay_discount + "元");
		holder.tv_discount.setVisibility(View.INVISIBLE);

		holder.tv_shop_name.setText("" + data.get(position).get("store_name"));

		holder.tv_goods_name.setText("" + data.get(position).get("goods_name"));

		holder.tv_price.setText("￥" + data.get(position).get("goods_price"));

		holder.tv_num.setText("x" + data.get(position).get("goods_num"));

		double goods_amount = Double.valueOf(data.get(position)
				.get("goods_amount").toString());
		double shipping_fee = Double.valueOf(data.get(position)
				.get("shipping_fee").toString());
		double discount = Double.valueOf(data.get(position).get("discount")
				.toString());
		double money = goods_amount + shipping_fee;
		double actually_money = money > BaseData.min_total_price ? money
				- discount : money;
		holder.tv_all_price.setText("合计:￥"
				+ MathTools.DoubleKeepTwo(actually_money));
		ll_gone = (LinearLayout) view.findViewById(R.id.ll_gone);

		if (data.get(position).get("position").equals("one")) {
			ll_gone.setVisibility(View.VISIBLE);
			holder.ll_shop_name.setVisibility(View.VISIBLE);
		} else if (data.get(position).get("position").equals("head")) {
			ll_gone.setVisibility(View.GONE);
			holder.ll_shop_name.setVisibility(View.VISIBLE);
		} else if (data.get(position).get("position").equals("body")) {
			ll_gone.setVisibility(View.GONE);
			holder.ll_shop_name.setVisibility(View.GONE);
		} else if (data.get(position).get("position").equals("tail")) {
			ll_gone.setVisibility(View.VISIBLE);
			holder.ll_shop_name.setVisibility(View.GONE);
		}
		String str_postage = data.get(position).get("shipping_fee").toString()
				.equals("0.0") ? "免邮" : "邮费:￥"
				+ data.get(position).get("shipping_fee").toString();
		holder.tv_postage.setText(str_postage);

		holder.tv_all_num.setText("共有" + data.get(position).get("all_num")
				+ "件商品");

		String url = data.get(position).get("goods_image") + "";
		ImageLoader.getInstance().displayImage(url, holder.iv_goods_image,
				BeeFrameworkApp.options_head);

		holder.iv_topay.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				double all_price = Double.parseDouble(data.get(position).get(
						"goods_amount"));
				Fiap f = new Fiap(context);
				f.setOrder_id(data.get(position).get("store_name") + "");
				f.android_pay(all_price);

			}
		});

		if (data.get(position).get("lock_state").equals("1")) {
			holder.btn_cancel.setText("订单审核中");
			holder.btn_cancel.setClickable(false);
		} else {
			holder.btn_cancel.setText("取消订单");
			holder.btn_cancel.setClickable(true);
			holder.btn_cancel.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					AlertDialog.Builder builder = new Builder(context);
					builder.setMessage("确认取消订单");
					if (data.get(position).get("lock_state").equals("1"))
						builder.setMessage("该订单退货中,请耐心等待");
					builder.setTitle("提示");

					builder.setPositiveButton("确认", new OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							String order_id = data.get(position)
									.get("order_id").toString();
							if (data.get(position).get("lock_state")
									.equals("0"))
								HttpUtils.cancelOrder(res, order_id, "");
							dialog.dismiss();
						}
					});

					builder.setNegativeButton("取消", new OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					});

					builder.create().show();

				}
			});
		}
		holder.iv_goods_image.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent it = new Intent(context, B2_ProductdetailsActivity.class);
				it.putExtra("goods_id", data.get(position).get("goods_id")
						.toString());
				context.startActivity(it);

			}
		});

		return view;
	}

	/** 存放控件 */
	public final class ViewHolder {
		public TextView tv_postage;
		public TextView tv_discount;
		public TextView tv_shop_name;
		public TextView tv_goods_name;
		public TextView tv_price;
		public TextView tv_num;
		public TextView tv_all_price;
		public LinearLayout ll_shop_name;
		public TextView tv_all_num;
		public ImageView iv_goods_image;
		public ImageView iv_topay;
		public Button btn_cancel;
	}

	JsonHttpResponseHandler res = new JsonHttpResponseHandler() {
		public void onSuccess(int statusCode, org.apache.http.Header[] headers,
				org.json.JSONObject response) {
			super.onSuccess(statusCode, headers, response);
			Log.i("add-test", response.toString());
			String message = "";
			int result = 0;
			try {
				result = Integer.valueOf(response.getString("result"));
				message = response.getString("message");

			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (result == 1 && statusCode == 200) {

				Toast.makeText(context, "订单取消", Toast.LENGTH_LONG).show();
				Intent it = new Intent(context, MyDialogActivity.class);
				context.startActivity(it);
			} else if (result != 1) {
				AlertDialog.Builder builder = new Builder(context);
				builder.setMessage(message + "请到网页查看详情");

				builder.setTitle("提示");

				builder.setPositiveButton("确认", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {

					}
				});

				builder.create().show();

			}
		};

		@Override
		public void onFailure(int statusCode, Header[] headers,
				Throwable throwable, JSONObject errorResponse) {
			// TODO Auto-generated method stub
			Toast.makeText(context, "网络连接超时", Toast.LENGTH_LONG).show();
			super.onFailure(statusCode, headers, throwable, errorResponse);

		}
	};
}
