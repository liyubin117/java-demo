package com.jdksource;

import org.openjdk.jol.info.ClassLayout;

public class InstanceLayout {
    public static void main(String[] args) {
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
    }
}
