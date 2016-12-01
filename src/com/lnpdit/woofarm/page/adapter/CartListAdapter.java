package com.lnpdit.woofarm.page.adapter;

import java.util.List;

import com.lnpdit.woofarm.R;
import com.lnpdit.woofarm.entity.Cart;
import com.lnpdit.woofarm.entity.DataInfoUn;
import com.lnpdit.woofarm.entity.ProductRow;
import com.lnpdit.woofarm.instance.Instance;
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
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CartListAdapter extends BaseAdapter {
    private class buttonViewHolder {
        RelativeLayout product_layout;
        CheckBox chkItem;
        ImageView imageView1;
        TextView productname;
        TextView productweight;
        TextView productprice;
        TextView productcount;
    }

    private Cart appInfo;
    private List<Cart> mAppList;
    private LayoutInflater mInflater;
    private Context mContext;
    private buttonViewHolder holder;

    public CartListAdapter(Context context, List<Cart> appList) {
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

            convertView = mInflater.inflate(R.layout.item_shopping, parent,
                    false);
            holder = new buttonViewHolder();
            holder.product_layout = (RelativeLayout) convertView
                    .findViewById(R.id.product_layout);
            holder.chkItem = (CheckBox) convertView.findViewById(R.id.chkItem);
            holder.imageView1 = (ImageView) convertView
                    .findViewById(R.id.imageView1);
            holder.productname = (TextView) convertView
                    .findViewById(R.id.productname);
            holder.productweight = (TextView) convertView
                    .findViewById(R.id.productweight);
            holder.productprice = (TextView) convertView
                    .findViewById(R.id.productprice);
            holder.productcount = (TextView) convertView
                    .findViewById(R.id.productcount);
            
            convertView.setTag(holder);

        } else {

            holder = (buttonViewHolder) convertView.getTag();

        }

        appInfo = mAppList.get(position);

        holder.productname.setText(appInfo.getName());
        holder.productprice.setText(appInfo.getPrice() + "元/KG");
        holder.productcount.setText("* " + appInfo.getQuantity());
        
        String imgPath = appInfo.getImage();
        Instance.imageLoader.displayImage(imgPath, holder.imageView1, Instance.user_s_options);
        
        holder.product_layout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(mContext, ProductInfoActivity.class);
                mContext.startActivity(intent);
            }
        });
        holder.imageView1.setOnClickListener(new View.OnClickListener() {

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