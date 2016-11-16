package com.lnpdit.woofarm.base.component;

import java.util.List;

import com.lnpdit.woofarm.base.application.DemoApplication;
import com.lnpdit.woofarm.customview.LoadingPage;
import com.lnpdit.woofarm.customview.LoadingPage.ILoadingDo;
import com.lnpdit.woofarm.db.DBHelper;
import com.lnpdit.woofarm.entity.CObject;
import com.lnpdit.woofarm.entity.LoginUser;
import com.lnpdit.woofarm.entity.UserInfo;
import com.lnpdit.woofarm.http.ISoapService;
import com.lnpdit.woofarm.http.SoapService;
import com.lnpdit.woofarm.utils.EventCache;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;

/**
 * sub Acy
 * 
 * @author huanyu 类名称：BaseActivity 创建时间:2014-10-26 下午7:38:53
 */
public class BaseActivity extends Activity implements OnClickListener {
	/** soapService **/
	public ISoapService soapService = new SoapService();
	/** loading **/
	private LoadingPage loading;
	private long exitTime;
	public DemoApplication myApplication;
	public Intent intent = new Intent();// 页面跳转
	private static final String TAG = "SU-JPush";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addLoading();
		myApplication = DemoApplication.getInstance();
		EventCache.commandActivity.unregister(this);
		EventCache.commandActivity.register(this);
		EventCache.errorHttp.unregister(this);
		EventCache.errorHttp.register(this);
		Log.d(TAG, "[ExampleApplication] onCreate");
	}

	/**
	 * 添加loading
	 */
	public void addLoading() {
		loading = new LoadingPage(this, new ILoadingDo() {
			
			@Override
			public void soapFail(String methodName) {
				EventCache.errorHttp.post(methodName);
			}
		});
		
		addContentView(loading, new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
	}

	/**
	 * 移除 loading
	 */
	public void removeLoading() {
		if (loading != null) {
			ViewGroup parent = (ViewGroup) loading.ll_bg.getParent();
			parent.removeView(loading.ll_bg);
			loading = null;
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
//		MobclickAgent.onResume(this);
	}

	@Override
	protected void onPause() {
		super.onPause();
//		MobclickAgent.onPause(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		EventCache.commandActivity.unregister(this);
		EventCache.errorHttp.unregister(this);
	}

	protected boolean isParentActivity = true;

	public boolean isParentActivity() {
		return isParentActivity;
	}

	public void setParentActivity(boolean isParentActivity) {
		this.isParentActivity = isParentActivity;
	}

	/** 监听返回键 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (isParentActivity == true) {
			if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
				if ((System.currentTimeMillis() - exitTime) > 2000) {
					Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
					exitTime = System.currentTimeMillis();
				} else {
					finish();
					System.exit(0);
				}
				return true;
			}
		} else {
			return super.onKeyDown(keyCode, event);
		}

		return super.onKeyDown(keyCode, event);
	}

	/**
	 * 登录用户
	 * 
	 * @return
	 */
	public LoginUser getLoginUser(){
		DBHelper dbh = new DBHelper(this);
		return dbh.queryLoginUserInfo();
	}
	
	public UserInfo getUserInfo() {
		DBHelper dbh = new DBHelper(this);
		List<UserInfo> userlist = dbh.queryUserInfo();
		if(userlist.size()>0){
			return userlist.get(0);
		}else{
			return null;
		}
	}

	public void setLoginUser(LoginUser loginUser) {
		DBHelper dbh = new DBHelper(this);
		dbh.insLoginUserInfo(loginUser);
		// myApplication.loginUser = loginUser;
	}

	@Override
	public void onClick(View v) {

	}

	/**
	 * http回调SoapObject
	 * 
	 * @param obj
	 */
	public void onEvent(Object obj) {

//		if (obj instanceof com.sxit.entity.eb.ChatMessageEB) {
//
//		} else {
//			SoapRes res = (SoapRes) obj;
//			if (res.getObj() == null && loading != null) {
//				loading.setState(1, res.getCode());
//			} else {
//				removeLoading();
//			}
//		}

	}

	/**
	 * chat回调
	 * 
	 * @param obj
	 */
	public void onEvent(CObject obj) {

	}

	/**
	 * http error回调
	 * 
	 * @param method
	 *            方法明名
	 */
	public void onEventMainThread(String method) {

	}
}
