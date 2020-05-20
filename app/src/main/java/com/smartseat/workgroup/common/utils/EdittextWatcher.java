package com.smartseat.workgroup.common.utils;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.smartseat.workgroup.R;

/**
 * @author jx
 * Edittext输入框监听按钮状态变化
 */
public class EdittextWatcher implements TextWatcher {
    //上下文环境
    Context mContext;
    //用户名
    EditText mEtUserName;
    //密码
    EditText mEtPassword;
    //手机号
    EditText mEtPhone;
    //验证码
    EditText mEtVertifyCode;
    //设置服务器地址
    EditText mEtSettingServiceAddress;
    //设置服务器端口号
    EditText mEtSettingServicePort;
    ImageView mIvAccountNameClose;
    ImageView mIvPasswordClose;
    ImageView mIvPasswordCloseEye;
    //登录按钮
    Button mButton;
    private int mFlagType;

    public EdittextWatcher(Context context, int flagType, ImageView ivAccountNameClose, ImageView ivPasswordClose, ImageView ivPasswordCloseEye, EditText etUserName, EditText etPassword, EditText etPhone, EditText etVertifyCode, Button button) {
        mContext = context;
        mFlagType = flagType;
        mIvAccountNameClose = ivAccountNameClose;
        mIvPasswordClose = ivPasswordClose;
        mIvPasswordCloseEye = ivPasswordCloseEye;
        mEtUserName = etUserName;
        mEtPassword = etPassword;
        mEtPhone = etPhone;
        mEtVertifyCode = etVertifyCode;
        mButton = button;
    }

    public EdittextWatcher(Context context, int flagType, EditText etSettingServiceAddress, EditText etSettingServicePort, Button button) {
        mContext = context;
        mFlagType = flagType;
        mEtSettingServiceAddress = etSettingServiceAddress;
        mEtSettingServicePort = etSettingServicePort;
        mButton = button;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count,
                                  int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable edit) {
        if (mFlagType == 1) {
            if (mEtUserName != null && mEtPassword != null) {
                if (mEtUserName.getText().toString().length() > 0 && mEtPassword.getText().toString().length() > 0) {//登录账号
                    setBtnSelectedStatus();
                } else {
                    setBtnUnSelectedStatus();
                }
                if (mEtUserName.getText().toString().length() > 0) {//用户名
                    mIvAccountNameClose.setVisibility(View.VISIBLE);
                } else {
                    mIvAccountNameClose.setVisibility(View.GONE);
                }
                if (mEtPassword.getText().toString().length() > 0) {//密码
                    mIvPasswordClose.setVisibility(View.VISIBLE);
                    mIvPasswordCloseEye.setVisibility(View.VISIBLE);
                } else {
                    mIvPasswordClose.setVisibility(View.GONE);
                    mIvPasswordCloseEye.setVisibility(View.GONE);
                }
            }
        }
        if (mFlagType == 2) {
            if (mEtPhone != null && mEtVertifyCode != null) {
                if (mEtPhone.getText().toString().length() > 0 && mEtVertifyCode.getText().toString().length() > 0) {//手机号登录
                    setBtnSelectedStatus();
                } else {
                    setBtnUnSelectedStatus();
                }
            }
        }
        if (mFlagType == 3) {
            if (mEtSettingServiceAddress != null && mEtSettingServicePort != null) {
                if (mEtSettingServiceAddress.getText().toString().length() > 0 && mEtSettingServicePort.getText().toString().length() > 0) {
                    setBtnSelectedStatus();
                } else {
                    setBtnUnSelectedStatus();
                }
            }
        }
    }

    /**
     * 设置按钮选中状态
     */
    private void setBtnSelectedStatus() {
        mButton.setClickable(true);
        mButton.setEnabled(true);
        mButton.setBackgroundResource(R.drawable.shape_login_select_button_corner);
    }

    /**
     * 设置按钮未选中状态
     */
    private void setBtnUnSelectedStatus() {
        mButton.setClickable(false);
        mButton.setEnabled(false);
        mButton.setBackgroundResource(R.drawable.shape_login_unselect_button_corner);
    }
}
