package com.lnpdit.woofarm.page.activity.tabhost.item;

import java.util.ArrayList;
import java.util.List;

import com.eroad.widget.calendar.CalanderActivity;
import com.lnpdit.woofarm.R;
import com.lnpdit.woofarm.db.DBHelper;
import com.lnpdit.woofarm.entity.Cart;
import com.lnpdit.woofarm.entity.DataInfoUn;
import com.lnpdit.woofarm.entity.Product;
import com.lnpdit.woofarm.entity.ProductRow;
import com.lnpdit.woofarm.http.SoapRes;
import com.lnpdit.woofarm.page.adapter.ProductListAdapter;
import com.lnpdit.woofarm.page.activity.product.ProductConfirmActivity;
import com.lnpdit.woofarm.page.activity.product.ProductInfoActivity;
import com.lnpdit.woofarm.page.adapter.CartListAdapter;
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
import android.widget.ListView;
import android.widget.Toast;

public class ShoppingActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */

    private DBHelper dbh;
    private Cart cart;
    private List<Cart> cartList;
    private ListView listview_shoppinglist;
    private ListView shoppingListView;
    private CartListAdapter cartlistAdapter;
    private Button submit_btn;
    Context context;
    private List<DataInfoUn> shoppingList;
    private CartListAdapter shoppinglistAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_shopping);

        initView();
        initData();
    }

    private void initView() {
        shoppingListView = (ListView) findViewById(R.id.listview_shoppinglist);
        submit_btn = (Button) findViewById(R.id.submit_btn);
        submit_btn.setOnClickListener(this);

    }

    private void initData() {

        dbh = new DBHelper(this);
        cart = new Cart();
        cartList = new ArrayList<Cart>();

        cartList = dbh.queryCart();

        cartlistAdapter = new CartListAdapter(context, cartList);
        shoppingListView.setAdapter(cartlistAdapter);
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
}