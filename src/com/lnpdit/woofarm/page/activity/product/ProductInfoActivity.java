package com.lnpdit.woofarm.page.activity.product;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.company.PlaySDK.IPlaySDK;
import com.dh.DpsdkCore.Enc_Channel_Info_Ex_t;
import com.dh.DpsdkCore.Get_RealStream_Info_t;
import com.dh.DpsdkCore.IDpsdkCore;
import com.dh.DpsdkCore.Login_Info_t;
import com.dh.DpsdkCore.Return_Value_Info_t;
import com.dh.DpsdkCore.fDPSDKStatusCallback;
import com.dh.DpsdkCore.fMediaDataCallback;
import com.eroad.widget.calendar.CalanderActivity;
import com.lnpdit.woofarm.R;
import com.lnpdit.woofarm.base.component.BaseActivity;
import com.lnpdit.woofarm.page.activity.video.RealPlayActivity;
import com.lnpdit.woofarm.widget.BuyNowPopWin;
import com.lnpdit.woofarm.widget.TakePhotoPopWin;

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
import android.view.SurfaceView;
import android.view.View;
import android.view.SurfaceHolder.Callback;
import android.widget.Button;
import android.widget.ImageView;
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
    private String name;

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
        initView();

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
            Toast.makeText(this, "成功加入购物车！", Toast.LENGTH_SHORT).show();
            carcount_tv.setText(carcount_tv.getText().toString() + 1);
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

}