package com.smartseat.workgroup.main.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.smartseat.workgroup.R;
import com.smartseat.workgroup.common.activity.HMBaseActivity;

/**
 * @author jx 服务协议条款页面
 */
public class HMServiceProtocalActivity extends HMBaseActivity {

    //返回按键
    private RelativeLayout mRlBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_protocal);
        initView();
        initEvent();
    }

    private void initView() {
        mRlBack = findViewById(R.id.rl_userprotocol_back);
    }

    private void initEvent() {
        mRlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
