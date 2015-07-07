package com.zykj.landous.activity;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zykj.landous.Tools.ShareUmengMain;
import com.zykj.xiangyangju.R;
/**
 * 体验馆页面
 * @author Administrator
 *
 */
public class G0_Tiyanguan extends Activity implements OnClickListener {

	ImageView iv_tyg_back,iv_share,iv_daohang,iv_phone;
	TextView tv_content,tv_title;
	String key = "98e9303791945a0f061b400d73225786";//高德地图申请的key，带地图组件的key，
	String phonenumber;
//    String dest ="116.470098,39.992838";经纬度
//    String destName ="阜通西";
    String dest,destName,title,content;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.g0_tiyanguan_activity);
		initView();
		Intent intent11 = getIntent();
		dest = intent11.getStringExtra("expr_longi")+","+intent11.getStringExtra("expr_lati");//经纬度
		destName = intent11.getStringExtra("expr_location");//具体位置
//		destName = "hello Kity";//具体位置
		phonenumber = intent11.getStringExtra("tel");//电话号码
		title = intent11.getStringExtra("title");
		content = intent11.getStringExtra("content");
		
		tv_title.setText(title);
		tv_content.setText(content);
		
		iv_share.setOnClickListener(this);
		iv_daohang.setOnClickListener(this);
		iv_phone.setOnClickListener(this);
		iv_tyg_back.setOnClickListener(this);
	}
	private void initView() {
		// TODO Auto-generated method stub
		iv_tyg_back = (ImageView) findViewById(R.id.iv_tyg_back);
		iv_share = (ImageView) findViewById(R.id.iv_share);
		iv_daohang = (ImageView) findViewById(R.id.iv_daohang);
		iv_phone = (ImageView) findViewById(R.id.iv_phone);
		tv_content = (TextView) findViewById(R.id.tv_content);
		tv_title = (TextView) findViewById(R.id.tv_title);
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.iv_tyg_back:
			this.finish();
			break;
		case R.id.iv_share:
			ShareUmengMain mShare = new ShareUmengMain(this);
			mShare.setContent("我在向阳居发现了这个体验馆，你也来看看吧");
			mShare.show();
			break;
		case R.id.iv_daohang:
//			Toast.makeText(this, "导航", 500).show();
	        Intent intent1 = new Intent();       
	        intent1.setAction("android.intent.action.VIEW");   

	        Uri content_url = Uri.parse("http://m.amap.com/navi/?dest="+dest+"&destName="+destName+"&hideRouteIcon=1&key="+key);  
	        intent1.setData(content_url); 
	        startActivity(intent1);
			break;
		case R.id.iv_phone:
//			Toast.makeText(this, "打电话", 500).show();
			AlertDialog.Builder builder1 = new Builder(this);
			builder1.setTitle("呼叫"+phonenumber);
			 
			builder1.setNegativeButton("确认",
			        new DialogInterface.OnClickListener() {
			 
			            public void onClick(DialogInterface dialog, int which) {
			                dialog.dismiss();
			                // 传入服务， parse（）解析号码
			                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phonenumber));
			                // 通知activtity处理传入的call服务
			                startActivity(intent);
			            }
			 
			        }).setPositiveButton("取消",
			        new DialogInterface.OnClickListener() {
			 
			            public void onClick(DialogInterface dialog, int which) {
			 
			            }
			 
			        });
			builder1.create().show();
			break;

		default:
			break;
		}
	}
}
