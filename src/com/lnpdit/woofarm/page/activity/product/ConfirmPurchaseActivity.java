package com.lnpdit.woofarm.page.activity.product;
import com.lnpdit.woofarm.R;
import com.lnpdit.woofarm.base.component.BaseActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

public class ConfirmPurchaseActivity extends BaseActivity{
	/** Called when the activity is first created. */
    private TextView tv_title;
    Context context;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_productorderconfirm);

        initView();
    }

    private void initView() {
        this.isParentActivity = false;

        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("商品详情");
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