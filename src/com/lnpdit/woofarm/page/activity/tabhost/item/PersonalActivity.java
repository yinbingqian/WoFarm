package com.lnpdit.woofarm.page.activity.tabhost.item;

import java.io.ByteArrayOutputStream;
import java.io.File;

import org.json.JSONObject;

import com.lnpdit.woofarm.R;
import com.lnpdit.woofarm.base.component.BaseActivity;
import com.lnpdit.woofarm.customview.FocusedtrueTV;
import com.lnpdit.woofarm.db.DBHelper;
import com.lnpdit.woofarm.entity.LoginUser;
import com.lnpdit.woofarm.entity.UserInfo;
import com.lnpdit.woofarm.http.ImgPostService;
import com.lnpdit.woofarm.instance.Instance;
import com.lnpdit.woofarm.page.activity.login.LoginActivity;
import com.lnpdit.woofarm.page.activity.product.MyOrderActivity;
import com.lnpdit.woofarm.page.activity.setting.AddressListActivity;
import com.lnpdit.woofarm.page.activity.setting.OldPasswordActivity;
import com.lnpdit.woofarm.utils.ImageTool;
import com.lnpdit.woofarm.utils.SOAP_UTILS;
import com.lnpdit.woofarm.widget.CircleImageView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PersonalActivity extends BaseActivity {
    /** Called when the activity is first created. */
    private FocusedtrueTV tv_title;
    Context context;
    private LinearLayout logout_layout;
    private LinearLayout myaddress_layout;
    private LinearLayout myorder_layout;
    private LinearLayout password_layout;
    private CircleImageView img_user_avatar;
    private TextView username;
    private String memberid="";
    private String nickname="";
    private String memberlogo="";
    UserInfo user;
    private DBHelper dbh;
    private AlertDialog dialog;
    private File sdcardTempFile = new File(Environment.getExternalStorageDirectory(), "head.jpg");// 图片截图路径
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_personal);

        SharedPreferences sharedPreferences = getSharedPreferences("userinfo",MODE_PRIVATE);
        memberid =sharedPreferences.getString("userid", ""); 
        nickname =sharedPreferences.getString("nickname", ""); 
        memberlogo =sharedPreferences.getString("memberlogo", ""); 

        dbh = new DBHelper(this);
        initView();
        setUI();
    }

    private void initView() {
        this.isParentActivity = false;

        username = (TextView) findViewById(R.id.username);
        img_user_avatar = (CircleImageView) findViewById(R.id.img_user_avatar);
        img_user_avatar.setClickable(true);
        img_user_avatar.setOnClickListener(this);
        logout_layout = (LinearLayout) findViewById(R.id.logout_layout);
        logout_layout.setOnClickListener(this);
        myaddress_layout = (LinearLayout) findViewById(R.id.myaddress_layout);
        myaddress_layout.setOnClickListener(this);
        myorder_layout = (LinearLayout) findViewById(R.id.myorder_layout);
        myorder_layout.setOnClickListener(this);
        password_layout = (LinearLayout) findViewById(R.id.password_layout);
        password_layout.setOnClickListener(this);

    }

    public void setUI() {
        user = dbh.queryUserInfoById(memberid);
        username.setText(user.getUsername());
        Instance.imageLoader.displayImage(user.getHeadpic(), img_user_avatar, Instance.user_options);
    }
    
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.img_user_avatar:
            if (dialog == null) {
                dialog = new AlertDialog.Builder(this)
                        .setItems(new String[] { "相机", "相册" }, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (which == 0) {
                                    Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                    openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(sdcardTempFile));
                                    startActivityForResult(openCameraIntent, 100);
                                } else {
                                    Intent openAlbumIntent = new Intent(Intent.ACTION_GET_CONTENT);
                                    openAlbumIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                            "image/*");
                                    startActivityForResult(openAlbumIntent, 100);
                                }
                            }
                        }).create();
            }
            if (!dialog.isShowing()) {
                dialog.show();
            }
            break;
        case R.id.myorder_layout:

            Intent intent_order = new Intent();
            intent_order.setClass(context, MyOrderActivity.class);
            startActivity(intent_order);
            break;
        case R.id.myaddress_layout:

            Intent intent = new Intent();
            intent.setClass(context, AddressListActivity.class);
            startActivity(intent);
            break;
        case R.id.password_layout:

            intent = new Intent();
            intent.setClass(context, OldPasswordActivity.class);
            startActivity(intent);
            break;
        case R.id.logout_layout:

            intent = new Intent();
            intent.setClass(context, LoginActivity.class);
            startActivity(intent);
            finish();
            break;

        default:
            break;
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == 100) {
                Uri uri = null;
                if (data != null) {
                    uri = data.getData();
                    System.out.println("Data");
                } else {
                    uri = Uri.fromFile(sdcardTempFile);
                }
                cropImage(uri, 100, 100, 101);
            }
            if (requestCode == 101) {
                Bitmap photo = null;
                if (data != null) {
                Uri photoUri = data.getData();
                if (photoUri != null) {
                    photo = BitmapFactory.decodeFile(photoUri.getPath());
                }
                if (photo == null) {
                    Bundle extra = data.getExtras();
                    if (extra != null) {
                        photo = (Bitmap) extra.get("data");
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        photo.compress(Bitmap.CompressFormat.JPEG, 100, stream);

                        byte[] data_array = ImageTool.bitmapToBytes(photo);
               
                        String[] property_nm = {"ImageContext","memberid"};
                        Object[] property_va = { data_array, memberid };
                        new postHeaderTask().execute(property_nm, property_va, data_array, memberid);
//                        ImgPostService.data(SOAP_UTILS.METHOD.UPLOADFILE, property_nm, property_va, data_array,memberid); 
                        img_user_avatar.setImageBitmap(photo);
                        
                    }
                    }
                }
                }else{
                    Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
                }
            }
        
    }


    class postHeaderTask extends AsyncTask<Object, Object, Object> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            rl_head.setEnabled(false);
        }

        @Override
        protected Object doInBackground(Object... params) {
            System.out.println(">>>>>");
            Object res_obj = (Object) ImgPostService.data(SOAP_UTILS.METHOD.UPLOADFILE, (String[]) params[0],
                    (Object[]) params[1], (byte[]) params[2],(String) params[3]);
            return res_obj;
        }

        @Override
        protected void onPostExecute(Object result) {
            super.onPostExecute(result);
//            rl_head.setEnabled(true);
             if(result != null){
            try {
                JSONObject json_obj = new JSONObject(result.toString());
                if (json_obj.get("status").toString().equals("true")) {
                    dbh.updateUser(user.getUserid(), "HEADPIC",json_obj.get("memberlogo").toString());
                    setUI();
                    Toast.makeText(context, "头像修改成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, json_obj.get("msg").toString(), Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                Toast.makeText(context, "网络异常，请检查网络", Toast.LENGTH_SHORT).show();
            }
             }else{
                    Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
                }
        }

    }
    
    // 截取图片
    public void cropImage(Uri uri, int outputX, int outputY, int requestCode) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        intent.putExtra("outputFormat", "JPEG");
        intent.putExtra("noFaceDetection", true);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, requestCode);
    }

    
    @Override
    protected void onResume() {
        super.onResume();
        setUI();
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