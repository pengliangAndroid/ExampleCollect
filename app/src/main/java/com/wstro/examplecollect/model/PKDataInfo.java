package com.wstro.examplecollect.model;

/**
 * ClassName: PKDataInfo
 * Function:
 * Date:     2017/8/27 17:40
 *
 * @author pengl
 * @see
 */
public class PKDataInfo {
    private String name;

    private float value1;
    private float value2;


    public PKDataInfo(String name, float value1, float value2) {
        this.name = name;
        this.value1 = value1;
        this.value2 = value2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getValue1() {
        return value1;
    }

    public void setValue1(float value1) {
        this.value1 = value1;
    }

    public float getValue2() {
        return value2;
    }

    public void setValue2(float value2) {
        this.value2 = value2;
    }
}
