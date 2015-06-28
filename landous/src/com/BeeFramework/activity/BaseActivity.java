package com.BeeFramework.activity;

/*
 *	 ______    ______    ______
 *	/\  __ \  /\  ___\  /\  ___\
 *	\ \  __<  \ \  __\_ \ \  __\_
 *	 \ \_____\ \ \_____\ \ \_____\
 *	  \/_____/  \/_____/  \/_____/
 *
 *
 *	Copyright (c) 2013-2014, {Bee} open source community
 *	http://www.bee-framework.com
 *
 *
 *	Permission is hereby granted, free of charge, to any person obtaining a
 *	copy of this software and associated documentation files (the "Software"),
 *	to deal in the Software without restriction, including without limitation
 *	the rights to use, copy, modify, merge, publish, distribute, sublicense,
 *	and/or sell copies of the Software, and to permit persons to whom the
 *	Software is furnished to do so, subject to the following conditions:
 *
 *	The above copyright notice and this permission notice shall be included in
 *	all copies or substantial portions of the Software.
 *
 *	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 *	FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 *	IN THE SOFTWARE.
 */

import org.json.JSONException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.BeeFramework.BeeFrameworkApp;
import com.BeeFramework.model.ActivityManagerModel;
import com.BeeFramework.model.BusinessMessage;
import com.zykj.xiangyangju.R;

@SuppressLint("NewApi")
public class BaseActivity extends Activity implements Handler.Callback
{
    public Handler mHandler;

    public BaseActivity()
    {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        mHandler = new Handler(this);
        ActivityManagerModel.addLiveActivity(this);
    }
    
    @Override
	public void setContentView(int layoutResID) {
		// TODO Auto-generated method stub
		super.setContentView(layoutResID);
		// 由于view必须在视图记载之后添加注入
		getTAApplication().getInjector().injectView(this);
	}
	
	public BeeFrameworkApp getTAApplication() {
		return (BeeFrameworkApp) getApplication();
	}

    @Override
    protected void onStart()
    {
        super.onStart();
        ActivityManagerModel.addVisibleActivity(this);
    }

    @Override
    protected void onStop() {
        super.onStop();

        ActivityManagerModel.removeVisibleActivity(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();

        ActivityManagerModel.addForegroundActivity(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        ActivityManagerModel.removeForegroundActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManagerModel.removeLiveActivity(this);
    }

    @Override
    public boolean handleMessage(Message msg) {
        return false;
    }

    public void OnMessageResponse(BusinessMessage response)
            throws JSONException
    {
        if (response.messageState == BusinessMessage.FAILURE_MESSAGE)
        {
        }
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
    }
}