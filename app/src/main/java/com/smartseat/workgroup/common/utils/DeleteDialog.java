package com.smartseat.workgroup.common.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.smartseat.workgroup.R;
import com.smartseat.workgroup.common.event.InitSwitchEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * @author jx
 * 删除弹框布局
 */

public class DeleteDialog extends Dialog {

    //上下文环境
    private Context mContext;
    //取消
    private TextView mTvCancel;
    //确定
    private TextView mTvConfirm;

    public DeleteDialog(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        //去除标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //去除边框
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.activity_setting_service_delete_dialog, null);
        setContentView(view);
        //设置点击屏幕不消失
        setCanceledOnTouchOutside(false);
        //设置点击返回键不消失
        setCancelable(false);
        mTvCancel = view.findViewById(R.id.tv_cancel);
        mTvConfirm = view.findViewById(R.id.tv_confirm);
    }

    private void initData() {
        //確定点击事件
        mTvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                EventBus.getDefault().post(new InitSwitchEvent.deleteSingleListData());
            }
        });
        //取消点击事件
        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

}
