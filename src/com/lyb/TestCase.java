package com.lyb;


import org.junit.Test;

import java.util.*;

public class TestCase {

    @Test
    public void test1(){
        String str = "jdbc:presto://fuxi-luoge-76:7988;user=hive;";
        System.out.println(str.replace(";","?").substring(0,str.length()-1));
    }

    @Test
    public void test2(){
        String str = "impala::ball_ods_kudu.ods_itembuy_123";
        System.out.println(str.substring(str.indexOf("::")+2,str.lastIndexOf(".")));

        System.out.println(str.contains("::") && str.contains("."));

    }

    @Test
    public void test3(){
        Map<String, Set<String>> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        set.addAll(Arrays.asList("a","b"));
        set.forEach(x -> {
            Set<String> tmp = map.getOrDefault("key",new HashSet<>());
            tmp.add(x);
            map.put("key", tmp);
        });
        System.out.println(map);
    }
}
