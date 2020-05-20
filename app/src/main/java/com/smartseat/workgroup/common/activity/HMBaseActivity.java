package com.smartseat.workgroup.common.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.smartseat.workgroup.common.utils.ExitAppUtils;

public abstract class HMBaseActivity extends FragmentActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ExitAppUtils.getInstance().addActivity(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ExitAppUtils.getInstance().delActivity(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

}
