package com.lnpdit.woofarm.page.activity.login;

import com.hp.hpl.sparta.Text;
import com.lnpdit.woofarm.R;
import com.lnpdit.woofarm.base.component.BaseActivity;
import com.lnpdit.woofarm.entity.LoginUser;
import com.lnpdit.woofarm.http.SoapRes;
import com.lnpdit.woofarm.md5.MD5Plus;
import com.lnpdit.woofarm.page.activity.tabhost.MainTabHostActivity;
import com.lnpdit.woofarm.utils.SOAP_UTILS;
import com.lnpdit.woofarm.utils.TimeCountUtil;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

public class RegisterActivity extends BaseActivity implements OnClickListener {
    Context context;
    Button reg_bt;
    Button code_btn;
    EditText phone_edit;
    EditText code_edit;
    EditText username_edit;
    EditText password_edit;
    private ImageView imgBack;
    private TextView tvBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        context = this;
        initView();

    }

    private void initView() {
        imgBack = (ImageView) findViewById(R.id.img_back);
        imgBack.setOnClickListener(this);
        tvBack = (TextView) findViewById(R.id.tv_back);
        tvBack.setOnClickListener(this);
        reg_bt = (Button) findViewById(R.id.reg_bt);
        reg_bt.setOnClickListener(this);
        code_btn = (Button) findViewById(R.id.code_btn);
        code_btn.setOnClickListener(this);
        phone_edit = (EditText) findViewById(R.id.phone_edit);
        code_edit = (EditText) findViewById(R.id.code_edit);
        username_edit = (EditText) findViewById(R.id.username_edit);
        password_edit = (EditText) findViewById(R.id.password_edit);

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
        case R.id.reg_bt:
            // 手机号校验
            if(!phone_edit.getText().toString().trim().matches(getString(R.string.is_phone))){
                Toast.makeText(context, "手机号码不能为空！", Toast.LENGTH_SHORT).show();
                break;
            }
            // 密码校验
            if (password_edit.getText().toString().trim().equals("")) {
                Toast.makeText(context, "密码不能为空！", Toast.LENGTH_SHORT).show();
                break;
            }
            if (password_edit.getText().toString().trim().length() < 6) {

                Toast.makeText(context, "密码不能少于6位！", Toast.LENGTH_SHORT).show();
                break;
            }

            // 昵称校验
            if (username_edit.getText().toString().trim().equals("")) {
                Toast.makeText(context, "昵称不能为空！", Toast.LENGTH_SHORT).show();
                break;
            }
            
            Object[] property_va2 = { phone_edit.getText().toString(),code_edit.getText().toString(),
                    username_edit.getText().toString(),password_edit.getText().toString() };
            soapService.memberReg(property_va2);
            break;
        case R.id.code_btn:
            final String phonenum = phone_edit.getText().toString();
            if (phonenum.equals("")) {
                Toast.makeText(context, "手机号码不能为空！", Toast.LENGTH_SHORT).show();
                return;
            }

           // 获取验证码
            Object[] property_va = { phonenum };
            soapService.getCodeByPhone(property_va);
            
            break;
        default:
            break;
        }

    }


    public void onEvent(SoapRes res) {
        if (res.getCode().equals(SOAP_UTILS.METHOD.MEMBERREG)) {
            if (res.getObj() != null) {
                if (res.getObj().toString().equals("true")) {
                    Toast.makeText(context, "注册成功", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(context, res.getObj().toString(), Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, "注册失败", Toast.LENGTH_SHORT).show();
            }
        }else if(res.getCode().equals(SOAP_UTILS.METHOD.GETCODEBYPHONE)){
            if (res.getObj() != null) {
                if (res.getObj().toString().equals("true")) {
                    Toast.makeText(context, "验证码发送成功", Toast.LENGTH_SHORT).show();

                    // 发送成功进入倒计时
                       TimeCountUtil timeCountUtil = new TimeCountUtil(this, 60000, 1000, code_btn);
                       timeCountUtil.start();
                } else {
                    Toast.makeText(context, res.getObj().toString(), Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, "验证码发送失败", Toast.LENGTH_SHORT).show();
            }
            
        }
        
    }

}
