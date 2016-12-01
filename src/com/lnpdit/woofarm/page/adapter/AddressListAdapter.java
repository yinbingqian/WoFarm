package com.lnpdit.woofarm.page.adapter;

import java.util.List;

import com.lnpdit.woofarm.R;
import com.lnpdit.woofarm.entity.Address;
import com.lnpdit.woofarm.http.ISoapService;
import com.lnpdit.woofarm.http.SoapService;
import com.lnpdit.woofarm.page.activity.setting.EditAddressActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

public class AddressListAdapter extends BaseAdapter {

    /** soapService **/
    public ISoapService soapService = new SoapService();
    private class buttonViewHolder {
        TextView user;
        TextView phone;
        TextView addinfo;
        ImageView img_edit;
        TextView tv_edit;
        ImageView img_delete;
        TextView tv_delete;
        RadioButton cb;
    }

    private Address appInfo;
    private List<Address> mAppList;
    private LayoutInflater mInflater;
    private Context mContext;
    private buttonViewHolder holder;

    String addressid = "";
    String username = "";
    String phone = "";
    String province = "";
    String city = "";
    String address = "";
    
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
            holder.tv_edit.setOnClickListener(new editButtonListener(position, holder.tv_edit));
            holder.img_delete = (ImageView) convertView
                    .findViewById(R.id.img_delete);
            holder.tv_delete = (TextView) convertView
                    .findViewById(R.id.tv_delete);
            holder.img_delete.setClickable(true);
//            holder.img_delete.setOnClickListener(new ButtonListener(position, addressid, holder.img_delete));
            
            holder.tv_delete.setClickable(true);
            holder.tv_delete.setOnClickListener(new ButtonListener(position, holder.tv_delete));
            holder.cb = (RadioButton) convertView.findViewById(R.id.chkItem);
            holder.cb.setClickable(true);
            holder.cb.setOnClickListener(new statButtonListener(position, holder.cb));
          
            convertView.setTag(holder);

        } else {

            holder = (buttonViewHolder) convertView.getTag();

        }

        appInfo = mAppList.get(position);

        if (appInfo.getStat().equals(R.string.defalt_checkbox)) {
            holder.cb.setChecked(true);
        }
        holder.user.setText("收货人：" + appInfo.getUsername());
        holder.phone.setText(appInfo.getMobile());
        holder.addinfo.setText("收货地址：" + appInfo.getProvince() + appInfo.getCity() + appInfo.getAddress());
        addressid = appInfo.getId();

      if(mAppList.get(position).getStat().toString().equals("默认")){
          
          holder.cb.setChecked(true);
      }else{

          holder.cb.setChecked(false);
      }
      

//     if(holder.cb.isChecked()){
//          
//          holder.cb.setBackgroundDrawable(R.drawable.select_reveal);
//      }else{
//
//          holder.cb.setBackground(R.drawable.selecte);
//      }
      
        return convertView;
    }
    class ButtonListener implements OnClickListener {
        private int position;
        private TextView tv;

        public ButtonListener(int pos, TextView _tv) {
            // TODO Auto-generated constructor stub
            position = pos;
            tv = _tv;
        }

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            String[] property_va = new String[] {mAppList.get(position).getId()};
            soapService.deleteReceaddressById(property_va);
        }
    }
    
    class editButtonListener implements OnClickListener {
        private int position;
        private TextView tv;

        public editButtonListener(int pos, TextView _tv) {
            // TODO Auto-generated constructor stub
            position = pos;
            tv = _tv;
        }

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            Intent intent = new Intent();
            intent.putExtra("type", "edit");
            intent.putExtra("addressid", mAppList.get(position).getId());
            intent.putExtra("username", mAppList.get(position).getUsername());
            intent.putExtra("phone", mAppList.get(position).getMobile());
            intent.putExtra("province", mAppList.get(position).getProvince());
            intent.putExtra("city", mAppList.get(position).getCity());
            intent.putExtra("address", mAppList.get(position).getAddress());
            intent.setClass(mContext, EditAddressActivity.class);
            mContext.startActivity(intent);
        }
    }
    
    class statButtonListener implements OnClickListener {
        private int position;
        private RadioButton tv;

        public statButtonListener(int pos, RadioButton _tv) {
            // TODO Auto-generated constructor stub
            position = pos;
            tv = _tv;
        }
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            String[] property_va = new String[] {mAppList.get(position).getId()};
            soapService.setReceaddressStatById(property_va);
            holder.cb.setChecked(true);
        }
    }
}