
package com.smartseat.workgroup.main.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.smartseat.workgroup.R;
import com.smartseat.workgroup.common.activity.HMBaseActivity;
import com.smartseat.workgroup.common.event.InitSwitchEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * @author jx
 * 设置页面
 */
public class SettingActivity extends HMBaseActivity {

    //退出登录布局
    private RelativeLayout mRlExitLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
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
        mRlExitLogin = findViewById(R.id.rel_exit);
    }

    protected void iniData() {

    }

    protected void iniEvent() {
        mRlExitLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new InitSwitchEvent.exitLogin());
                finish();
            }
        });
    }
}

