package org.rick.useful;


import org.junit.Test;

import java.sql.Timestamp;

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
}
