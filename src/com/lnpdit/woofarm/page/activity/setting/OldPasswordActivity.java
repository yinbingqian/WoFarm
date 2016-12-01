package com.lnpdit.woofarm.page.activity.setting;

import java.util.List;

import org.json.JSONObject;

import com.hp.hpl.sparta.Text;
import com.lnpdit.woofarm.R;
import com.lnpdit.woofarm.base.component.BaseActivity;
import com.lnpdit.woofarm.entity.Address;
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

public class OldPasswordActivity extends BaseActivity implements OnClickListener {
    Context context;
    private ImageView imgBack;
    private TextView tvBack;
    EditText username_edit;
    EditText password_edit;
    Button login_bt;
    private TextView tvNext;
    private String memberid = "";
    private String password="";    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatepwd);

        context = this;

        SharedPreferences sharedPreferences = getSharedPreferences("userinfo",MODE_PRIVATE);
        memberid =sharedPreferences.getString("userid", ""); 
       
        initView();

    }

    private void initView() {
        username_edit = (EditText) findViewById(R.id.username_edit);
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
            if(memberid.equals("")||memberid.equals(null)){

                Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
            }else{
                password = username_edit.getText().toString();
                if(password.equals("")||password.equals(null)){

                    Toast.makeText(this, "请输入旧密码", Toast.LENGTH_SHORT).show();
                }else{

                    String[] property_va = new String[] { memberid, password};
                    soapService.validationPasswordByMember(property_va);
                }
                }
          
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

    
    public void onEvent(SoapRes obj) {
      if (obj.getCode().equals(SOAP_UTILS.METHOD.VALIDATIONPASSWORD)) {
            if (obj.getObj() != null) {
                try {
                    JSONObject json_obj = new JSONObject(obj.getObj().toString());

                    String result = json_obj.get("status").toString();
                    String message = json_obj.get("msg").toString();
                    if(result.equals("true")){
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();  
                        
                        Intent intent= new Intent();
                        intent.putExtra("oldpassword", password);
                        intent.setClass(context, ModifyPasswordActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();  
                    }
                    } catch (Exception e) {
                        // TODO: handle exception
                        e.printStackTrace();
                    }
            }else{
                Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
