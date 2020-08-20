package com.reneuby.webapi;

import java.io.Serializable;

public class WebApiCoeff implements Serializable {
    private String a;
    private String b;
    private String c;

    public WebApiCoeff() {
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }
}
