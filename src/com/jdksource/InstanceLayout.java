package com.jdksource;

import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

import java.util.HashMap;

public class InstanceLayout {
    @Test
    public void testObject() {
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        synchronized (o){
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }

    @Test
    public void testHashMap(){
        HashMap<Long, Long> map = new HashMap<>();
        map.put(10L, 20L);
        map.put(20L, 20L);
        System.out.println(ClassLayout.parseInstance(map).toPrintable());
        System.out.println(ClassLayout.parseInstance(map.get(10L)).toPrintable());

    }
}
