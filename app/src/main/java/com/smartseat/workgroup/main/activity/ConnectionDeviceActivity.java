package com.smartseat.workgroup.main.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.smartseat.workgroup.R;
import com.smartseat.workgroup.common.activity.HMBaseActivity;
import com.smartseat.workgroup.common.utils.BaseVolume;
import com.smartseat.workgroup.common.utils.DeviceBandAynsTask;
import com.smartseat.workgroup.common.utils.LoadingDialog;
import com.smartseat.workgroup.common.utils.NetworkUtils;
import com.smartseat.workgroup.common.utils.Result;
import com.smartseat.workgroup.common.utils.SPUtils;
import com.smartseat.workgroup.common.utils.ToaskUtils;
import com.smartseat.workgroup.main.db.dao.DeviceInfoDao;
import com.smartseat.workgroup.main.model.DeviceInfoCache;
import com.smartseat.workgroup.main.service.ControlService;

/**
 * 登录页面
 */
public class ConnectionDeviceActivity extends HMBaseActivity {

    private EditText m_etUser, m_etPwd;
    private LoadingDialog loading;
    private String sUser = "";
    private String sPwd = "";
    private int place = 0;
    private LinearLayout m_llParent;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_device);
        place = getIntent().getIntExtra("devicePlace", 0);
        loading = new LoadingDialog(this, R.style.LoadingDialogStyle);
        initUI();

        IntentFilter mFilter = new IntentFilter();
        mFilter.addAction(BaseVolume.CONNECT_DEVICE_ERROR);
        mFilter.addAction(BaseVolume.CONNECT_DEVICE_OK);
        registerReceiver(mReceiver, mFilter);

        Intent service = new Intent(ConnectionDeviceActivity.this, ControlService.class);
        bindService(service, conn, Context.BIND_AUTO_CREATE);
    }

    private ControlService controlService = null;
    private ServiceConnection conn = new ServiceConnection() {
        public void onServiceConnected(ComponentName name, IBinder service) {
            controlService = ((ControlService.ServiceBinder) service).getService();
        }

        public void onServiceDisconnected(ComponentName name) {
            controlService = null;
        }
    };

    private void initUI() {
        findViewById(R.id.imgbtn_top_left).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.deviceConnection).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ConnectionDeviceActivity.this, AKeyConfigActivity.class);
                startActivity(intent);
            }
        });

        m_llParent = findViewById(R.id.llParent);
        m_llParent.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });

        m_etUser = findViewById(R.id.etUser);
        m_etPwd = findViewById(R.id.etPWD);
        findViewById(R.id.submit).setOnClickListener(submitListener);

    }

    private int loginCount = 0;
    private OnClickListener submitListener = new OnClickListener() {

        public void onClick(View v) {
            sUser = m_etUser.getText().toString();
            sPwd = m_etPwd.getText().toString();
//			sUser = "HL00011128";
//			sPwd = "9baoXs";
            DeviceInfoDao deviceInfoDao = new DeviceInfoDao(ConnectionDeviceActivity.this);

            if (sUser.equals("") || sPwd.equals("")) {
                ToaskUtils.showToast("用户名和密码为空！");
            } else if (deviceInfoDao.isDeviceExist(sUser, BaseVolume.IS_MAN_ADD)) {
                ToaskUtils.showToast("设备不存在！");
            } else if (!isNetworkAvailable()) {
                ToaskUtils.showToast("网络不可用！");
            } else {
                loading.show();
                Intent intent = new Intent(BaseVolume.CONNECT_DEVICE);
                intent.putExtra(BaseVolume.DEVICE_USER, sUser);
                intent.putExtra(BaseVolume.DEVICE_PWD, sPwd);
                controlService.onStartCommand(intent, 0, 0);
            }
        }
    };

    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case BaseVolume.TASK_START:
                    loading.show();
                    break;
                case BaseVolume.TASK_OK:
                    if (loading.isShowing()) {
                        loading.dismiss();
                    }
                    String time = NetworkUtils.getNowTime();
                    DeviceInfoDao deviceInfoDao = new DeviceInfoDao(ConnectionDeviceActivity.this);
                    deviceInfoDao.deleteDataByID(sUser);
                    DeviceInfoCache device = new DeviceInfoCache(sUser, sPwd, time, BaseVolume.IS_MAN_ADD, place);
                    deviceInfoDao.insertSingleData(device);
                    deviceInfoDao.closeDb();
                    device.setOnLine(BaseVolume.IS_ONLINE);
                    sendBroadcast(new Intent(BaseVolume.ADD_DEVICE).putExtra("device", device));
                    sendBroadcast(new Intent(BaseVolume.DEVICE_DELETE).putExtra(BaseVolume.DEVICE_USER, sUser));
                    SPUtils.put(ConnectionDeviceActivity.this,"isLogin",true);
                    finish();
                    break;
                case BaseVolume.TASK_ERROR:
                    if (loginCount == 0) {
                        ++loginCount;
                        DeviceBandAynsTask task = new DeviceBandAynsTask(mHandler, ConnectionDeviceActivity.this);
                        task.execute(sUser, sPwd);
                    } else {
                        if (loading.isShowing()) {
                            loading.dismiss();
                        }
                        Result temp = (Result) msg.obj;
                        if (temp.getError().equals("")) {
                            ToaskUtils.showToast("登录错误");
                        } else
                            ToaskUtils.showToast(temp.getError());
                    }

                    break;

                default:
                    break;
            }
        }
    };

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(BaseVolume.CONNECT_DEVICE_OK)) {
                String deviceUser = intent.getStringExtra(BaseVolume.DEVICE_USER);
                if (!deviceUser.equals(sUser)) {
                    return;
                }
                if (loading.isShowing()) {
                    loading.dismiss();
                }
                String time = NetworkUtils.getNowTime();
                DeviceInfoDao deviceInfoDao = new DeviceInfoDao(ConnectionDeviceActivity.this);
                deviceInfoDao.deleteDataByID(sUser);

                DeviceInfoCache device = new DeviceInfoCache(sUser, sPwd, time, BaseVolume.IS_MAN_ADD, place);
                deviceInfoDao.insertSingleData(device);
                deviceInfoDao.closeDb();
                device.setOnLine(BaseVolume.IS_ONLINE);
                sendBroadcast(new Intent(BaseVolume.ADD_DEVICE).putExtra("device", device));
                sendBroadcast(new Intent(BaseVolume.DEVICE_DELETE).putExtra(BaseVolume.DEVICE_USER, sUser));
                BaseVolume.IS_REGISTER = true;
                finish();
            } else if (action.equals(BaseVolume.CONNECT_DEVICE_ERROR)) {
                if (loading.isShowing()) {
                    loading.dismiss();
                }
                ToaskUtils.showToast("连接设备出错！");
            }
        }
    };

    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
        unregisterReceiver(mReceiver);
    }
}