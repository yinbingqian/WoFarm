package com.lnpdit.woofarm.page.activity.tabhost.item;

import com.lnpdit.woofarm.R;
import com.lnpdit.woofarm.base.component.BaseActivity;
import com.lnpdit.woofarm.customview.FocusedtrueTV;
import com.lnpdit.woofarm.md5.MD5Plus;
import com.lnpdit.woofarm.page.activity.login.LoginActivity;
import com.lnpdit.woofarm.page.activity.product.MyOrderActivity;
import com.lnpdit.woofarm.page.activity.setting.AddressListActivity;
import com.lnpdit.woofarm.page.activity.setting.OldPasswordActivity;
import com.lnpdit.woofarm.page.activity.tabhost.MainTabHostActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PersonalActivity extends BaseActivity {
    /** Called when the activity is first created. */
    private FocusedtrueTV tv_title;
    Context context;
    private LinearLayout logout_layout;
    private LinearLayout myaddress_layout;
    private LinearLayout myorder_layout;
    private LinearLayout password_layout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_personal);

        initView();
    }

    private void initView() {
        this.isParentActivity = false;

        logout_layout = (LinearLayout) findViewById(R.id.logout_layout);
        logout_layout.setOnClickListener(this);
        myaddress_layout = (LinearLayout) findViewById(R.id.myaddress_layout);
        myaddress_layout.setOnClickListener(this);
        myorder_layout = (LinearLayout) findViewById(R.id.myorder_layout);
        myorder_layout.setOnClickListener(this);
        password_layout = (LinearLayout) findViewById(R.id.password_layout);
        password_layout.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.myorder_layout:

            Intent intent_order = new Intent();
            intent_order.setClass(context, MyOrderActivity.class);
            startActivity(intent_order);
            break;
        case R.id.myaddress_layout:

            Intent intent = new Intent();
            intent.setClass(context, AddressListActivity.class);
            startActivity(intent);
            break;
        case R.id.password_layout:

            intent = new Intent();
            intent.setClass(context, OldPasswordActivity.class);
            startActivity(intent);
            break;
        case R.id.logout_layout:

            intent = new Intent();
            intent.setClass(context, LoginActivity.class);
            startActivity(intent);
            finish();
            break;

        default:
            break;
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}