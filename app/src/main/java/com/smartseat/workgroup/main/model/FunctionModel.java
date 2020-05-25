package com.smartseat.workgroup.main.model;

import java.io.Serializable;

/**
 * 座椅功能模型
 */
public class FunctionModel implements Serializable{
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
    /**
     * 氛围灯档位--一档：1，二档：2，三档：3
     */
    private int vembientGear;

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

    public int getVembientGear() {
        return vembientGear;
    }

    public void setVembientGear(int vembientGear) {
        this.vembientGear = vembientGear;
    }
}
