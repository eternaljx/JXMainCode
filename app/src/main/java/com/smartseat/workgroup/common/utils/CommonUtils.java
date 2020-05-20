package com.smartseat.workgroup.common.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

public class CommonUtils {

    /**
     * 输入框点击显示键盘获取焦点
     *
     * @param activity
     * @param editText
     */
    public static void setEditTextOnClick(final Activity activity, EditText editText) {
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
            }
        });
    }

    /**
     * 判断软键盘是否弹出
     *
     * @return
     */
    public static boolean isSoftShowing(Activity activity) {
        //获取当前屏幕内容的高度
        int screenHeight = activity.getWindow().getDecorView().getHeight();
        //获取View可见区域的bottom
        Rect rect = new Rect();
        //DecorView即为activity的顶级view
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        //考虑到虚拟导航栏的情况（虚拟导航栏情况下：screenHeight = rect.bottom + 虚拟导航栏高度）
        //选取screenHeight*2/3进行判断
        return screenHeight * 2 / 3 > rect.bottom;
    }


    /**
     * 全局注册软键盘
     * @param activity
     * @param imageView
     */
    public static void setListenerToRootView(final Activity activity, final ImageView imageView, final String typeFlag) {
        final View rootView = activity.getWindow().getDecorView().findViewById(android.R.id.content);
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (CommonUtils.isSoftShowing(activity)) {
                    imageView.setVisibility(View.GONE);
                } else {
                    if(typeFlag!=null){
                        if(typeFlag.equals("add")){
                            imageView.setVisibility(View.VISIBLE);
                        }else if(typeFlag.equals("edit")||typeFlag.equals("select")){
                            imageView.setVisibility(View.GONE);
                        }else{
                            imageView.setVisibility(View.VISIBLE);
                        }
                    }else{
                        imageView.setVisibility(View.VISIBLE);
                    }
                }
                //如果只想检测一次，需要注销
                //rootView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    /**
     * 隐藏键盘
     * 弹窗弹出的时候把键盘隐藏掉
     */
    public static void hideInputKeyboard(Context context,View v) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

}
