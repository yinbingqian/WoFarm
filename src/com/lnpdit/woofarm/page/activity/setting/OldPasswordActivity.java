package com.lnpdit.woofarm.page.activity.setting;

import com.hp.hpl.sparta.Text;
import com.lnpdit.woofarm.R;
import com.lnpdit.woofarm.base.component.BaseActivity;
import com.lnpdit.woofarm.entity.LoginUser;
import com.lnpdit.woofarm.http.SoapRes;
import com.lnpdit.woofarm.md5.MD5Plus;
import com.lnpdit.woofarm.page.activity.tabhost.MainTabHostActivity;
import com.lnpdit.woofarm.utils.SOAP_UTILS;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class OldPasswordActivity extends Activity implements OnClickListener {
    Context context;
    private ImageView imgBack;
    private TextView tvBack;
    EditText username_edit;
    EditText password_edit;
    Button login_bt;
    private TextView tvNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatepwd);

        context = this;
        initView();

    }

    private void initView() {
        imgBack = (ImageView) findViewById(R.id.img_back);
        imgBack.setOnClickListener(this);
        tvBack = (TextView) findViewById(R.id.tv_back);
        tvBack.setOnClickListener(this);
        tvNext = (TextView) findViewById(R.id.tv_next);
        tvNext.setOnClickListener(this);

    }

    public String getStringData(int id) {
        return getResources().getString(id);
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
        case R.id.tv_next:

            Intent intent= new Intent();
            intent.setClass(context, ModifyPasswordActivity.class);
            startActivity(intent);
            finish();
            break;

        default:
            break;
        }

    }

    /**
     * 用户登录
     * 
     * @param username
     * @param password
     */
    private void login_validate(String username, String password) {
        if (username == null || username.equals("")) {
            Toast.makeText(this, "用户名为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password == null || password.equals("")) {
            Toast.makeText(this, "密码为空", Toast.LENGTH_SHORT).show();
            return;
        }
        // Object[] property_va = { username_edit.getText().toString(),
        // password_edit.getText().toString() };
        // soapService.userLogin(property_va);

        Toast.makeText(this, "登录成功！", Toast.LENGTH_SHORT).show();
        Intent intent_login = new Intent();
        intent_login.setClass(context, MainTabHostActivity.class);
        startActivity(intent_login);
        finish();
    }

    /**
     * 判断GPS是否开启，GPS或者AGPS开启一个就认为是开启的
     * 
     * @param context
     * @return true 表示开启
     */
    public static final boolean gPSIsOPen(final Context context) {
        LocationManager locationManager = (LocationManager) context
                .getSystemService(Context.LOCATION_SERVICE);
        // 通过GPS卫星定位，定位级别可以精确到街（通过24颗卫星定位，在室外和空旷的地方定位准确、速度快）
        boolean gps = locationManager
                .isProviderEnabled(LocationManager.GPS_PROVIDER);
        // 通过WLAN或移动网络(3G/2G)确定的位置（也称作AGPS，辅助GPS定位。主要用于在室内或遮盖物（建筑群或茂密的深林等）密集的地方定位）
        boolean network = locationManager
                .isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if (gps || network) {
            return true;
        }
        return false;
    }

}
