package com.lnpdit.woofarm.page.activity.tabhost.item;

import java.util.ArrayList;
import java.util.List;

import com.eroad.widget.calendar.CalanderActivity;
import com.lnpdit.woofarm.R;
import com.lnpdit.woofarm.db.DBHelper;
import com.lnpdit.woofarm.entity.Area;
import com.lnpdit.woofarm.entity.Cart;
import com.lnpdit.woofarm.entity.DataInfoUn;
import com.lnpdit.woofarm.http.SoapRes;
import com.lnpdit.woofarm.page.activity.video.RealPlayActivity;
import com.lnpdit.woofarm.page.adapter.AreaListAdapter;
import com.lnpdit.woofarm.page.adapter.CartListAdapter;
import com.lnpdit.woofarm.page.adapter.MonitorListAdapter;
import com.lnpdit.woofarm.pulltorefresh.library.PullToRefreshBase;
import com.lnpdit.woofarm.pulltorefresh.library.PullToRefreshListView;
import com.lnpdit.woofarm.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.lnpdit.woofarm.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;

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

public class AreaListActivity extends Activity {
    /** Called when the activity is first created. */

    private DBHelper dbh;
    Button m_btLogin;
    private PullToRefreshListView listview_monitorlist;
    private ListView areaListView;
    private int pageIndex = 1;
    Context context;
    private Area area;
    private List<Area> areaList;
    private AreaListAdapter arealistAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_arealist);

        initView();

        initData();

    }

    private void initView() {
        areaListView = (ListView) findViewById(R.id.listview_arealist);

    }

    private void initData() {

        dbh = new DBHelper(this);
        area = new Area();
        areaList = new ArrayList<Area>();

        areaList = dbh.queryArea();

        arealistAdapter = new AreaListAdapter(context, areaList);
        areaListView.setAdapter(arealistAdapter);
    }

}