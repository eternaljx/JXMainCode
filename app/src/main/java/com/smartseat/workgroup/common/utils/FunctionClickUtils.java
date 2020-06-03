package com.smartseat.workgroup.common.utils;

/**
 * 功能方法工具类
 *
 * @author jx
 */
public class FunctionClickUtils {
    public static FunctionClickUtils instance = new FunctionClickUtils();
    //是否打开睡眠模式
    public static boolean mIsOpenSleepModel;
    //是否打开一键复位模式
    public static boolean mIsOpenOneKeyResetModel;
    //是否打开氛围灯
    public static boolean mIsOpenVembient;
    //按摩档位 1,2
    public static int mMassageGear;
    //通风档位 1,2,3
    public static int mVentilationGear;
    //加热档位 1,2,3
    public static int mHeatingGear;
    //靠背向前调节开关 0：关闭 1：开启
    public static int mKaobeiBeforeSwitch;
    //靠背向后调节开关 0：关闭 1：开启
    public static int mKaobeiAfterSwitch;
    //头枕向上调节开关 0：关闭 1：开启
    public static int mTouzhenTopSwitch;
    //头枕向下调节开关 0：关闭 1：开启
    public static int mTouzhenBottomSwitch;
    //腰托向上调节开关 0：关闭 1：开启
    public static int mYaotuoTopSwitch;
    //腰托向下调节开关 0：关闭 1：开启
    public static int mYaotuoBottomSwitch;
    //腰托向左调节开关 0：关闭 1：开启
    public static int mYaotuoLeftSwitch;
    //腰托向右调节开关 0：关闭 1：开启
    public static int mYaotuoRightSwitch;
    //腿托向前调节开关 0：关闭 1：开启
    public static int mTuituoBeforeSwitch;
    //腿托向后调节开关 0：关闭 1：开启
    public static int mTuituoAfterSwitch;
    //座椅前后向前调节开关 0：关闭 1：开启
    public static int mQianhouBeforeSwitch;
    //座椅前后向后调节开关 0：关闭 1：开启
    public static int mQianhouAfterSwitch;

    /**
     * 将构造函数私有化
     */
    private FunctionClickUtils() {
    }

    /**
     * 获取ExitAppUtils的实例，保证只有一个ExitAppUtils实例存在
     *
     * @return
     */
    public static FunctionClickUtils getInstance() {
        return instance;
    }

    /**
     * 是否开启睡眠模式
     *
     * @return
     */
    public void isOpenSleepModel(boolean isOpenSleepModel) {
        this.mIsOpenSleepModel = isOpenSleepModel;
    }

    /**
     * 获取睡眠模式状态--指令获取（true：打开 false：关闭）
     *
     * @return
     */
    public boolean getOpenSleepStatus() {
        return mIsOpenSleepModel;
    }

    /**
     * 是否开启一键复位模式
     *
     * @param isOpenOneKeyResetModel
     * @return
     */
    public void isOpenOneKeyResetModel(boolean isOpenOneKeyResetModel) {
        this.mIsOpenOneKeyResetModel = isOpenOneKeyResetModel;
    }

    /**
     * 获取一键复位模式状态--指令获取（true：打开 false：关闭）
     *
     * @return
     */
    public boolean getOpenOneKeyResetModel() {
        return mIsOpenOneKeyResetModel;
    }

    /**
     * 是否开启氛围灯
     *
     * @param isOpenVembient
     */
    public void isOpenVembient(boolean isOpenVembient) {
        this.mIsOpenVembient = isOpenVembient;
    }

    /**
     * 获取氛围灯状态--指令获取
     *
     * @return
     */
    public boolean getOpenVembient() {
        return mIsOpenVembient;
    }

    /**
     * 设置按摩档位开关--1,2
     *
     * @param massageGear
     * @return
     */
    public void setMassageGear(int massageGear) {
        this.mMassageGear = massageGear;
    }

    /**
     * 获取按摩档位开关--指令获取（1：一档位 2：二档位）
     *
     * @return
     */
    public int getMassageGear() {
        return mMassageGear;
    }


    /**
     * 设置通风档位开关--1,2,3
     *
     * @param ventilationGear
     */
    public void setVentilationGear(int ventilationGear) {
        this.mVentilationGear = ventilationGear;
    }

    /**
     * 获取通风档位开关--指令获取（1：一档位 2：二档位 3：三档位）
     *
     * @return
     */
    public int getVentilationGear() {
        return mVentilationGear;
    }

    /**
     * 设置加热档位开关-- 1,2,3
     *
     * @param heatingGear
     */
    public void setHeatingGear(int heatingGear) {
        this.mHeatingGear = heatingGear;
    }

    /**
     * 获取加热档位开关--指令获取（1：一档位 2：二档位 3：三档位）
     *
     * @return
     */
    public int getHeatingGear() {
        return mHeatingGear;
    }

    /**
     * 设置靠背向前调节开关
     *
     * @param kaobeiBeforeSwitch
     */
    public void setmKaobeiBeforeSwitch(int kaobeiBeforeSwitch) {
        this.mKaobeiBeforeSwitch = kaobeiBeforeSwitch;
    }

    /**
     * 设置靠背向后调节开关
     *
     * @param kaobeiAfterSwitch
     */
    public void setmKaobeiAfterSwitch(int kaobeiAfterSwitch) {
        this.mKaobeiAfterSwitch = kaobeiAfterSwitch;
    }

    /**
     * 获取靠背向前调节开关
     *
     * @return
     */
    public int getKaobeiBeforeSwitch() {
        return mKaobeiBeforeSwitch;
    }

    /**
     * 获取靠背向后调节开关
     *
     * @return
     */
    public int getKaobeiAfterSwitch() {
        return mKaobeiAfterSwitch;
    }

    /**
     * 设置头枕向上调节开关 0：关闭 1：开启
     *
     * @param touzhenTopSwitch
     */
    public void setTouzhenTopSwitch(int touzhenTopSwitch) {
        this.mTouzhenTopSwitch = touzhenTopSwitch;
    }

    /**
     * 设置头枕向下调节开关 0：关闭 1：开启
     *
     * @param touzhenBottomSwitch
     */
    public void setTouzhenBottomSwitch(int touzhenBottomSwitch) {
        this.mTouzhenBottomSwitch = touzhenBottomSwitch;
    }

    /**
     * 获取头枕向上调节开关--指令获取
     *
     * @return
     */
    public int getTouzhenTopSwitch() {
        return mTouzhenTopSwitch;
    }

    /**
     * 获取头枕向下调节开关--指令获取
     *
     * @return
     */
    public int getTouzhenBottomSwitch() {
        return mTouzhenBottomSwitch;
    }

    /**
     * 设置腰托向上调节开关 0：关闭 1：开启
     *
     * @param yaotuoTopSwitch
     */
    public void setYaotuoTopSwitch(int yaotuoTopSwitch) {
        this.mYaotuoTopSwitch = yaotuoTopSwitch;
    }

    /**
     * 设置腰托向下调节开关 0：关闭 1：开启
     *
     * @param yaotuoBottomSwitch
     */
    public void setYaotuoBottomSwitch(int yaotuoBottomSwitch) {
        this.mYaotuoBottomSwitch = yaotuoBottomSwitch;
    }

    /**
     * 设置腰托向左调节开关 0：关闭 1：开启
     *
     * @param yaotuoLeftSwitch
     */
    public void setYaotuoLeftSwitch(int yaotuoLeftSwitch) {
        this.mYaotuoLeftSwitch = yaotuoLeftSwitch;
    }

    /**
     * 设置腰托向右调节开关 0：关闭 1：开启
     *
     * @param yaotuoRightSwitch
     */
    public void setYaotuoRightSwitch(int yaotuoRightSwitch) {
        this.mYaotuoRightSwitch = yaotuoRightSwitch;
    }

    /**
     * 获取腰托向上调节开关--指令获取
     *
     * @return
     */
    public int getYaotuoTopSwitch() {
        return mYaotuoTopSwitch;
    }

    /**
     * 获取腰托向下调节开关--指令获取
     *
     * @return
     */
    public int getYaotuoBottomSwitch() {
        return mYaotuoBottomSwitch;
    }

    /**
     * 获取腰托向左调节开关--指令获取
     *
     * @return
     */
    public int getYaotuoLeftSwitch() {
        return mYaotuoLeftSwitch;
    }

    /**
     * 获取腰托向右调节开关--指令获取
     *
     * @return
     */
    public int getYaotuoRightSwitch() {
        return mYaotuoRightSwitch;
    }

    /**
     * 设置腿托向前调节开关 0：关闭 1：开启
     *
     * @param tuituoBeforeSwitch
     */
    public void setTuituoBeforeSwitch(int tuituoBeforeSwitch) {
        this.mTuituoBeforeSwitch = tuituoBeforeSwitch;
    }

    /**
     * 设置腿托向前调节开关 指令获取（0：关闭 1：开启）
     *
     * @param tuituoAfterSwitch
     */
    public void setTuituoAfterSwitch(int tuituoAfterSwitch) {
        this.mTuituoAfterSwitch = tuituoAfterSwitch;
    }

    /**
     * 获取腿托向前调节开关 0：关闭 1：开启
     *
     * @return
     */
    public int getTuituoBeforeSwitch() {
        return mTuituoBeforeSwitch;
    }

    /**
     * 获取腿托向后调节开关--指令获取 （0：关闭 1：开启）
     *
     * @return
     */
    public int getTuituoAfterSwitch() {
        return mTuituoAfterSwitch;
    }

    /**
     * 设置座椅向前调节开关 0：关闭 1：开启
     *
     * @param qianhouBeforeSwitch
     */
    public void setQianhouBeforeSwitch(int qianhouBeforeSwitch) {
        this.mQianhouBeforeSwitch = qianhouBeforeSwitch;
    }

    /**
     * 设置座椅向后调节开关 0：关闭 1：开启
     *
     * @param qianhouAfterSwitch
     */
    public void getQianhouBeforeSwitch(int qianhouAfterSwitch) {
        this.mQianhouAfterSwitch = qianhouAfterSwitch;
    }

    /**
     * 获取座椅向前调节开关--指令获取
     *
     * @return
     */
    public int getQianhouBeforeSwitch() {
        return mQianhouBeforeSwitch;
    }

    /**
     * 获取座椅向后调节开关--指令获取
     *
     * @return
     */
    public int getQianhouAfterSwitch() {
        return mQianhouAfterSwitch;
    }


}
