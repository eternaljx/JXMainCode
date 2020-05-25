package com.smartseat.workgroup.main.model;

import java.io.Serializable;

/**
 * 座椅调节模型
 */
public class AdjustModel implements Serializable {

    /**
     * 靠背向前调节：0：未调节、1：调节
     */
    private int kaobeiBeforeFlag;
    /**
     * 靠背向后调节：0：未调节、1：调节
     */
    private int kaobeiAfterFlag;
    /**
     * 头枕向上调节：0：未调节、1：调节
     */
    private int touzhenTopFlag;
    /**
     * 头枕向下调节：0：未调节、1：调节
     */
    private int touzhenBottomFlag;
    /**
     * 腰枕向上调节：0：未调节、1：调节
     */
    private int yaozhenTopFlag;
    /**
     * 腰枕向下调节：0：未调节、1：调节
     */
    private int yaozhenBottomFlag;
    /**
     * 腰枕向左调节：0：未调节、1：调节
     */
    private int yaozhenLeftFlag;
    /**
     * 腰枕向右调节：0：未调节、1：调节
     */
    private int yaozhenRightFlag;
    /**
     * 腿托向前调节：0：未调节、1：调节
     */
    private int tuituoBeforeFlag;
    /**
     * 腿托向后调节：0：未调节、1：调节
     */
    private int tuituoAfterFlag;
    /**
     * 座椅前后向前调节：0：未调节、1：调节
     */
    private int qianhouBeforeFlag;
    /**
     * 座椅前后向后调节：0：未调节、1：调节
     */
    private int qianhouAfterFlag;

    public int getKaobeiBeforeFlag() {
        return kaobeiBeforeFlag;
    }

    public void setKaobeiBeforeFlag(int kaobeiBeforeFlag) {
        this.kaobeiBeforeFlag = kaobeiBeforeFlag;
    }

    public int getKaobeiAfterFlag() {
        return kaobeiAfterFlag;
    }

    public void setKaobeiAfterFlag(int kaobeiAfterFlag) {
        this.kaobeiAfterFlag = kaobeiAfterFlag;
    }

    public int getTouzhenTopFlag() {
        return touzhenTopFlag;
    }

    public void setTouzhenTopFlag(int touzhenTopFlag) {
        this.touzhenTopFlag = touzhenTopFlag;
    }

    public int getTouzhenBottomFlag() {
        return touzhenBottomFlag;
    }

    public void setTouzhenBottomFlag(int touzhenBottomFlag) {
        this.touzhenBottomFlag = touzhenBottomFlag;
    }

    public int getYaozhenTopFlag() {
        return yaozhenTopFlag;
    }

    public void setYaozhenTopFlag(int yaozhenTopFlag) {
        this.yaozhenTopFlag = yaozhenTopFlag;
    }

    public int getYaozhenBottomFlag() {
        return yaozhenBottomFlag;
    }

    public void setYaozhenBottomFlag(int yaozhenBottomFlag) {
        this.yaozhenBottomFlag = yaozhenBottomFlag;
    }

    public int getYaozhenLeftFlag() {
        return yaozhenLeftFlag;
    }

    public void setYaozhenLeftFlag(int yaozhenLeftFlag) {
        this.yaozhenLeftFlag = yaozhenLeftFlag;
    }

    public int getYaozhenRightFlag() {
        return yaozhenRightFlag;
    }

    public void setYaozhenRightFlag(int yaozhenRightFlag) {
        this.yaozhenRightFlag = yaozhenRightFlag;
    }

    public int getTuituoBeforeFlag() {
        return tuituoBeforeFlag;
    }

    public void setTuituoBeforeFlag(int tuituoBeforeFlag) {
        this.tuituoBeforeFlag = tuituoBeforeFlag;
    }

    public int getTuituoAfterFlag() {
        return tuituoAfterFlag;
    }

    public void setTuituoAfterFlag(int tuituoAfterFlag) {
        this.tuituoAfterFlag = tuituoAfterFlag;
    }

    public int getQianhouBeforeFlag() {
        return qianhouBeforeFlag;
    }

    public void setQianhouBeforeFlag(int qianhouBeforeFlag) {
        this.qianhouBeforeFlag = qianhouBeforeFlag;
    }

    public int getQianhouAfterFlag() {
        return qianhouAfterFlag;
    }

    public void setQianhouAfterFlag(int qianhouAfterFlag) {
        this.qianhouAfterFlag = qianhouAfterFlag;
    }
}
