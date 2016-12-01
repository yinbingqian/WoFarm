package com.lnpdit.woofarm.page.activity.welcome;

import com.lnpdit.woofarm.base.component.BaseActivity;
import com.lnpdit.woofarm.db.DBHelper;
import com.lnpdit.woofarm.entity.Address;
import com.lnpdit.woofarm.entity.Area;
import com.lnpdit.woofarm.entity.Camera;
import com.lnpdit.woofarm.entity.Cart;
import com.lnpdit.woofarm.entity.Classify;
import com.lnpdit.woofarm.entity.Order;
import com.lnpdit.woofarm.entity.Product;
import com.lnpdit.woofarm.http.RdaResultPack;
import com.lnpdit.woofarm.mservice.UserService;
import com.lnpdit.woofarm.page.activity.login.LoginActivity;
import com.lnpdit.woofarm.page.activity.tabhost.MainTabHostActivity;

import java.util.List;

import com.lnpdit.woofarm.R;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class WelcomeActivity extends BaseActivity {

    private UserService userService;
    private DBHelper dbh;
    private Product product;
    private Order order;
    private Cart cart;
    private Address address;
    private Classify classify;
    private Area area;
    private Camera camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_welcome);

        initData();

        new Handler().postDelayed(new Runnable() {

            public void run() {

                boolean mFirst = isFirstEnter(WelcomeActivity.this,
                        WelcomeActivity.this.getClass().getName());
                if (mFirst) {

                    Intent intent = new Intent();
                    intent.setClass(getBaseContext(), GuideActivity.class);
                    startActivity(intent);
                    writeEnter(WelcomeActivity.this);
                } else {
                    Intent intent = new Intent();
                    intent.setClass(getBaseContext(),LoginActivity.class);
//                    intent.setClass(getBaseContext(),MainTabHostActivity.class);
                    startActivity(intent);
                }

                finish();
            }

        }, 1500);
    }

    private void initData() {
        dbh = new DBHelper(this);
        product = new Product();
        order = new Order();
        cart = new Cart();
        classify = new Classify();
        address = new Address();
        area = new Area();
        camera = new Camera();

        dbh.clearAllProduct();
        dbh.clearAllOrder();
        dbh.clearAllCart();
        dbh.clearAllClassify();
        dbh.clearAllAddress();
        dbh.clearAllArea();
        dbh.clearAllCamera();

        initProduct();
//        initOrder();
//        initCart();
//        initAddress();
//        initArea();
        initCamera();

    }

    private void initProduct() {
        product.setProid("1");
        product.setPrice("4.5");
        product.setUptime("2016年10月12日");
        product.setPlace("辽宁省锦州市");
        product.setName("甜查理");
        product.setThumb("cm1");
        product.setPic("cm1");
        product.setIp("");
        product.setPort("");
        product.setUsername("");
        product.setPassword("");
        product.setChno("");
        dbh.insertProduct(product);

        product.setProid("2");
        product.setPrice("2.5");
        product.setUptime("2016年10月13日");
        product.setPlace("辽宁省锦州市");
        product.setName("白雪公主");
        product.setThumb("cm2");
        product.setPic("cm2");
        product.setIp("");
        product.setPort("");
        product.setUsername("");
        product.setPassword("");
        product.setChno("");
        dbh.insertProduct(product);

        product.setProid("3");
        product.setPrice("6");
        product.setUptime("2016年10月13日");
        product.setPlace("辽宁省锦州市");
        product.setName("红颜九九");
        product.setThumb("cm3");
        product.setPic("cm3");
        product.setIp("");
        product.setPort("");
        product.setUsername("");
        product.setPassword("");
        product.setChno("");
        dbh.insertProduct(product);

        product.setProid("4");
        product.setPrice("7");
        product.setUptime("2016年10月13日");
        product.setPlace("辽宁省锦州市");
        product.setName("京桃香");
        product.setThumb("cm4");
        product.setPic("cm4");
        product.setIp("");
        product.setPort("");
        product.setUsername("");
        product.setPassword("");
        product.setChno("");
        dbh.insertProduct(product);

    }

//    private void initOrder() {
//        order.setUserid("1");
//        order.setOrderid("1");
//        order.setProid("1");
//        order.setType("水果类");
//        order.setName("白雪公主");
//        order.setPrice("4.5");
//        order.setThumb("cm1");
//        order.setHj("45");
//        order.setResult("交易成功");
//        dbh.insertOrder(order);
//
//        order.setUserid("1");
//        order.setOrderid("2");
//        order.setProid("1");
//        order.setType("水果类");
//        order.setName("甜查理");
//        order.setPrice("4.5");
//        order.setThumb("cm2");
//        order.setHj("20");
//        order.setResult("交易成功");
//        dbh.insertOrder(order);
//
//        order.setUserid("1");
//        order.setOrderid("3");
//        order.setProid("1");
//        order.setType("水果类");
//        order.setName("甜查理");
//        order.setPrice("4.5");
//        order.setThumb("cm3");
//        order.setHj("35");
//        order.setResult("交易成功");
//        dbh.insertOrder(order);
//    }

//    private void initCart() {
//        cart.setUserid("1");
//        cart.setCartid("1");
//        cart.setProid("1");
//        cart.setCount("5");
//        cart.setName("甜查理");
//        cart.setPrice("4.5");
//        cart.setThumb("cm1");
//        cart.setHj("45");
//        dbh.insertCart(cart);
//
//        cart.setUserid("1");
//        cart.setCartid("2");
//        cart.setProid("1");
//        cart.setCount("4");
//        cart.setName("桃熏");
//        cart.setPrice("6.0");
//        cart.setThumb("cm2");
//        cart.setHj("18");
//        dbh.insertCart(cart);
//
//        cart.setUserid("1");
//        cart.setCartid("4");
//        cart.setProid("1");
//        cart.setCount("4");
//        cart.setName("京藏香");
//        cart.setPrice("8.0");
//        cart.setThumb("cm3");
//        cart.setHj("18");
//        dbh.insertCart(cart);
//
//        cart.setUserid("1");
//        cart.setCartid("4");
//        cart.setProid("1");
//        cart.setCount("4");
//        cart.setName("京桃香");
//        cart.setPrice("8.0");
//        cart.setThumb("cm4");
//        cart.setHj("18");
//        dbh.insertCart(cart);
//    }
//
//    private void initAddress() {
//        address.setUserid("1");
//        address.setAddid("1");
//        address.setIfdefault("1");
//        address.setName("白杰");
//        address.setPhone("18831405840");
//        address.setAddinfo("收货地址：沈阳市浑南新区金科街");
//        dbh.insertAddress(address);
//
//        address.setUserid("1");
//        address.setAddid("2");
//        address.setIfdefault("0");
//        address.setName("白杰");
//        address.setPhone("18831405840");
//        address.setAddinfo("收货地址：沈阳市浑南新区金科街");
//        dbh.insertAddress(address);
//
//        address.setUserid("1");
//        address.setAddid("3");
//        address.setIfdefault("0");
//        address.setName("白杰");
//        address.setPhone("18831405840");
//        address.setAddinfo("收货地址：沈阳市浑南新区金科街");
//        dbh.insertAddress(address);
//    }

//    private void initArea() {
//        area.setAreaid("1");
//        area.setImg("area1");
//        area.setName("前社草莓");
//        dbh.insertArea(area);
//
//        area.setAreaid("2");
//        area.setImg("area2");
//        area.setName("李官油桃");
//        dbh.insertArea(area);
//
//        area.setAreaid("4");
//        area.setImg("area3");
//        area.setName("铁岭食用菌");
//        dbh.insertArea(area);
//    }

    private void initCamera() {
        camera.setAreaid("1");
        camera.setCameraid("1");
        camera.setImg("area1");
        camera.setName("甜查理1号棚");
        dbh.insertCamera(camera);

        camera.setAreaid("1");
        camera.setCameraid("2");
        camera.setImg("area1");
        camera.setName("甜查理2号棚");
        dbh.insertCamera(camera);

        camera.setAreaid("1");
        camera.setCameraid("3");
        camera.setImg("area1");
        camera.setName("甜查理3号棚");
        dbh.insertCamera(camera);

    }

    private void initClassify() {

    }

    protected void onEventMainThread(RdaResultPack http) {
        if (http.Match(userService.This(), "test")) {
            if (http.Success()) {
                // TODO
            } else if (http.HttpFail()) {
                Toast.makeText(this, "服务器连接失败，请稍后再试！", Toast.LENGTH_SHORT)
                        .show();
                // Alert("");
            } else if (http.ServerError()) {
                if (http.Message().toString().trim().equals("E003")) {
                    Toast.makeText(this, "未查询到相关数据", Toast.LENGTH_SHORT).show();
                    // Alert("暂无车会活动！");
                } else {
                    Toast.makeText(this, "服务器请求失败，请稍后再试！", Toast.LENGTH_SHORT)
                            .show();
                }
            }
        }
    }

    // ****************************************************************
    // 判断应用是否初次加载，读取SharedPreferences中的guide_activity字段
    // ****************************************************************
    private static final String SHAREDPREFERENCES_NAME = "my_pref";
    private static final String KEY_GUIDE_ACTIVITY = "guide_activity";

    private boolean isFirstEnter(Context context, String className) {
        if (context == null || className == null
                || "".equalsIgnoreCase(className))
            return false;
        String mResultStr = context
                .getSharedPreferences(SHAREDPREFERENCES_NAME,
                        Context.MODE_WORLD_READABLE)
                .getString(KEY_GUIDE_ACTIVITY, "");// 取得所有类名 如
                                                   // com.my.MainActivity
        if (mResultStr.equalsIgnoreCase("false"))
            return false;
        else
            return true;
    }

    private void writeEnter(Context context) {
        SharedPreferences settings = getSharedPreferences(
                SHAREDPREFERENCES_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();

        editor.putString(KEY_GUIDE_ACTIVITY, "false");
        editor.commit();

    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }
}
