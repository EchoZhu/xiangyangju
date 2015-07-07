package com.zykj.landous.activity;

import com.zykj.xiangyangju.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * 活动详情
 * @author zyk
 *
 */
public class F0_huodong extends Activity implements OnClickListener {
	ImageView iv_back_huodngxiangqing;
	TextView tv_title_ac,tv_date,tv_content_ac;
	String title=null;
	String content=null;
	String date=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.f0_hongdongxiangqing);
		initview();
		Intent intent  = getIntent();
		title = intent.getStringExtra("title");
		content = intent.getStringExtra("content");
		date = intent.getStringExtra("date");
		tv_title_ac.setText(title);
		tv_content_ac.setText(content);
		tv_date.setText(date);
		iv_back_huodngxiangqing.setOnClickListener(this);
	}
	private void initview() {
		// TODO Auto-generated method stub
		iv_back_huodngxiangqing = (ImageView) findViewById(R.id.iv_back_huodngxiangqing);
		tv_title_ac = (TextView) findViewById(R.id.tv_title_ac);
		tv_date = (TextView) findViewById(R.id.tv_date);
		tv_content_ac = (TextView) findViewById(R.id.tv_content_ac);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.iv_back_huodngxiangqing:
			this.finish();
			break;

		default:
			break;
		}
	}
}
