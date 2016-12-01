package com.lnpdit.woofarm.page.activity.product;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.company.PlaySDK.IPlaySDK;
import com.dh.DpsdkCore.Enc_Channel_Info_Ex_t;
import com.dh.DpsdkCore.Get_RealStream_Info_t;
import com.dh.DpsdkCore.IDpsdkCore;
import com.dh.DpsdkCore.Login_Info_t;
import com.dh.DpsdkCore.Return_Value_Info_t;
import com.dh.DpsdkCore.fMediaDataCallback;
import com.lnpdit.woofarm.R;
import com.lnpdit.woofarm.base.component.BaseActivity;
import com.lnpdit.woofarm.db.DBHelper;
import com.lnpdit.woofarm.entity.Menu;
import com.lnpdit.woofarm.entity.ProductImgs;
import com.lnpdit.woofarm.entity.ProductInfo;
import com.lnpdit.woofarm.http.SoapRes;
import com.lnpdit.woofarm.instance.Instance;
import com.lnpdit.woofarm.page.activity.video.RealPlayActivity;
import com.lnpdit.woofarm.page.adapter.ProductImgsListAdapter;
import com.lnpdit.woofarm.page.adapter.ProductListAdapter;
import com.lnpdit.woofarm.utils.SOAP_UTILS;
import com.lnpdit.woofarm.widget.BuyNowPopWin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ProductInfoActivity extends BaseActivity {
    /** Called when the activity is first created. */
    private TextView tv_title;
    private ImageView imgBack; 
    private ImageView add_img;
    private ImageView subtract_img;
    private TextView count_tv;
    private TextView tvBack;
    private TextView btnAddCart;
    private TextView carcount_tv;
    private TextView btnBuyNow;
    private ImageView imgAd1;
    BuyNowPopWin buyNowPopWin;
    Context context;
    private String name = "";
    private String productid = "";
    private String memberid = ""; 
    
    private TextView proname_tv;
    private TextView proweight_tv;
    private TextView prodate_tv;
    private TextView location_tv;
    private TextView prototal_tv;
    private ListView prodetail_layout;
    private ProductListAdapter productlistAdapter;//商品详情图片
    
    String image = "";
    String price = "";
    String shelvestime = "";
    String area = "";
    String channelid = "";
    String proip = "";
    String proport = "";
    String account = "";
    String propassword = "";
    String type = "";
    String id = "";
    String yield = "";
    
    private DBHelper dbh;
    private ProductInfo productInfo;
    private List<ProductInfo> productInfoList;
    private List<ProductImgs> productimgsListView;
    private ProductImgsListAdapter productimgsAdapter;
    // 视频

    // 登录部分

    static IDpsdkCore dpsdkcore = new IDpsdkCore();
    Resources res;

    // 标记是否第一次登入
    Boolean isFullPlay = false;
    private String isfirstLogin;
    static int m_nLastError = 0;
    static Return_Value_Info_t m_ReValue = new Return_Value_Info_t();

    private Button btnCaptureImg;
    public final static String IMAGE_PATH = Environment
            .getExternalStorageDirectory().getPath() + "/snapshot/";
    public final static String IMGSTR = new SimpleDateFormat("yyyyMMddHHmmss")
            .format(new Date()) + ".jpg";
    private static final int PicFormat_JPEG = 1;

    private TextView etCam;
    private TextView tvRet;

    String ip;
    String port;
    String username;
    String password;

    private byte[] m_szCameraId = null;
    private static int m_pDLLHandle = 0;
    SurfaceView m_svPlayer = null;
    private int m_nPort = 0;
    private int m_nSeq = 0;
    private int mTimeOut = 30 * 1000;
    // 超时时间
    private static final int DPSDK_CORE_DEFAULT_TIMEOUT = 600000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_productinfo);

        name = this.getIntent().getStringExtra("name");
        productid = this.getIntent().getStringExtra("productid");

        SharedPreferences sharedPreferences = getSharedPreferences("userinfo",MODE_PRIVATE);
        memberid =sharedPreferences.getString("userid", ""); 
        initView();
        
        String[] property_va = new String[] {productid};
        soapService.getProduct(property_va);

        // // 播放视频
        //
        // ip = "223.100.225.101";
        // port = "9000";
        // username = "system";
        // password = "123456";
        //
        // m_szCameraId = "1000001$1$0$12".getBytes();
        // // int nRet;
        // m_nPort = IPlaySDK.PLAYGetFreePort();
        //
        // // 登录过程
        // Log.d("onCreate:", m_nLastError + "");
        // int nType = 1;
        // m_nLastError = IDpsdkCore.DPSDK_Create(nType, m_ReValue);
        //
        // IDpsdkCore.DPSDK_SetDPSDKStatusCallback(m_ReValue.nReturnValue,
        // new fDPSDKStatusCallback() {
        //
        // @Override
        // public void invoke(int nPDLLHandle, int nStatus) {
        // Log.v("fDPSDKStatusCallback", "nStatus = " + nStatus);
        // }
        // });
        //
        // Log.d("DpsdkCreate:", m_nLastError + "");
        //
        // new LoginTask().execute();
    }

    private void initView() {
        this.isParentActivity = false;

        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("商品详情");
        imgBack = (ImageView) findViewById(R.id.img_back);
        imgBack.setOnClickListener(this);
        add_img = (ImageView) findViewById(R.id.add_img);
        add_img.setOnClickListener(this);
        subtract_img = (ImageView) findViewById(R.id.subtract_img);
        subtract_img.setOnClickListener(this);
        count_tv = (TextView) findViewById(R.id.count_tv);
        tvBack = (TextView) findViewById(R.id.tv_back);
        tvBack.setOnClickListener(this);
        imgAd1 = (ImageView) findViewById(R.id.img_ad1);
        imgAd1.setOnClickListener(this);
        btnBuyNow = (TextView) findViewById(R.id.btnbuynow);
        btnBuyNow.setOnClickListener(this);
        btnAddCart = (TextView) findViewById(R.id.btnaddcart);
        btnAddCart.setOnClickListener(this);
        // m_svPlayer = (SurfaceView) findViewById(R.id.sv_player);
        carcount_tv = (TextView) findViewById(R.id.carcount_tv);
        
        proname_tv = (TextView) findViewById(R.id.proname_tv);
        proweight_tv = (TextView) findViewById(R.id.proweight_tv);
        prodate_tv = (TextView) findViewById(R.id.prodate_tv);
        location_tv = (TextView) findViewById(R.id.location_tv);
        prototal_tv = (TextView) findViewById(R.id.prototal_tv);
        prodetail_layout = (ListView) findViewById(R.id.prodetail_layout);
        
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
            count_tv.setText(Integer.valueOf(count_tv.getText().toString()) + 1 + "" );
            break;
        case R.id.subtract_img:
            String count_str = count_tv.getText().toString();
            int coutn_int =  Integer.parseInt(count_str);
          
            if(coutn_int <= 1){
                count_tv.setText(count_str);
            }else{
                count_tv.setText(coutn_int - 1 + "");
            }
            break;
        case R.id.btnbuynow:
            showPopFormBottom();
            break;
        case R.id.img_ad1:
            Intent intent = new Intent();
            intent.setClass(ProductInfoActivity.this, RealPlayActivity.class);
            intent.putExtra("name", name);
            startActivity(intent);
            break;
        case R.id.btnaddcart:

            if(memberid.equals("")||memberid.equals(null)){
                Toast.makeText(context, "请先登录", Toast.LENGTH_SHORT).show();
            }else{

                String[] property_va = new String[] { productid, count_tv.getText().toString(),memberid};
                soapService.addcart(property_va);
            }
            break;
        default:
            break;
        }
    }

    public void showPopFormBottom() {
        buyNowPopWin = new BuyNowPopWin(this, onClickListener);
        buyNowPopWin.showAtLocation(findViewById(R.id.main_view),
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
                intent.setClass(ProductInfoActivity.this,
                        ProductConfirmActivity.class);
                startActivity(intent);
                buyNowPopWin.dismiss();
                break;
            }
        }
    };

    // 视频

    // 登录过程

    private void saveLoginInfo() {
        SharedPreferences sp = getSharedPreferences("LOGININFO", 0);
        Editor ed = sp.edit();
        StringBuilder sb = new StringBuilder();
        sb.append(ip).append(",").append(port).append(",").append(password)
                .append(",").append(username);
        ed.putString("INFO", sb.toString());
        ed.putString("ISFIRSTLOGIN", "false");
        ed.commit();
        Log.i("TestDpsdkCoreActivity", "saveLoginInfo" + sb.toString());
    }

    static public int getDpsdkHandle() {
        if (m_pDLLHandle == 1)
            return m_ReValue.nReturnValue;
        else
            return 0;
    }

    public void Logout() {
        if (m_pDLLHandle == 0) {
            return;
        }
        int nRet = IDpsdkCore.DPSDK_Logout(m_ReValue.nReturnValue, 30000);

        if (0 == nRet) {
            m_pDLLHandle = 0;
        }
    }

    class LoginTask extends AsyncTask<Void, Integer, Integer> {

        @Override
        protected Integer doInBackground(Void... arg0) { // 在此处处理UI会导致异常
            if (m_pDLLHandle != 0) {
                IDpsdkCore.DPSDK_Logout(m_ReValue.nReturnValue, 30000);
                m_pDLLHandle = 0;
            }
            Login_Info_t loginInfo = new Login_Info_t();
            Integer error = Integer.valueOf(0);
            loginInfo.szIp = ip.getBytes();
            String strPort = port.trim();
            loginInfo.nPort = Integer.parseInt(strPort);
            loginInfo.szUsername = username.getBytes();
            loginInfo.szPassword = password.getBytes();
            loginInfo.nProtocol = 2;
            saveLoginInfo();
            int nRet = IDpsdkCore.DPSDK_Login(m_ReValue.nReturnValue, loginInfo,
                    30000);
            return nRet;
        }

        @Override
        protected void onPostExecute(Integer result) {

            super.onPostExecute(result);
            if (result == 0) {
                Log.d("DpsdkLogin success:", result + "");
                IDpsdkCore.DPSDK_SetCompressType(m_ReValue.nReturnValue, 0);
                m_pDLLHandle = 1;

                Return_Value_Info_t rvInfo2 = new Return_Value_Info_t();
                int ret = IDpsdkCore.DPSDK_LoadDGroupInfo(m_pDLLHandle, rvInfo2,
                        DPSDK_CORE_DEFAULT_TIMEOUT);

                openVideo();
            } else {
                Log.d("DpsdkLogin failed:", result + "");
                Toast.makeText(getApplicationContext(), "登录失败：" + result,
                        Toast.LENGTH_SHORT).show();
                m_pDLLHandle = 0;
            }
        }

    }

    // 打开视频
    private void openVideo() {

        SurfaceHolder holder = m_svPlayer.getHolder();
        holder.addCallback(new Callback() {
            public void surfaceCreated(SurfaceHolder holder) {
                Log.d("xss", "surfaceCreated");
                IPlaySDK.InitSurface(m_nPort, m_svPlayer);
            }

            public void surfaceChanged(SurfaceHolder holder, int format,
                    int width, int height) {
                Log.d("xss", "surfaceChanged");
            }

            public void surfaceDestroyed(SurfaceHolder holder) {
                Log.d("xss", "surfaceDestroyed");
            }
        });

        final fMediaDataCallback fm = new fMediaDataCallback() {

            @Override
            public void invoke(int nPDLLHandle, int nSeq, int nMediaType,
                    byte[] szNodeId, int nParamVal, byte[] szData,
                    int nDataLen) {

                int ret = IPlaySDK.PLAYInputData(m_nPort, szData, nDataLen);
                if (ret == 1) {
                    Log.e("xss", "playing success=" + nSeq + " package size="
                            + nDataLen);
                } else {
                    Log.e("xss", "playing failed=" + nSeq + " package size="
                            + nDataLen);
                }
            }
        };

        if (!StartRealPlay()) {
            Log.e("xss", "StartRealPlay failed!");
            Toast.makeText(getApplicationContext(), "视频播放失败!",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            Return_Value_Info_t retVal = new Return_Value_Info_t();

            Get_RealStream_Info_t getRealStreamInfo = new Get_RealStream_Info_t();
            // m_szCameraId = etCam.getText().toString().getBytes();

            String cameraId = new String(m_szCameraId);

            System.arraycopy(m_szCameraId, 0, getRealStreamInfo.szCameraId, 0,
                    m_szCameraId.length);
            // getRealStreamInfo.szCameraId =
            // "1000096$1$0$0".getBytes();
            getRealStreamInfo.nMediaType = 1;
            getRealStreamInfo.nRight = 1;
            getRealStreamInfo.nStreamType = 1;
            getRealStreamInfo.nTransType = 1;
            Enc_Channel_Info_Ex_t ChannelInfo = new Enc_Channel_Info_Ex_t();

            IDpsdkCore.DPSDK_GetChannelInfoById(m_pDLLHandle, m_szCameraId,
                    ChannelInfo);
            int ret = IDpsdkCore.DPSDK_GetRealStream(m_pDLLHandle, retVal,
                    getRealStreamInfo, fm, mTimeOut);
            if (ret == 0) {
                m_nSeq = retVal.nReturnValue;
                Log.e("xss DPSDK_GetRealStream success!", ret + "");
                Toast.makeText(getApplicationContext(), "视频播放成功!",
                        Toast.LENGTH_SHORT).show();
            } else {
                StopRealPlay();
                Log.e("xss DPSDK_GetRealStream failed!", ret + "");
                Toast.makeText(getApplicationContext(), "视频播放失败!",
                        Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e("xss", e.toString());
        }

    }

    private void saveIntoMediaCore() {
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        // intent.setAction(MEDIA_ROUTER_SERVICE);
        Uri uri = Uri.parse(IMAGE_PATH + IMGSTR);
        intent.setData(uri);
        ProductInfoActivity.this.setIntent(intent);
    }

    private void showToast(int str) {
        Toast.makeText(getApplicationContext(), getResources().getString(str),
                Toast.LENGTH_SHORT).show();
    }

    public void StopRealPlay() {
        try {
            IPlaySDK.PLAYStopSoundShare(m_nPort);
            IPlaySDK.PLAYStop(m_nPort);
            IPlaySDK.PLAYCloseStream(m_nPort);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean StartRealPlay() {
        if (m_svPlayer == null)
            return false;

        boolean bOpenRet = IPlaySDK.PLAYOpenStream(m_nPort, null, 0,
                1500 * 1024) == 0 ? false : true;
        if (bOpenRet) {
            boolean bPlayRet = IPlaySDK.PLAYPlay(m_nPort, m_svPlayer) == 0
                    ? false : true;
            Log.i("StartRealPlay", "StartRealPlay1");
            if (bPlayRet) {
                boolean bSuccess = IPlaySDK.PLAYPlaySoundShare(m_nPort) == 0
                        ? false : true;

                Log.i("StartRealPlay", "StartRealPlay2");
                if (!bSuccess) {
                    IPlaySDK.PLAYStop(m_nPort);
                    IPlaySDK.PLAYCloseStream(m_nPort);
                    Log.i("StartRealPlay", "StartRealPlay3");
                    return false;
                }
            } else {
                IPlaySDK.PLAYCloseStream(m_nPort);
                Log.i("StartRealPlay", "StartRealPlay4");
                return false;
            }
        } else {
            Log.i("StartRealPlay", "StartRealPlay5");
            return false;
        }

        return true;
    }

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

//    private void getDBData() {
//        dbh = new DBHelper(this);
//        productInfo = new ProductInfo();
//
//        productInfoList = dbh.queryProductInfo();
//
//     
//         
//   }
    
    public void onEvent(SoapRes obj) {
        if (obj.getCode().equals(SOAP_UTILS.METHOD.GETPRODUCT)) {
            if (obj.getObj() != null) {
                    try {
                        JSONObject json_obj = new JSONObject(obj.getObj().toString());
                        String result = json_obj.get("status").toString();
                        if(result.equals("true")){
                            
                             image = json_obj.get("image").toString();
                             name = json_obj.get("name").toString();
                             price = json_obj.get("price").toString();
                             shelvestime = json_obj.get("shelvestime").toString();
                             area = json_obj.get("area").toString();
                             channelid = json_obj.get("channelid").toString();
                             proip = json_obj.get("ip").toString();
                             proport = json_obj.get("port").toString();
                             account = json_obj.get("account").toString();
                             propassword = json_obj.get("password").toString();
                             type = json_obj.get("type").toString();
                             id = json_obj.get("id").toString();
                             yield = json_obj.get("yield").toString();

                             JSONArray imgs_array = json_obj.getJSONArray("imgs");
                              productimgsListView = new ArrayList<ProductImgs>();
                             for (int i = 0; i < imgs_array.length(); i++) {
                                 ProductImgs images = new ProductImgs();
                                 String imgs = imgs_array.get(i).toString();
                                 images.setImgs(imgs);
                                 productimgsListView.add(images);
                             }
                             
                             productimgsAdapter = new ProductImgsListAdapter(context, productimgsListView);
                             prodetail_layout.setAdapter(productimgsAdapter);

                           Instance.imageLoader.displayImage(image,imgAd1, Instance.user_s_options);
                           proname_tv.setText(name);
                           proweight_tv.setText(price);
                           prodate_tv.setText(shelvestime);
                           location_tv.setText(area);
                           prototal_tv.setText(price);
                           if(account.equals("")||account.equals(null)){
                               account = "0";
                           }
                           carcount_tv.setText(account);
   
//                            dbh.clearProductInfo();
                            
//                        getDBData();
                        
                        }else{
                            Toast.makeText(context, json_obj.get("msg").toString(), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                
                }else{
                    Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
                }
        }else if (obj.getCode().equals(SOAP_UTILS.METHOD.ADDCART)) {
            if (obj.getObj() != null) {
                if (obj.getObj().toString().equals("true")) {
//                    Toast.makeText(context, "加入购物车成功", Toast.LENGTH_SHORT).show();

                    carcount_tv.setText(Integer.valueOf(carcount_tv.getText().toString()) + 1 + "");
                } else {
                    Toast.makeText(context, obj.getObj().toString(), Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, "加入购物车失败", Toast.LENGTH_SHORT).show();
            }
        }
    }
}