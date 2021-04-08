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
                    System.out.println(System.getProperty("user.dir") + "\t" + this.getClass().getClassLoader());
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
                    System.out.println("IOException catched");
                    return super.loadClass(name);
//                    e.printStackTrace();
                }
//                return super.loadClass(name);
            }
        };

        Object o = loader.loadClass("com.jvm.ClassLoaderDemo").newInstance();
        System.out.println(o.getClass().getClassLoader());
        System.out.println(o.getClass().getClassLoader().getParent());
        System.out.println(o instanceof ClassLoaderDemo);
    }

    @Test
    public void testLayer(){
        ClassLoader loader = this.getClass().getClassLoader();
        System.out.println("系统类加载器：" + loader);
        System.out.println("系统类加载器的加载路径：" + System.getProperty("java.class.path"));

        ClassLoader extLoader = loader.getParent();
        System.out.println("扩展类加载器：" + extLoader);
        System.out.println("扩展类加载器的加载路径：" + System.getProperty("java.ext.dirs"));

        ClassLoader bootLoader = extLoader.getParent();
        System.out.println("启动类加载器：" + bootLoader);
        System.out.println("启动类加载器的加载路径：" + System.getProperty("sun.boot.class.path"));
    }
}
