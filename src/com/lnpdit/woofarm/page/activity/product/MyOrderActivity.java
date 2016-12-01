package com.lnpdit.woofarm.page.activity.product;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.lnpdit.woofarm.R;
import com.lnpdit.woofarm.base.component.BaseActivity;
import com.lnpdit.woofarm.db.DBHelper;
import com.lnpdit.woofarm.entity.Order;
import com.lnpdit.woofarm.entity.ProductByClass;
import com.lnpdit.woofarm.entity.ProductRow;
import com.lnpdit.woofarm.http.SoapRes;
import com.lnpdit.woofarm.page.adapter.MyOrderListAdapter;
import com.lnpdit.woofarm.page.adapter.ProductListAdapter;
import com.lnpdit.woofarm.pulltorefresh.library.PullToRefreshBase;
import com.lnpdit.woofarm.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.lnpdit.woofarm.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.lnpdit.woofarm.utils.SOAP_UTILS;
import com.lnpdit.woofarm.pulltorefresh.library.PullToRefreshListView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 我的订单
 * 
 * @author yinbingqian 类名称：MyOrderActivity 创建时间:2016-10-29
 */

public class MyOrderActivity extends BaseActivity implements OnClickListener {
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
    
    private int pageIndex = 1;
    private String memberid = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_myorder);
        
        SharedPreferences sharedPreferences = getSharedPreferences("userinfo",MODE_PRIVATE);
        memberid =sharedPreferences.getString("userid", ""); 
        
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
        getDBData();
        if(memberid.equals("")||memberid.equals(null)){
            Toast.makeText(context, "请先登录", Toast.LENGTH_SHORT).show();
        }else{

            String[] property_va = new String[] {memberid , "10", pageIndex + ""};
            soapService.getOrderListByMember(property_va, false);
        }
    }

    private void getDBData() {
        dbh = new DBHelper(this);
        myorderList = dbh.queryOrder();
      if(myorderList.size()!=0){
         myorderlistAdapter = new MyOrderListAdapter(context, myorderList);
         myorderListView.setAdapter(myorderlistAdapter);
         }else{
             myorderListView.setAdapter(null);
        }
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
                        
                         String[] property_va = new String[] {memberid , "10", ++pageIndex + ""};
                         soapService.getOrderListByMember(property_va, true);

                    }
                });
    }

    public void onEvent(SoapRes obj) {
        if (obj.getCode().equals(SOAP_UTILS.METHOD.GETORDERLISTBYMEMBER)) {
            listview_myorderlist.onRefreshComplete();
            if (obj.getObj() != null) {
                if(obj.getObj().equals("false")){
                    Toast.makeText(context, "获取数据失败", Toast.LENGTH_SHORT).show();  
                }else{
                if (obj.isPage()) {
                    for (Order bean : (List<Order>) obj.getObj()) {
                        myorderList.add(bean);
                    }
                    myorderlistAdapter.notifyDataSetChanged();
                } else {
                    myorderList = (List<Order>) obj.getObj();
                    if (myorderList.size() != 0) {

                        dbh.clearAllOrder();
                        dbh.insOrderList(myorderList);
                        pageIndex = 1;
                    }
                    getDBData();
                }
                }
                }else{
                    Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
                }
        }else if (obj.getCode().equals(SOAP_UTILS.METHOD.DELETEORDERBYID)) {
            if (obj.getObj() != null) {
                try {
                JSONObject json_obj = new JSONObject(obj.getObj().toString());

                String result = json_obj.get("status").toString();
                String message = json_obj.get("msg").toString();
                if(result.equals("true")){
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();  
                }else{
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();  
                }
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
            }else{
                Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
            }
        }
    }

}