package com.lnpdit.woofarm.page.activity.tabhost.item;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.eroad.widget.calendar.CalanderActivity;
import com.lnpdit.woofarm.R;
import com.lnpdit.woofarm.base.component.BaseActivity;
import com.lnpdit.woofarm.db.DBHelper;
import com.lnpdit.woofarm.entity.ADInfo;
import com.lnpdit.woofarm.entity.IndexAdvertise;
import com.lnpdit.woofarm.entity.Menu;
import com.lnpdit.woofarm.entity.PreProduct;
import com.lnpdit.woofarm.entity.Product;
import com.lnpdit.woofarm.entity.ProductByClass;
import com.lnpdit.woofarm.entity.ProductRow;
import com.lnpdit.woofarm.http.SoapRes;
import com.lnpdit.woofarm.instance.Instance;
import com.lnpdit.woofarm.page.activity.product.ProductInfoActivity;
import com.lnpdit.woofarm.page.activity.product.ProductListActivity;
import com.lnpdit.woofarm.page.activity.product.ProductSearchActivity;
import com.lnpdit.woofarm.page.adapter.MenuListAdapter;
import com.lnpdit.woofarm.page.adapter.PreProductListAdapter;
import com.lnpdit.woofarm.pulltorefresh.library.PullToRefreshBase;
import com.lnpdit.woofarm.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.lnpdit.woofarm.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.lnpdit.woofarm.pulltorefresh.library.PullToRefreshListView;
import com.lnpdit.woofarm.utils.SOAP_UTILS;
import com.lnpdit.woofarm.utils.advert.ImageCycleView;
import com.lnpdit.woofarm.utils.advert.ImageCycleView.ImageCycleViewListener;
import com.slidingmenu.lib.SlidingMenu;

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
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 首页
 * 
 * @author yinbingqian 类名称：FrontActivity 创建时间:2016-11-22
 */
public class FrontActivity extends BaseActivity implements OnClickListener {
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
//    private List<ProductRow> productRowList;
    private List<ProductRow> preproductRowList;
    private List<Menu> menuList;
    private View firstView;
    // private View secondView;
//    private ListView productListView;//新品推荐
    private ListView preproductListView;//预订商品
    private PullToRefreshListView listview_preproductlist;//预订商品  
    private ListView menuListView;//左侧弹出分类列表
    
//    private ProductListAdapter productlistAdapter;//新品推荐
    private PreProductListAdapter preproductlistAdapter;//预订商品
    private MenuListAdapter menulistAdapter;//左侧弹出列表
    private SearchView search_view;
    private TextView tvMorexp;
    private TextView tvMoreyd;
    private Resources resources;

    private List<IndexAdvertise> advertise_list;//轮播广告
//    private List<ProductByClass> product_list;//新品推荐
    private List<PreProduct> preproduct_list;//预订商品
    private PreProduct preproduct;//预订商品
//    private ProductByClass productByClass;//新品推荐
    
    private ImageView cm1;
    private ImageView cm2;
    private ImageView cm3;
    private ImageView cm4;
    private Button cm1Btn;
    private Button cm2Btn;
    private Button cm3Btn;
    private Button cm4Btn;
    private TextView cm1_tv;
    private TextView cm2_tv;
    private TextView cm3_tv;
    private TextView cm4_tv;
    private TextView cm1_price;
    private TextView cm2_price;
    private TextView cm3_price;
    private TextView cm4_price;

    private RelativeLayout layout1;
    private RelativeLayout layout2;
    private RelativeLayout layout3;
    private RelativeLayout layout4;
    private String productid1="";
    private String productid2="";
    private String productid3="";
    private String productid4="";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front);
        context = this;
        resources = this.getResources();

        initView();
        initData();  
        setListeners();

    }

    private void getDBData() {
        dbh = new DBHelper(this);
//      //新品推荐
//      productByClass = new ProductByClass();
//      product_list = dbh.queryProductByClass();
//      productRowList = new ArrayList<ProductRow>();
//      for (int i = 0; i < product_list.size(); i = i + 2) {
//          productByClass = product_list.get(i);
//          productRow = new ProductRow();
//          productRow.setThumb1(productByClass.getImage());
//          productRow.setProid1(productByClass.getId());
//          productRow.setName1(productByClass.getName());
//          productRow.setPrice1(productByClass.getPrice());
//
//          if (i + 1 != product_list.size()) {
//              productByClass = product_list.get(i + 1);
//              productRow.setThumb2(productByClass.getImage());
//              productRow.setProid2(productByClass.getId());
//              productRow.setName2(productByClass.getName());
//              productRow.setPrice2(productByClass.getPrice());
//          } else {
//              productRow.setThumb2("none");
//              productRow.setProid2("none");
//              productRow.setName2("none");
//              productRow.setPrice2("none");
//          }
//          productRowList.add(productRow);
//      }
        

        //商品预订
          preproduct = new PreProduct();
          preproduct_list = dbh.queryPreProduct();
          preproductRowList = new ArrayList<ProductRow>();
          
          for (int i = 0; i < preproduct_list.size(); i = i + 2) {
              preproduct = preproduct_list.get(i);
              productRow = new ProductRow();
              productRow.setThumb1(preproduct.getImage());
              productRow.setProid1(preproduct.getId());
              productRow.setName1(preproduct.getName());
              productRow.setPrice1(preproduct.getPrice());

              if (i + 1 != preproduct_list.size()) {
                  preproduct = preproduct_list.get(i + 1);
                  productRow.setThumb2(preproduct.getImage());
                  productRow.setProid2(preproduct.getId());
                  productRow.setName2(preproduct.getName());
                  productRow.setPrice2(preproduct.getPrice());
              } else {
                  productRow.setThumb2("none");
                  productRow.setProid2("none");
                  productRow.setName2("none");
                  productRow.setPrice2("none");
              }
              preproductRowList.add(productRow);
          }
        
//        productListView.addHeaderView(firstView);
//        productlistAdapter = new ProductListAdapter(context, productRowList);
//        productListView.setAdapter(productlistAdapter);
//        
        preproductListView.addHeaderView(firstView);
        
        preproductlistAdapter = new PreProductListAdapter(context, preproductRowList);
        preproductListView.setAdapter(preproductlistAdapter);
        
        infos = new ArrayList<ADInfo>();
        //轮播广告
        infos = dbh.queryADIndex();
        mAdView = (ImageCycleView) findViewById(R.id.ad_view);
        if (infos.size() != 0) {
            mAdView.setImageResources(infos, mAdCycleViewListener);
        }
    }
    private void getMenuDBData() {
        dbh = new DBHelper(this);
        
        menuList = new ArrayList<Menu>();    
        menuList = dbh.queryMenu();
        menulistAdapter = new MenuListAdapter(context, menuList);
        menuListView.setAdapter(menulistAdapter);
    }
    private void initView() {
        firstView = getLayoutInflater().inflate(R.layout.front_first, null);

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
         
        menuListView = (ListView) view.findViewById(R.id.listview_menulist);
//        menuListView.setClickable(true);

        menu.setSlidingEnabled(false);


        layout1 = (RelativeLayout) view.findViewById(R.id.layout1);
        layout2 = (RelativeLayout) view.findViewById(R.id.layout2);
        layout3 = (RelativeLayout) view.findViewById(R.id.layout3);
        layout4 = (RelativeLayout) view.findViewById(R.id.layout4);
                
        search_view = (SearchView) this.findViewById(R.id.search_view);
        search_view.setClickable(true);
        search_view.setOnClickListener(this);
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

        // 轮训广告
        mAdView = (ImageCycleView) firstView.findViewById(R.id.ad_view);

        tvMorexp = (TextView) firstView.findViewById(R.id.morexp_tv);
        tvMorexp.setOnClickListener(this);
        tvMoreyd = (TextView) firstView.findViewById(R.id.moreyd_tv);
        tvMoreyd.setOnClickListener(this);

//        productListView = (ListView) firstView.findViewById(R.id.listview_productlist);
        listview_preproductlist = (PullToRefreshListView) this.findViewById(R.id.listview_preproductlist);
        preproductListView = listview_preproductlist.getRefreshableView();
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
        cm1_tv = (TextView) firstView.findViewById(R.id.cm1_tv);
        cm2_tv = (TextView) firstView.findViewById(R.id.cm2_tv);
        cm3_tv = (TextView) firstView.findViewById(R.id.cm3_tv);
        cm4_tv = (TextView) firstView.findViewById(R.id.cm4_tv);
        cm1_price = (TextView) firstView.findViewById(R.id.cm1_price);
        cm2_price = (TextView) firstView.findViewById(R.id.cm2_price);
        cm3_price = (TextView) firstView.findViewById(R.id.cm3_price);
        cm4_price = (TextView) firstView.findViewById(R.id.cm4_price);
    }

    private void initData() {
        getDBData();
        
        String[] property_va = new String[] {};
        soapService.getIndex(property_va);
        
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
            intent.putExtra("selltype", "sell");
            intent.putExtra("title", "新品推荐");
            startActivity(intent);
            break;
        case R.id.moreyd_tv:
            intent = new Intent();
            intent.setClass(this, ProductListActivity.class);
            intent.putExtra("title", "预订商品");
            intent.putExtra("selltype", "presell");
            startActivity(intent);
            break;
        case R.id.cm1:
        case R.id.cm1_btn:

            intent = new Intent();
            intent.setClass(this, ProductInfoActivity.class);
            intent.putExtra("name", cm1_tv.getText().toString());
            intent.putExtra("productid", productid1);
            startActivity(intent);
            break;
        case R.id.cm2:
        case R.id.cm2_btn:
            intent = new Intent();
            intent.setClass(this, ProductInfoActivity.class);
            intent.putExtra("name", cm2_tv.getText().toString());
            intent.putExtra("productid", productid2);
            startActivity(intent);
            break;
        case R.id.cm3:
        case R.id.cm3_btn:
            intent = new Intent();
            intent.setClass(this, ProductInfoActivity.class);
            intent.putExtra("name", cm3_tv.getText().toString());
            intent.putExtra("productid", productid3);
            startActivity(intent);
            break;
        case R.id.cm4:
        case R.id.cm4_btn:
            intent = new Intent();
            intent.setClass(this, ProductInfoActivity.class);
            intent.putExtra("name", cm4_tv.getText().toString());
            intent.putExtra("productid", productid4);
            startActivity(intent);
            break;

        default:
            break;
        }
    }

//    private void goList(String title) {
//
//        Intent intent = new Intent();
//        intent.setClass(this, ProductListActivity.class);
//        intent.putExtra("title", title);
//        startActivity(intent);
//        menu.toggle();
//    }

    private void setListeners() {
        
        menuListView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {

                Intent intent = new Intent();
                intent.putExtra("title", menuList.get(position).getName());
                intent.putExtra("classId", menuList.get(position).getId());
                intent.putExtra("selltype", "menu");
                intent.setClass(context, ProductListActivity.class);
                startActivity(intent);
                menu.toggle();
            }
        });
        
        listview_preproductlist.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("Click News");
           
            }
        });
        listview_preproductlist.setOnRefreshListener(new OnRefreshListener<ListView>() {

            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                String[] property_va = new String[] {};
                soapService.getIndex(property_va);

            }
        });
        // end of list
        listview_preproductlist.setOnLastItemVisibleListener(new OnLastItemVisibleListener() {

            @Override
            public void onLastItemVisible() {
            
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdView.startImageCycle();
        
        String[] property_va = new String[] {};
        soapService.getIndex(property_va);
//        
//        String[] property_vamenu = new String[] {};
//        soapService.getProductClass(property_vamenu);
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

    
    public void onEvent(SoapRes obj) {
        if (obj.getCode().equals(SOAP_UTILS.METHOD.GETINDEX)) {
            listview_preproductlist.onRefreshComplete();
            if (obj.getObj() != null) {
                try {
                    
                JSONArray json_arr = new JSONArray(obj.getObj().toString());

                JSONObject json_advertise = (JSONObject) json_arr.get(0);

                String advertiseresult = json_advertise.get("status_advertise").toString();
             
                JSONArray advertise_array = json_advertise.getJSONArray("msg_advertise");
                advertise_list = new ArrayList<IndexAdvertise>();
                if(advertiseresult.equals("true")){
                
                for (int j = 0; j < advertise_array.length(); j++) {
                    JSONObject json_advertisenews = (JSONObject) advertise_array.get(j);
                    IndexAdvertise advertise = new IndexAdvertise();
                    advertise.setImage(json_advertisenews.get("image").toString());
                    advertise.setLinkaddress(json_advertisenews.get("linkaddress").toString());

                    advertise_list.add(advertise);
                 }
                }else{
                    String advertiseresult_str = json_advertise.get("msg_advertise").toString();
                    Toast.makeText(context, advertiseresult_str, Toast.LENGTH_SHORT).show();
                }
                
                JSONObject json_product = (JSONObject) json_arr.get(1);

                String productresult = json_product.get("status_product").toString();
             
                JSONArray product_array = json_product.getJSONArray("msg_product");
//                product_list = new ArrayList<ProductByClass>();
                if(productresult.equals("true")){
                    //设置数据空隐藏布局
                    if(product_array.length() < 1){
                        layout1.setVisibility(8);
                    }else if(product_array.length() < 2){

                        layout2.setVisibility(8);
                    }else if(product_array.length() < 3){

                        layout3.setVisibility(8);
                    }else if(product_array.length() < 4){

                        layout4.setVisibility(8);
                    }
                    //获取数据
                    for (int k = 0; k < product_array.length(); k++) {
                        JSONObject json_productnews = (JSONObject) product_array.get(k);
                        ProductByClass product = new ProductByClass();
                        product.setImage(json_productnews.get("image").toString());
                        product.setId(json_productnews.get("id").toString());
                        product.setPrice(json_productnews.get("price").toString());
                        product.setName(json_productnews.get("name").toString());

                        String name = json_productnews.get("name").toString();
                        String price = json_productnews.get("price").toString();
                        if(k == 0){
                            cm1_tv.setText(name);
                            cm1_price.setText(price);
                            productid1 = json_productnews.get("id").toString();
                        }else if(k == 1){
                            cm2_tv.setText(name);
                            cm2_price.setText(price);
                            productid2 = json_productnews.get("id").toString();
                        }else if(k == 2){
                            cm3_tv.setText(name);
                            cm3_price.setText(price);
                            productid3 = json_productnews.get("id").toString();
                        }else if(k == 3){
                            cm4_tv.setText(name);
                            cm4_price.setText(price);
                            productid4 = json_productnews.get("id").toString();
                        }
                     }
                    
                    }else{
                        String productresult_str = json_advertise.get("msg_product").toString();
                        Toast.makeText(context, productresult_str, Toast.LENGTH_SHORT).show();
                    }
//                dbh.clearProductByClass();
//                if (product_list.size() != 0) {
//
//                    dbh.insProductByClassList(product_list);
//                }else{
//                    productListView.setAdapter(null);
//                }

                
                JSONObject json_preproduct = (JSONObject) json_arr.get(2);

                String preproductresult = json_preproduct.get("status_preproduct").toString();
             
                preproduct_list = new ArrayList<PreProduct>();
                if(preproductresult.equals("true")){

                    JSONArray preproduct_array = json_preproduct.getJSONArray("msg_preproduct");
                    for (int g = 0; g < preproduct_array.length(); g++) {
                        JSONObject json_preproductnews = (JSONObject) preproduct_array.get(g);
                        PreProduct preproduct = new PreProduct();
                        preproduct.setImage(json_preproductnews.get("image").toString());
                        preproduct.setId(json_preproductnews.get("id").toString());
                        preproduct.setPrice(json_preproductnews.get("price").toString());
                        preproduct.setName(json_preproductnews.get("name").toString());
                        preproduct_list.add(preproduct);
                     }
                    }else{
                        String preproductresult_str = json_advertise.get("msg_preproduct").toString();
                        Toast.makeText(context, preproductresult_str, Toast.LENGTH_SHORT).show();
                    }
                dbh.clearPreProduct();
                if (preproduct_list.size() != 0) {

                    dbh.insPreProductList(preproduct_list);
                }else{
                    preproductListView.setAdapter(null);
                }
                //广告轮训
                ADInfo adi = new ADInfo();
                for (int i = 0; i < advertise_list.size(); i++) {
                    adi = new ADInfo();
                    adi.setImage( advertise_list.get(i).getImage());
                    adi.setLinkaddress( advertise_list.get(i).getLinkaddress());
                    infos.add(adi);
                }
               
                dbh.clearADIndex();
                dbh.insADIndexList(infos);
                getDBData();
                } catch (Exception e) {
                    // TODO: handle exception
                }

              String[] property_vamenu = new String[] {};
              soapService.getProductClass(property_vamenu);
                }else{
                    Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
                }
        }else if (obj.getCode().equals(SOAP_UTILS.METHOD.GETPRODUCTCLASS)) {
            if (obj.getObj() != null) {
                try {
                    JSONObject json_obj = new JSONObject(obj.getObj().toString());
                    String result = json_obj.get("status").toString();
                    
                    List<Menu> menu_list = new ArrayList<Menu>();
                   if(result.equals("true")){
                       
                    JSONArray message_array = json_obj.getJSONArray("msg");
                    for (int i = 0; i < message_array.length(); i++) {
                        JSONObject json_news = (JSONObject) message_array.get(i);
                        Menu hpn = new Menu();
                        hpn.setId(json_news.get("id").toString());
                        hpn.setName(json_news.get("name").toString());

                        menu_list.add(hpn);
                    }
                   }else{
                       String msg_str = json_obj.get("msg").toString();
                       Toast.makeText(context, msg_str, Toast.LENGTH_SHORT).show();
                   }
                   dbh.clearMenu();
                   if (menu_list.size() != 0) {

                       dbh.insMenuList(menu_list);
                   }else{
                       menuListView.setAdapter(null);
                   }
                   getMenuDBData();
                } catch (Exception e) {
                    // TODO: handle exception
                }
                }else{
                    Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
                }  
        }
     }
}
