package com.smartseat.workgroup.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smartseat.workgroup.R;
import com.smartseat.workgroup.common.utils.FunctionClickUtils;
import com.smartseat.workgroup.common.utils.SPUtils;
import com.smartseat.workgroup.main.activity.ConnectionDeviceActivity;
import com.smartseat.workgroup.main.model.AdjustModel;

import static com.smartseat.workgroup.common.utils.SPUtils.ADJUST_MODEL_KEY;

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
    //用户名
    private String username;
    //调节页面数据模型初始化
    private AdjustModel mAdjustModel;
    //是否打开靠背
    private boolean mIsOpenKaobei;
    //是否打开头枕
    private boolean mIsOpenTouzhen;
    //是否打开腰枕
    private boolean mIsOpenYaozhen;
    //是否打开腿托
    private boolean mIsOpenTuiTuo;
    //是否打开座椅前后
    private boolean mIsOpenQianhou;
    //靠背向前调节
    private int mKaobeiBeforeFlag;
    //靠背向后调节
    private int mKaobeiAfterFlag;
    //头枕向上调节
    private int mTouzhenTopFlag;
    //头枕向下调节
    private int mTouzhenBottomFlag;
    //腰枕向上调节
    private int mYaozhenTopFlag;
    //腰枕向下调节
    private int mYaozhenBottomFlag;
    //腰枕向左调节
    private int mYaozhenLeftFlag;
    //腰枕向右调节
    private int mYaozhenRightFlag;
    //腿托向前调节
    private int mTuituoBeforeFlag;
    //腿托向后调节
    private int mTuituoAfterFlag;
    //座椅向前调节
    private int mQianhouBeforeFlag;
    //座椅向后调节
    private int mQianhouAfterFlag;
    private boolean mIsLogin;


    public static AdjustFragment newInstance() {
        return new AdjustFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //通过参数中的布局填充获取对应布局
        View view = inflater.inflate(R.layout.activity_adjust_fragment, container, false);
        initView(view);
        initData();
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

    private void initData() {
        mIsLogin = (boolean) SPUtils.get(getContext(), "isLogin", false);
        username = (String) SPUtils.get(getContext(), "username", "");
        mAdjustModel = (AdjustModel) SPUtils.getAdjustModel(getContext(), username);
        if (mAdjustModel != null) {
            mIsOpenKaobei = mAdjustModel.isOpenKaobei();
            mIsOpenTouzhen = mAdjustModel.isOpenTouzhen();
            mIsOpenYaozhen = mAdjustModel.isOpenYaozhen();
            mIsOpenTuiTuo = mAdjustModel.isOpenTuituo();
            mIsOpenQianhou = mAdjustModel.isOpenQianhou();
            if (mIsOpenKaobei) {//打开靠背
                mKaobeiBeforeFlag = mAdjustModel.getKaobeiBeforeFlag();
                mKaobeiAfterFlag = mAdjustModel.getKaobeiAfterFlag();
                if (mKaobeiBeforeFlag == 1) {
                    showKaobeiBeforeView();
                    mViewKaobeiAfter.setVisibility(View.VISIBLE);
                }
                if (mKaobeiAfterFlag == 1) {
                    showKaobeiAfterView();
                }
            } else {
                mViewKaobeiBefore.setVisibility(View.GONE);
                mViewKaobeiAfter.setVisibility(View.GONE);
            }
            if (mIsOpenTouzhen) {//打开头枕
                mTouzhenTopFlag = mAdjustModel.getTouzhenTopFlag();
                mTouzhenBottomFlag = mAdjustModel.getTouzhenBottomFlag();
                if (mTouzhenTopFlag == 1) {
                    showTouzhenTopView();
                    mViewToutuoBottom.setVisibility(View.VISIBLE);
                }
                if (mTouzhenBottomFlag == 1) {
                    showTouzhenBottomView();
                }
            } else {
                mViewToutuoTop.setVisibility(View.GONE);
                mViewToutuoBottom.setVisibility(View.GONE);
            }
            if (mIsOpenYaozhen) {//打开腰枕
                mYaozhenTopFlag = mAdjustModel.getYaozhenTopFlag();
                mYaozhenBottomFlag = mAdjustModel.getYaozhenBottomFlag();
                mYaozhenLeftFlag = mAdjustModel.getYaozhenLeftFlag();
                mYaozhenRightFlag = mAdjustModel.getYaozhenRightFlag();
                if (mYaozhenTopFlag == 1) {
                    showYaotuoTopView();
                    mViewYaotuoBottom.setVisibility(View.VISIBLE);
                    mViewYaotuoLeft.setVisibility(View.VISIBLE);
                    mViewYaotuoRight.setVisibility(View.VISIBLE);
                }
                if (mYaozhenBottomFlag == 1) {
                    showYaotuoBottomView();
                }
                if (mYaozhenLeftFlag == 1) {
                    showYaotuoLeftView();
                }
                if (mYaozhenRightFlag == 1) {
                    showYaotuoRightView();
                }
            } else {
                mViewYaotuoTop.setVisibility(View.GONE);
                mViewYaotuoBottom.setVisibility(View.GONE);
                mViewYaotuoLeft.setVisibility(View.GONE);
                mViewYaotuoRight.setVisibility(View.GONE);
            }
            if (mIsOpenTuiTuo) {//打开腿托
                mTuituoBeforeFlag = mAdjustModel.getTuituoBeforeFlag();
                mTuituoAfterFlag = mAdjustModel.getTuituoAfterFlag();
                if (mTuituoBeforeFlag == 1) {
                    showTuituoBeforeView();
                    mViewTuituoAfter.setVisibility(View.VISIBLE);
                }
                if (mTuituoAfterFlag == 1) {
                    showTuituoAfterView();
                }
            } else {
                mViewTuituoBefore.setVisibility(View.GONE);
                mViewTuituoAfter.setVisibility(View.GONE);
            }
            if (mIsOpenQianhou) {//打开座椅前后
                mQianhouBeforeFlag = mAdjustModel.getQianhouBeforeFlag();
                mQianhouAfterFlag = mAdjustModel.getQianhouAfterFlag();
                if (mQianhouBeforeFlag == 1) {
                    showQianhouBeforeView();
                    mViewQianhouAfter.setVisibility(View.VISIBLE);
                }
                if (mQianhouAfterFlag == 1) {
                    showQianhouAfterView();
                }
            } else {
                mViewQianhouBefore.setVisibility(View.GONE);
                mViewQianhouAfter.setVisibility(View.GONE);
            }
        } else {
            mAdjustModel = new AdjustModel();
        }
    }

    private void initEvent() {
        //点击靠背事件，显示前后调节布局（前后都是白色）
        mViewKaobeiClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsLogin) {
                    showKaobeiBeforeAndAfterView();
                    //存储开启靠背调节
                    mAdjustModel.setOpenKaobei(true);
                    SPUtils.putAdjustModel(getContext(), username, mAdjustModel);
                } else {
                    Intent toLoginPage = new Intent(getContext(), ConnectionDeviceActivity.class);
                    startActivity(toLoginPage);
                }
            }
        });
        //点击后调节事件，后调节按钮更换成选中图片、靠背按钮也变成选中图片
        mViewKaobeiBefore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showKaobeiBeforeView();
                //存储靠背向后节
                FunctionClickUtils.getInstance().setmKaobeiBeforeSwitch(1);
                mAdjustModel.setKaobeiBeforeFlag(FunctionClickUtils.getInstance().getKaobeiBeforeSwitch());
                SPUtils.putAdjustModel(getContext(), username, mAdjustModel);
            }
        });
        //点击前调节事件，前调节按钮更换成选中图片，靠背按钮也变成选中图片
        mViewKaobeiAfter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showKaobeiAfterView();
                //存储靠背向前调节
                FunctionClickUtils.getInstance().setmKaobeiAfterSwitch(1);
                mAdjustModel.setKaobeiAfterFlag(FunctionClickUtils.getInstance().getKaobeiAfterSwitch());
                SPUtils.putAdjustModel(getContext(), username, mAdjustModel);
            }
        });
        //点击头枕事件，显示向上向下调节布局（上下都是白色）
        mViewToutuoClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsLogin) {
                    showTouzhenTopAndBottomView();
                    //存储开启头枕调节
                    mAdjustModel.setOpenTouzhen(true);
                    SPUtils.putAdjustModel(getContext(), username, mAdjustModel);
                } else {
                    Intent toLoginPage = new Intent(getContext(), ConnectionDeviceActivity.class);
                    startActivity(toLoginPage);
                }
            }
        });
        //点击头枕向上调节
        mViewToutuoTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTouzhenTopView();
                //存储头枕向上调节
                FunctionClickUtils.getInstance().setTouzhenTopSwitch(1);
                mAdjustModel.setTouzhenTopFlag(FunctionClickUtils.getInstance().getTouzhenTopSwitch());
                SPUtils.putAdjustModel(getContext(), username, mAdjustModel);
            }
        });
        //点击头枕向下调节
        mViewToutuoBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTouzhenBottomView();
                //存储头枕向下调节
                FunctionClickUtils.getInstance().setTouzhenBottomSwitch(1);
                mAdjustModel.setTouzhenBottomFlag(FunctionClickUtils.getInstance().getTouzhenBottomSwitch());
                SPUtils.putAdjustModel(getContext(), username, mAdjustModel);
            }
        });
        //点击腰托事件，显示向上、向下、向左、向右布局（显示白色）
        mViewYaotuoClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsLogin) {
                    showYaotuoClickView();
                    //存储开启腰托调节
                    mAdjustModel.setOpenYaozhen(true);
                    SPUtils.putAdjustModel(getContext(), username, mAdjustModel);
                } else {
                    Intent toLoginPage = new Intent(getContext(), ConnectionDeviceActivity.class);
                    startActivity(toLoginPage);
                }
            }
        });
        //点击腰托向上调节
        mViewYaotuoTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showYaotuoTopView();
                //存储腰托向上调节
                FunctionClickUtils.getInstance().setYaotuoTopSwitch(1);
                mAdjustModel.setYaozhenTopFlag(FunctionClickUtils.getInstance().getYaotuoTopSwitch());
                SPUtils.putAdjustModel(getContext(), username, mAdjustModel);
            }
        });
        //点击腰托向下调节
        mViewYaotuoBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showYaotuoBottomView();
                //存储腰托向下调节
                FunctionClickUtils.getInstance().setYaotuoBottomSwitch(1);
                mAdjustModel.setYaozhenBottomFlag(FunctionClickUtils.getInstance().getYaotuoBottomSwitch());
                SPUtils.putAdjustModel(getContext(), username, mAdjustModel);
            }
        });
        //点击腰托向左调节
        mViewYaotuoLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showYaotuoLeftView();
                //存储腰托向左调节
                FunctionClickUtils.getInstance().setYaotuoLeftSwitch(1);
                mAdjustModel.setYaozhenLeftFlag(FunctionClickUtils.getInstance().getYaotuoLeftSwitch());
                SPUtils.putAdjustModel(getContext(), username, mAdjustModel);
            }
        });
        //点击腰托向右调节
        mViewYaotuoRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showYaotuoRightView();
                //存储腰托向右调节
                FunctionClickUtils.getInstance().setYaotuoRightSwitch(1);
                mAdjustModel.setYaozhenRightFlag(FunctionClickUtils.getInstance().getYaotuoRightSwitch());
                SPUtils.putAdjustModel(getContext(), username, mAdjustModel);
            }
        });
        //点击腿托view事件，显示向前调节和向后调节布局（显示白色）
        mViewTuituoClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsLogin) {
                    showTuituoView();
                    //存储开启腿托调节
                    mAdjustModel.setOpenTuituo(true);
                    SPUtils.putAdjustModel(getContext(), username, mAdjustModel);
                } else {
                    Intent toLoginPage = new Intent(getContext(), ConnectionDeviceActivity.class);
                    startActivity(toLoginPage);
                }
            }
        });
        //点击腿托向前view布局
        mViewTuituoBefore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTuituoBeforeView();
                //存储腿托向前调节
                FunctionClickUtils.getInstance().setTuituoBeforeSwitch(1);
                mAdjustModel.setTuituoBeforeFlag(FunctionClickUtils.getInstance().getTuituoBeforeSwitch());
                SPUtils.putAdjustModel(getContext(), username, mAdjustModel);
            }
        });
        //显示腿托向后view布局
        mViewTuituoAfter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTuituoAfterView();
                //存储腿托向后调节
                FunctionClickUtils.getInstance().setTuituoAfterSwitch(1);
                mAdjustModel.setTuituoAfterFlag(FunctionClickUtils.getInstance().getTuituoAfterSwitch());
                SPUtils.putAdjustModel(getContext(), username, mAdjustModel);
            }
        });
        //点击座椅前后事件，显示向前和向后布局（显示白色）
        mViewQianhouClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsLogin) {
                    showQianhouView();
                    //存储开启座椅前后调节
                    mAdjustModel.setOpenQianhou(true);
                    SPUtils.putAdjustModel(getContext(), username, mAdjustModel);
                } else {
                    Intent toLoginPage = new Intent(getContext(), ConnectionDeviceActivity.class);
                    startActivity(toLoginPage);
                }
            }
        });
        //点击座椅向前事件
        mViewQianhouBefore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showQianhouBeforeView();
                //存储座椅向前调节
                FunctionClickUtils.getInstance().setQianhouBeforeSwitch(1);
                mAdjustModel.setQianhouBeforeFlag(FunctionClickUtils.getInstance().getQianhouBeforeSwitch());
                SPUtils.putAdjustModel(getContext(), username, mAdjustModel);
            }
        });
        //点击座椅向后事件
        mViewQianhouAfter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showQianhouAfterView();
                //存储座椅向后调节
                FunctionClickUtils.getInstance().setQianhouBeforeSwitch(1);
                mAdjustModel.setQianhouAfterFlag(FunctionClickUtils.getInstance().getQianhouAfterSwitch());
                SPUtils.putAdjustModel(getContext(), username, mAdjustModel);
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
        mViewKaobeiBefore.setVisibility(View.VISIBLE);
        mViewKaobeiBefore.setBackgroundResource(R.drawable.icon_kaobei_before_selected_h730);
    }

    /**
     * 显示向后调节靠背选中样式布局
     */
    private void showKaobeiAfterView() {
        mViewKaobeiSelected.setVisibility(View.VISIBLE);
        mViewKaobeiAfter.setVisibility(View.VISIBLE);
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
        mViewToutuoTop.setVisibility(View.VISIBLE);
        mViewToutuoTop.setBackgroundResource(R.drawable.icon_toutuo_top_selected_h600);
    }

    /**
     * 显示头枕向下调节布局
     */
    private void showTouzhenBottomView() {
        mViewToutuoSelected.setVisibility(View.VISIBLE);
        mViewToutuoBottom.setVisibility(View.VISIBLE);
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
        mViewYaotuoTop.setVisibility(View.VISIBLE);
        mViewYaotuoTop.setBackgroundResource(R.drawable.icon_yaotuo_top_selected_h600);
    }

    /**
     * 显示腰托向下调节布局
     */
    private void showYaotuoBottomView() {
        mViewYaotuoSelected.setVisibility(View.VISIBLE);
        mViewYaotuoBottom.setVisibility(View.VISIBLE);
        mViewYaotuoBottom.setBackgroundResource(R.drawable.icon_yaotuo_bottom_selected_h600);
    }

    /**
     * 显示腰托向左调节布局
     */
    private void showYaotuoLeftView() {
        mViewYaotuoSelected.setVisibility(View.VISIBLE);
        mViewYaotuoLeft.setVisibility(View.VISIBLE);
        mViewYaotuoLeft.setBackgroundResource(R.drawable.icon_yaotuo_left_selected_h600);
    }

    /**
     * 显示腰托向右调节布局
     */
    private void showYaotuoRightView() {
        mViewYaotuoSelected.setVisibility(View.VISIBLE);
        mViewYaotuoRight.setVisibility(View.VISIBLE);
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
        mViewTuituoBefore.setVisibility(View.VISIBLE);
        mViewTuituoBefore.setBackgroundResource(R.drawable.icon_tuituo_before_selected);
    }

    /**
     * 显示腿托向后调节view布局
     */
    private void showTuituoAfterView() {
        mViewTuituoSelected.setVisibility(View.VISIBLE);
        mViewTuituoAfter.setVisibility(View.VISIBLE);
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
        mViewQianhouBefore.setVisibility(View.VISIBLE);
        mViewQianhouBefore.setBackgroundResource(R.drawable.icon_qianhou_before_selected);

    }

    /**
     * 显示座椅向后调节viewbuju
     */
    private void showQianhouAfterView() {
        mViewQianhouSelected.setVisibility(View.VISIBLE);
        mViewQianhouAfter.setVisibility(View.VISIBLE);
        mViewQianhouAfter.setBackgroundResource(R.drawable.icon_qianhou_after_selected);
    }

    /**
     * 功能失效方法
     */
    private void functionInvalid() {
        //移除调节页面存储数据
        SPUtils.remove(getContext(), ADJUST_MODEL_KEY);
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