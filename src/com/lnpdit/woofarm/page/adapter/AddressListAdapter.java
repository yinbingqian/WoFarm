package com.lnpdit.woofarm.page.adapter;

import java.util.List;

import com.lnpdit.woofarm.R;
import com.lnpdit.woofarm.entity.Address;
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
import android.widget.TextView;

public class AddressListAdapter extends BaseAdapter {
    private class buttonViewHolder {
        TextView user;
        TextView phone;
        TextView addinfo;
        ImageView img_edit;
        TextView tv_edit;
        ImageView img_delete;
        TextView tv_delete;
        CheckBox cb;
    }

    private Address appInfo;
    private List<Address> mAppList;
    private LayoutInflater mInflater;
    private Context mContext;
    private buttonViewHolder holder;

    public AddressListAdapter(Context context, List<Address> appList) {
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

            convertView = mInflater.inflate(R.layout.item_address, parent,
                    false);
            holder = new buttonViewHolder();
            holder.user = (TextView) convertView.findViewById(R.id.user_tv);
            holder.phone = (TextView) convertView.findViewById(R.id.phone_tv);
            holder.addinfo = (TextView) convertView
                    .findViewById(R.id.address_tv);
            holder.img_edit = (ImageView) convertView
                    .findViewById(R.id.img_edit);
            holder.tv_edit = (TextView) convertView.findViewById(R.id.tv_edit);
            holder.img_delete = (ImageView) convertView
                    .findViewById(R.id.img_delete);
            holder.tv_delete = (TextView) convertView
                    .findViewById(R.id.tv_delete);
            holder.cb = (CheckBox) convertView.findViewById(R.id.chkItem);
            convertView.setTag(holder);

        } else {

            holder = (buttonViewHolder) convertView.getTag();

        }

        appInfo = mAppList.get(position);

        if (appInfo.getIfdefault().equals("1")) {
            holder.cb.setChecked(true);
        }
        holder.user.setText("收货人" + appInfo.getName());
        holder.phone.setText(appInfo.getPhone());
        holder.addinfo.setText("收货地址" + appInfo.getAddinfo());

        holder.img_edit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(mContext, EditAddressActivity.class);
                mContext.startActivity(intent);
            }
        });
        holder.tv_edit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(mContext, EditAddressActivity.class);
                mContext.startActivity(intent);
            }
        });
        return convertView;
    }

}