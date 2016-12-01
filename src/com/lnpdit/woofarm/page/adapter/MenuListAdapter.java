package com.lnpdit.woofarm.page.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.lnpdit.woofarm.R;
import com.lnpdit.woofarm.entity.Menu;
import com.lnpdit.woofarm.entity.ProductRow;
import com.lnpdit.woofarm.page.activity.product.ProductInfoActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuListAdapter extends BaseAdapter {

    private Menu appInfo;
    private ArrayList<HashMap<String, Object>> appList;

    private class buttonViewHolder {
        TextView menu_tv;
    }

    private List<Menu> mAppList;
    private Menu app;
    private LayoutInflater mInflater;
    private Context mContext;
    private buttonViewHolder holder;

    public MenuListAdapter(Context context, List<Menu> appList) {
        mAppList = appList;
        mContext = context;
        mInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mAppList.size();
    }

    @Override
    public Object getItem(int position) {
        return mAppList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void removeItem(int positon) {
        mAppList.remove(positon);
        // this.notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        buttonViewHolder holder = null;
        appInfo = mAppList.get(position);
        // 无convertView，需要new出各个控件
        if (convertView == null) {
            // 按当前所需的样式，确定new的布局
            convertView = mInflater.inflate(R.layout.item_menu, parent,
                    false);
            holder = new buttonViewHolder();
            holder.menu_tv = (TextView) convertView
                    .findViewById(R.id.menu_tv);
            holder.menu_tv.setText(mAppList.get(position).getName());
//            holder.menu_tv.setClickable(true);
            convertView.setTag(holder);

        } else {
            holder = (buttonViewHolder) convertView.getTag();
        }

//        holder.menu_tv.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setClass(mContext, ProductListActivity.class);
//                intent.putExtra("title", appInfo.getName());
//                mContext.startActivity(intent);
//            }
//        });
        return convertView;
    }

}