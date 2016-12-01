package com.lnpdit.woofarm.page.activity.setting;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.lnpdit.woofarm.R;
import com.lnpdit.woofarm.base.component.BaseActivity;
import com.lnpdit.woofarm.db.DBHelper;
import com.lnpdit.woofarm.entity.Address;
import com.lnpdit.woofarm.http.SoapRes;
import com.lnpdit.woofarm.page.adapter.AddressListAdapter;
import com.lnpdit.woofarm.pulltorefresh.library.PullToRefreshBase;
import com.lnpdit.woofarm.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.lnpdit.woofarm.pulltorefresh.library.PullToRefreshListView;
import com.lnpdit.woofarm.utils.SOAP_UTILS;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AddressListActivity extends BaseActivity implements OnClickListener {
    private DBHelper dbh;
    private Context context;
    private Address address;
    private List<Address> addressList;
    private ListView addressListView;
    private PullToRefreshListView listview_addresslist;//地址 
    private AddressListAdapter addresslistAdapter;
    private Button addBtn;
    private ImageView imgBack;
    private TextView tvBack;

    private String memberid = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        context = this;
        SharedPreferences sharedPreferences = getSharedPreferences("userinfo",MODE_PRIVATE);
        memberid =sharedPreferences.getString("userid", ""); 
        
        initView();
        initData();
        setListeners();

    }

    private void initView() {
        imgBack = (ImageView) findViewById(R.id.img_back);
        imgBack.setOnClickListener(this);
        tvBack = (TextView) findViewById(R.id.tv_back);
        tvBack.setOnClickListener(this);
        addBtn = (Button) findViewById(R.id.add_btn);
        addBtn.setOnClickListener(this);

        listview_addresslist = (PullToRefreshListView) this.findViewById(R.id.listview_addresslist);
        addressListView = listview_addresslist.getRefreshableView();
    }

    private void initData() {
        getDBData();
        if(memberid.equals("")||memberid.equals(null)){
            Toast.makeText(context, "请先登录", Toast.LENGTH_SHORT).show();
        }else{

        String[] property_va = new String[] {memberid};
        soapService.getReceaddressByMember(property_va);
        }
    }

    private void getDBData() {
        dbh = new DBHelper(this);
        address = new Address();
        addressList = new ArrayList<Address>();

        addressList = dbh.queryAddress();
        if(addressList.size()!=0){

            addresslistAdapter = new AddressListAdapter(context, addressList);
            addressListView.setAdapter(addresslistAdapter);
        }else{
            addressListView.setAdapter(null);
        }
        
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
        case R.id.add_btn:
            Intent intent = new Intent();
            intent.putExtra("type", "add");
            intent.setClass(context, EditAddressActivity.class);
            startActivity(intent);
            break;
        default:
            break;
        }
    }

    private void setListeners() {
        // listview_addresslist.setOnItemClickListener(new OnItemClickListener()
        // {
        //
        // @Override
        // public void onItemClick(AdapterView<?> parent, View view,
        // int position, long id) {
        //
        // // Intent intent = new Intent();
        // // intent.setClass(context, AlarmUnInformationActivity.class);
        // // intent.putExtra("Id", alarmunList.get(position - 1).getId());
        // // intent.putExtra("tagName",alarmunList.get(position -
        // // 1).getTagName());
        // // intent.putExtra("CTime",alarmunList.get(position -
        // // 1).getCTime());
        // // intent.putExtra("type", "1");
        // // startActivity(intent);
        // }
        // });
         listview_addresslist.setOnRefreshListener(new OnRefreshListener<ListView>() {
        
         @Override
         public void onRefresh(
         PullToRefreshBase<ListView> refreshView) {
             if(memberid.equals("")||memberid.equals(null)){
                 Toast.makeText(context, "请先登录", Toast.LENGTH_SHORT).show();
             }else{

             String[] property_va = new String[] {memberid};
             soapService.getReceaddressByMember(property_va);
             }
         }
         });

    
    }

    @Override
    protected void onResume() {
        super.onResume();

    };


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void onEvent(SoapRes obj) {
        if (obj.getCode().equals(SOAP_UTILS.METHOD.GETRECEADDRESSBYMEMBER)) {
            listview_addresslist.onRefreshComplete();
            if (obj.getObj() != null) {
                if(obj.getObj().equals("false")){
                    Toast.makeText(context, "获取数据失败", Toast.LENGTH_SHORT).show();  
                }else{
                    addressList = (List<Address>) obj.getObj();
                    if (addressList.size() != 0) {

                        dbh.clearAllAddress();
                        dbh.insAddressList(addressList);
                    }
                    getDBData();
                }
                }else{
                    Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
                }
        }else if (obj.getCode().equals(SOAP_UTILS.METHOD.DELETERECEADDRESSBYID)) {
            if (obj.getObj() != null) {
                try {
                    JSONObject json_obj = new JSONObject(obj.getObj().toString());

                    String result = json_obj.get("status").toString();
                    String message = json_obj.get("msg").toString();
                    if(result.equals("true")){
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();  
                        if(memberid.equals("")||memberid.equals(null)){
                            Toast.makeText(context, "请先登录", Toast.LENGTH_SHORT).show();
                        }else{

                        String[] property_va = new String[] {memberid};
                        soapService.getReceaddressByMember(property_va);
                        }
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
        }else if (obj.getCode().equals(SOAP_UTILS.METHOD.ADDRECEADDRESS)) {
            if (obj.getObj() != null) {
                try {
                    JSONObject json_obj = new JSONObject(obj.getObj().toString());

                    String result = json_obj.get("status").toString();
                    String message = json_obj.get("msg").toString();
                    if(result.equals("true")){
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show(); 
                        if(memberid.equals("")||memberid.equals(null)){
                            Toast.makeText(context, "请先登录", Toast.LENGTH_SHORT).show();
                        }else{

                        String[] property_va = new String[] {memberid};
                        soapService.getReceaddressByMember(property_va);
                        } 
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
        }else if (obj.getCode().equals(SOAP_UTILS.METHOD.UPDATERECEADDRESS)) {
            if (obj.getObj() != null) {
                try {
                    JSONObject json_obj = new JSONObject(obj.getObj().toString());

                    String result = json_obj.get("status").toString();
                    String message = json_obj.get("msg").toString();
                    if(result.equals("true")){
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();  
                        if(memberid.equals("")||memberid.equals(null)){
                            Toast.makeText(context, "请先登录", Toast.LENGTH_SHORT).show();
                        }else{

                        String[] property_va = new String[] {memberid};
                        soapService.getReceaddressByMember(property_va);
                        }
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
        }else if (obj.getCode().equals(SOAP_UTILS.METHOD.SETRECEADDRESSSTATBYID)) {
            if (obj.getObj() != null) {
                try {
                    JSONObject json_obj = new JSONObject(obj.getObj().toString());

                    String result = json_obj.get("status").toString();
                    String message = json_obj.get("msg").toString();
                    if(result.equals("true")){
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show(); 
                        if(memberid.equals("")||memberid.equals(null)){
                            Toast.makeText(context, "请先登录", Toast.LENGTH_SHORT).show();
                        }else{

                        String[] property_va = new String[] {memberid};
                        soapService.getReceaddressByMember(property_va);
                        }
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
