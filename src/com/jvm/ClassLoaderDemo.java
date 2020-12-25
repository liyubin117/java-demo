package com.jvm;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ClassLoaderDemo {
    @Test
    public void testEquality() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader loader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    System.out.println(System.getProperty("user.dir"));
                    String fileName = "target/classes/" + name.replaceAll("\\.","/")+".class";
                    System.out.println(fileName);
                    InputStream is = new FileInputStream(fileName);
                    if(is == null){
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    return super.loadClass(name);
//                    e.printStackTrace();
                }
//                return super.loadClass(name);
            }
        };

        Object o = loader.loadClass("com.jvm.ClassLoaderDemo").newInstance();
        System.out.println(o.getClass());
        System.out.println(o instanceof ClassLoaderDemo);
    }
}
