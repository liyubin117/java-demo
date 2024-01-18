package org.rick.classloader;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ClassLoaderTest {
    @Test
    public void testEquality() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader loader = this.getClass().getClassLoader();
        Object o = loader.loadClass("org.rick.classloader.ClassLoaderTest").newInstance();
        System.out.println(o.getClass().getClassLoader());
        System.out.println(o.getClass().getClassLoader().getParent());
        System.out.println(o instanceof ClassLoaderTest);

        Object o2 = new MySubClassLoader().loadClass("org.rick.classloader.ClassLoaderTest").newInstance();
        System.out.println(o2.getClass().getClassLoader());
        System.out.println(o2.getClass().getClassLoader().getParent());
        Assertions.assertNotSame(o.getClass(), o2.getClass());
        Assertions.assertNotSame(o, o2);

        Object o3 = new MySubClassLoader().loadClass("org.rick.classloader.ClassLoaderTest").newInstance();;
        Assertions.assertNotSame(o, o3);
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

    @Test
    public void testContextClassLoader() {
        System.out.println(Thread.currentThread().getContextClassLoader());
    }
}

class MyClassLoader extends ClassLoader {
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
        }
    }

    @Override
    public String toString() {
        return "this is my class loader";
    }
}

class MySubClassLoader extends MyClassLoader {
    @Override
    public String toString() {
        return "this is my sub class loader";
    }
}
