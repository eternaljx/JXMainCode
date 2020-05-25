package com.smartseat.workgroup.main.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smartseat.workgroup.R;

/**
 * 调节页面
 *
 * @author jx
 */
public class AdjustFragment extends Fragment {

    //点击靠背view事件
    private View mViewKaobeiClick;
    //靠背图片布局
    private View mViewKaobeiSelected;
    //靠背后调节布局
    private View mViewKaobeiBefore;
    //靠背前调节选中布局
    private View mViewKaobeiAfter;
    //头托view事件
    private View mViewToutuoClick;
    //头托选中背景view图片布局
    private View mViewToutuoSelected;
    //头托向上调节view布局
    private View mViewToutuoTop;
    //头托向下调节view布局
    private View mViewToutuoBottom;
    //腰托view事件
    private View mViewYaotuoClick;
    //腰托选中背景view图片布局
    private View mViewYaotuoSelected;
    //腰托向上调节view布局
    private View mViewYaotuoTop;
    //腰托向下调节view布局
    private View mViewYaotuoBottom;
    //腰托向左调节view布局
    private View mViewYaotuoLeft;
    //腰托向右调节view布局
    private View mViewYaotuoRight;
    //腿托view事件
    private View mViewTuituoClick;
    //腿托选中背景view图片布局
    private View mViewTuituoSelected;
    //腿托向前调节view布局
    private View mViewTuituoBefore;
    //腿托向后调节view布局
    private View mViewTuituoAfter;
    //座椅前后view事件
    private View mViewQianhouClick;
    //座椅前后选中背景view图片布局
    private View mViewQianhouSelected;
    //座椅向前调节view布局
    private View mViewQianhouBefore;
    //座椅向后调节view布局
    private View mViewQianhouAfter;


    public static AdjustFragment newInstance() {
        return new AdjustFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //通过参数中的布局填充获取对应布局
        View view = inflater.inflate(R.layout.activity_adjust_fragment, container, false);
        initView(view);
        initEvent();
        return view;

    }

    private void initView(View view) {
        mViewKaobeiClick = view.findViewById(R.id.v_kaobei_click);
        mViewKaobeiSelected = view.findViewById(R.id.iv_kaobei_selected);
        mViewKaobeiBefore = view.findViewById(R.id.iv_kaobei_before_white_view);
        mViewKaobeiAfter = view.findViewById(R.id.iv_kaobei_after_view);
        mViewToutuoClick = view.findViewById(R.id.v_toutuo_click);
        mViewToutuoSelected = view.findViewById(R.id.iv_toutuo_selected);
        mViewToutuoTop = view.findViewById(R.id.iv_toutuo_top_view);
        mViewToutuoBottom = view.findViewById(R.id.iv_toutuo_bottom_view);
        mViewYaotuoClick = view.findViewById(R.id.v_yaotuo_click);
        mViewYaotuoSelected = view.findViewById(R.id.iv_yaotuo_selected);
        mViewYaotuoTop = view.findViewById(R.id.iv_yaotuo_top_view);
        mViewYaotuoBottom = view.findViewById(R.id.iv_yaotuo_bottom_view);
        mViewYaotuoLeft = view.findViewById(R.id.iv_yaotuo_left_view);
        mViewYaotuoRight = view.findViewById(R.id.iv_yaotuo_right_view);
        mViewTuituoClick = view.findViewById(R.id.v_tuituo_click);
        mViewTuituoSelected = view.findViewById(R.id.iv_tuituo_selected);
        mViewTuituoBefore = view.findViewById(R.id.iv_tuituo_before_view);
        mViewTuituoAfter = view.findViewById(R.id.iv_tuituo_after_view);
        mViewQianhouClick = view.findViewById(R.id.v_qianhou_click);
        mViewQianhouSelected = view.findViewById(R.id.iv_qianhou_selected);
        mViewQianhouBefore = view.findViewById(R.id.iv_qianhou_before_view);
        mViewQianhouAfter = view.findViewById(R.id.iv_qianhou_after_view);
    }

    private void initEvent() {
        //点击靠背事件，显示前后调节布局（前后都是白色）
        mViewKaobeiClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showKaobeiBeforeAndAfterView();
            }
        });
        //点击后调节事件，后调节按钮更换成选中图片、靠背按钮也变成选中图片
        mViewKaobeiBefore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showKaobeiBeforeView();
            }
        });
        //点击前调节事件，前调节按钮更换成选中图片，靠背按钮也变成选中图片
        mViewKaobeiAfter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showKaobeiAfterView();
            }
        });
        //点击头枕事件，显示向上向下调节布局（上下都是白色）
        mViewToutuoClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTouzhenTopAndBottomView();
            }
        });
        //点击头枕向上调节
        mViewToutuoTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTouzhenTopView();
            }
        });
        //点击头枕向下调节
        mViewToutuoBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTouzhenBottomView();
            }
        });
        //点击腰托事件，显示向上、向下、向左、向右布局（显示白色）
        mViewYaotuoClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showYaotuoClickView();
            }
        });
        //点击腰托向上调节
        mViewYaotuoTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showYaotuoTopView();
            }
        });
        //点击腰托向下调节
        mViewYaotuoBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showYaotuoBottomView();
            }
        });
        //点击腰托向左调节
        mViewYaotuoLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showYaotuoLeftView();
            }
        });
        //点击腰托向右调节
        mViewYaotuoRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showYaotuoRightView();
            }
        });
        //点击腿托view事件，显示向前调节和向后调节布局（显示白色）
        mViewTuituoClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTuituoView();
            }
        });
        //点击腿托向前view布局
        mViewTuituoBefore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTuituoBeforeView();
            }
        });
        //显示腿托向后view布局
        mViewTuituoAfter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTuituoAfterView();
            }
        });
        //点击座椅前后事件，显示向前和向后布局（显示白色）
        mViewQianhouClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showQianhouView();
            }
        });
        //点击座椅向前事件
        mViewQianhouBefore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showQianhouBeforeView();
            }
        });
        //点击座椅向后事件
        mViewQianhouAfter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showQianhouAfterView();
            }
        });
    }

    /**
     * 显示前后调节靠背布局样式
     */
    private void showKaobeiBeforeAndAfterView() {
        mViewKaobeiBefore.setVisibility(View.VISIBLE);
        mViewKaobeiAfter.setVisibility(View.VISIBLE);
    }

    /**
     * 显示向前调节靠背选中样式布局
     */
    private void showKaobeiBeforeView() {
        mViewKaobeiSelected.setVisibility(View.VISIBLE);
        mViewKaobeiBefore.setBackgroundResource(R.drawable.icon_kaobei_before_selected_h730);
    }

    /**
     * 显示向后调节靠背选中样式布局
     */
    private void showKaobeiAfterView() {
        mViewKaobeiSelected.setVisibility(View.VISIBLE);
        mViewKaobeiAfter.setBackgroundResource(R.drawable.icon_kaobei_after_selected);
    }

    /**
     * 显示头枕向上调节和向下调节布局
     */
    private void showTouzhenTopAndBottomView() {
        mViewToutuoTop.setVisibility(View.VISIBLE);
        mViewToutuoBottom.setVisibility(View.VISIBLE);
    }

    /**
     * 显示头枕向上调节布局
     */
    private void showTouzhenTopView() {
        mViewToutuoSelected.setVisibility(View.VISIBLE);
        mViewToutuoTop.setBackgroundResource(R.drawable.icon_toutuo_top_selected_h600);
    }

    /**
     * 显示头枕向下调节布局
     */
    private void showTouzhenBottomView() {
        mViewToutuoSelected.setVisibility(View.VISIBLE);
        mViewToutuoBottom.setBackgroundResource(R.drawable.icon_toutuo_bottom_selected_h600);
    }

    /**
     * 显示腰托向上、向下、向左、向右布局
     */
    private void showYaotuoClickView() {
        mViewYaotuoTop.setVisibility(View.VISIBLE);
        mViewYaotuoBottom.setVisibility(View.VISIBLE);
        mViewYaotuoLeft.setVisibility(View.VISIBLE);
        mViewYaotuoRight.setVisibility(View.VISIBLE);
    }

    /**
     * 显示腰托向上调节布局
     */
    private void showYaotuoTopView() {
        mViewYaotuoSelected.setVisibility(View.VISIBLE);
        mViewYaotuoTop.setBackgroundResource(R.drawable.icon_yaotuo_top_selected_h600);
    }

    /**
     * 显示腰托向下调节布局
     */
    private void showYaotuoBottomView() {
        mViewYaotuoSelected.setVisibility(View.VISIBLE);
        mViewYaotuoBottom.setBackgroundResource(R.drawable.icon_yaotuo_bottom_selected_h600);
    }

    /**
     * 显示腰托向左调节布局
     */
    private void showYaotuoLeftView() {
        mViewYaotuoSelected.setVisibility(View.VISIBLE);
        mViewYaotuoLeft.setBackgroundResource(R.drawable.icon_yaotuo_left_selected_h600);
    }

    /**
     * 显示腰托向右调节布局
     */
    private void showYaotuoRightView() {
        mViewYaotuoSelected.setVisibility(View.VISIBLE);
        mViewYaotuoRight.setBackgroundResource(R.drawable.icon_yaotuo_right_selected_h600);
    }

    /**
     * 显示腿托view布局
     */
    private void showTuituoView() {
        mViewTuituoBefore.setVisibility(View.VISIBLE);
        mViewTuituoAfter.setVisibility(View.VISIBLE);
    }

    /**
     * 显示腿托向前调节view布局
     */
    private void showTuituoBeforeView() {
        mViewTuituoSelected.setVisibility(View.VISIBLE);
        mViewTuituoBefore.setBackgroundResource(R.drawable.icon_tuituo_before_selected);
    }

    /**
     * 显示腿托向后调节view布局
     */
    private void showTuituoAfterView() {
        mViewTuituoSelected.setVisibility(View.VISIBLE);
        mViewTuituoAfter.setBackgroundResource(R.drawable.icon_tuituo_after_selected);
    }

    /**
     * 显示座椅前后view布局
     */
    private void showQianhouView() {
        mViewQianhouBefore.setVisibility(View.VISIBLE);
        mViewQianhouAfter.setVisibility(View.VISIBLE);
    }

    /**
     * 显示座椅向前调节view布局
     */
    private void showQianhouBeforeView() {
        mViewQianhouSelected.setVisibility(View.VISIBLE);
        mViewQianhouBefore.setBackgroundResource(R.drawable.icon_qianhou_before_selected);

    }

    /**
     * 显示座椅向后调节viewbuju
     */
    private void showQianhouAfterView() {
        mViewQianhouSelected.setVisibility(View.VISIBLE);
        mViewQianhouAfter.setBackgroundResource(R.drawable.icon_qianhou_after_selected);
    }

    /**
     * 功能失效方法
     */
    private void functionInvalid() {
        mViewKaobeiClick.setVisibility(View.GONE);
        mViewKaobeiSelected.setVisibility(View.GONE);
        mViewKaobeiBefore.setVisibility(View.GONE);
        mViewKaobeiAfter.setVisibility(View.GONE);
        mViewToutuoClick.setVisibility(View.GONE);
        mViewToutuoSelected.setVisibility(View.GONE);
        mViewToutuoTop.setVisibility(View.GONE);
        mViewToutuoBottom.setVisibility(View.GONE);
        mViewYaotuoClick.setVisibility(View.GONE);
        mViewYaotuoSelected.setVisibility(View.GONE);
        mViewYaotuoTop.setVisibility(View.GONE);
        mViewYaotuoBottom.setVisibility(View.GONE);
        mViewYaotuoLeft.setVisibility(View.GONE);
        mViewYaotuoRight.setVisibility(View.GONE);
        mViewTuituoClick.setVisibility(View.GONE);
        mViewTuituoSelected.setVisibility(View.GONE);
        mViewTuituoBefore.setVisibility(View.GONE);
        mViewTuituoAfter.setVisibility(View.GONE);
        mViewQianhouClick.setVisibility(View.GONE);
        mViewQianhouSelected.setVisibility(View.GONE);
        mViewQianhouBefore.setVisibility(View.GONE);
        mViewQianhouAfter.setVisibility(View.GONE);
    }

}