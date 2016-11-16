package com.lnpdit.woofarm.page.activity.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.eroad.widget.calendar.CalanderActivity;
import com.lnpdit.woofarm.R;
import com.lnpdit.woofarm.db.DBHelper;
import com.lnpdit.woofarm.entity.ADInfo;
import com.lnpdit.woofarm.entity.DataInfoUn;
import com.lnpdit.woofarm.entity.Address;
import com.lnpdit.woofarm.entity.Camera;
import com.lnpdit.woofarm.entity.ProductRow;
import com.lnpdit.woofarm.instance.Instance;
import com.lnpdit.woofarm.page.activity.login.LoginActivity;
import com.lnpdit.woofarm.page.adapter.AddressListAdapter;
import com.lnpdit.woofarm.page.adapter.CameraListAdapter;
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
import android.widget.Button;

public class CameraListActivity extends Activity implements OnClickListener {
    private DBHelper dbh;
    private Context context;
    private Camera camera;
    private List<Camera> cameraList;
    private ListView cameraListView;
    private CameraListAdapter cameralistAdapter;
    private ImageView imgBack;
    private TextView tvBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cameralist);
        context = this;

        initView();
        initData();
        // setListeners();

    }

    private void initView() {
        imgBack = (ImageView) findViewById(R.id.img_back);
        imgBack.setOnClickListener(this);
        tvBack = (TextView) findViewById(R.id.tv_back);
        tvBack.setOnClickListener(this);

        cameraListView = (ListView) findViewById(R.id.listview_cameralist);
    }

    private void initData() {

        dbh = new DBHelper(this);
        camera = new Camera();
        cameraList = new ArrayList<Camera>();

        cameraList = dbh.queryCamera();
        
        cameralistAdapter = new CameraListAdapter(context, cameraList);
        cameraListView.setAdapter(cameralistAdapter);
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
        // listview_productlist.setOnItemClickListener(new OnItemClickListener()
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
