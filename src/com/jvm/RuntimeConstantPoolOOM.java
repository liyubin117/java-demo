package com.jvm;

import org.junit.Test;

public class RuntimeConstantPoolOOM {
    /**
     * 1.7  true    false
     * 1.8  true    true
     */
    public static void main(String[] args) {
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);
        System.out.println(str1);

        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);
    }

    /**
     * jdk6 false false
     * jdk7 false true
     */
    @Test
    public void test1(){
        //生成常量池1 s=堆1
        String s = new String("1");
        s.intern();
        //s2=常量池1
        String s2 = "1";
        //自然为false
        System.out.println(s == s2);

        //生成常量池1 s3=堆11
        String s3 = new String("1") + new String("1");
        //11不在常量池，在jdk6直接在常量池生成新的11,即使堆里有11。jdk7的常量池从PermGen划分出来，生成的常量池对象引用堆里的11，不用另存一份
        s3.intern();
        //s4即上一步创建的常量池对象
        String s4 = "11";
        //jdk6 false; jdk7 true
        System.out.println(s3 == s4);
    }

    /**
     * jdk6 false false
     * jdk7 false false
     */
    @Test
    public void test2(){
        String s = new String("1");
        String s2 = "1";
        s.intern();
        System.out.println(s == s2);

        String s3 = new String("1") + new String("1");
        String s4 = "11";
        s3.intern();
        System.out.println(s3 == s4);
    }
}
