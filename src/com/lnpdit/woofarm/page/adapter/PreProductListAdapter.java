package com.lnpdit.woofarm.page.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.lnpdit.woofarm.R;
import com.lnpdit.woofarm.entity.DataInfoUn;
import com.lnpdit.woofarm.entity.Product;
import com.lnpdit.woofarm.entity.ProductRow;
import com.lnpdit.woofarm.instance.Instance;
import com.lnpdit.woofarm.page.activity.product.ProductInfoActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

public class PreProductListAdapter extends BaseAdapter {

    private ProductRow appInfo;
    private ArrayList<HashMap<String, Object>> appList;

    private class buttonViewHolder {
        ImageView imageView1;
        ImageView imageView2;
        TextView productname1_tv;
        TextView productname2_tv;
        TextView productprice1_tv;
        TextView productprice2_tv;
        Button submit1_btn;
        Button submit2_btn;
    }

    private List<ProductRow> mAppList;
    private ProductRow app;
    private LayoutInflater mInflater;
    private Context mContext;
    private buttonViewHolder holder;

    public PreProductListAdapter(Context context, List<ProductRow> appList) {
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

        // 无convertView，需要new出各个控件
        if (convertView == null) {
            // 按当前所需的样式，确定new的布局
            convertView = mInflater.inflate(R.layout.item_product, parent,
                    false);
            holder = new buttonViewHolder();
            holder.imageView1 = (ImageView) convertView
                    .findViewById(R.id.imageView1);
            holder.imageView2 = (ImageView) convertView
                    .findViewById(R.id.imageView2);
            holder.productname1_tv = (TextView) convertView
                    .findViewById(R.id.productname1_tv);
            holder.productname2_tv = (TextView) convertView
                    .findViewById(R.id.productname2_tv);
            holder.productprice1_tv = (TextView) convertView
                    .findViewById(R.id.productprice1_tv);
            holder.productprice2_tv = (TextView) convertView
                    .findViewById(R.id.productprice2_tv);
            holder.submit1_btn = (Button) convertView
                    .findViewById(R.id.submit1_btn);
            holder.submit2_btn = (Button) convertView
                    .findViewById(R.id.submit2_btn);

            convertView.setTag(holder);

        } else {
            holder = (buttonViewHolder) convertView.getTag();
        }
        appInfo = mAppList.get(position);

        holder.productname1_tv.setText(appInfo.getName1());
        holder.productprice1_tv.setText("¥ " + appInfo.getPrice1());
        String imgPath1 = appInfo.getThumb1();
        Instance.imageLoader.displayImage(imgPath1,holder.imageView1, Instance.user_s_options);
//        int value1 = 0;
//        int value2 = 0;
//        Class<R.drawable> cls = R.drawable.class;
//        try {
//            value1 = cls.getDeclaredField(appInfo.getThumb1()).getInt(null);
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        holder.imageView1.setImageResource(value1);
        
        if (!appInfo.getName2().equals("none")) {
            holder.productname2_tv.setText(appInfo.getName2());
            holder.productprice2_tv.setText("¥ " + appInfo.getPrice2());
            String imgPath2 = appInfo.getThumb2();
            Instance.imageLoader.displayImage(imgPath2,holder.imageView2, Instance.user_s_options);
//            try {
//                value2 = cls.getDeclaredField(appInfo.getThumb2()).getInt(null);
//            } catch (Exception e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//            holder.imageView2.setImageResource(value2);
        } else {
            holder.productname2_tv.setVisibility(TextView.GONE);
            holder.productprice2_tv.setVisibility(TextView.GONE);
            holder.imageView2.setVisibility(TextView.GONE);
            holder.submit2_btn.setVisibility(TextView.GONE);
        }
        holder.imageView1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(mContext, ProductInfoActivity.class);
                intent.putExtra("name", appInfo.getName1());
                intent.putExtra("productid", appInfo.getProid1());
                mContext.startActivity(intent);
            }
        });
        holder.submit1_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(mContext, ProductInfoActivity.class);
                intent.putExtra("name", appInfo.getName1());
                intent.putExtra("productid", appInfo.getProid1());
                mContext.startActivity(intent);
            }
        });
        holder.imageView2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(mContext, ProductInfoActivity.class);
                intent.putExtra("name", appInfo.getName2());
                intent.putExtra("productid", appInfo.getProid2());
                mContext.startActivity(intent);
            }
        });
        holder.submit2_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(mContext, ProductInfoActivity.class);
                intent.putExtra("name", appInfo.getName2());
                intent.putExtra("productid", appInfo.getProid2());
                mContext.startActivity(intent);
            }
        });
        return convertView;
    }

}