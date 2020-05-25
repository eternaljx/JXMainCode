package com.smartseat.workgroup.main.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Binder;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.smartseat.workgroup.R;
import com.smartseat.workgroup.common.utils.BaseVolume;
import com.smartseat.workgroup.common.utils.DeviceBandAynsTask;
import com.smartseat.workgroup.common.utils.NetworkUtils;
import com.smartseat.workgroup.common.utils.Result;
import com.smartseat.workgroup.common.utils.ToaskUtils;
import com.smartseat.workgroup.main.SeatMainActivity;
import com.smartseat.workgroup.main.db.dao.DeviceInfoDao;
import com.smartseat.workgroup.main.model.DeviceInfoCache;
import com.smartseat.workgroup.main.socket.FindDeviceIP;
import com.smartseat.workgroup.main.socket.NetManager;
import com.smartseat.workgroup.main.socket.SocketThreadManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 控制设备服务
 *
 * @author Denny
 */
public class ControlService extends Service {

    private FindDeviceIP findIP = new FindDeviceIP();

    public IBinder onBind(Intent intent) {
        IBinder result = null;
        if (null == result)
            result = new ServiceBinder();
        return result;

    }

    public class ServiceBinder extends Binder {
        public ControlService getService() {
            return ControlService.this;
        }
    }

    private TelephonyManager tm;
    public static SharedPreferences sharedPreferences;

    public void onCreate() {
        super.onCreate();
        // 首�?�?用于存储用户
        sharedPreferences = getSharedPreferences("XlinkOfficiaDemo", Context.MODE_PRIVATE);
        tm = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);


        IntentFilter mFilter = new IntentFilter();
        mFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mReceiver, mFilter);
    }

    public String versionName;
    public int versionCode;
    public String packageName;
    private static Handler mainHandler = null;

    public static void initHandler() {
        mainHandler = new Handler();
    }

    /**
     * 执行在主线程任务
     *
     * @param runnable
     */
    public static void postToMainThread(Runnable runnable) {
        mainHandler.post(runnable);
    }

    private String device_user = "";
    private String device_pwd = "";
    private Map<String, Boolean> device_conn_state = new HashMap<>();

    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            String action = intent.getAction();
            // UDP扫描   myapp.java 启动
            if (action.equals(BaseVolume.SCAN_DEVICE)) {
                BaseVolume.APP_IS_WAN = true;
                DeviceInfoDao deviceInfoDao = new DeviceInfoDao(ControlService.this);
                list = deviceInfoDao.queryAllDevice();
                if (list != null) {
                    for (DeviceInfoCache device : list) {
                        device_conn_state.put(device.getId(), false);
                    }
                }
                deviceInfoDao.closeDb();
                if (NetManager.isWifiConnected(ControlService.this)) {
                    findCount = new TimeCount(1000 * 30, 1000 * 1, handler);
                    findCount.start();
                    handler.sendEmptyMessageDelayed(isConnect, 1000 * 6);
                } else {
                    if (BaseVolume.APP_IS_WAN && isNetworkAvailable()) {
                        ConncetionLan();
                    }
                }
            }
            // 连接设备,先UDP检测，再连接
            else if (action.equals(BaseVolume.CONNECT_DEVICE)) {
                if (!isNetworkAvailable()) {
                    ToaskUtils.showToast("网络连接异常");
                } else {
                    BaseVolume.APP_IS_WAN = true;
                    device_user = intent.getStringExtra(BaseVolume.DEVICE_USER);
                    device_pwd = intent.getStringExtra(BaseVolume.DEVICE_PWD);

                    if (NetManager.isWifiConnected(ControlService.this)) {
                        findCount = new TimeCount(1000 * 20, 1000 * 1, handler1);
                        findCount.start();
                        handler1.sendEmptyMessageDelayed(isConnect, 1000 * 6);
                    } else {
                        DeviceBandAynsTask task = new DeviceBandAynsTask(mHandler, ControlService.this);
                        task.execute(device_user, device_pwd);
                    }
                }
            }


        }
        return super.onStartCommand(intent, flags, startId);
    }

    /***
     * 外网连接设备
     */
    private void ConncetionLan() {
        for (DeviceInfoCache device : list) {
            // 不在线且，如果是 手动添加的  或者   二维码扫描并未超时10分钟
            if (device.getOnLine() == BaseVolume.NOT_ONLINE && // 不在线
                    (device.getStyle() == BaseVolume.IS_MAN_ADD || // 如果是 手动添加的
                            (device.getStyle() == BaseVolume.IS_SHARE_ADD && !NetworkUtils.isTimeOut(device.getTime()))) // // 如果是 二维码扫描并未超时10分钟
                    ) {
                // 连接设备
                DeviceBandAynsTask task = new DeviceBandAynsTask(mHandler, ControlService.this);
                task.execute(device.getId(), device.getPwd());
            }
        }
    }

    private final int isConnect = 3;
    /***
     * 弹出消息回调
     */
    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case BaseVolume.TASK_START:
                    break;
                case BaseVolume.TASK_OK:
                    ToaskUtils.showToast("连接成功！");
                    Bundle bundle = (Bundle) msg.getData();
                    String user = bundle.getString(BaseVolume.DEVICE_USER);
                    Log.e("xiaoxiao", user);
                    sendBroadcast(new Intent(BaseVolume.CONNECT_DEVICE_OK).putExtra(BaseVolume.DEVICE_USER, user));

                    break;
                case BaseVolume.TASK_ERROR:
                    Result temp = (Result) msg.obj;
                    if (temp.getError().equals(""))
                        ToaskUtils.showToast("远程连接失败，请检查网络！");
                    else
                        ToaskUtils.showToast(temp.getError());
                    Bundle bundle1 = msg.getData();
                    String user1 = bundle1.getString(BaseVolume.DEVICE_USER);
                    sendBroadcast(new Intent(BaseVolume.CONNECT_DEVICE_ERROR).putExtra(BaseVolume.DEVICE_USER, user1));
                    break;


                default:
                    break;
            }
        }
    };

    //首次进入和设备启动
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case BaseVolume.IS_DEVICE_IP:
                    BaseVolume.APP_IS_WAN = false;
                    Bundle bundle = msg.getData();
                    String ip = bundle.getString("ip");
                    String user = bundle.getString("user");
                    Log.e("tagFirstRun", "ip:" + ip);
                    Log.e("tagFirstRun", "user:" + user);
//				if (ip.equals("192.168.16.254")) {
//					return;
//				}
                    Boolean all_conn = true;
                    for (String key : device_conn_state.keySet()) {
                        all_conn = device_conn_state.get(key);
                        if (!all_conn) break;
                    }
                    if (all_conn) {
                        findCount.cancel();
                        sendBroadcast(new Intent(BaseVolume.CONNECT_DEVICE_OK).putExtra(BaseVolume.DEVICE_USER, user));
                        break;
                    }
                    for (DeviceInfoCache device : list) {
                        // 数据库中存在
                        if (device.getId().equals(user) &&
                                device.getOnLine() == BaseVolume.NOT_ONLINE && // 不在线
                                (device.getStyle() == BaseVolume.IS_MAN_ADD || // 如果是 手动添加的
                                        (device.getStyle() == BaseVolume.IS_SHARE_ADD && !NetworkUtils.isTimeOut(device.getTime()))) // // 如果是 二维码扫描并未超时10分钟
                                ) {
                            if (SocketThreadManager.sharedInstance(ControlService.this).getTCPClient(user) == null) {
                                SocketThreadManager.sharedInstance(ControlService.this).createSocket(user, ip, BaseVolume.LAN_PORT);
                                // socket创建成功
                                if (SocketThreadManager.sharedInstance(ControlService.this).getTCPClient(user) != null) {
                                    device.setOnLine(1);
                                    device.setIp(ip);
                                    device_conn_state.put(user, true);
                                    sendBroadcast(new Intent(BaseVolume.CONNECT_DEVICE_OK).putExtra(BaseVolume.DEVICE_USER, user));
                                }
                            }
                        }
                    }
                    break;
                case isConnect:
                    if (BaseVolume.APP_IS_WAN) {
                        for (DeviceInfoCache device : list) {
                            if (device.getOnLine() == BaseVolume.NOT_ONLINE && // 不在线
                                    (device.getStyle() == BaseVolume.IS_MAN_ADD || // 如果是 手动添加的
                                            (device.getStyle() == BaseVolume.IS_SHARE_ADD && !NetworkUtils.isTimeOut(device.getTime()))) // // 如果是 二维码扫描并未超时10分钟
                                    ) {
                                // 连接设备
                                DeviceBandAynsTask task = new DeviceBandAynsTask(mHandler, ControlService.this);
                                task.execute(device.getId(), device.getPwd());
                            }
                        }
                    } else {
                        for (DeviceInfoCache device : list) {
                            if (SocketThreadManager.sharedInstance(ControlService.this).getTCPClient(device.getId()) == null) {
                                if (device.getOnLine() == BaseVolume.NOT_ONLINE && // 不在线
                                        (device.getStyle() == BaseVolume.IS_MAN_ADD || // 如果是 手动添加的
                                                (device.getStyle() == BaseVolume.IS_SHARE_ADD && !NetworkUtils.isTimeOut(device.getTime()))) // // 如果是 二维码扫描并未超时10分钟
                                        ) {
                                    // 连接设备
//								Log.e("连接", "局域网连接失败，尝试走外网连接！");
//	                			DeviceBandAynsTask task = new DeviceBandAynsTask(mHandler,ControlService.this);
//	                    		task.execute(device.getId(),device.getPwd());
                                }

                                sendBroadcast(new Intent(BaseVolume.CONNECT_DEVICE_ERROR).putExtra(BaseVolume.DEVICE_USER, device.getId()));
                            }
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    };
    //用户注册回调
    Handler handler1 = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case BaseVolume.IS_DEVICE_IP:
                    BaseVolume.APP_IS_WAN = false;
                    Bundle bundle = msg.getData();
                    String ip = bundle.getString("ip");
                    String user = bundle.getString("user");
                    if (user.equals(device_user)) {
                        SocketThreadManager.sharedInstance(ControlService.this).createSocket(user, ip, BaseVolume.LAN_PORT);
                        if (SocketThreadManager.sharedInstance(ControlService.this).getTCPClient(device_user) != null) {
                            findCount.cancel();
                            sendBroadcast(new Intent(BaseVolume.CONNECT_DEVICE_OK).putExtra(BaseVolume.DEVICE_USER, user));
                        }
                    }
                    break;
                // udp未连接上，则走远程连接
                case isConnect:
                    // 外网
                    if (BaseVolume.APP_IS_WAN && isNetworkAvailable()) {
                        DeviceBandAynsTask task = new DeviceBandAynsTask(mHandler, ControlService.this);
                        task.execute(device_user, device_pwd);
                    }
                    // 内网
                    else {
                        if (SocketThreadManager.sharedInstance(ControlService.this).getTCPClient(device_user) == null) {
                            Log.e("连接", "局域网连接失败，尝试走外网连接！");
                            DeviceBandAynsTask task = new DeviceBandAynsTask(mHandler, ControlService.this);
                            task.execute(device_user, device_pwd);

//						sendBroadcast(new Intent(BaseVolume.CONNECT_DEVICE_ERROR).putExtra(BaseVolume.DEVICE_USER, device_user));
                        }
                    }
                    break;

                default:
                    break;
            }


        }

    };

    private boolean isNetworkAvailable() {
        Context context = getApplicationContext();
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null)
            return false;

        // 获取NetworkInfo对象
        NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();
        if (networkInfo != null && networkInfo.length > 0) {
            for (int i = 0; i < networkInfo.length; i++) {
                // 判断当前网络状态是否为连接状态
                if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }

        return false;
    }

    private TimeCount findCount;

    public class TimeCount extends CountDownTimer {

        Handler handler = null;

        public TimeCount(long millisInFuture, long countDownInterval, Handler handler) {
            super(millisInFuture, countDownInterval);
            this.handler = handler;
        }

        @Override
        public void onTick(long millisUntilFinished) {
            Log.e("设备", "UDP查找！");
            findIP.sendUdpCommand(ControlService.this, handler);
        }

        @Override
        public void onFinish() {
            this.cancel();
            Log.e("设备", "停止查找！");
        }
    }

    private ConnectivityManager connectivityManager;
    private NetworkInfo info;
    private ArrayList<DeviceInfoCache> list = new ArrayList<>();
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                info = connectivityManager.getActiveNetworkInfo();
                // 网络已连接
                if (info != null && info.isAvailable()) {
                    if (NetManager.instance(ControlService.this).isWifiConnected()) {
                    } else {
                        BaseVolume.APP_IS_WAN = true;
                    }
                }
            }
        }
    };

    /***
     * 获取当前时间
     * @return
     */
    private long getNowTime() {
        long time = -1;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy,MM,dd,HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String sTime = formatter.format(curDate);
        String year = sTime.substring(2, 4);
        String month = sTime.substring(5, 7);
        String day = sTime.substring(8, 10);
        String hour = sTime.substring(11, 13);
        String minute = sTime.substring(14, 16);
        String seconds = sTime.substring(17, 19);
        time = Long.parseLong(year + month + day + hour + minute + seconds);
        return time;
    }

    /**
     * 显示通知栏消�?
     */
    private int Notification_ID_BASE = 110;
    private Notification baseNF;
    //通知管理�?
    private NotificationManager nm;
    //通知显示内容
    private PendingIntent pd;

    private void showNotification(String str) {
        baseNF = new Notification();
        //通知时在状�?栏显示的内容
        baseNF.tickerText = str;
        baseNF.icon = R.drawable.img_logo;
        baseNF.defaults |= Notification.DEFAULT_SOUND;
        baseNF.defaults |= Notification.DEFAULT_VIBRATE;
        baseNF.defaults |= Notification.DEFAULT_LIGHTS;
        baseNF.flags |= Notification.FLAG_AUTO_CANCEL;
        //第二个参�?：下拉状态栏时显示的消息标题 expanded message title
        //第三个参数：下拉状�?栏时显示的消息内�?expanded message text
        //第四个参数：点击该�?知时执行页面跳转
        Intent intent = new Intent(this, SeatMainActivity.class);
        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        pd = PendingIntent.getActivity(this, 0, intent, 0);
        nm.notify(Notification_ID_BASE, baseNF);
    }

    /**
     * 将字符串转换成二进制字符�?
     *
     * @param hexString
     * @return
     */
    public static String hexString2binaryString(String hexString) {
        if (hexString == null || hexString.length() % 2 != 0)
            return null;
        String bString = "", tmp;
        for (int i = 0; i < hexString.length(); i++) {
            tmp = "0000"
                    + Integer.toBinaryString(Integer.parseInt(hexString
                    .substring(i, i + 1), 16));
            bString += tmp.substring(tmp.length() - 4) + "";
        }
        return bString;
    }

    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }
}