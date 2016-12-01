package com.lnpdit.woofarm.page.activity.setting;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bigkoo.pickerview.OptionsPickerView;
import com.lnpdit.woofarm.R;
import com.lnpdit.woofarm.http.ISoapService;
import com.lnpdit.woofarm.http.SoapRes;
import com.lnpdit.woofarm.http.SoapService;
import com.lnpdit.woofarm.utils.SOAP_UTILS;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class EditAddressActivity extends Activity implements OnClickListener {
    /** soapService **/
    public ISoapService soapService = new SoapService();
    Context context;
    private ImageView imgBack;
    private TextView tvBack;
    private TextView tv_save;
    EditText username_edit;
    EditText password_edit;
    Button login_bt;
    private TextView tvNext;
    private RelativeLayout choosecity_layout;
    private TextView cityEdit;
    private TextView addInfo;
    private EditText phone_edit;
    private EditText code_edit;
    private String province_str = "";
    private String city_str = "";
    private String area_str = "";

    // 城市选择器

    // 省数据集合
    private ArrayList<String> mListProvince = new ArrayList<String>();
    // 市数据集合
    private ArrayList<ArrayList<String>> mListCiry = new ArrayList<ArrayList<String>>();
    // 区数据集合
    private ArrayList<ArrayList<ArrayList<String>>> mListArea = new ArrayList<ArrayList<ArrayList<String>>>();

    private OptionsPickerView<String> mOpv;
    private JSONObject mJsonObj;
    private TextView mCity;
    private String memberid = "";
    private String type = "";
    private String addressid = "";
    private String username = "";
    private String phone = "";
    private String province = "";
    private String city = "";
    private String address = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editaddress);

        context = this;
        SharedPreferences sharedPreferences = getSharedPreferences("userinfo",MODE_PRIVATE);
        memberid =sharedPreferences.getString("userid", ""); 
        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        if(type.equals("edit")){
            addressid = intent.getStringExtra("addressid");
            username = intent.getStringExtra("username");
            phone = intent.getStringExtra("phone");
            province = intent.getStringExtra("province");
            city = intent.getStringExtra("city");
            address = intent.getStringExtra("address");
        }
        initView();

        // 初始化Json对象
        initJsonData();
        // 初始化Json数据
        initJsonDatas();

        // 创建选项选择器对象
        mOpv = new OptionsPickerView<String>(this);

        // 设置标题
        mOpv.setTitle("");

        // 设置三级联动效果
        mOpv.setPicker(mListProvince, mListCiry, mListArea, true);

        // 设置是否循环滚动
        mOpv.setCyclic(false, false, false);

        // 设置默认选中的三级项目
        mOpv.setSelectOptions(0, 0, 0);

        // 监听确定选择按钮
        mOpv.setOnoptionsSelectListener(
                new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int option2,
                            int options3) {
                        // 返回的分别是三个级别的选中位置
                        String tx = mListProvince.get(options1)
                                + mListCiry.get(options1).get(option2)
                                + mListArea.get(options1).get(option2)
                                        .get(options3);
                        addInfo.setText(tx);

                        province_str = mListProvince.get(options1);
                        city_str = mListCiry.get(options1).get(option2);
                        area_str = mListArea.get(options1).get(option2).get(options3);
                    }
                });

    }

    private void initView() {
        imgBack = (ImageView) findViewById(R.id.img_back);
        imgBack.setClickable(true);
        imgBack.setOnClickListener(this);
        tvBack = (TextView) findViewById(R.id.tv_back);
        tvBack.setClickable(true);
        tvBack.setOnClickListener(this);
        tv_save = (TextView) findViewById(R.id.tv_save);
        tv_save.setClickable(true);
        tv_save.setOnClickListener(this);
        cityEdit = (TextView) findViewById(R.id.city_edit);
        cityEdit.setOnClickListener(this);
        addInfo = (TextView) findViewById(R.id.add_info);
        phone_edit = (EditText) findViewById(R.id.phone_edit);
        code_edit = (EditText) findViewById(R.id.code_edit);
        password_edit = (EditText) findViewById(R.id.password_edit);
        choosecity_layout = (RelativeLayout) findViewById(
                R.id.choosecity_layout);
        choosecity_layout.setOnClickListener(this);
        phone_edit.setText(username);
        code_edit.setText(phone);
        password_edit.setText(address);
    }

    public String getStringData(int id) {
        return getResources().getString(id);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.tv_save:
            if(phone_edit.getText().toString().equals("")||phone_edit.getText().toString().equals(null)){
                Toast.makeText(context, "请输入收货人", Toast.LENGTH_SHORT).show();
            }else if(code_edit.getText().toString().equals("")||code_edit.getText().toString().equals(null)){
                Toast.makeText(context, "请输入联系电话", Toast.LENGTH_SHORT).show();
            }else if(password_edit.getText().toString().equals("")||password_edit.getText().toString().equals(null)){
                Toast.makeText(context, "请输入详细地址", Toast.LENGTH_SHORT).show();
            }else{
               if(type.equals("add")){
 
                 String[] property_vas = new String[] {memberid , phone_edit.getText().toString(), code_edit.getText().toString(), area_str + password_edit.getText().toString(),province_str, city_str};
                 soapService.addReceaddress(property_vas);
               }else{

                 String[] property_vas = new String[] {addressid , phone_edit.getText().toString(), code_edit.getText().toString(), area_str + password_edit.getText().toString(),province_str, city_str};
                 soapService.updateReceaddress(property_vas);
               }
                finish();
            }
            break;
        case R.id.tv_back:
        case R.id.img_back:
            finish();
            break;
        case R.id.choosecity_layout:
            mOpv.show();
            break;
        case R.id.city_edit:
            mOpv.show();
            break;

        default:
            break;
        }

    }

    /** 从assert文件夹中读取省市区的json文件，然后转化为json对象 */
    private void initJsonData() {
        try {
            StringBuffer sb = new StringBuffer();
            InputStream is = getAssets().open("city.json");
            int len = -1;
            byte[] buf = new byte[1024];
            while ((len = is.read(buf)) != -1) {
                sb.append(new String(buf, 0, len, "UTF-8"));
            }
            is.close();
            mJsonObj = new JSONObject(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /** 初始化Json数据，并释放Json对象 */
    private void initJsonDatas() {
        try {
            JSONArray jsonArray = mJsonObj.getJSONArray("citylist");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonP = jsonArray.getJSONObject(i);// 获取每个省的Json对象
                String province = jsonP.getString("name");

                ArrayList<String> options2Items_01 = new ArrayList<String>();
                ArrayList<ArrayList<String>> options3Items_01 = new ArrayList<ArrayList<String>>();
                JSONArray jsonCs = jsonP.getJSONArray("city");
                for (int j = 0; j < jsonCs.length(); j++) {
                    JSONObject jsonC = jsonCs.getJSONObject(j);// 获取每个市的Json对象
                    String city = jsonC.getString("name");
                    options2Items_01.add(city);// 添加市数据

                    ArrayList<String> options3Items_01_01 = new ArrayList<String>();
                    JSONArray jsonAs = jsonC.getJSONArray("area");
                    for (int k = 0; k < jsonAs.length(); k++) {
                        options3Items_01_01.add(jsonAs.getString(k));// 添加区数据
                    }
                    options3Items_01.add(options3Items_01_01);
                }
                mListProvince.add(province);// 添加省数据
                mListCiry.add(options2Items_01);
                mListArea.add(options3Items_01);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mJsonObj = null;
    }
    
    public void onEvent(SoapRes obj) {
        if (obj.getCode().equals(SOAP_UTILS.METHOD.ADDRECEADDRESS)) {
            if (obj.getObj() != null) {
                try {
                    JSONObject json_obj = new JSONObject(obj.getObj().toString());

                    String result = json_obj.get("status").toString();
                    String message = json_obj.get("msg").toString();
                    if(result.equals("true")){
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();  
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
        }else if (obj.getCode().equals(SOAP_UTILS.METHOD.UPDATERECEADDRESS)) {
            if (obj.getObj() != null) {
                try {
                    JSONObject json_obj = new JSONObject(obj.getObj().toString());

                    String result = json_obj.get("status").toString();
                    String message = json_obj.get("msg").toString();
                    if(result.equals("true")){
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();  
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
