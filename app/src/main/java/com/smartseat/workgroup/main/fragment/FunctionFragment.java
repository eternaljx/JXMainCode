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
import com.smartseat.workgroup.common.utils.SPUtils;
import com.smartseat.workgroup.main.activity.ConnectionDeviceActivity;
import com.smartseat.workgroup.main.model.FunctionModel;

/**
 * 功能页面
 */

public class FunctionFragment extends Fragment {
    //加热布局控件
    private RelativeLayout mRlHeatingModel;
    //加热图片控件
    private ImageView mIvHeating;
    //加热选中图片控件
    private ImageView mIvHeatingSelected;
    //加热中文名称
    private TextView mTvHeatingTitle;
    //加热英文名称
    private TextView mTvHeatingEnglishName;
    //通风布局控件
    private RelativeLayout mRlVentilationModel;
    //通风图片控件
    private ImageView mIvVentilation;
    private ImageView mIvVentilationSelected;
    //通风中文名称
    private TextView mTvVentilationTitle;
    //通风英文名称
    private TextView mTvVentilationEnglishName;
    //按摩布局控件
    private RelativeLayout mRlMassageModel;
    //按摩图片控件
    private ImageView mIvMassage;
    private ImageView mIvMassageSelected;
    //按摩中文名称
    private TextView mTvMassageTitle;
    //按摩英文名称
    private TextView mTvMassageEnglishName;
    //氛围灯布局控件
    private RelativeLayout mRlAmbientModel;
    //氛围灯图片控件
    private ImageView mIvAmbient;
    private ImageView mIvAmbientSelected;
    //氛围灯中文名称
    private TextView mTvAmbientTitle;
    //氛围灯英文名称
    private TextView mTvAmbientEnglishName;
    //加热挡位横线布局控件
    private RelativeLayout mRlHeatingLineLayout;
    //加热座椅档位一档
    private RelativeLayout mRlHeatingOneGear;
    private ImageView mIvHeatingOneGear;
    private ImageView mIvHeatingOneGearTwo;
    private ImageView mIvHeatingOneGearThree;
    //加热座椅档位二档
    private RelativeLayout mRlHeatingTwoGear;
    private ImageView mIvHeatingTwoGear;
    private ImageView mIvHeatingTwoGearTwo;
    private ImageView mIvHeatingTwoGearThree;
    //加热挡位一横线布局控件
    private ImageView mIvHeatingOneGearLine;
    //加热挡位二横线布局控件
    private ImageView mIvHeatingTwoGearLine;
    //加热挡位三横线布局控件
    private ImageView mIvHeatingThreeGearLine;
    //加热挡位三横线选中布局控件
    private ImageView mIvHeatingThreeGearLine_selected;
    //通风座椅档位top布局控件
    private RelativeLayout mRlVentilationOneGearTop;
    //通风座椅档位一top布局控件
    private ImageView mIvVentilationOneGearTop;
    //通风座椅档位bottom布局控件
    private RelativeLayout mRlVentilationOneGearBottom;
    //通风座椅档位一bottom布局控件
    private ImageView mIvVentilationOneGearBottom;
    //通风挡位横线布局控件
    private RelativeLayout mRlVentilationLineLayout;
    //通风挡位一横线布局控件
    private ImageView mIvVentilationOneGearLine;
    //通风挡位二横线布局控件
    private ImageView mIvVentilationTwoGearLine;
    //通风挡位三横线布局控件
    private ImageView mIvVentilationThreeGearLine;
    //按摩座椅档位小手布局控件
    private RelativeLayout mRlMasageOneGear;
    //按摩座椅档位一左侧图片控件
    private ImageView mIvMassageLeftOneGear;
    //按摩座椅档位一右侧图片控件
    private ImageView mIvMassageRightOneGear;
    //按摩座椅档位一右侧选中图片控件
    private ImageView mIvMassageRightOneGearSelected;
    //按摩挡位横线布局控件
    private RelativeLayout mRlMassageLineLayout;
    //按摩挡位一横线布局控件
    private ImageView mIvMassageOneGearLine;
    //按摩挡位二横线布局控件
    private ImageView mIvMassageTwoGearLine;
    private ImageView mIvMassageTwoGearLineSelected;
    //氛围灯阴影图片控件
    private ImageView mIvVembientShadow;
    //是否开启氛围灯
    private boolean mIsOpenVembientShadow = true;
    private int mHeatingCount = 1;
    private int mVentilationCount = 1;
    private int mMassageCount = 1;
    private boolean mIsLogin;
    private String userName;
    private FunctionModel mFunctionModel = new FunctionModel();

    public static FunctionFragment newInstance() {
        return new FunctionFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //通过参数中的布局填充获取对应布局
        View view = inflater.inflate(R.layout.activity_function_fragment, container, false);
        initView(view);
        initData();
        initEvent();
        return view;
    }

    private void initView(View view) {
        mRlHeatingModel = view.findViewById(R.id.rl_heating_model);
        mIvHeating = view.findViewById(R.id.iv_heating_model);
        mIvHeatingSelected = view.findViewById(R.id.iv_heating_model_selected);
        mTvHeatingTitle = view.findViewById(R.id.tv_heating_model_title);
        mTvHeatingEnglishName = view.findViewById(R.id.tv_heating_model_englishname);
        mRlVentilationModel = view.findViewById(R.id.rl_ventilation);
        mIvVentilation = view.findViewById(R.id.iv_ventilation);
        mIvVentilationSelected = view.findViewById(R.id.iv_ventilation_selected);
        mTvVentilationTitle = view.findViewById(R.id.tv_ventilation_title);
        mTvVentilationEnglishName = view.findViewById(R.id.tv_ventilation_englishname);
        mRlMassageModel = view.findViewById(R.id.rl_massage);
        mIvMassage = view.findViewById(R.id.iv_massage);
        mIvMassageSelected = view.findViewById(R.id.iv_massage_selected);
        mTvMassageTitle = view.findViewById(R.id.tv_massage_title);
        mTvMassageEnglishName = view.findViewById(R.id.tv_massage_englishname);
        mRlAmbientModel = view.findViewById(R.id.rl_ambient);
        mIvAmbient = view.findViewById(R.id.iv_ambient);
        mIvAmbientSelected = view.findViewById(R.id.iv_ambient_selected);
        mTvAmbientTitle = view.findViewById(R.id.tv_ambient_title);
        mTvAmbientEnglishName = view.findViewById(R.id.tv_ambient_englishname);
        mRlHeatingOneGear = view.findViewById(R.id.rl_heating_onegear);
        mIvHeatingOneGear = view.findViewById(R.id.iv_heating_onegear);
        mIvHeatingOneGearTwo = view.findViewById(R.id.iv_heating_onegear_two);
        mIvHeatingOneGearThree = view.findViewById(R.id.iv_heating_onegear_three);
        mRlHeatingTwoGear = view.findViewById(R.id.rl_heating_twogear);
        mIvHeatingTwoGear = view.findViewById(R.id.iv_heating_twogear);
        mIvHeatingTwoGearTwo = view.findViewById(R.id.iv_heating_twogear_two);
        mIvHeatingTwoGearThree = view.findViewById(R.id.iv_heating_twogear_three);
        mRlHeatingLineLayout = view.findViewById(R.id.rl_heating_line);
        mIvHeatingOneGearLine = view.findViewById(R.id.iv_heating_one_line);
        mIvHeatingTwoGearLine = view.findViewById(R.id.iv_heating_two_line);
        mIvHeatingThreeGearLine = view.findViewById(R.id.iv_heating_three_line);
        mIvHeatingThreeGearLine_selected = view.findViewById(R.id.iv_heating_three_line_selected);
        mRlVentilationOneGearTop = view.findViewById(R.id.rl_ventilation_onegear_top);
        mIvVentilationOneGearTop = view.findViewById(R.id.iv_ventilation_onegear_top);
        mRlVentilationOneGearBottom = view.findViewById(R.id.rl_ventilation_onegear_bottom);
        mIvVentilationOneGearBottom = view.findViewById(R.id.iv_ventilation_onegear_bottom);
        mRlVentilationLineLayout = view.findViewById(R.id.rl_ventilation_line);
        mIvVentilationOneGearLine = view.findViewById(R.id.iv_ventilation_one_line);
        mIvVentilationTwoGearLine = view.findViewById(R.id.iv_ventilation_two_line);
        mIvVentilationThreeGearLine = view.findViewById(R.id.iv_ventilation_three_line);
        mRlMasageOneGear = view.findViewById(R.id.rl_massage_onegear);
        mIvMassageLeftOneGear = view.findViewById(R.id.iv_massage_left_onegear);
        mIvMassageRightOneGear = view.findViewById(R.id.iv_massage_right_onegear);
        mIvMassageRightOneGearSelected = view.findViewById(R.id.iv_massage_right_onegear_selected);
        mRlMassageLineLayout = view.findViewById(R.id.rl_massage_function_line);
        mIvMassageOneGearLine = view.findViewById(R.id.iv_massage_one_line);
        mIvMassageTwoGearLine = view.findViewById(R.id.iv_massage_two_line);
        mIvMassageTwoGearLineSelected = view.findViewById(R.id.iv_massage_two_line_selected);
        mIvVembientShadow = view.findViewById(R.id.iv_vebient_shadow);
    }

    private void initData() {
        mIsLogin = (boolean) SPUtils.get(getContext(), "isLogin", false);
        userName = (String) SPUtils.get(getContext(), "username", "");
    }

    private void initEvent() {
        //点击加热事件
        mRlHeatingModel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsLogin) {
                    //开启加热模式
                    mFunctionModel.setOpenHeatingGear(true);
                    if (mHeatingCount == 1) {//1
                        setHeatingModelSelected();
                        openHeatingOneGearView();
                        mHeatingCount = 2;
                        mFunctionModel.setHeatingGear(1);
                        //存储加热一档数据
                    } else if (mHeatingCount == 2) {//2
                        setHeatingModelSelected();
                        openHeatingTwoGearView();
                        mHeatingCount = 3;
                        mFunctionModel.setHeatingGear(2);
                        //存储加热二档数据
                    } else if (mHeatingCount == 3) {//3
                        setHeatingModelSelected();
                        openHeatingThreeGearView();
                        mHeatingCount = 0;
                        //存储加热三档数据
                        mFunctionModel.setHeatingGear(3);
                    } else {//0
                        //清除加热档位数据（一、二、三档位）
                        closeHeatingGearView();
                        mHeatingCount = 1;
                        mFunctionModel.setHeatingGear(0);
                        mFunctionModel.setOpenHeatingGear(false);
                    }
                    SPUtils.putFunctionModel(getContext(), userName, mFunctionModel);
                } else {
                    Intent toLoginPage = new Intent(getContext(), ConnectionDeviceActivity.class);
                    startActivity(toLoginPage);
                }
            }
        });
        //点击通风事件
        mRlVentilationModel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsLogin) {
                    mFunctionModel.setOpenVentilation(true);
                    if (mVentilationCount == 1) {
                        setVentilationModelSelected();
                        openVentilationOneGearView();
                        mVentilationCount = 2;
                        //存储通风一档数据
                        mFunctionModel.setVentilationGear(1);
                    } else if (mVentilationCount == 2) {
                        setVentilationModelSelected();
                        openVentilationTwoGearView();
                        mVentilationCount = 3;
                        //存储通风二档数据
                        mFunctionModel.setVentilationGear(2);
                    } else if (mVentilationCount == 3) {
                        setVentilationModelSelected();
                        openVentilationThreeGearView();
                        mVentilationCount = 0;
                        //存储通风三档数据
                        mFunctionModel.setVentilationGear(3);
                    } else {
                        closeVentilationGearView();
                        mVentilationCount = 1;
                        //清除通风档位数据（一、二、三档位）
                        mFunctionModel.setVentilationGear(0);
                        mFunctionModel.setOpenVentilation(false);
                    }
                } else {
                    Intent toLoginPage = new Intent(getContext(), ConnectionDeviceActivity.class);
                    startActivity(toLoginPage);
                }
            }
        });
        //点击按摩事件
        mRlMassageModel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsLogin) {
                    mFunctionModel.setOpenMassage(true);
                    if (mMassageCount == 1) {
                        setmRlMassageModelSelected();
                        openMassageOneGearView();
                        mMassageCount = 2;
                        //存储按摩一档数据
                        mFunctionModel.setMassageGear(1);
                    } else if (mMassageCount == 2) {
                        setmRlMassageModelSelected();
                        openMassageTwoGearView();
                        mMassageCount = 0;
                        //存储按摩二档数据
                        mFunctionModel.setMassageGear(2);
                    } else {
                        closeMassageGearView();
                        mMassageCount = 1;
                        //清除按摩档位数据（一、二档位）
                        mFunctionModel.setMassageGear(0);
                        mFunctionModel.setOpenMassage(false);
                    }
                } else {
                    Intent toLoginPage = new Intent(getContext(), ConnectionDeviceActivity.class);
                    startActivity(toLoginPage);
                }
            }
        });
        //点击氛围灯事件
        mRlAmbientModel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsLogin) {
                    if (mIsOpenVembientShadow) {
                        openAmbientShadowView();
                        setAmbientModelSelected();
                        mIsOpenVembientShadow = false;
                        //存储氛围灯数据
                        mFunctionModel.setOpenVembient(true);
                    } else {
                        closeAmbientShadowView();
                        setAmbientModelUnSelected();
                        mIsOpenVembientShadow = true;
                        //清除氛围灯数据
                        mFunctionModel.setOpenVembient(false);
                    }
                } else {
                    Intent toLoginPage = new Intent(getContext(), ConnectionDeviceActivity.class);
                    startActivity(toLoginPage);
                }
            }
        });
    }

    /**
     * 加热未选中状态样式
     */
    private void setHeatingModelUnSelected() {
        mIvHeating.setVisibility(View.VISIBLE);
        mIvHeatingSelected.setVisibility(View.GONE);
        mRlHeatingModel.setBackgroundResource(R.drawable.shape_white_corner);
        mIvHeating.setBackgroundResource(R.drawable.icon_function_heating);
        mTvHeatingTitle.setTextColor(getResources().getColor(R.color.white));
        mTvHeatingEnglishName.setTextColor(getResources().getColor(R.color.white));
    }

    /**
     * 加热选中状态样式
     */
    private void setHeatingModelSelected() {
        mIvHeating.setVisibility(View.GONE);
        mIvHeatingSelected.setVisibility(View.VISIBLE);
        mRlHeatingModel.setBackgroundResource(R.drawable.shape_orange_corner);
        mIvHeatingSelected.setBackgroundResource(R.drawable.icon_function_heating_selected);
        mTvHeatingTitle.setTextColor(getResources().getColor(R.color.orange));
        mTvHeatingEnglishName.setTextColor(getResources().getColor(R.color.orange));
    }

    /**
     * 通风未选中状态样式
     */
    private void setVentilationModelUnSelected() {
        mRlVentilationLineLayout.setVisibility(View.INVISIBLE);
        mRlVentilationOneGearTop.setVisibility(View.INVISIBLE);
        mRlVentilationOneGearBottom.setVisibility(View.INVISIBLE);
        mIvVentilation.setVisibility(View.VISIBLE);
        mIvVentilationSelected.setVisibility(View.INVISIBLE);
        mRlVentilationModel.setBackgroundResource(R.drawable.shape_white_corner);
        mTvVentilationTitle.setTextColor(getResources().getColor(R.color.white));
        mTvVentilationEnglishName.setTextColor(getResources().getColor(R.color.white));
    }

    /**
     * 通风选中状态样式
     */
    private void setVentilationModelSelected() {
        mRlVentilationLineLayout.setVisibility(View.VISIBLE);
        mRlVentilationOneGearTop.setVisibility(View.VISIBLE);
        mRlVentilationOneGearBottom.setVisibility(View.VISIBLE);
        mIvVentilation.setVisibility(View.INVISIBLE);
        mIvVentilationSelected.setVisibility(View.VISIBLE);
        mRlVentilationModel.setBackgroundResource(R.drawable.shape_orange_corner);
        mTvVentilationTitle.setTextColor(getResources().getColor(R.color.orange));
        mTvVentilationEnglishName.setTextColor(getResources().getColor(R.color.orange));
    }

    /**
     * 按摩未选中状态样式
     */
    private void setMassageModelUnSelected() {
        mRlMassageModel.setBackgroundResource(R.drawable.shape_white_corner);
        mIvMassage.setVisibility(View.VISIBLE);
        mIvMassageSelected.setVisibility(View.INVISIBLE);
        mTvMassageTitle.setTextColor(getResources().getColor(R.color.white));
        mTvMassageEnglishName.setTextColor(getResources().getColor(R.color.white));
    }

    /**
     * 按摩选中状态样式
     */
    private void setmRlMassageModelSelected() {
        mRlMassageModel.setBackgroundResource(R.drawable.shape_orange_corner);
        mIvMassage.setVisibility(View.INVISIBLE);
        mIvMassageSelected.setVisibility(View.VISIBLE);
        mTvMassageTitle.setTextColor(getResources().getColor(R.color.orange));
        mTvMassageEnglishName.setTextColor(getResources().getColor(R.color.orange));
    }

    /**
     * 氛围灯未选中状态样式
     */
    private void setAmbientModelUnSelected() {
        mIvAmbient.setVisibility(View.VISIBLE);
        mIvAmbientSelected.setVisibility(View.INVISIBLE);
        mRlAmbientModel.setBackgroundResource(R.drawable.shape_white_corner);
        mIvAmbient.setBackgroundResource(R.drawable.icon_function_ambient);
        mTvAmbientTitle.setTextColor(getResources().getColor(R.color.white));
        mTvAmbientEnglishName.setTextColor(getResources().getColor(R.color.white));
    }

    /**
     * 氛围灯选中状态样式
     */
    private void setAmbientModelSelected() {
        mIvAmbient.setVisibility(View.INVISIBLE);
        mIvAmbientSelected.setVisibility(View.VISIBLE);
        mRlAmbientModel.setBackgroundResource(R.drawable.shape_orange_corner);
        mIvAmbientSelected.setBackgroundResource(R.drawable.icon_function_ambient_selected);
        mTvAmbientTitle.setTextColor(getResources().getColor(R.color.orange));
        mTvAmbientEnglishName.setTextColor(getResources().getColor(R.color.orange));
    }

    /**
     * 打开加热一档view布局样式
     */
    private void openHeatingOneGearView() {
        mRlHeatingLineLayout.setVisibility(View.VISIBLE);
        mRlHeatingOneGear.setVisibility(View.VISIBLE);
        mRlHeatingTwoGear.setVisibility(View.VISIBLE);
        mIvHeatingThreeGearLine.setVisibility(View.VISIBLE);
        mIvHeatingThreeGearLine_selected.setVisibility(View.INVISIBLE);
        mIvHeatingOneGear.setVisibility(View.VISIBLE);
        mIvHeatingOneGearTwo.setVisibility(View.INVISIBLE);
        mIvHeatingOneGearThree.setVisibility(View.INVISIBLE);
        mIvHeatingTwoGear.setVisibility(View.VISIBLE);
        mIvHeatingTwoGearTwo.setVisibility(View.INVISIBLE);
        mIvHeatingTwoGearThree.setVisibility(View.INVISIBLE);
        mIvHeatingOneGear.setBackgroundResource(R.drawable.image_heating_onegear);
        mIvHeatingTwoGear.setBackgroundResource(R.drawable.image_heating_onegear);
        mIvHeatingOneGearLine.setBackgroundResource(R.drawable.icon_heating_one_line_selected);
        mIvHeatingTwoGearLine.setBackgroundResource(R.drawable.icon_heating_second_line);
        mIvHeatingThreeGearLine.setBackgroundResource(R.drawable.icon_heating_three_line);

    }

    /**
     * 打开加热二档view布局样式
     */
    private void openHeatingTwoGearView() {
        mRlHeatingLineLayout.setVisibility(View.VISIBLE);
        mRlHeatingOneGear.setVisibility(View.VISIBLE);
        mRlHeatingTwoGear.setVisibility(View.VISIBLE);
        mIvHeatingThreeGearLine.setVisibility(View.VISIBLE);
        mIvHeatingThreeGearLine_selected.setVisibility(View.INVISIBLE);
        mIvHeatingOneGear.setVisibility(View.INVISIBLE);
        mIvHeatingOneGearTwo.setVisibility(View.VISIBLE);
        mIvHeatingOneGearThree.setVisibility(View.INVISIBLE);
        mIvHeatingTwoGear.setVisibility(View.INVISIBLE);
        mIvHeatingTwoGearTwo.setVisibility(View.VISIBLE);
        mIvHeatingTwoGearThree.setVisibility(View.INVISIBLE);
        mIvHeatingOneGearTwo.setBackgroundResource(R.drawable.image_heating_twogear);
        mIvHeatingOneGearLine.setBackgroundResource(R.drawable.icon_heating_one_line_selected);
        mIvHeatingTwoGearLine.setBackgroundResource(R.drawable.icon_heating_second_line_selected);
        mIvHeatingThreeGearLine.setBackgroundResource(R.drawable.icon_heating_three_line);
    }

    /**
     * 打开加热三档view布局样式
     */
    private void openHeatingThreeGearView() {
        mRlHeatingLineLayout.setVisibility(View.VISIBLE);
        mRlHeatingOneGear.setVisibility(View.VISIBLE);
        mRlHeatingTwoGear.setVisibility(View.VISIBLE);
        mIvHeatingThreeGearLine.setVisibility(View.INVISIBLE);
        mIvHeatingThreeGearLine_selected.setVisibility(View.VISIBLE);
        mIvHeatingOneGear.setVisibility(View.INVISIBLE);
        mIvHeatingOneGearTwo.setVisibility(View.INVISIBLE);
        mIvHeatingOneGearThree.setVisibility(View.VISIBLE);
        mIvHeatingTwoGear.setVisibility(View.INVISIBLE);
        mIvHeatingTwoGearTwo.setVisibility(View.INVISIBLE);
        mIvHeatingTwoGearThree.setVisibility(View.VISIBLE);
        mIvHeatingOneGearThree.setBackgroundResource(R.drawable.image_heating_threegear);
        mIvHeatingOneGearLine.setBackgroundResource(R.drawable.icon_heating_one_line_selected);
        mIvHeatingTwoGearLine.setBackgroundResource(R.drawable.icon_heating_second_line_selected);
        mIvHeatingThreeGearLine_selected.setBackgroundResource(R.drawable.icon_heating_three_line_selected);
    }

    /**
     * 关闭加热档位view布局样式
     */
    private void closeHeatingGearView() {
        mRlHeatingLineLayout.setVisibility(View.INVISIBLE);
        mRlHeatingOneGear.setVisibility(View.INVISIBLE);
        mRlHeatingTwoGear.setVisibility(View.INVISIBLE);
        setHeatingModelUnSelected();
    }

    /**
     * 打开通风一档view布局样式
     */
    private void openVentilationOneGearView() {
        mRlVentilationLineLayout.setVisibility(View.VISIBLE);
        mRlVentilationOneGearTop.setVisibility(View.VISIBLE);
        mRlVentilationOneGearBottom.setVisibility(View.VISIBLE);
        mIvVentilationOneGearLine.setBackgroundResource(R.drawable.icon_ventilation_one_line_selected);
        mIvVentilationTwoGearLine.setBackgroundResource(R.drawable.icon_heating_second_line);
        mIvVentilationThreeGearLine.setBackgroundResource(R.drawable.icon_heating_three_line);
        mIvVentilationOneGearTop.setBackgroundResource(R.drawable.image_ventilation_onegear);
        mIvVentilationOneGearBottom.setBackgroundResource(R.drawable.image_ventilation_onegear);
    }

    /**
     * 打开通风二档view布局样式
     */
    private void openVentilationTwoGearView() {
        mRlVentilationLineLayout.setVisibility(View.VISIBLE);
        mRlVentilationOneGearTop.setVisibility(View.VISIBLE);
        mRlVentilationOneGearBottom.setVisibility(View.VISIBLE);
        mIvVentilationOneGearLine.setBackgroundResource(R.drawable.icon_ventilation_one_line_selected);
        mIvVentilationTwoGearLine.setBackgroundResource(R.drawable.icon_ventilation_second_line_selected);
        mIvVentilationThreeGearLine.setBackgroundResource(R.drawable.icon_heating_three_line);
        mIvVentilationOneGearTop.setBackgroundResource(R.drawable.image_ventilation_twogear);
        mIvVentilationOneGearBottom.setBackgroundResource(R.drawable.image_ventilation_twogear);
    }

    /**
     * 打开通风三档view布局样式
     */
    private void openVentilationThreeGearView() {
        mRlVentilationLineLayout.setVisibility(View.VISIBLE);
        mRlVentilationOneGearTop.setVisibility(View.VISIBLE);
        mRlVentilationOneGearBottom.setVisibility(View.VISIBLE);
        mIvVentilationOneGearLine.setBackgroundResource(R.drawable.icon_ventilation_one_line_selected);
        mIvVentilationTwoGearLine.setBackgroundResource(R.drawable.icon_ventilation_second_line_selected);
        mIvVentilationThreeGearLine.setBackgroundResource(R.drawable.icon_ventilation_three_line_selected);
        mIvVentilationOneGearTop.setBackgroundResource(R.drawable.image_ventilation_threegear);
        mIvVentilationOneGearBottom.setBackgroundResource(R.drawable.image_ventilation_threegear);
    }

    /**
     * 关闭通风档位view布局样式
     */
    private void closeVentilationGearView() {
        mRlVentilationLineLayout.setVisibility(View.INVISIBLE);
        mRlVentilationOneGearTop.setVisibility(View.INVISIBLE);
        mRlVentilationOneGearBottom.setVisibility(View.INVISIBLE);
        setVentilationModelUnSelected();
    }

    /**
     * 打开按摩一档view布局样式
     */
    private void openMassageOneGearView() {
        mRlMassageLineLayout.setVisibility(View.VISIBLE);
        mRlMasageOneGear.setVisibility(View.VISIBLE);
        mIvMassageRightOneGear.setVisibility(View.VISIBLE);
        mIvMassageRightOneGearSelected.setVisibility(View.INVISIBLE);
        mIvMassageTwoGearLine.setVisibility(View.VISIBLE);
        mIvMassageTwoGearLineSelected.setVisibility(View.INVISIBLE);
        mIvMassageLeftOneGear.setBackgroundResource(R.drawable.image_massage_left_onegear);
        mIvMassageOneGearLine.setBackgroundResource(R.drawable.icon_massage_one_line_selected);
    }

    /**
     * 打开按摩二档view布局样式
     */
    private void openMassageTwoGearView() {
        mRlMassageLineLayout.setVisibility(View.VISIBLE);
        mRlMasageOneGear.setVisibility(View.VISIBLE);
        mIvMassageRightOneGear.setVisibility(View.INVISIBLE);
        mIvMassageRightOneGearSelected.setVisibility(View.VISIBLE);
        mIvMassageTwoGearLine.setVisibility(View.INVISIBLE);
        mIvMassageTwoGearLineSelected.setVisibility(View.VISIBLE);
        mIvMassageLeftOneGear.setBackgroundResource(R.drawable.image_massage_left_onegear);
        mIvMassageOneGearLine.setBackgroundResource(R.drawable.icon_massage_one_line_selected);
    }

    /**
     * 关闭按摩档位view布局样式
     */
    private void closeMassageGearView() {
        mRlMassageLineLayout.setVisibility(View.INVISIBLE);
        mRlMasageOneGear.setVisibility(View.INVISIBLE);
        setMassageModelUnSelected();
    }

    /**
     * 打开氛围灯view布局
     */
    private void openAmbientShadowView() {
        mIvVembientShadow.setVisibility(View.VISIBLE);
    }

    /**
     * 关闭氛围灯view布局
     */
    private void closeAmbientShadowView() {
        mIvVembientShadow.setVisibility(View.GONE);
    }

}
