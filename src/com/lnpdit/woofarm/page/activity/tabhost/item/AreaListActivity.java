package com.lnpdit.woofarm.page.activity.tabhost.item;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.lnpdit.woofarm.R;
import com.lnpdit.woofarm.base.component.BaseActivity;
import com.lnpdit.woofarm.db.DBHelper;
import com.lnpdit.woofarm.entity.Area;
import com.lnpdit.woofarm.http.SoapRes;
import com.lnpdit.woofarm.page.adapter.AreaListAdapter;
import com.lnpdit.woofarm.pulltorefresh.library.PullToRefreshListView;
import com.lnpdit.woofarm.utils.SOAP_UTILS;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class AreaListActivity extends BaseActivity {
    /** Called when the activity is first created. */

    private DBHelper dbh;
    Button m_btLogin;
    private ListView areaListView;
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
        getDBData();

        String[] property_va= new String[] {};
        soapService.getShopList(property_va);
    }

    
    private void getDBData() {
        dbh = new DBHelper(this);
        area = new Area();
        areaList = new ArrayList<Area>();

        areaList = dbh.queryArea();

        arealistAdapter = new AreaListAdapter(context, areaList);
        areaListView.setAdapter(arealistAdapter);
    }
    
    public void onEvent(SoapRes obj) {
        if (obj.getCode().equals(SOAP_UTILS.METHOD.GETSHOPLIST)) {
            if (obj.getObj() != null) {
                try {
                    
                JSONObject json_obj = new JSONObject(obj.getObj().toString());

                String result = json_obj.get("status").toString();
                JSONArray message_array = json_obj.getJSONArray("msg");
             
                areaList = new ArrayList<Area>();
                if(result.equals("true")){
                    
                    for (int i = 0; i < message_array.length(); i++) {
                        JSONObject json_news = (JSONObject) message_array.get(i);
                        Area hpn = new Area();
                        hpn.setAreaid(json_news.get("id").toString());
                        hpn.setAddress(json_news.get("address").toString());
                        hpn.setImg(json_news.get("image").toString());

                        areaList.add(hpn);
                    }
                    }else{
                        String result_str = json_obj.get("msg").toString();
                        Toast.makeText(context, result_str, Toast.LENGTH_SHORT).show();
                    }
                dbh.clearAllArea();
                if (areaList.size() != 0) {

                    dbh.insAreaList(areaList);
                }else{
                    areaListView.setAdapter(null);
                }
                
                getDBData();
                } catch (Exception e) {
                    // TODO: handle exception
                }
                }else{
                    Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
                }
        }
     }
}