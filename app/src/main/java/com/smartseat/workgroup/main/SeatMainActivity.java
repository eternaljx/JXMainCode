package com.smartseat.workgroup.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RelativeLayout;

import com.smartseat.workgroup.R;
import com.smartseat.workgroup.common.event.InitSwitchEvent;
import com.smartseat.workgroup.common.utils.SPUtils;
import com.smartseat.workgroup.main.fragment.AdjustFragment;
import com.smartseat.workgroup.main.fragment.FunctionFragment;
import com.smartseat.workgroup.main.fragment.MainFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * 智能座椅主页面
 *
 * @author jx
 */
public class SeatMainActivity extends FragmentActivity {

    //底部功能布局控件
    private RelativeLayout mRlBottomFunction;
    //底部首页布局控件
    private RelativeLayout mRlBottomMain;
    //底部调节布局控件
    private RelativeLayout mRlBottomAdjust;
    //底部功能横线布局控件
    private View mViewFunctionLine;
    //底部首页横线布局控件
    private View mViewMainLine;
    //底部调节横线布局控件
    private View mViewAdjustLine;
    private FragmentTransaction transaction;
    //调节页面
    private AdjustFragment mAdjustFragment;
    //功能页面
    private FunctionFragment functionFragment;
    //首页页面
    private MainFragment mainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_main);
        EventBus.getDefault().register(this);
        initView();
        initData();
        initEvent();
    }

    private void initView() {
        mRlBottomFunction = findViewById(R.id.rl_bottom_one);
        mRlBottomMain = findViewById(R.id.rl_bottom_two);
        mRlBottomAdjust = findViewById(R.id.rl_bottom_three);
        mViewFunctionLine = findViewById(R.id.v_function_line);
        mViewMainLine = findViewById(R.id.v_main_line);
        mViewAdjustLine = findViewById(R.id.v_adjust_line);
    }

    private void initData() {
        //添加初始Fragment
        defaultFragment(null == mAdjustFragment ? mAdjustFragment = AdjustFragment.newInstance() : mAdjustFragment);
        mViewMainLine.setVisibility(View.VISIBLE);
        mViewFunctionLine.setVisibility(View.GONE);
        mViewAdjustLine.setVisibility(View.GONE);
        if (null == mainFragment) {
            mainFragment = MainFragment.newInstance();
        }
        replaceFragment(mainFragment);
    }

    private void initEvent() {
        //点击功能事件
        mRlBottomFunction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewMainLine.setVisibility(View.GONE);
                mViewFunctionLine.setVisibility(View.VISIBLE);
                mViewAdjustLine.setVisibility(View.GONE);
                if (null == functionFragment) {
                    functionFragment = FunctionFragment.newInstance();
                }
                replaceFragment(functionFragment);
            }
        });
        //点击首页事件
        mRlBottomMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewMainLine.setVisibility(View.VISIBLE);
                mViewFunctionLine.setVisibility(View.GONE);
                mViewAdjustLine.setVisibility(View.GONE);
                if (null == mainFragment) {
                    mainFragment = MainFragment.newInstance();
                }
                replaceFragment(mainFragment);
            }
        });
        //点击调节事件
        mRlBottomAdjust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewMainLine.setVisibility(View.GONE);
                mViewFunctionLine.setVisibility(View.GONE);
                mViewAdjustLine.setVisibility(View.VISIBLE);
                if (null == mAdjustFragment) {
                    mAdjustFragment = AdjustFragment.newInstance();
                }
                replaceFragment(mAdjustFragment);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        transaction = fm.beginTransaction();
        transaction.replace(R.id.main_fragment_layout, fragment);
//        transaction.hide();
        transaction.commit();

    }

    private void defaultFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        transaction = fm.beginTransaction();
        transaction.add(R.id.main_fragment_layout, fragment);
        transaction.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void exitLogin(InitSwitchEvent.exitLogin event) {
        SPUtils.remove(SeatMainActivity.this, "isLogin");
        finish();
       /* Intent toLoginPage = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(toLoginPage);*/
    }

}
