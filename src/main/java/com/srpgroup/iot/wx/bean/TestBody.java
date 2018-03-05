package com.srpgroup.iot.wx.bean;

import java.io.Serializable;

public class TestBody extends TestBody1 {
    @Override
    public String toString() {
        return "TestBody{" +
                "c='" + c + '\'' +
                "} " + super.toString();
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    private String c;
}