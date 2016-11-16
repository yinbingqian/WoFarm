package com.lnpdit.woofarm.page.adapter;

import java.util.List;

import com.lnpdit.woofarm.R;
import com.lnpdit.woofarm.entity.Order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyOrderListAdapter extends BaseAdapter {
    private class buttonViewHolder {
        ImageView orderimg;
        TextView ordertype;
        TextView orderresult;
        TextView proname;
        TextView proprice;
        TextView tvhj;
        TextView btndelete;
    }

    private List<Order> orderList;
    private LayoutInflater mInflater;
    private Context mContext;
    private buttonViewHolder holder;

    public MyOrderListAdapter(Context context, List<Order> _orderList) {
        orderList = _orderList;
        mContext = context;
        mInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return orderList.size();
    }

    @Override
    public Object getItem(int position) {
        return orderList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void removeItem(int positon) {
        orderList.remove(positon);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // buttonViewHolder holder = null;

        // 无convertView，需要new出各个控件
        if (convertView == null) {
            // 按当前所需的样式，确定new的布局

            convertView = mInflater.inflate(R.layout.item_order, parent, false);
            holder = new buttonViewHolder();

            holder.orderimg = (ImageView) convertView
                    .findViewById(R.id.orderimg);
            holder.ordertype = (TextView) convertView
                    .findViewById(R.id.ordertype);
            holder.orderresult = (TextView) convertView
                    .findViewById(R.id.orderresult);
            holder.proname = (TextView) convertView.findViewById(R.id.proname);
            holder.proprice = (TextView) convertView
                    .findViewById(R.id.proprice);
            holder.tvhj = (TextView) convertView.findViewById(R.id.tvhj);
            holder.btndelete = (TextView) convertView
                    .findViewById(R.id.btndelete);
            // Instance.imageLoader.displayImage(
            // SOAP_UTILS.HTTP_HEAD_PATH + orderList.get(position).getheadpic(),
            // viewHolder.headpic, Instance.user_s_options);
            holder.ordertype.setText(orderList.get(position).getType());
            holder.orderresult.setText(orderList.get(position).getResult());
            holder.proname.setText(orderList.get(position).getName());
            holder.proprice.setText(orderList.get(position).getPrice() + "/KG");
            holder.tvhj.setText("共计：¥" + orderList.get(position).getHj());

            int value1 = 0;
            Class<R.drawable> cls = R.drawable.class;
            try {
                value1 = cls
                        .getDeclaredField(orderList.get(position).getThumb())
                        .getInt(null);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            holder.orderimg.setImageResource(value1);

            convertView.setTag(holder);

        } else {

            holder = (buttonViewHolder) convertView.getTag();

        }

        return convertView;
    }

}