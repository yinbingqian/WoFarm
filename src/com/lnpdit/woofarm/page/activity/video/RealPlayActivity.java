package com.lnpdit.woofarm.page.activity.video;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.company.PlaySDK.IPlaySDK;
import com.dh.DpsdkCore.Enc_Channel_Info_Ex_t;
import com.dh.DpsdkCore.Get_RealStream_Info_t;
import com.dh.DpsdkCore.IDpsdkCore;
import com.dh.DpsdkCore.Login_Info_t;
import com.dh.DpsdkCore.Ptz_Direct_Info_t;
import com.dh.DpsdkCore.Ptz_Operation_Info_t;
import com.dh.DpsdkCore.Return_Value_Info_t;
import com.dh.DpsdkCore.fDPSDKStatusCallback;
import com.dh.DpsdkCore.fMediaDataCallback;
import com.lnpdit.woofarm.R;
import com.lnpdit.woofarm.page.activity.product.ProductInfoActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class RealPlayActivity extends Activity implements OnClickListener {

    static IDpsdkCore dpsdkcore = new IDpsdkCore();
    Resources res;

    private String isfirstLogin;
    static int m_nLastError = 0;
    static Return_Value_Info_t m_ReValue = new Return_Value_Info_t();

    Handler handler;
    // 云台按钮
    private Button upButton;
    private Button downButton;
    private Button leftButton;
    private Button rightButton;
    private Button zoominButton;
    private Button zoomoutButton;

    String ptz = "1";

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
    // ��ʱʱ��
    private static final int DPSDK_CORE_DEFAULT_TIMEOUT = 600000;

    private ImageView imgBack;
    private TextView tvBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_real_play);

        handler = new Handler();

        findViews();

        String name = this.getIntent().getStringExtra("name");
        if (name.equals("甜查理")) {
            ip = "123.57.74.234";
            port = "9000";
            username = "2222";
            password = "12345678";
            m_szCameraId = "1000008$1$0$0".getBytes();
        } else {
            ip = "223.100.225.101";
            port = "9000";
            username = "system";
            password = "123456";

            m_szCameraId = "1000001$1$0$12".getBytes();
        }

        if (ptz.equals("1")) {
            new Thread() {
                public void run() {
                    handler.post(runnableDrawWidget);
                }
            }.start();
        }
        // int nRet;
        m_nPort = IPlaySDK.PLAYGetFreePort();

        // ��¼���
        Log.d("onCreate:", m_nLastError + "");
        int nType = 1;
        m_nLastError = IDpsdkCore.DPSDK_Create(nType, m_ReValue);

        IDpsdkCore.DPSDK_SetDPSDKStatusCallback(m_ReValue.nReturnValue,
                new fDPSDKStatusCallback() {

                    @Override
                    public void invoke(int nPDLLHandle, int nStatus) {
                        Log.v("fDPSDKStatusCallback", "nStatus = " + nStatus);
                    }

                });

        Log.d("DpsdkCreate:", m_nLastError + "");

        new LoginTask().execute();

    }

    // ��¼���

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
        protected Integer doInBackground(Void... arg0) { // �ڴ˴�����UI�ᵼ���쳣
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

    // ����Ƶ
    private void openVideo() {

        // ���ü�����
        // setListener();

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

    private void findViews() {
        // btLeft = (Button) findViewById(R.id.button_ptz_left);
        // btRight = (Button) findViewById(R.id.button_right);
        // btTop = (Button) findViewById(R.id.button_top);
        // btBottom = (Button) findViewById(R.id.button_bottom);
        // btAddZoom = (Button) findViewById(R.id.button_add_zoom);
        // btReduceZoom = (Button) findViewById(R.id.button_reduce_zoom);
        // btAddFocus = (Button) findViewById(R.id.button_add_focus);
        // btReduceFocus = (Button) findViewById(R.id.button_reduce_focus);
        // btAddAperture = (Button) findViewById(R.id.button_add_aperture);
        // btReduceAperture = (Button)
        // findViewById(R.id.button_reduce_aperture);
        // etCam = (TextView) findViewById(R.id.et_cam_id);
        // tvRet = (TextView) findViewById(R.id.tv_excute_result);
        m_svPlayer = (SurfaceView) findViewById(R.id.sv_player);
        // btnCaptureImg = (Button) findViewById(R.id.capture_img);
        imgBack = (ImageView) findViewById(R.id.img_back);
        imgBack.setOnClickListener(this);
        tvBack = (TextView) findViewById(R.id.tv_back);
        tvBack.setOnClickListener(this);
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
        default:
            break;
        }
    }

    // 向视频上添加按钮等Layout
    Runnable runnableDrawWidget = new Runnable() {
        @Override
        public void run() {
            drawWidget();
        }

    };

    private void drawWidget() {

        try {

            // btnSize.setVisibility(Button.VISIBLE);
            // btnPlayPause.setVisibility(Button.VISIBLE);

            Display display = getWindowManager().getDefaultDisplay();
            final LayoutInflater inflater = LayoutInflater.from(this);
            //
            // // 显示云台箭头
            LinearLayout layout = (LinearLayout) inflater
                    .inflate(R.layout.arrow, null)
                    .findViewById(R.id.arrowlayout);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    (int) display.getWidth(), (int) display.getHeight(), 200);
            addContentView(layout, params);

            upButton = (Button) findViewById(R.id.btnUp);
            upButton.setOnTouchListener(new OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    // TODO Auto-generated method stub
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        // 更改为按下时的背景图片
                        v.setBackgroundResource(R.drawable.uppress);
                        Ptz_Direct_Info_t ptzDirectInfo = new Ptz_Direct_Info_t();
                        System.arraycopy(m_szCameraId, 0,
                                ptzDirectInfo.szCameraId, 0,
                                m_szCameraId.length);
                        ptzDirectInfo.bStop = false;
                        ptzDirectInfo.nDirect = 1;
                        ptzDirectInfo.nStep = 4;

                        int ret = IDpsdkCore.DPSDK_PtzDirection(m_pDLLHandle,
                                ptzDirectInfo, mTimeOut);
                        if (ret == 0) {
                            Log.e("xss", "DPSDK_PtzDirection success!");
                        } else {
                            Log.e("xss", "DPSDK_PtzDirection failed!");
                        }

                    } else if (event.getAction() == MotionEvent.ACTION_UP) {
                        // 改为抬起时的图片
                        v.setBackgroundResource(R.drawable.up);
                        Ptz_Direct_Info_t ptzDirectInfo = new Ptz_Direct_Info_t();
                        System.arraycopy(m_szCameraId, 0,
                                ptzDirectInfo.szCameraId, 0,
                                m_szCameraId.length);
                        ptzDirectInfo.bStop = true;
                        ptzDirectInfo.nDirect = 1;
                        ptzDirectInfo.nStep = 4;

                        int ret = IDpsdkCore.DPSDK_PtzDirection(m_pDLLHandle,
                                ptzDirectInfo, mTimeOut);
                        if (ret == 0) {
                            Log.e("xss", "DPSDK_PtzDirection success!");
                        } else {
                            Log.e("xss", "DPSDK_PtzDirection failed!");
                        }

                    }
                    return false;

                }
            });
            downButton = (Button) findViewById(R.id.btnDown);
            downButton.setOnTouchListener(new OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    // TODO Auto-generated method stub
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        // 更改为按下时的背景图片
                        v.setBackgroundResource(R.drawable.downpress);
                        Ptz_Direct_Info_t ptzDirectInfo = new Ptz_Direct_Info_t();
                        System.arraycopy(m_szCameraId, 0,
                                ptzDirectInfo.szCameraId, 0,
                                m_szCameraId.length);
                        ptzDirectInfo.bStop = false;
                        ptzDirectInfo.nDirect = 2;
                        ptzDirectInfo.nStep = 4;

                        int ret = IDpsdkCore.DPSDK_PtzDirection(m_pDLLHandle,
                                ptzDirectInfo, mTimeOut);
                        if (ret == 0) {
                            Log.e("xss", "DPSDK_PtzDirection success!");
                        } else {
                            Log.e("xss", "DPSDK_PtzDirection failed!");
                        }

                    } else if (event.getAction() == MotionEvent.ACTION_UP) {
                        // 改为抬起时的图片
                        v.setBackgroundResource(R.drawable.down);
                        Ptz_Direct_Info_t ptzDirectInfo = new Ptz_Direct_Info_t();
                        System.arraycopy(m_szCameraId, 0,
                                ptzDirectInfo.szCameraId, 0,
                                m_szCameraId.length);
                        ptzDirectInfo.bStop = true;
                        ptzDirectInfo.nDirect = 2;
                        ptzDirectInfo.nStep = 4;

                        int ret = IDpsdkCore.DPSDK_PtzDirection(m_pDLLHandle,
                                ptzDirectInfo, mTimeOut);
                        if (ret == 0) {
                            Log.e("xss", "DPSDK_PtzDirection success!");
                        } else {
                            Log.e("xss", "DPSDK_PtzDirection failed!");
                        }

                    }

                    return false;
                }
            });
            leftButton = (Button) findViewById(R.id.btnLeft);
            leftButton.setOnTouchListener(new OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    // TODO Auto-generated method stub
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        // 更改为按下时的背景图片
                        v.setBackgroundResource(R.drawable.leftpress);
                        Ptz_Direct_Info_t ptzDirectInfo = new Ptz_Direct_Info_t();
                        System.arraycopy(m_szCameraId, 0,
                                ptzDirectInfo.szCameraId, 0,
                                m_szCameraId.length);
                        ptzDirectInfo.bStop = false;
                        ptzDirectInfo.nDirect = 3;
                        ptzDirectInfo.nStep = 4;

                        int ret = IDpsdkCore.DPSDK_PtzDirection(m_pDLLHandle,
                                ptzDirectInfo, mTimeOut);
                        if (ret == 0) {
                            Log.e("xss", "DPSDK_PtzDirection success!");
                        } else {
                            Log.e("xss", "DPSDK_PtzDirection failed!");
                        }

                    } else if (event.getAction() == MotionEvent.ACTION_UP) {
                        // 改为抬起时的图片
                        v.setBackgroundResource(R.drawable.left);
                        Ptz_Direct_Info_t ptzDirectInfo = new Ptz_Direct_Info_t();
                        System.arraycopy(m_szCameraId, 0,
                                ptzDirectInfo.szCameraId, 0,
                                m_szCameraId.length);
                        ptzDirectInfo.bStop = true;
                        ptzDirectInfo.nDirect = 3;
                        ptzDirectInfo.nStep = 4;

                        int ret = IDpsdkCore.DPSDK_PtzDirection(m_pDLLHandle,
                                ptzDirectInfo, mTimeOut);
                        if (ret == 0) {
                            Log.e("xss", "DPSDK_PtzDirection success!");
                        } else {
                            Log.e("xss", "DPSDK_PtzDirection failed!");
                        }

                    }

                    return false;
                }
            });
            rightButton = (Button) findViewById(R.id.btnRight);
            rightButton.setOnTouchListener(new OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    // TODO Auto-generated method stub
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        // 更改为按下时的背景图片
                        v.setBackgroundResource(R.drawable.rightpress);
                        Ptz_Direct_Info_t ptzDirectInfo = new Ptz_Direct_Info_t();
                        System.arraycopy(m_szCameraId, 0,
                                ptzDirectInfo.szCameraId, 0,
                                m_szCameraId.length);
                        ptzDirectInfo.bStop = false;
                        ptzDirectInfo.nDirect = 4;
                        ptzDirectInfo.nStep = 4;

                        int ret = IDpsdkCore.DPSDK_PtzDirection(m_pDLLHandle,
                                ptzDirectInfo, mTimeOut);
                        if (ret == 0) {
                            Log.e("xss", "DPSDK_PtzDirection success!");
                        } else {
                            Log.e("xss", "DPSDK_PtzDirection failed!");
                        }

                    } else if (event.getAction() == MotionEvent.ACTION_UP) {
                        // 改为抬起时的图片
                        v.setBackgroundResource(R.drawable.right);
                        Ptz_Direct_Info_t ptzDirectInfo = new Ptz_Direct_Info_t();
                        System.arraycopy(m_szCameraId, 0,
                                ptzDirectInfo.szCameraId, 0,
                                m_szCameraId.length);
                        ptzDirectInfo.bStop = true;
                        ptzDirectInfo.nDirect = 4;
                        ptzDirectInfo.nStep = 4;

                        int ret = IDpsdkCore.DPSDK_PtzDirection(m_pDLLHandle,
                                ptzDirectInfo, mTimeOut);
                        if (ret == 0) {
                            Log.e("xss", "DPSDK_PtzDirection success!");
                        } else {
                            Log.e("xss", "DPSDK_PtzDirection failed!");
                        }

                    }

                    return false;
                }
            });
            zoominButton = (Button) findViewById(R.id.btnZoomin);
            zoominButton.setOnTouchListener(new OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    // TODO Auto-generated method stub
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        // 更改为按下时的背景图片
                        v.setBackgroundResource(R.drawable.zoom_inpress);
                        Ptz_Operation_Info_t ptzOperationInfo = new Ptz_Operation_Info_t();
                        System.arraycopy(m_szCameraId, 0,
                                ptzOperationInfo.szCameraId, 0,
                                m_szCameraId.length);
                        ptzOperationInfo.bStop = false;
                        ptzOperationInfo.nOperation = 0;
                        ptzOperationInfo.nStep = 4;

                        int ret = IDpsdkCore.DPSDK_PtzCameraOperation(
                                m_pDLLHandle, ptzOperationInfo, mTimeOut);
                        if (ret == 0) {
                            Log.e("xss", "DPSDK_PtzCameraOperation success!");
                        } else {
                            Log.e("xss", "DPSDK_PtzCameraOperation failed!");
                        }

                    } else if (event.getAction() == MotionEvent.ACTION_UP) {
                        // 改为抬起时的图片
                        v.setBackgroundResource(R.drawable.zoom_in);
                        Ptz_Operation_Info_t ptzOperationInfo = new Ptz_Operation_Info_t();
                        System.arraycopy(m_szCameraId, 0,
                                ptzOperationInfo.szCameraId, 0,
                                m_szCameraId.length);
                        ptzOperationInfo.bStop = true;
                        ptzOperationInfo.nOperation = 0;
                        ptzOperationInfo.nStep = 4;

                        int ret = IDpsdkCore.DPSDK_PtzCameraOperation(
                                m_pDLLHandle, ptzOperationInfo, mTimeOut);
                        if (ret == 0) {
                            Log.e("xss", "DPSDK_PtzCameraOperation success!");
                        } else {
                            Log.e("xss", "DPSDK_PtzCameraOperation failed!");
                        }

                    }

                    return false;
                }
            });
            zoomoutButton = (Button) findViewById(R.id.btnZoomout);
            zoomoutButton.setOnTouchListener(new OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    // TODO Auto-generated method stub
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        // 更改为按下时的背景图片
                        v.setBackgroundResource(R.drawable.zoom_outpress);
                        Ptz_Operation_Info_t ptzOperationInfo = new Ptz_Operation_Info_t();
                        System.arraycopy(m_szCameraId, 0,
                                ptzOperationInfo.szCameraId, 0,
                                m_szCameraId.length);
                        ptzOperationInfo.bStop = false;
                        ptzOperationInfo.nOperation = 3;
                        ptzOperationInfo.nStep = 4;

                        int ret = IDpsdkCore.DPSDK_PtzCameraOperation(
                                m_pDLLHandle, ptzOperationInfo, mTimeOut);
                        if (ret == 0) {
                            Log.e("xss", "DPSDK_PtzCameraOperation success!");
                        } else {
                            Log.e("xss", "DPSDK_PtzCameraOperation failed!");
                        }

                    } else if (event.getAction() == MotionEvent.ACTION_UP) {
                        // 改为抬起时的图片
                        v.setBackgroundResource(R.drawable.zoom_out);
                        Ptz_Operation_Info_t ptzOperationInfo = new Ptz_Operation_Info_t();
                        System.arraycopy(m_szCameraId, 0,
                                ptzOperationInfo.szCameraId, 0,
                                m_szCameraId.length);
                        ptzOperationInfo.bStop = true;
                        ptzOperationInfo.nOperation = 3;
                        ptzOperationInfo.nStep = 4;

                        int ret = IDpsdkCore.DPSDK_PtzCameraOperation(
                                m_pDLLHandle, ptzOperationInfo, mTimeOut);
                        if (ret == 0) {
                            Log.e("xss", "DPSDK_PtzCameraOperation success!");
                        } else {
                            Log.e("xss", "DPSDK_PtzCameraOperation failed!");
                        }

                    }

                    return false;
                }
            });

        } catch (Exception e) {
            // TODO: handle exception
            if (e != null) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void saveIntoMediaCore() {
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        // intent.setAction(MEDIA_ROUTER_SERVICE);
        Uri uri = Uri.parse(IMAGE_PATH + IMGSTR);
        intent.setData(uri);
        RealPlayActivity.this.setIntent(intent);
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
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

}
