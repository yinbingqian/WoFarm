package com.lnpdit.woofarm.page.adapter;

import java.util.List;

import com.lnpdit.woofarm.R;
import com.lnpdit.woofarm.entity.Area;
import com.lnpdit.woofarm.entity.ProductImgs;
import com.lnpdit.woofarm.instance.Instance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ProductImgsListAdapter extends BaseAdapter {
    private class buttonViewHolder {
        ImageView prodetail_iv;
    }

    private ProductImgs appInfo;
    private List<ProductImgs> mAppList;
    private LayoutInflater mInflater;
    private Context mContext;
    private buttonViewHolder holder;

    public ProductImgsListAdapter(Context context, List<ProductImgs> appList) {
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

        // 无convertView，需要new出各个控件
        if (convertView == null) {
            // 按当前所需的样式，确定new的布局

            convertView = mInflater.inflate(R.layout.item_productinfoimgs, parent, false);
            holder = new buttonViewHolder();
            holder.prodetail_iv = (ImageView) convertView
                    .findViewById(R.id.prodetail_iv);
            convertView.setTag(holder);

        } else {

            holder = (buttonViewHolder) convertView.getTag();

        }

        appInfo = mAppList.get(position);
        String imgPath = appInfo.getImgs();
        Instance.imageLoader.displayImage(imgPath, holder.prodetail_iv, Instance.user_s_options);
        return convertView;
    }

}