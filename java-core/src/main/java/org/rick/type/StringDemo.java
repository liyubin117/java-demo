package org.rick.type;

import org.junit.Test;

import java.sql.Timestamp;
import java.util.Arrays;

public class StringDemo {
    @Test
    public void format(){
        Timestamp t = new Timestamp(System.currentTimeMillis());
        System.out.println(String.format("now is:%s",t));
    }

    @Test
    public void test2(){
        System.out.println("http://www.hello.com/".replaceAll("/$",""));
    }

    @Test
    public void test3(){
        String str = "http://www.hello.com/";
        System.out.println(str.substring(str.length()-1));
    }

    @Test
    public void testSplit() {
        String str = "a b  cc d ";
        System.out.println(Arrays.toString(str.split(" ")));
        System.out.println(Arrays.toString(str.split("( )+")));
    }
}