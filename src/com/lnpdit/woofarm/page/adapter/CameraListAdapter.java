package com.lnpdit.woofarm.page.adapter;

import java.util.List;

import com.lnpdit.woofarm.R;
import com.lnpdit.woofarm.entity.Address;
import com.lnpdit.woofarm.entity.Area;
import com.lnpdit.woofarm.entity.Camera;
import com.lnpdit.woofarm.entity.Cart;
import com.lnpdit.woofarm.entity.DataInfoUn;
import com.lnpdit.woofarm.entity.ProductRow;
import com.lnpdit.woofarm.page.activity.product.ProductInfoActivity;
import com.lnpdit.woofarm.page.activity.setting.EditAddressActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CameraListAdapter extends BaseAdapter {
    private class buttonViewHolder {
        RelativeLayout all_layout;
        TextView tv_name;
    }

    private Camera appInfo;
    private List<Camera> mAppList;
    private LayoutInflater mInflater;
    private Context mContext;
    private buttonViewHolder holder;

    public CameraListAdapter(Context context, List<Camera> appList) {
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
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // buttonViewHolder holder = null;

        // 无convertView，需要new出各个控件
        if (convertView == null) {
            // 按当前所需的样式，确定new的布局

            convertView = mInflater.inflate(R.layout.item_camera, parent,
                    false);
            holder = new buttonViewHolder();
            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            holder.all_layout = (RelativeLayout) convertView
                    .findViewById(R.id.all_layout);
            convertView.setTag(holder);

        } else {

            holder = (buttonViewHolder) convertView.getTag();

        }

        appInfo = mAppList.get(position);
        int value = 0;
        Class<R.drawable> cls = R.drawable.class;
        try {
            value = cls.getDeclaredField(appInfo.getImg()).getInt(null);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        holder.tv_name.setText(appInfo.getName());
        holder.all_layout.setBackgroundResource(value);
        holder.tv_name.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(mContext, ProductInfoActivity.class);
                mContext.startActivity(intent);
            }
        });
        holder.all_layout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(mContext, ProductInfoActivity.class);
                mContext.startActivity(intent);
            }
        });
        return convertView;
    }

}