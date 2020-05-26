package com.smartseat.workgroup.main.model;

import java.io.Serializable;

/**
 * 座椅功能模型
 */
public class FunctionModel implements Serializable {
    /**
     * 是否开启加热档位功能
     */
    private boolean isOpenHeatingGear;
    /**
     * 是否开启通风档位功能
     */
    private boolean isOpenVentilation;
    /**
     * 是否开启按摩档位功能
     */
    private boolean isOpenMassage;
    /**
     * 是否开启氛围灯
     */
    private boolean isOpenVembient;
    /**
     * 加热档位--一档：1，二档：2，三档：3
     */
    private int heatingGear;
    /**
     * 通风档位--一档：1，二档：2，三档：3
     */
    private int ventilationGear;
    /**
     * 按摩档位--一档：1，二档：2
     */
    private int massageGear;

    public boolean isOpenHeatingGear() {
        return isOpenHeatingGear;
    }

    public void setOpenHeatingGear(boolean openHeatingGear) {
        isOpenHeatingGear = openHeatingGear;
    }

    public boolean isOpenVentilation() {
        return isOpenVentilation;
    }

    public void setOpenVentilation(boolean openVentilation) {
        isOpenVentilation = openVentilation;
    }

    public boolean isOpenMassage() {
        return isOpenMassage;
    }

    public void setOpenMassage(boolean openMassage) {
        isOpenMassage = openMassage;
    }

    public boolean isOpenVembient() {
        return isOpenVembient;
    }

    public void setOpenVembient(boolean openVembient) {
        isOpenVembient = openVembient;
    }

    public int getHeatingGear() {
        return heatingGear;
    }

    public void setHeatingGear(int heatingGear) {
        this.heatingGear = heatingGear;
    }

    public int getVentilationGear() {
        return ventilationGear;
    }

    public void setVentilationGear(int ventilationGear) {
        this.ventilationGear = ventilationGear;
    }

    public int getMassageGear() {
        return massageGear;
    }

    public void setMassageGear(int massageGear) {
        this.massageGear = massageGear;
    }

}
