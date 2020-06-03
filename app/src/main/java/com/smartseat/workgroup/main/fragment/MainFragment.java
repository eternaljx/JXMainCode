package com.smartseat.workgroup.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.smartseat.workgroup.R;
import com.smartseat.workgroup.common.utils.FunctionClickUtils;
import com.smartseat.workgroup.common.utils.SPUtils;
import com.smartseat.workgroup.main.activity.ConnectionDeviceActivity;

/**
 * 首页页面
 *
 * @author jx
 */
public class MainFragment extends Fragment {

    //主背景布局控件
    private ImageView mIvMainBg;
    //设置控件按钮
    private ImageView mIvSetting;
    //pop设置弹框布局
    private RelativeLayout mRlPopLayout;
    //睡眠模式布局控件
    private RelativeLayout mRlSleepModel;
    //一键复位模式布局控件
    private RelativeLayout mRlOneKeyResetModel;
    //睡眠模式图片
    private ImageView mIvSleepModel;
    //睡眠模式选中图片
    private ImageView mIvSleepModelSelected;
    //睡眠模式中文名称
    private TextView mTvSleepModelTitle;
    //睡眠模式英文名称
    private TextView mTvSleepModelEnglishName;
    //一键复位模式图片
    private ImageView mIvOneKeyResetModel;
    //一键复位模式中文名称
    private TextView mTvOneKeyResetModelTitle;
    //一键复位模式英文名称
    private TextView mTvOneKeyResetEnglishName;
    //设置弹框--睡眠模式存储未选中图片控件
    private ImageView mIvSleepModelSave;
    //设置弹框--睡眠模式存储选中图片控件
    private ImageView mIvSleepModelSaveSelected;
    //设置弹框--睡眠模式清除未选中图片控件
    private ImageView mIvSleepModelDelete;
    //设置弹框--睡眠模式清除选中图片控件
    private ImageView mIvSleepModelDeleteSelected;
    //设置弹框--一键复位模式存储未选中图片控件
    private ImageView mIvOneKeyResetModelSave;
    //设置弹框--一键复位模式存储选中图片控件
    private ImageView mIvOneKeyResetModelSaveSelected;
    //设置弹框--一键复位模式清除未选中图片控件
    private ImageView mIvOneKeyResetModelDelete;
    //设置弹框--一键复位模式清除选中图片控件
    private ImageView mIvOneKeyResetModelDeleteSelected;
    //是否登录过
    private boolean mIsLogin;
    //用户名
    private String mUserName;
    //是否开启睡眠模式标识
    private boolean mIsOpenSleepModel;
    //是否开启一键复位模式标识
    private boolean mIsOpenOneKeyResetModel;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        //通过参数中的布局填充获取对应布局
        View view = inflater.inflate(R.layout.activity_main_fragment, container, false);
        initView(view);
        initData();
        initEvent();
        return view;
    }

    private void initView(View view) {
        mIvMainBg = view.findViewById(R.id.iv_main_fragment);
        mIvSetting = view.findViewById(R.id.iv_setting);
        mRlPopLayout = view.findViewById(R.id.rl_setting_pop);
        mRlSleepModel = view.findViewById(R.id.rl_sleep_model);
        mIvSleepModel = view.findViewById(R.id.iv_sleep_model);
        mIvSleepModelSelected = view.findViewById(R.id.iv_sleep_model_selected);
        mTvSleepModelTitle = view.findViewById(R.id.tv_sleep_model_title);
        mTvSleepModelEnglishName = view.findViewById(R.id.tv_sleep_model_english_name);
        mRlOneKeyResetModel = view.findViewById(R.id.rl_one_keyreset);
        mIvOneKeyResetModel = view.findViewById(R.id.iv_one_keyreset);
        mTvOneKeyResetModelTitle = view.findViewById(R.id.tv_one_keyreset_title);
        mTvOneKeyResetEnglishName = view.findViewById(R.id.tv_one_keyreset_english_name);
        mIvSleepModelSave = view.findViewById(R.id.iv_sleep_model_save);
        mIvSleepModelSaveSelected = view.findViewById(R.id.iv_sleep_model_save_selected);
        mIvSleepModelDelete = view.findViewById(R.id.iv_sleep_model_delete);
        mIvSleepModelDeleteSelected = view.findViewById(R.id.iv_sleep_model_delete_selected);
        mIvOneKeyResetModelSave = view.findViewById(R.id.iv_onekeyreset_save);
        mIvOneKeyResetModelSaveSelected = view.findViewById(R.id.iv_onekeyreset_save_selected);
        mIvOneKeyResetModelDelete = view.findViewById(R.id.iv_onekeyreset_delete);
        mIvOneKeyResetModelDeleteSelected = view.findViewById(R.id.iv_onekeyreset_delete_selected);
    }

    private void initData() {
        mIsLogin = (boolean) SPUtils.get(getContext(), "isLogin", false);
        mUserName = (String) SPUtils.get(getContext(), "username", "");
        mIsOpenSleepModel = (boolean) SPUtils.get(getContext(), "openSleep", false);
        mIsOpenOneKeyResetModel = (boolean) SPUtils.get(getContext(), "oneKeyRest", false);
        if (mIsOpenSleepModel) {//判断时候开启睡眠模式
            setSleepModelSelectedStatus();
        } else {
            setSleepModelUnSelectedStatus();
        }
        if (mIsOpenOneKeyResetModel) {//判断时候开启一键复位模式
            setOnKeyResetModelSelected();
        } else {
            setOnKeyResetModelUnSelected();
        }
    }

    private void initEvent() {

        mRlPopLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //点击设置事件
        mIvSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRlPopLayout.setVisibility(View.VISIBLE);
                mIvSetting.setVisibility(View.GONE);
            }
        });
        //点击屏幕，隐藏pop弹框，显示设置按钮
        mIvMainBg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRlPopLayout.setVisibility(View.GONE);
                mIvSetting.setVisibility(View.VISIBLE);
            }
        });
        //点击睡眠模式
        mRlSleepModel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsLogin) {
                    //弹出pop设置窗口，同时点亮设置里的睡眠模式
                    FunctionClickUtils.getInstance().isOpenSleepModel(true);
                    setSleepModelSelectedStatus();
                    SPUtils.saveSleepModelData(getContext(), mUserName, FunctionClickUtils.getInstance().getOpenSleepStatus());
                    mRlPopLayout.setVisibility(View.VISIBLE);
                    mIvSetting.setVisibility(View.GONE);
                } else {
                    Intent toLoginPage = new Intent(getContext(), ConnectionDeviceActivity.class);
                    startActivity(toLoginPage);
                }
            }
        });
        //点击一键复位模式
        mRlOneKeyResetModel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsLogin) {
                    //弹出pop设置窗口，同时点亮设置里的一键复位模式
                    FunctionClickUtils.getInstance().isOpenOneKeyResetModel(true);
                    setOnKeyResetModelSelected();
                    SPUtils.saveOneKeyResetData(getContext(), mUserName, FunctionClickUtils.getInstance().getOpenOneKeyResetModel());
                    mRlPopLayout.setVisibility(View.VISIBLE);
                    mIvSetting.setVisibility(View.GONE);
                } else {
                    Intent toLoginPage = new Intent(getContext(), ConnectionDeviceActivity.class);
                    startActivity(toLoginPage);
                }
            }
        });
        //点击设置弹框--存储睡眠模式
        mIvSleepModelSave.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setSleepModelSelectedStatus();
                FunctionClickUtils.getInstance().isOpenSleepModel(true);
                //存储睡眠模式
                SPUtils.saveSleepModelData(getContext(), mUserName,  FunctionClickUtils.getInstance().getOpenSleepStatus());
                return false;
            }
        });
        //点击设置弹框--清除睡眠模式
        mIvSleepModelDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FunctionClickUtils.getInstance().isOpenSleepModel(false);
                mIvSleepModelSave.setVisibility(View.VISIBLE);
                mIvSleepModelSaveSelected.setVisibility(View.GONE);
                mIvSleepModelDelete.setVisibility(View.GONE);
                mIvSleepModelDeleteSelected.setVisibility(View.VISIBLE);
                setSleepModelUnSelectedStatus();
                //清除睡眠模式
                SPUtils.remove(getContext(), "openSleep");
            }
        });
        //点击设置弹框--存储一键复位模式
        mIvOneKeyResetModelSave.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setOnKeyResetModelSelected();
                //存储一键复位模式
                FunctionClickUtils.getInstance().isOpenOneKeyResetModel(true);
                SPUtils.saveOneKeyResetData(getContext(), mUserName, FunctionClickUtils.getInstance().getOpenOneKeyResetModel());
                return false;
            }
        });
        //点击设置弹框--清除一键复位模式
        mIvOneKeyResetModelDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FunctionClickUtils.getInstance().isOpenOneKeyResetModel(false);
                mIvOneKeyResetModelSave.setVisibility(View.VISIBLE);
                mIvOneKeyResetModelSaveSelected.setVisibility(View.GONE);
                mIvOneKeyResetModelDelete.setVisibility(View.GONE);
                mIvOneKeyResetModelDeleteSelected.setVisibility(View.VISIBLE);
                setOnKeyResetModelUnSelected();
                //清除一键复位模式
                SPUtils.remove(getContext(), "oneKeyRest");
            }
        });
    }

    /**
     * 睡眠模式未选中状态样式
     */
    private void setSleepModelUnSelectedStatus() {
        mRlSleepModel.setBackgroundResource(R.drawable.shape_white_corner);
        mIvSleepModel.setVisibility(View.VISIBLE);
        mIvSleepModelSelected.setVisibility(View.GONE);
        mTvSleepModelTitle.setTextColor(getResources().getColor(R.color.white));
        mTvSleepModelEnglishName.setTextColor(getResources().getColor(R.color.white));

    }

    /**
     * 睡眠模式选中状态样式
     */
    private void setSleepModelSelectedStatus() {
        mRlSleepModel.setBackgroundResource(R.drawable.shape_orange_corner);
        mIvSleepModel.setVisibility(View.GONE);
        mIvSleepModelSelected.setVisibility(View.VISIBLE);
        mTvSleepModelTitle.setTextColor(getResources().getColor(R.color.orange));
        mTvSleepModelEnglishName.setTextColor(getResources().getColor(R.color.orange));
        mIvSleepModelSave.setVisibility(View.GONE);
        mIvSleepModelSaveSelected.setVisibility(View.VISIBLE);
        mIvSleepModelDelete.setVisibility(View.VISIBLE);
        mIvSleepModelDeleteSelected.setVisibility(View.GONE);
    }

    /**
     * 一键复位未选中状态样式
     */
    private void setOnKeyResetModelUnSelected() {
        mRlOneKeyResetModel.setBackgroundResource(R.drawable.shape_white_corner);
        mIvOneKeyResetModel.setBackgroundResource(R.drawable.icon_main_onekeyreset);
        mTvOneKeyResetModelTitle.setTextColor(getResources().getColor(R.color.white));
        mTvOneKeyResetEnglishName.setTextColor(getResources().getColor(R.color.white));
    }

    /**
     * 一键复位选中状态样式
     */
    private void setOnKeyResetModelSelected() {
        mRlOneKeyResetModel.setBackgroundResource(R.drawable.shape_orange_corner);
        mIvOneKeyResetModel.setBackgroundResource(R.drawable.icon_main_onekeyreset_selected);
        mTvOneKeyResetModelTitle.setTextColor(getResources().getColor(R.color.orange));
        mTvOneKeyResetEnglishName.setTextColor(getResources().getColor(R.color.orange));
        mIvOneKeyResetModelSave.setVisibility(View.GONE);
        mIvOneKeyResetModelSaveSelected.setVisibility(View.VISIBLE);
        mIvOneKeyResetModelDelete.setVisibility(View.VISIBLE);
        mIvOneKeyResetModelDeleteSelected.setVisibility(View.GONE);
    }
}
