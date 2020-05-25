package com.smartseat.workgroup.main.model;

import java.io.Serializable;

/**
 * 座椅主页面
 */
public class MainModel implements Serializable {
    /**
     * 睡眠模式开关：0：关闭睡眠模式、1：开启睡眠模式
     */
    private int sleepModelSwitch;
    /**
     * 一键复位开关：0：关闭一键复位模式、1：开启一键复位模式
     */
    private int oneKeyResetSwitch;

    public int getSleepModelSwitch() {
        return sleepModelSwitch;
    }

    public void setSleepModelSwitch(int sleepModelSwitch) {
        this.sleepModelSwitch = sleepModelSwitch;
    }

    public int getOneKeyResetSwitch() {
        return oneKeyResetSwitch;
    }

    public void setOneKeyResetSwitch(int oneKeyResetSwitch) {
        this.oneKeyResetSwitch = oneKeyResetSwitch;
    }
}
