package com.lnpdit.woofarm.page.activity.product;

import java.util.List;

import com.lnpdit.woofarm.R;
import com.lnpdit.woofarm.base.component.BaseActivity;
import com.lnpdit.woofarm.db.DBHelper;
import com.lnpdit.woofarm.entity.Order;
import com.lnpdit.woofarm.http.SoapRes;
import com.lnpdit.woofarm.page.adapter.MyOrderListAdapter;
import com.lnpdit.woofarm.pulltorefresh.library.PullToRefreshBase;
import com.lnpdit.woofarm.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.lnpdit.woofarm.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.lnpdit.woofarm.pulltorefresh.library.PullToRefreshListView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 我的订单
 * 
 * @author yinbingqian 类名称：MyOrderActivity 创建时间:2016-10-29
 */

public class MyOrderActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */

    private PullToRefreshListView listview_myorderlist;
    private ListView myorderListView;
    Context context;
    private DBHelper dbh;
    private Order order;
    private List<Order> myorderList;
    private String orderId;
    private MyOrderListAdapter myorderlistAdapter;
    ImageView img_back;
    TextView tv_back;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_myorder);

        initView();
        initData();
        setListeners();

    }

    private void initView() {
        listview_myorderlist = (PullToRefreshListView) findViewById(
                R.id.listview_myorderlist);
        myorderListView = listview_myorderlist.getRefreshableView();
        img_back = (ImageView) findViewById(R.id.img_back);
        tv_back = (TextView) findViewById(R.id.tv_back);
        img_back.setClickable(true);
        tv_back.setClickable(true);
        img_back.setOnClickListener(this);
        tv_back.setOnClickListener(this);

    }

    private void initData() {

        dbh = new DBHelper(this);
        myorderList = dbh.queryOrder();

        myorderlistAdapter = new MyOrderListAdapter(context, myorderList);
        myorderListView.setAdapter(myorderlistAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        case R.id.img_back:
        case R.id.tv_back:
            finish();
            break;

        default:
            break;
        }
    }

    private void setListeners() {
        listview_myorderlist.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                // orderId = myorderList.get(position).getOrderid();
                Intent intent = new Intent();
                // intent.putExtra("orderId", orderId);
                intent.setClass(context, MyOrderDetailActivity.class);
                startActivity(intent);
            }
        });
        // listview_myorderlist
        // .setOnRefreshListener(new OnRefreshListener<ListView>() {
        //
        // @Override
        // public void onRefresh(
        // PullToRefreshBase<ListView> refreshView) {
        // initData();
        //
        // listview_myorderlist.onRefreshComplete();
        ////
        //
        // }
        // });

        // end of list
        listview_myorderlist
                .setOnLastItemVisibleListener(new OnLastItemVisibleListener() {

                    @Override
                    public void onLastItemVisible() {
                        // String[] property_va = new String[] { "10",
                        // ++pageIndex + "" };
                        // soapService.GetDateListUn(property_va, true);

                    }
                });
    }

    public void onEvent(SoapRes obj) {
        // if (obj.getCode().equals(SOAP_UTILS.METHOD.GETDATELISTUN)) {
        // if (obj.isPage()) {
        // for (DataInfoUn bean : (List<DataInfoUn>) obj.getObj()) {
        // monitorList.add(bean);
        // }
        // monitorlistAdapter.notifyDataSetChanged();
        // } else {
        // monitorList = (List<DataInfoUn>) obj.getObj();
        // if (monitorList != null) {
        // if (monitorList.size() != 0) {
        // monitorlistAdapter = new MonitorListAdapter(context,monitorList);
        // monitorListView.setAdapter(monitorlistAdapter);
        // }
        // }
        // }
        // listview_monitorlist.onRefreshComplete();
    }
    // }

}