package com.serial;

import java.io.Serializable;

//相同引用：A和B都引用Common
class Common implements Serializable {
    String c;

    public Common(String c) {
        this.c = c;
    }
}

class A implements Serializable {
    String a;
    Common common;
    
    public A(String a, Common common) {
        this.a = a;
        this.common = common;
    }

    public Common getCommon() {
        return common;
    }
}

class B implements Serializable {
    String b;
    Common common;
    
    public B(String b, Common common) {
        this.b = b;
        this.common = common;
    }

    public Common getCommon() {
        return common;
    }
}