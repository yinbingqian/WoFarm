package com.lnpdit.woofarm.page.activity.tabhost.item;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.lnpdit.woofarm.R;
import com.lnpdit.woofarm.base.component.BaseActivity;
import com.lnpdit.woofarm.db.DBHelper;
import com.lnpdit.woofarm.entity.Cart;
import com.lnpdit.woofarm.entity.DataInfoUn;
import com.lnpdit.woofarm.entity.Order;
import com.lnpdit.woofarm.entity.ShopList;
import com.lnpdit.woofarm.http.SoapRes;
import com.lnpdit.woofarm.page.activity.product.MyOrderDetailActivity;
import com.lnpdit.woofarm.page.activity.product.ProductConfirmActivity;
import com.lnpdit.woofarm.page.adapter.CartListAdapter;
import com.lnpdit.woofarm.pulltorefresh.library.PullToRefreshListView;
import com.lnpdit.woofarm.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.lnpdit.woofarm.utils.SOAP_UTILS;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class ShoppingActivity extends BaseActivity implements OnClickListener {
    /** Called when the activity is first created. */

    private DBHelper dbh;
    private Cart cart;
    private List<Cart> cartList;
    private PullToRefreshListView listview_cartlist;
    private ListView cartListView;
    private CartListAdapter cartlistAdapter;
    private Button submit_btn;
    Context context;
    private List<DataInfoUn> shoppingList;
    
    private TextView lefts_tv;
    private String memberid = "";
    private int pageIndex = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_shopping);

        SharedPreferences sharedPreferences = getSharedPreferences("userinfo",MODE_PRIVATE);
        memberid =sharedPreferences.getString("userid", ""); 
        
        initView();
        initData();
        setListeners();
    }

    private void initView() {
        listview_cartlist = (PullToRefreshListView) findViewById(
                R.id.listview_cartlist);
        cartListView = listview_cartlist.getRefreshableView();
        lefts_tv = (TextView) findViewById(R.id.lefts_tv);
        submit_btn = (Button) findViewById(R.id.submit_btn);
        submit_btn.setOnClickListener(this);

    }

    private void initData() {
        getDBData();
        if(memberid.equals("")||memberid.equals(null)){
            Toast.makeText(context, "请先登录", Toast.LENGTH_SHORT).show();
        }else{

            String[] property_va = new String[] { memberid, "10",pageIndex + ""};
            soapService.getCartList(property_va,false);
        }
    }

    private void setListeners() {
        listview_cartlist.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
//                Intent intent = new Intent();
//                intent.setClass(context, MyOrderDetailActivity.class);
//                startActivity(intent);
            }
        });
        // listview_cartlist
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
        listview_cartlist
                .setOnLastItemVisibleListener(new OnLastItemVisibleListener() {

                    @Override
                    public void onLastItemVisible() {
                        
                        String[] property_va = new String[] { memberid, "10",++pageIndex + ""};
                        soapService.getCartList(property_va,false);

                    }
                });
        
        listview_cartlist.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                    int position, long id) {
                
                String[] property_va = new String[] { memberid, cartList.get(position).getId()};
                soapService.deleteCart(property_va);
                
                String item = (String) cartListView.getItemAtPosition(position);
 
//                SharedPreferences Addresses = getSharedPreferences(FileListenerActivity.PREFS_NAME, 0);
//                SharedPreferences.Editor editor = Addresses.edit();
//                editor.remove(item);
//                editor.commit();
                 
                Toast.makeText(getBaseContext(), item + "被删除了",
                        Toast.LENGTH_SHORT).show();
                 
                cartList.remove(item);
                cartlistAdapter.notifyDataSetChanged();
 
                return true;//不触发短点击效果 
            }
 
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.submit_btn:
            Intent intent = new Intent();
            intent.setClass(this, ProductConfirmActivity.class);
            startActivity(intent);
            break;
        default:
            break;
        }
    }
    
    private void getDBData() {

        dbh = new DBHelper(this);
        cart = new Cart();
        cartList = new ArrayList<Cart>();

        cartList = dbh.queryCart();

        cartlistAdapter = new CartListAdapter(context, cartList);
        cartListView.setAdapter(cartlistAdapter);
    }
    
    public void onEvent(SoapRes obj) {
        if (obj.getCode().equals(SOAP_UTILS.METHOD.GETCARTLIST)) {
            listview_cartlist.onRefreshComplete();
            if (obj.getObj() != null) {
                try {
                    
                JSONObject json_obj = new JSONObject(obj.getObj().toString());

                String result = json_obj.get("status").toString();
                if(result.equals("true")){
                JSONArray message_array = json_obj.getJSONArray("msg");
             
                cartList = new ArrayList<Cart>();
                    
                    for (int i = 0; i < message_array.length(); i++) {
                        JSONObject json_news = (JSONObject) message_array.get(i);
                        Cart hpn = new Cart();
                        hpn.setImage(json_news.get("image").toString());
                        hpn.setId(json_news.get("id").toString());
                        hpn.setName(json_news.get("name").toString());
                        hpn.setPrice(json_news.get("price").toString());
                        hpn.setQuantity(json_news.get("quantity").toString());
                        hpn.setTotalprice(json_news.get("totalprice").toString());
                        hpn.setShopid(json_news.get("shopid").toString());

                        lefts_tv.setText("共计：" + json_news.get("totalprice").toString() + "元");
                        
                        cartList.add(hpn);
                    }
                    
                    
                    if (obj.isPage()) {
                        for (Cart bean : (List<Cart>) cartList) {
                            cartList.add(bean);
                        }
                        cartlistAdapter.notifyDataSetChanged();
                    } else {
                        cartList = (List<Cart>) cartList;
                        dbh.clearAllCart();
                        if (cartList.size() != 0) {

                            dbh.insCartList(cartList);
                        }else{
                            cartListView.setAdapter(null);
                        }
                        getDBData();
                    }
                 }else{
                        String result_str = json_obj.get("msg").toString();
                        Toast.makeText(context, result_str, Toast.LENGTH_SHORT).show();
                    }
               
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
                }else{
                    Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
                }
        }else if (obj.getCode().equals(SOAP_UTILS.METHOD.DELETECART)) {
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