package com.lnpdit.woofarm.page.adapter;

import java.util.List;

import com.lnpdit.woofarm.R;
import com.lnpdit.woofarm.entity.DataInfoUn;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MonitorListAdapter extends BaseAdapter {
    // private class buttonViewHolder {
    // TextView textView1;
    // TextView textView2;
    // TextView textView3;
    // TextView textView4;
    // ImageView imageView1;
    // }

    private class viewHolder1 {
        ImageView imageView1;
    }

    private class viewHolder2 {
        TextView textView1;
        TextView textView2;
        TextView textView3;
        TextView textView4;
        ImageView imageView1;
    }

    private List<DataInfoUn> mAppList;
    private LayoutInflater mInflater;
    private Context mContext;
    // private buttonViewHolder holder;

    final int VIEW_TYPE = 2;
    final int TYPE_1 = 0;
    final int TYPE_2 = 1;

    // public MonitorListAdapter(Context context) {
    public MonitorListAdapter(Context context, List<DataInfoUn> appList) {
        mAppList = appList;
        mContext = context;
        mInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mAppList.size();
    }

    // 每个convert view都会调用此方法，获得当前所需要的view样式
    @Override
    public int getItemViewType(int position) {
        int p = position;
        if (p == 0)
            return TYPE_1;
        else
            return TYPE_2;

    }

    @Override
    public int getViewTypeCount() {
        // TODO Auto-generated method stub
        return 2;
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
        viewHolder1 holder1 = null;
        viewHolder2 holder2 = null;
        int type = getItemViewType(position);

        // 无convertView，需要new出各个控件
        if (convertView == null) {
            // 按当前所需的样式，确定new的布局
            switch (type) {
            case TYPE_1:
                convertView = mInflater.inflate(R.layout.item_monitortop,
                        parent, false);
                holder1 = new viewHolder1();
                holder1.imageView1 = (ImageView) convertView
                        .findViewById(R.id.imageView1);
                convertView.setTag(holder1);
                break;
            case TYPE_2:
                convertView = mInflater.inflate(R.layout.item_monitor, parent,
                        false);
                holder2 = new viewHolder2();
                holder2.textView1 = (TextView) convertView
                        .findViewById(R.id.textView1);
                holder2.textView2 = (TextView) convertView
                        .findViewById(R.id.textView2);
                holder2.textView3 = (TextView) convertView
                        .findViewById(R.id.textView3);
                holder2.textView4 = (TextView) convertView
                        .findViewById(R.id.textView4);
                ;
                holder2.imageView1 = (ImageView) convertView
                        .findViewById(R.id.imageView1);
                convertView.setTag(holder2);
                break;
            default:
                break;
            }
        } else {
            switch (type) {
            case TYPE_1:
                holder1 = (viewHolder1) convertView.getTag();
                break;
            case TYPE_2:
                holder2 = (viewHolder2) convertView.getTag();
                break;
            default:
                break;
            }

        }

        // if (convertView != null) {
        // holder = (buttonViewHolder) convertView.getTag();
        // } else {
        // convertView = mInflater.inflate(R.layout.item_monitor, null);
        // holder = new buttonViewHolder();
        // holder.textView1 = (TextView)
        // convertView.findViewById(R.id.textView1);
        // holder.textView2 = (TextView)
        // convertView.findViewById(R.id.textView2);
        // holder.textView3 = (TextView)
        // convertView.findViewById(R.id.textView3);
        // holder.textView4 = (TextView)
        // convertView.findViewById(R.id.textView4);;
        // holder.imageView1 = (ImageView)
        // convertView.findViewById(R.id.imageView1);
        // convertView.setTag(holder);
        // }

        // DataInfoUn appInfo = mAppList.get(position);
        // if (appInfo != null) {
        // String type_str = appInfo.getTagName();
        // String time = appInfo.getCTime();
        // try{
        // holder.type_tv.setText("事件类型："+type_str);
        // holder.time_tv.setText("时间："+time);
        // convertView.setOnClickListener(new AdapterListener(position,
        // appInfo));
        // }catch(Exception e){
        //
        // }
        // }

        // //设置资源
        // switch(type)
        // {
        // case TYPE_1:
        // holder1.textView.setText(Integer.toString(position));
        // holder1.checkBox.setChecked(true);
        // break;
        // case TYPE_2:
        // holder2.textView.setText(Integer.toString(position));
        // break;
        // case TYPE_3:
        // holder3.textView.setText(Integer.toString(position));
        // holder3.imageView.setBackgroundResource(R.drawable.icon);
        // break;
        // }
        return convertView;
    }

    // class AdapterListener implements OnClickListener{
    // private int position;
    // private DataInfoUn transfordate;
    //
    // public AdapterListener(int pos, DataInfoUn td) {
    // // TODO Auto-generated constructor stub
    // position = pos;
    // transfordate = td;
    // }

    // @Override
    // public void onClick(View v) {
    // TODO Auto-generated method stub
    // String Id = transfordate.getId();
    // String TagName = transfordate.getTagName();
    // String CTime = transfordate.getCTime();
    //
    // Intent intent = new Intent();
    // intent.putExtra("Id", Id);
    // intent.putExtra("TagName", TagName);
    // intent.putExtra("CTime", CTime);
    // intent.putExtra("type", "0");
    //
    // intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    //
    // intent.setClass(mContext, AlarmUnInformationActivity.class);
    // mContext.startActivity(intent);
    //
    // }
    // }
}