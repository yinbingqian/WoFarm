package com.lnpdit.woofarm.page.adapter;

import java.util.List;

import com.lnpdit.woofarm.R;
import com.lnpdit.woofarm.entity.Order;
import com.lnpdit.woofarm.http.ISoapService;
import com.lnpdit.woofarm.http.SoapService;
import com.lnpdit.woofarm.instance.Instance;
import com.lnpdit.woofarm.page.activity.product.ProductInfoActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyOrderListAdapter extends BaseAdapter {

    /** soapService **/
    public ISoapService soapService = new SoapService();
    private class buttonViewHolder {
        ImageView orderimg;
        TextView ordertype;
        TextView orderresult;
        TextView proname;
        TextView proprice;
        TextView procount;
        TextView tvhj;
        TextView btndelete;
        String proid = "";
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
            holder.procount = (TextView) convertView
                    .findViewById(R.id.procount);
            holder.tvhj = (TextView) convertView.findViewById(R.id.tvhj);
            holder.btndelete = (TextView) convertView
                    .findViewById(R.id.btndelete);
            holder.btndelete.setClickable(true);
            // Instance.imageLoader.displayImage(
            // SOAP_UTILS.HTTP_HEAD_PATH + orderList.get(position).getheadpic(),
            // viewHolder.headpic, Instance.user_s_options);
            holder.ordertype.setText(orderList.get(position).getPaymenttype());
            holder.orderresult.setText(orderList.get(position).getOrderstate());
            holder.proname.setText(orderList.get(position).getProductname());
            holder.proprice.setText(orderList.get(position).getProductprice() + "/KG");
            holder.procount.setText("* " + orderList.get(position).getProductnum());
            holder.tvhj.setText("共计：¥" + orderList.get(position).getProductprice());
            holder.proid = orderList.get(position).getId();
            holder.btndelete.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    String[] property_va = new String[] {holder.proid};
                    soapService.deleteOrderById(property_va);
                }
            });
//获取本地图片
//            int value1 = 0;
//            Class<R.drawable> cls = R.drawable.class;
//            try {
//                value1 = cls
//                        .getDeclaredField(orderList.get(position).getThumb())
//                        .getInt(null);
//            } catch (Exception e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//            holder.orderimg.setImageResource(value1);

            String imgPath1 = orderList.get(position).getProductimg();
            Instance.imageLoader.displayImage(imgPath1,holder.orderimg, Instance.user_s_options);
            convertView.setTag(holder);

        } else {

            holder = (buttonViewHolder) convertView.getTag();

        }
       
        
        return convertView;
    }

}