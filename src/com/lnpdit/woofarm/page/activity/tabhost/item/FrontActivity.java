package com.lnpdit.woofarm.page.activity.tabhost.item;

import java.util.ArrayList;
import java.util.List;

import com.eroad.widget.calendar.CalanderActivity;
import com.lnpdit.woofarm.R;
import com.lnpdit.woofarm.db.DBHelper;
import com.lnpdit.woofarm.entity.ADInfo;
import com.lnpdit.woofarm.entity.Product;
import com.lnpdit.woofarm.entity.ProductRow;
import com.lnpdit.woofarm.instance.Instance;
import com.lnpdit.woofarm.page.activity.product.ProductInfoActivity;
import com.lnpdit.woofarm.page.activity.product.ProductListActivity;
import com.lnpdit.woofarm.page.activity.product.ProductSearchActivity;
import com.lnpdit.woofarm.page.adapter.ProductListAdapter;
import com.lnpdit.woofarm.utils.advert.ImageCycleView;
import com.lnpdit.woofarm.utils.advert.ImageCycleView.ImageCycleViewListener;
import com.slidingmenu.lib.SlidingMenu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

public class FrontActivity extends Activity implements OnClickListener {
    private DBHelper dbh;
    private Context context;
    private ImageView menubtn;
    private ImageView calbtn;
    private SlidingMenu menu;
    private ImageCycleView mAdView;
    private ArrayList<ADInfo> infos = new ArrayList<ADInfo>();
    private Product product;
    private List<Product> productList;
    private ProductRow productRow;
    private List<ProductRow> productRowList;
    private View firstView;
    // private View secondView;
    private ListView listview_productlist;
    private ListView productListView;
    private ProductListAdapter productlistAdapter;
    private SearchView search_view;
    private TextView tvMorexp;
    private TextView tvMoreyd;
    private Resources resources;

    private TextView tvTcl;
    private TextView tvBxgz;
    private TextView tvTx;
    private TextView tvHyjj;
    private TextView tvZj;
    private TextView tvJtx;
    private TextView tvJzx;
    private TextView tvGl;
    private TextView tvYl;

    private ImageView cm1;
    private ImageView cm2;
    private ImageView cm3;
    private ImageView cm4;
    private Button cm1Btn;
    private Button cm2Btn;
    private Button cm3Btn;
    private Button cm4Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front);
        context = this;
        resources = this.getResources();

        initAd();
        initView();
        initData();
        // setListeners();

    }

    private void initView() {
        firstView = getLayoutInflater().inflate(R.layout.front_first, null);
        // secondView = getLayoutInflater().inflate(R.layout.front_second,
        // null);
        // 顶部日历和左侧弹出菜单按钮
        menubtn = (ImageView) findViewById(R.id.topButton);
        menubtn.setOnClickListener(this);
        calbtn = (ImageView) findViewById(R.id.calButton);
        calbtn.setOnClickListener(this);

        menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        menu.setFadeDegree(0.35f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        View view = LayoutInflater.from(this).inflate(R.layout.layout_menu,
                null);
        menu.setMenu(view);
        tvTcl = (TextView) view.findViewById(R.id.tvTcl);
        tvTcl.setOnClickListener(this);
        tvBxgz = (TextView) view.findViewById(R.id.tvBxgz);
        tvBxgz.setOnClickListener(this);
        tvTx = (TextView) view.findViewById(R.id.tvTx);
        tvTx.setOnClickListener(this);
        tvHyjj = (TextView) view.findViewById(R.id.tvHyjj);
        tvHyjj.setOnClickListener(this);
        tvZj = (TextView) view.findViewById(R.id.tvZj);
        tvZj.setOnClickListener(this);
        tvJtx = (TextView) view.findViewById(R.id.tvJtx);
        tvJtx.setOnClickListener(this);
        tvJzx = (TextView) view.findViewById(R.id.tvJzx);
        tvJzx.setOnClickListener(this);
        tvGl = (TextView) view.findViewById(R.id.tvGl);
        tvGl.setOnClickListener(this);
        tvYl = (TextView) view.findViewById(R.id.tvYl);
        tvYl.setOnClickListener(this);

        menu.setSlidingEnabled(false);

        search_view = (SearchView) this.findViewById(R.id.search_view);
        search_view.setClickable(true);
        search_view.setOnClickListener(this);
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
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);// 14sp
        }

        // 轮训广
        mAdView = (ImageCycleView) firstView.findViewById(R.id.ad_view);
        // if (infos.size() != 0) {
        mAdView.setImageResources(infos, mAdCycleViewListener);
        // }

        tvMorexp = (TextView) firstView.findViewById(R.id.morexp_tv);
        tvMorexp.setOnClickListener(this);
        tvMoreyd = (TextView) firstView.findViewById(R.id.moreyd_tv);
        tvMoreyd.setOnClickListener(this);

        cm1 = (ImageView) firstView.findViewById(R.id.cm1);
        cm1.setOnClickListener(this);
        cm2 = (ImageView) firstView.findViewById(R.id.cm2);
        cm2.setOnClickListener(this);
        cm3 = (ImageView) firstView.findViewById(R.id.cm3);
        cm3.setOnClickListener(this);
        cm4 = (ImageView) firstView.findViewById(R.id.cm4);
        cm4.setOnClickListener(this);
        cm1Btn = (Button) firstView.findViewById(R.id.cm1_btn);
        cm1Btn.setOnClickListener(this);
        cm2Btn = (Button) firstView.findViewById(R.id.cm2_btn);
        cm2Btn.setOnClickListener(this);
        cm3Btn = (Button) firstView.findViewById(R.id.cm3_btn);
        cm3Btn.setOnClickListener(this);
        cm4Btn = (Button) firstView.findViewById(R.id.cm4_btn);
        cm4Btn.setOnClickListener(this);

        productListView = (ListView) this
                .findViewById(R.id.listview_productlist);
    }

    private void initAd() {
        infos = new ArrayList<ADInfo>();
        ADInfo adi = new ADInfo();
        for (int i = 0; i < 4; i++) {
            adi = new ADInfo();
            adi.setId(String.valueOf(i + 1));
            if (i == 0) {
                adi.setUrl("http://www.tvlicai.cn/ad1.png");
                adi.setImg(R.drawable.ad1);
            } else if (i == 1) {
                adi.setUrl("http://www.tvlicai.cn/ad2.png");
                adi.setImg(R.drawable.ad2);
            } else if (i == 2) {
                adi.setUrl("http://www.tvlicai.cn/ad3.png");
                adi.setImg(R.drawable.ad3);
            } else if (i == 3) {
                adi.setUrl("http://www.tvlicai.cn/ad4.png");
                adi.setImg(R.drawable.ad4);
            }
            adi.setTitle("");
            adi.setContent("");
            infos.add(adi);
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

        productListView.addHeaderView(firstView);

        productlistAdapter = new ProductListAdapter(context, productRowList);
        productListView.setAdapter(productlistAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.search_view:
            Intent intent_search = new Intent();
            intent_search.setClass(this, ProductSearchActivity.class);
            startActivity(intent_search);
            break;
        case R.id.topButton:
            menu.toggle();
            break;
        case R.id.calButton:
            Intent intent = new Intent();
            intent.setClass(this, CalanderActivity.class);
            startActivity(intent);
            break;
        case R.id.morexp_tv:
            intent = new Intent();
            intent.setClass(this, ProductListActivity.class);
            intent.putExtra("title", "新品推荐");
            startActivity(intent);
            break;
        case R.id.moreyd_tv:
            intent = new Intent();
            intent.setClass(this, ProductListActivity.class);
            intent.putExtra("title", "预订商品");
            startActivity(intent);
            break;
        case R.id.cm1:
        case R.id.cm1_btn:

            intent = new Intent();
            intent.setClass(this, ProductInfoActivity.class);
            intent.putExtra("name", "甜查理");
            startActivity(intent);
            break;
        case R.id.cm2:
        case R.id.cm2_btn:
            intent = new Intent();
            intent.setClass(this, ProductInfoActivity.class);
            intent.putExtra("name", "白雪公主");
            startActivity(intent);
            break;
        case R.id.cm3:
        case R.id.cm3_btn:
            intent = new Intent();
            intent.setClass(this, ProductInfoActivity.class);
            intent.putExtra("name", "红颜九九");
            startActivity(intent);
            break;
        case R.id.cm4:
        case R.id.cm4_btn:
            intent = new Intent();
            intent.setClass(this, ProductInfoActivity.class);
            intent.putExtra("name", "京桃香");
            startActivity(intent);
            break;

        case R.id.tvTcl:
            goList("甜查理");
            break;
        case R.id.tvBxgz:
            goList("白雪公主");
            break;
        case R.id.tvTx:
            goList("桃熏");
            break;
        case R.id.tvHyjj:
            goList("红颜九九");
            break;
        case R.id.tvZj:
            goList("章姬");
            break;
        case R.id.tvJtx:
            goList("京桃香");
            break;
        case R.id.tvJzx:
            goList("京藏香");
            break;
        case R.id.tvGl:
            goList("甘露");
            break;
        case R.id.tvYl:
            goList("艳丽");
            break;
        default:
            break;
        }
    }

    private void goList(String title) {

        Intent intent = new Intent();
        intent.setClass(this, ProductListActivity.class);
        intent.putExtra("title", title);
        startActivity(intent);
        menu.toggle();
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
        mAdView.startImageCycle();

    };

    private ImageCycleViewListener mAdCycleViewListener = new ImageCycleViewListener() {

        @Override
        public void onImageClick(ADInfo info, int position, View imageView) {

        }

        @Override
        public void displayImage(String imageURL, ImageView imageView) {

            // 使用ImageLoader对图片进行加装！
            Instance.imageLoader.displayImage(imageURL, imageView,
                    Instance.advert_options);// 使用ImageLoader对图片进行加装！
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        mAdView.pushImageCycle();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdView.pushImageCycle();
    }

}
