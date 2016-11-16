package com.lnpdit.woofarm.page.activity.product;

import com.lnpdit.woofarm.R;
import com.lnpdit.woofarm.base.component.BaseActivity;
import com.lnpdit.woofarm.page.activity.setting.AddressListActivity;
import com.lnpdit.woofarm.page.activity.video.RealPlayActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ProductConfirmActivity extends BaseActivity {
    /** Called when the activity is first created. */
    private TextView tv_title;
    private TextView btnSubmit;
    private ImageView imgBack;
    private TextView tvBack;
    private ImageView add_img;
    private ImageView subtract_img;
    private TextView count_tv;
    private RelativeLayout addLayout;
    Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_confirmorder);

        initView();
    }

    private void initView() {
        this.isParentActivity = false;

        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("订单确认");
        imgBack = (ImageView) findViewById(R.id.img_back);
        imgBack.setOnClickListener(this);
        add_img = (ImageView) findViewById(R.id.add_img);
        add_img.setOnClickListener(this);
        subtract_img = (ImageView) findViewById(R.id.subtract_img);
        subtract_img.setOnClickListener(this);
        count_tv = (TextView) findViewById(R.id.count_tv);
        tvBack = (TextView) findViewById(R.id.tv_back);
        tvBack.setOnClickListener(this);
        btnSubmit = (TextView) findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(this);
        addLayout = (RelativeLayout) findViewById(R.id.add_layout);
        addLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.tv_back:
            finish();
            break;
        case R.id.img_back:
            finish();
            break;
        case R.id.add_img:
            String add_str = count_tv.getText().toString();
            int add_int =  Integer.parseInt(add_str);
            count_tv.setText(add_int + 1 );
            break;
        case R.id.subtract_img:
            String count_str = count_tv.getText().toString();
            int coutn_int =  Integer.parseInt(count_str);
          
            if(coutn_int <= 1){
                count_tv.setText(coutn_int);
            }else{
                count_tv.setText(coutn_int - 1);
            }
            break;
        case R.id.add_layout:
            Intent intent = new Intent();
            intent.setClass(this,
                    AddressListActivity.class);
            startActivity(intent);
            break;
        case R.id.btn_submit:
            Toast.makeText(this, "订单已提交！", Toast.LENGTH_SHORT).show();
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