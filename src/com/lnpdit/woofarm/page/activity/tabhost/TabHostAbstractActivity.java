package com.lnpdit.woofarm.page.activity.tabhost;

import android.app.TabActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;
import android.widget.TextView;

import com.lnpdit.woofarm.R;

@SuppressWarnings("deprecation")
public abstract class TabHostAbstractActivity extends TabActivity {

	private TabHost mTabHost;
	private TabWidget mTabWidget;
	private LayoutInflater mLayoutflater;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			setTheme(R.style.Theme_Tabhost);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
			setContentView(R.layout.activity_tabhost_main);

			mLayoutflater = getLayoutInflater();

			mTabHost = getTabHost();
			mTabWidget = getTabWidget();

			prepare();
			initTabSpec();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * tabHost中item初始化
	 */
	private void initTabSpec() {

		int count = getTabItemCount();
		System.out.println("count =" + count);
		for (int i = 0; i < count; i++) {
			// 获取自定义布局
			View tabItem = mLayoutflater.inflate(R.layout.activity_tabhost_item, null);
			// 图片
			ImageView imgTabItem = (ImageView) tabItem.findViewById(R.id.tab_item_img);
			// 文字
			TextView tvTabItem = (TextView) tabItem.findViewById(R.id.tab_item_tv);
			// 背景
			LinearLayout ll = (LinearLayout) tabItem.findViewById(R.id.tab_itme_ll);
			setTabItemTextView(tvTabItem, imgTabItem, ll, i);
			// 设置ID
			String tabItemId = getTabItemId(i);
			// 添加，设置spec
			TabSpec tabSpec = mTabHost.newTabSpec(tabItemId);
			tabSpec.setIndicator(tabItem);
			tabSpec.setContent(getTabItemIntent(i));
			mTabHost.addTab(tabSpec);
		}

	}

	protected void prepare() {
	}

	protected View getTop() {
		return null;
	}

	protected int getTabCount() {
		return mTabHost.getTabWidget().getTabCount();
	}

	abstract protected void setTabItemTextView(TextView textView, ImageView imgTabItem, LinearLayout ll, int position);

	abstract protected String getTabItemId(int position);

	abstract protected Intent getTabItemIntent(int position);

	abstract protected int getTabItemCount();

	protected void setCurrentTab(int index) {
		mTabHost.setCurrentTab(index);
	}

	protected void focusCurrentTab(int index) {
		mTabWidget.focusCurrentTab(index);
	}

}
