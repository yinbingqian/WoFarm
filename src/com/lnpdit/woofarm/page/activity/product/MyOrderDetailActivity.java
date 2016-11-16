package com.lnpdit.woofarm.page.activity.product;

import com.lnpdit.woofarm.R;
import com.lnpdit.woofarm.base.component.BaseActivity;
import com.lnpdit.woofarm.db.DBHelper;
import com.lnpdit.woofarm.entity.Order;
import com.lnpdit.woofarm.http.SoapRes;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 我的订单-订单详情
 * 
 * @author yinbingqian 类名称：MyOrderActivity 创建时间:2016-10-29
 */

public class MyOrderDetailActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */

    Context context;

    private DBHelper dbh;
    private Order order;
    private String orderId;
    TextView orderresult;
    TextView user_tv;
    TextView phone_tv;
    TextView address_tv;
    TextView proname_tv;
    TextView proprice_tv;
    TextView procount_tv;
    TextView realprice_tv;
    TextView paytype_tv;
    TextView ordernum_tv;
    TextView createtime_tv;
    TextView dealtime_tv;
    Button btndelete;
    ImageView img_back;
    TextView tv_back;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_orderdetail);
        // Intent intent = getIntent();
        // orderId = intent.getStringExtra("orderId");
        initView();
        // initData();

    }

    private void initView() {
        orderresult = (TextView) findViewById(R.id.orderresult);
        user_tv = (TextView) findViewById(R.id.user_tv);
        phone_tv = (TextView) findViewById(R.id.phone_tv);
        address_tv = (TextView) findViewById(R.id.address_tv);
        proname_tv = (TextView) findViewById(R.id.proname_tv);
        proprice_tv = (TextView) findViewById(R.id.proprice_tv);
        procount_tv = (TextView) findViewById(R.id.procount_tv);
        realprice_tv = (TextView) findViewById(R.id.realprice_tv);
        paytype_tv = (TextView) findViewById(R.id.paytype_tv);
        ordernum_tv = (TextView) findViewById(R.id.ordernum_tv);
        createtime_tv = (TextView) findViewById(R.id.createtime_tv);
        dealtime_tv = (TextView) findViewById(R.id.dealtime_tv);
        btndelete = (Button) findViewById(R.id.btndelete);
        btndelete.setOnClickListener(this);

        img_back = (ImageView) findViewById(R.id.img_back);
        tv_back = (TextView) findViewById(R.id.tv_back);
        img_back.setClickable(true);
        tv_back.setClickable(true);
        img_back.setOnClickListener(this);
        tv_back.setOnClickListener(this);
    }

    private void initData() {

        dbh = new DBHelper(this);
        // order = dbh.queryOrderById(orderId);
        //
        // orderresult.setText(order.getResult().toString());
        // // user_tv
        // // phone_tv
        // // address_tv
        // proname_tv.setText(order.getName().toString());
        // proprice_tv.setText(order.getPrice().toString());
        // realprice_tv.setText(order.getHj().toString());
        // procount_tv
        // paytype_tv
        // ordernum_tv
        // createtime_tv
        // dealtime_tv

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.btndelete:
            // onBackPressed();
            // finish();
            break;
        case R.id.img_back:
        case R.id.tv_back:
            finish();
            break;

        default:
            break;
        }
    }
}