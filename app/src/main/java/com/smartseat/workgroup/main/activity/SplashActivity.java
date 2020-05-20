
package com.smartseat.workgroup.main.activity;

import android.content.Intent;
import android.os.Bundle;

import com.smartseat.workgroup.R;
import com.smartseat.workgroup.common.activity.HMBaseActivity;
import com.smartseat.workgroup.common.utils.SPUtils;
import com.smartseat.workgroup.main.SeatMainActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author jx
 * 启动页
 */
public class SplashActivity extends HMBaseActivity {

    //是否已经登录
    private boolean mIsLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        iniView();
        iniData();
        iniEvent();
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

    protected void iniView() {

    }

    protected void iniData() {
        mIsLogin = (boolean) SPUtils.get(SplashActivity.this, "isLogin", false);
        //2秒自动跳转到登录或者首页，通过判断是否已登录
        final Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (mIsLogin) {
                            // 用户已经登陆
                            Intent mainIntent = new Intent(SplashActivity.this, SeatMainActivity.class);
                            startActivity(mainIntent);
                            finish();
                        } else {
                            //跳转主页面
                            Intent loginIntent = new Intent(SplashActivity.this, SeatMainActivity.class);
                            startActivity(loginIntent);
                            finish();
                        }
                    }
                });
            }
        };
        timer.schedule(timerTask, 2000);
    }

    protected void iniEvent() {

    }
}

