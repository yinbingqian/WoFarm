package com.lnpdit.woofarm.page.activity.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.eroad.widget.calendar.CalanderActivity;
import com.lnpdit.woofarm.R;
import com.lnpdit.woofarm.db.DBHelper;
import com.lnpdit.woofarm.entity.ADInfo;
import com.lnpdit.woofarm.entity.DataInfoUn;
import com.lnpdit.woofarm.entity.Product;
import com.lnpdit.woofarm.entity.ProductRow;
import com.lnpdit.woofarm.instance.Instance;
import com.lnpdit.woofarm.page.adapter.ProductListAdapter;
import com.lnpdit.woofarm.pulltorefresh.library.PullToRefreshBase;
import com.lnpdit.woofarm.pulltorefresh.library.PullToRefreshListView;
import com.lnpdit.woofarm.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.lnpdit.woofarm.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.lnpdit.woofarm.utils.advert.ImageCycleView;
import com.lnpdit.woofarm.utils.advert.ImageCycleView.ImageCycleViewListener;
import com.slidingmenu.lib.SlidingMenu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class ProductListActivity extends Activity implements OnClickListener {
    private DBHelper dbh;
    private Context context;
    private Product product;
    private List<Product> productList;
    private ProductRow productRow;
    private List<ProductRow> productRowList;
    private ListView listview_productlist;
    private ListView productListView;
    private ProductListAdapter productlistAdapter;
    private SearchView search_view;
    private ImageView imgBack;
    private TextView tvBack;
    private String title;
    private TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producttype);
        context = this;

        title = this.getIntent().getStringExtra("title");
        initView();
        initData();
        // setListeners();

    }

    private void initView() {
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitle.setText(title);
        imgBack = (ImageView) findViewById(R.id.img_back);
        imgBack.setOnClickListener(this);
        tvBack = (TextView) findViewById(R.id.tv_back);
        tvBack.setOnClickListener(this);
        search_view = (SearchView) this.findViewById(R.id.search_view);

        productListView = (ListView) findViewById(R.id.listview_one);
        // productListView = listview_productlist.getRefreshableView();
        if (search_view == null) {
            return;
        } else {

            int imgId = search_view.getContext().getResources()
                    .getIdentifier("android:id/search_mag_icon", null, null);
            // 获取ImageView
            ImageView searchButton = (ImageView) search_view
                    .findViewById(imgId);
            // 设置图片
            searchButton.setImageResource(R.drawable.search);
            // 不使用默认
            search_view.setIconifiedByDefault(false);

            // 获取到TextView的ID
            int id = search_view.getContext().getResources()
                    .getIdentifier("android:id/search_src_text", null, null);
            // 获取到TextView的控件
            TextView textView = (TextView) search_view.findViewById(id);
            // 设置字体大小为14sp
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);// 14sp
        }

    }

    private void initData() {

        dbh = new DBHelper(this);
        product = new Product();
        productList = new ArrayList<Product>();
        productRowList = new ArrayList<ProductRow>();

        productList = dbh.queryProduct();

        for (int i = 0; i < productList.size(); i = i + 2) {
            product = productList.get(i);
            productRow = new ProductRow();
            productRow.setName1(product.getName());
            productRow.setPrice1(product.getPrice());
            productRow.setProid1(product.getProid());
            productRow.setThumb1(product.getThumb());

            if (i + 1 != productList.size()) {
                product = productList.get(i + 1);
                productRow.setName2(product.getName());
                productRow.setPrice2(product.getPrice());
                productRow.setProid2(product.getProid());
                productRow.setThumb2(product.getThumb());
            } else {
                productRow.setName2("none");
                productRow.setPrice2("none");
                productRow.setProid2("none");
                productRow.setThumb2("none");
            }
            productRowList.add(productRow);
        }

        productlistAdapter = new ProductListAdapter(context, productRowList);
        productListView.setAdapter(productlistAdapter);
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
        default:
            break;
        }
    }

    private void setListeners() {
        listview_productlist.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {

                // Intent intent = new Intent();
                // intent.setClass(context, AlarmUnInformationActivity.class);
                // intent.putExtra("Id", alarmunList.get(position - 1).getId());
                // intent.putExtra("tagName",alarmunList.get(position -
                // 1).getTagName());
                // intent.putExtra("CTime",alarmunList.get(position -
                // 1).getCTime());
                // intent.putExtra("type", "1");
                // startActivity(intent);
            }
        });
        // listview_productlist
        // .setOnRefreshListener(new OnRefreshListener<ListView>() {
        //
        // @Override
        // public void onRefresh(
        // PullToRefreshBase<ListView> refreshView) {
        // // pageIndex = 1;
        // // String[] property_va = new String[] { "10",
        // // pageIndex + "" };
        // // soapService.GetDateListUn(property_va, false);
        //
        // }
        // });

        // end of list
        // listview_productlist
        // .setOnLastItemVisibleListener(new OnLastItemVisibleListener() {
        //
        // @Override
        // public void onLastItemVisible() {
        // // String[] property_va = new String[] { "10",
        // // ++pageIndex + "" };
        // // soapService.GetDateListUn(property_va, true);
        //
        // }
        // });
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

}
