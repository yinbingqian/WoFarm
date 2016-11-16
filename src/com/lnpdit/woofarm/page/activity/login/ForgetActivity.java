package com.lnpdit.woofarm.page.activity.login;

import com.hp.hpl.sparta.Text;
import com.lnpdit.woofarm.R;
import com.lnpdit.woofarm.base.component.BaseActivity;
import com.lnpdit.woofarm.entity.LoginUser;
import com.lnpdit.woofarm.http.SoapRes;
import com.lnpdit.woofarm.md5.MD5Plus;
import com.lnpdit.woofarm.page.activity.setting.ModifyPasswordActivity;
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

public class ForgetActivity extends Activity implements OnClickListener {
    Context context;
    TextView tvNext;
    private ImageView imgBack;
    private TextView tvBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findpassword);

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

        /**
         * 限制只能输入字母和数字，默认弹出英文输入法
         */
        // password_edit.setKeyListener(new DigitsKeyListener() {
        // @Override
        // public int getInputType() {
        // return InputType.TYPE_TEXT_VARIATION_PASSWORD;
        // }
        //
        // @Override
        // protected char[] getAcceptedChars() {
        // char[] data = getStringData(R.string.login_only_can_input)
        // .toCharArray();
        // return data;
        // }
        // });
        // username_edit.setKeyListener(new DigitsKeyListener() {
        // @Override
        // public int getInputType() {
        // return InputType.TYPE_TEXT_VARIATION_PASSWORD;
        // }
        //
        // @Override
        // protected char[] getAcceptedChars() {
        // char[] data = getStringData(R.string.login_only_can_input)
        // .toCharArray();
        // return data;
        // }
        // });

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

        Intent intent_login = new Intent();
        intent_login.setClass(context, MainTabHostActivity.class);
        startActivity(intent_login);
        finish();
    }

}
