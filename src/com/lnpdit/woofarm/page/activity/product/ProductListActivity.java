package com.lnpdit.woofarm.page.activity.product;

import java.util.ArrayList;
import java.util.List;

import com.lnpdit.woofarm.R;
import com.lnpdit.woofarm.base.component.BaseActivity;
import com.lnpdit.woofarm.db.DBHelper;
import com.lnpdit.woofarm.entity.Product;
import com.lnpdit.woofarm.entity.ProductByClass;
import com.lnpdit.woofarm.entity.ProductRow;
import com.lnpdit.woofarm.http.SoapRes;
import com.lnpdit.woofarm.page.adapter.ProductListAdapter;
import com.lnpdit.woofarm.utils.SOAP_UTILS;

import android.content.Context;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 商品列表
 * 
 * @author yinbingqian 类名称：ProductListActivity 创建时间:2016-11-22
 */
public class ProductListActivity extends BaseActivity implements OnClickListener {
    private DBHelper dbh;
    private Context context;
    private ProductByClass productByClass;
    private List<ProductByClass> productByClassList;
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
    private int pageIndex = 1;
    private String selltype = "";
    private String classId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producttype);
        context = this;

        selltype = this.getIntent().getStringExtra("selltype");
        title = this.getIntent().getStringExtra("title");
        if(selltype.equals("menu")){
            classId = this.getIntent().getStringExtra("classId");
        }
        initView();
        initData();
//         setListeners();

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
        getDBData();
        
        String[] property_va = new String[] {selltype , "10", pageIndex + ""};
        soapService.getProductBySellType(property_va, false);
        
        String[] property_vamenu = new String[] {classId , "10", pageIndex + ""};
        soapService.getProductByClass(property_vamenu, false);
    }

    private void getDBData() {
        dbh = new DBHelper(this);
        productByClass = new ProductByClass();
        productRowList = new ArrayList<ProductRow>();

        productByClassList = dbh.queryProductByClass();

        for (int i = 0; i < productByClassList.size(); i = i + 2) {
            productByClass = productByClassList.get(i);
            productRow = new ProductRow();
            productRow.setThumb1(productByClass.getImage());
            productRow.setProid1(productByClass.getId());
            productRow.setName1(productByClass.getName());
            productRow.setPrice1(productByClass.getPrice());

            if (i + 1 != productByClassList.size()) {
                productByClass = productByClassList.get(i + 1);
                productRow.setThumb2(productByClass.getImage());
                productRow.setProid2(productByClass.getId());
                productRow.setName2(productByClass.getName());
                productRow.setPrice2(productByClass.getPrice());
            } else {
                productRow.setThumb2("none");
                productRow.setProid2("none");
                productRow.setName2("none");
                productRow.setPrice2("none");
            }
            productRowList.add(productRow);
        }
           if(productRowList.size() != 0){
               productlistAdapter = new ProductListAdapter(context, productRowList);
               productListView.setAdapter(productlistAdapter);
          }else{
           productListView.setAdapter(null);
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
    
    public void onEvent(SoapRes obj) {
        if (obj.getCode().equals(SOAP_UTILS.METHOD.GETPRODUCTBYSELLTYPE)) {
            if (obj.getObj() != null) {
                if(obj.getObj().equals("false")){
                    Toast.makeText(context, "获取数据失败", Toast.LENGTH_SHORT).show();  
                }else{
                if (obj.isPage()) {
                    for (ProductByClass bean : (List<ProductByClass>) obj.getObj()) {
                        productByClassList.add(bean);
                    }
                    productlistAdapter.notifyDataSetChanged();
                } else {
                    productByClassList = (List<ProductByClass>) obj.getObj();
                    if (productByClassList.size() != 0) {

                        dbh.clearProductByClass();
                        dbh.insProductByClassList(productByClassList);
                        pageIndex = 1;
                    }
                    getDBData();
                }
                }
                }else{
                    Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
                }
        }else if(obj.getCode().equals(SOAP_UTILS.METHOD.GETPRODUCTBYCLASS)) {
            if (obj.getObj() != null) {
                if(obj.getObj().equals("false")){
                    Toast.makeText(context, "获取数据失败", Toast.LENGTH_SHORT).show();  
                    productListView.setAdapter(null);
                }else{
                if (obj.isPage()) {
                    for (ProductByClass bean : (List<ProductByClass>) obj.getObj()) {
                        productByClassList.add(bean);
                    }
                    productlistAdapter.notifyDataSetChanged();
                } else {
                    productByClassList = (List<ProductByClass>) obj.getObj();
                    if (productByClassList.size() != 0) {

                        dbh.clearProductByClass();
                        dbh.insProductByClassList(productByClassList);
                        pageIndex = 1;
                    }
                    getDBData();
                }
                }
                }else{
                    Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
                }
        } 
    }
}
