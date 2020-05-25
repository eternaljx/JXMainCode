package com.smartseat.workgroup.main.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.smartseat.workgroup.R;
import com.smartseat.workgroup.common.activity.HMBaseActivity;
import com.smartseat.workgroup.common.utils.AKeyConfiguration;
import com.smartseat.workgroup.common.utils.LoadingDialog;
import com.smartseat.workgroup.main.elian.ElianNative;
import com.smartseat.workgroup.main.socket.SocketThreadManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ??????????
 *
 * @author Denny
 */
public class AKeyConfigActivity extends HMBaseActivity {

    private LinearLayout m_llParent;
    private EditText m_etSSID;
    private Spinner wps = null;
    private LinearLayout m_llSetPwd;
    private EditText m_etPassWord;
    private byte mAuthMode = 9;
    private String sAuthMode = "";
    private String custom = "";
    private ImageView m_imgSelect;
    private ImageView m_imgShowPwd;
    private boolean showPwd = false;
    private WifiManager mWifiManager;
    private Button m_btnAdapter;
    private String m_sOldID = "";
    private ElianNative elian;
    private LoadingDialog loading;
    private byte AuthModeOpen = 0x00;
    private byte AuthModeWPA = 0x03;
    private byte AuthModeWPAPSK = 0x04;
    private byte AuthModeWPA2 = 0x06;
    private byte AuthModeWPA2PSK = 0x07;
    private byte AuthModeWPA1WPA2 = 0x08;
    private byte AuthModeWPA1PSKWPA2PSK = 0x09;
    private PopupWindow m_pwWifiSelect;
    private View popupWindowWifi;
    private PopupWindow m_pwWifiExcep;
    private View viewWifiExcep;
    private ListView m_lvWifi;
    private ConnectivityManager connectivityManager;
    private NetworkInfo info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seek_device);
        initUi();
        ElianNative.LoadLib();
        elian = new ElianNative();
        loading = new LoadingDialog(AKeyConfigActivity.this, R.style.LoadingDialogStyle);
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mBroadcastReceiver, filter);
        loading.show();
        loading.updateStatusText("??????...");
        new Thread() {
            public void run() {
                SocketThreadManager.sharedInstance(AKeyConfigActivity.this).createSocket("12345678", "192.168.16.254", 80);
                if (SocketThreadManager.sharedInstance(AKeyConfigActivity.this).getTCPClient("12345678") == null) {
                    handler.sendEmptyMessage(0);
                } else {
                    handler.sendEmptyMessage(1);
                }
            }
        }.start();
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            loading.dismiss();
            if (msg.what == 0) {
                if (viewWifiExcep == null) {
                    LayoutInflater layoutInflater = LayoutInflater.from(AKeyConfigActivity.this);
                    viewWifiExcep = layoutInflater.inflate(R.layout.popup_window_wifi_except, null);
                }
                if (m_pwWifiExcep == null) {
                    m_pwWifiExcep = new PopupWindow(viewWifiExcep, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
                }
                viewWifiExcep.findViewById(R.id.btnConfirm).setOnClickListener(new OnClickListener() {

                    public void onClick(View v) {
                        m_pwWifiExcep.dismiss();
                        finish();
                    }
                });

                m_pwWifiExcep.setFocusable(true);
                m_pwWifiExcep.showAtLocation(m_llParent, Gravity.CENTER, 0, 0);
            } else {
            }
        }
    };

    private void initUi() {
        findViewById(R.id.imgbtn_top_left).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
        m_llSetPwd = findViewById(R.id.llSetPwd);
        wps = findViewById(R.id.wps);
        m_llParent = findViewById(R.id.llContext);
        m_llParent.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });
        m_etSSID = findViewById(R.id.tvSSID);
        m_etPassWord = findViewById(R.id.etPWD);
        m_imgSelect = findViewById(R.id.imgSelectSSID);
        m_imgSelect.setOnClickListener(netSelect);
        m_imgShowPwd = findViewById(R.id.imgShowPwd);
        m_imgShowPwd.setOnClickListener(pwdShow);
        m_btnAdapter = findViewById(R.id.submit);
        m_btnAdapter.setOnClickListener(AKeyAdapter);
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        Log.d("wifiInfo", wifiInfo.toString());
    }

    private OnClickListener netSelect = new OnClickListener() {

        public void onClick(View arg0) {
            if (popupWindowWifi == null) {
                LayoutInflater layoutInflater = LayoutInflater.from(AKeyConfigActivity.this);
                popupWindowWifi = layoutInflater.inflate(R.layout.popup_window_wifi, null);
                m_lvWifi = popupWindowWifi.findViewById(R.id.lvModer);
            }
            if (m_pwWifiSelect == null) {
                m_pwWifiSelect = new PopupWindow(popupWindowWifi, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            }
            ((TextView) popupWindowWifi.findViewById(R.id.titles)).setText("???·???");
            WifiManager wifimanager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            if (!wifimanager.isWifiEnabled()) {
                wifimanager.setWifiEnabled(true);
            }
            List<ScanResult> list = wifimanager.getScanResults();
            List<HashMap<String, Object>> list_arr = new ArrayList<>();
            for (ScanResult result : list) {
                HashMap<String, Object> item = new HashMap<>();
                item.put("ssid", result.SSID);
                item.put("capabilities", result.capabilities);
                list_arr.add(item);
            }
            SimpleAdapter sa = new SimpleAdapter(AKeyConfigActivity.this, list_arr, R.layout.dialog_list, new String[]{"ssid"}, new int[]{R.id.ssid});
            m_lvWifi.setAdapter(sa);
            m_lvWifi.setOnItemClickListener(new OnItemClickListener() {

                public void onItemClick(AdapterView<?> adapter, View view, int position,
                                        long arg3) {

                    Object obj = adapter.getItemAtPosition(position);
                    int wpaStr = 0;
                    String typeStr = "";
                    Map map = (Map) obj;
                    m_etSSID.setText(map.get("ssid").toString());
                    Toast.makeText(AKeyConfigActivity.this, "????????????:" + map.get("ssid").toString(), Toast.LENGTH_SHORT).show();
                    typeStr = map.get("capabilities").toString().toUpperCase();
                    m_llSetPwd.setVisibility(View.INVISIBLE);
                    if (typeStr.indexOf("[WPA-PSK-CCMP]") != -1 && typeStr.indexOf("[WPA2-PSK-CCMP]") == -1) {
                        wpaStr = 4;
                    } else if (typeStr.indexOf("[WPA-PSK-TKIP]") != -1 && typeStr.indexOf("[WPA2-PSK-TKIP]") == -1) {
                        wpaStr = 3;
                    } else if (typeStr.indexOf("[WPA2-PSK-CCMP]") != -1 && typeStr.indexOf("[WPA-PSK-CCMP]") == -1) {
                        wpaStr = 6;
                    } else if (typeStr.indexOf("[WPA2-PSK-TKIP]") != -1 && typeStr.indexOf("[WPA-PSK-TKIP]") == -1) {
                        wpaStr = 5;
                    } else if (typeStr.indexOf("[WPA-PSK-CCMP][WPA2-PSK-CCMP]") != -1) {
                        wpaStr = 8;
                    } else if (typeStr.indexOf("[WPA-PSK-TKIP][WPA2-PSK-TKIP]") != -1) {
                        wpaStr = 7;
                    } else if (typeStr.indexOf("[WEP]") != -1) {
                        wpaStr = 1;
                    }
                    if (wpaStr == 0) {
                        Toast.makeText(AKeyConfigActivity.this, "?????????????????'" + typeStr + "'????????????.", Toast.LENGTH_SHORT).show();
                        m_llSetPwd.setVisibility(View.VISIBLE);
                    }
                    wps.setSelection(wpaStr, false);
                    m_pwWifiSelect.dismiss();
                }
            });
            m_pwWifiSelect.setFocusable(true);
            m_pwWifiSelect.showAtLocation(m_llParent, Gravity.CENTER, 0, 0);
        }
    };
    private String ssid = "";
    private String pwd = "";
    private OnClickListener AKeyAdapter = new OnClickListener() {

        public void onClick(View v) {
            ssid = m_etSSID.getText().toString();
            pwd = m_etPassWord.getText().toString();

            if (ssid.equals("")) {
                Toast.makeText(AKeyConfigActivity.this, "?????????", Toast.LENGTH_SHORT).show();
                return;
            }
            if (pwd.equals("")) {
                Toast.makeText(AKeyConfigActivity.this, "??????????", Toast.LENGTH_SHORT).show();
                return;
            }
            loading.show();
            loading.updateStatusText("???????...");
            String ssidv = "";
            String strwps = "";
            String strpdw = "";
            ssidv = ssid.trim();
            strwps = wps.getSelectedItem().toString().trim().toLowerCase();
            strwps = strwps.replace("/", "");
            strpdw = pwd.trim();
            WifiManager wifimsg = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            AKeyConfiguration aKey = new AKeyConfiguration(ssidv, strwps, strpdw, wifimsg);
            aKey.configuration();
        }
    };

    /**
     * 隐藏popwindow
     */
    public void hidePop(View v) {
        if (m_pwWifiSelect != null) {
            m_pwWifiSelect.dismiss();
        }
    }

    private boolean isNetWork = false;
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                info = connectivityManager.getActiveNetworkInfo();
                if (info != null && info.isAvailable()) {
                    if (info.getType() == ConnectivityManager.TYPE_WIFI && ssid.equals("")) {
                        isNetWork = true;
                    } else {
                        if (isNetWork) {
                            finish();
                            isNetWork = false;
                        }
                    }
                } else {
                    if (isNetWork) {
                        finish();
                        isNetWork = false;
                    }
                }
            }
        }
    };

    private OnClickListener pwdShow = new OnClickListener() {

        public void onClick(View v) {
            if (showPwd) {
                m_etPassWord.setTransformationMethod(PasswordTransformationMethod.getInstance());
                m_imgShowPwd.setImageResource(R.drawable.hint_pwd);
                showPwd = false;
            } else {
                m_etPassWord.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                m_imgShowPwd.setImageResource(R.drawable.show_pwd);
                showPwd = true;
            }
        }
    };

    /**
     * ???AuthMode
     */
    private String getAuthMode(String ssid) {
        mWifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (mWifiManager.isWifiEnabled()) {
            List<ScanResult> ScanResultlist = mWifiManager.getScanResults();
            for (int i = 0, len = ScanResultlist.size(); i < len; i++) {
                ScanResult AccessPoint = ScanResultlist.get(i);
                if (AccessPoint.SSID.equals(ssid)) {
                    boolean WpaPsk = AccessPoint.capabilities.contains("WPA-PSK");
                    boolean Wpa2Psk = AccessPoint.capabilities.contains("WPA2-PSK");
                    boolean Wpa = AccessPoint.capabilities.contains("WPA-EAP");
                    boolean Wpa2 = AccessPoint.capabilities.contains("WPA2-EAP");
                    if (AccessPoint.capabilities.contains("WEP")) {
                        mAuthMode = AuthModeOpen;
                        sAuthMode = "WEP";
                        break;
                    }
                    if (WpaPsk && Wpa2Psk) {
                        mAuthMode = AuthModeWPA1PSKWPA2PSK;
                        sAuthMode = "WPA-PSK??WPA2-PSK";
                        break;
                    } else if (Wpa2Psk) {
                        mAuthMode = AuthModeWPA2PSK;
                        sAuthMode = "WPA2-PSK";
                        break;
                    } else if (WpaPsk) {
                        mAuthMode = AuthModeWPAPSK;
                        sAuthMode = "WPA-PSK";
                        break;
                    }

                    if (Wpa && Wpa2) {
                        mAuthMode = AuthModeWPA1WPA2;
                        sAuthMode = "WPA-EAP??WPA2-EAP";
                        break;
                    } else if (Wpa2) {
                        mAuthMode = AuthModeWPA2;
                        sAuthMode = "WPA2-EAP";
                        break;
                    } else if (Wpa) {
                        mAuthMode = AuthModeWPA;
                        sAuthMode = "WPA-EAP";
                        break;
                    }
                }
            }
        }
        return String.valueOf(mAuthMode);
    }

    private int getAndroidSDKVersion() {
        int version = 0;
        try {
            version = Integer.valueOf(android.os.Build.VERSION.SDK);
        } catch (NumberFormatException e) {
        }
        return version;
    }

    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBroadcastReceiver);
    }

}
