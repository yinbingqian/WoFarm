package com.lnpdit.woofarm.page.activity.product;

import com.eroad.widget.calendar.CalanderActivity;
import com.lnpdit.woofarm.R;
import com.lnpdit.woofarm.base.component.BaseActivity;
import com.lnpdit.woofarm.widget.BuyNowPopWin;
import com.lnpdit.woofarm.widget.OrderNowPopWin;
import com.lnpdit.woofarm.widget.TakePhotoPopWin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ProductOrderActivity extends BaseActivity {
    /** Called when the activity is first created. */
    private TextView tv_title;
    private ImageView imgBack;
    private ImageView add_img;
    private ImageView subtract_img;
    private TextView count_tv;
    private TextView tvBack;
    private TextView btnAddCart;
    private TextView btnOrderNow;
    private TextView carcount_tv;
    OrderNowPopWin orderNowPopWin;
    Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_productorder);

        initView();
    }

    private void initView() {
        this.isParentActivity = false;

        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("商品预定");
        imgBack = (ImageView) findViewById(R.id.img_back);
        imgBack.setOnClickListener(this);
        add_img = (ImageView) findViewById(R.id.add_img);
        add_img.setOnClickListener(this);
        subtract_img = (ImageView) findViewById(R.id.subtract_img);
        subtract_img.setOnClickListener(this);
        count_tv = (TextView) findViewById(R.id.count_tv);
        tvBack = (TextView) findViewById(R.id.tv_back);
        tvBack.setOnClickListener(this);
        btnOrderNow = (TextView) findViewById(R.id.btnordernow);
        btnOrderNow.setOnClickListener(this);
        btnAddCart = (TextView) findViewById(R.id.btnaddcart);
        btnAddCart.setOnClickListener(this);
        carcount_tv = (TextView) findViewById(R.id.carcount_tv);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.tv_back:
            finish();
            break;
        case R.id.img_back:
            finish();
            break;
        case R.id.add_img:
            String add_str = count_tv.getText().toString();
            int add_int =  Integer.parseInt(add_str);
            count_tv.setText(add_int + 1 );
            break;
        case R.id.subtract_img:
            String count_str = count_tv.getText().toString();
            int coutn_int =  Integer.parseInt(count_str);
          
            if(coutn_int <= 1){
                count_tv.setText(coutn_int);
            }else{
                count_tv.setText(coutn_int - 1);
            }
            break;
        case R.id.btnordernow:
            showPopFormBottom();
            break;
        case R.id.btnaddcart:
            Toast.makeText(this, "成功加入购物车！", Toast.LENGTH_SHORT).show();
            carcount_tv.setText(carcount_tv.getText().toString() + 1);
            break;
        default:
            break;
        }
    }

    public void showPopFormBottom() {
        orderNowPopWin = new OrderNowPopWin(this, onClickListener);
        orderNowPopWin.showAtLocation(findViewById(R.id.main_view),
                Gravity.CENTER, 0, 0);
        // TakePhotoPopWin takePhotoPopWin = new TakePhotoPopWin(this,
        // onClickListener);
        // // showAtLocation(View parent, int gravity, int x, int y)
        // takePhotoPopWin.showAtLocation(findViewById(R.id.main_view),
        // Gravity.CENTER, 0, 0);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
            case R.id.submit_btn:
                Intent intent = new Intent();
                intent.setClass(ProductOrderActivity.this, ProductConfirmActivity.class);
                startActivity(intent);
                orderNowPopWin.dismiss();
                break;
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}