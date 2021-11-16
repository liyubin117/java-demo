package org.rick;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class CollectionFunc {
    HashMap<Integer, String> map = new HashMap<>();

    @Before
    public void setUp(){
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("a","b","c");
        CollectionFunc.mapper(list, t -> System.out.println(t.toUpperCase()));

        System.out.println(CollectionFunc.create(list, t -> t+"test"));

        // 使用Lambda表达式实现
        ArrayList<String> al = new ArrayList<>(Arrays.asList("I", "love", "you", "too"));
        al.replaceAll(str -> {
            if(str.length()>3)
                return str.toUpperCase();
            return str;
        });
        System.out.println(al);


    }

    @Test
    public void testMapForEach(){
        // 使用forEach()结合Lambda表达式迭代Map
        map.forEach((k, v) -> System.out.println(k + "=" + v));
    }

    @Test
    public void testMapReplaceAll(){
        map.replaceAll((k,v) -> v.toUpperCase());
        System.out.println(map);
    }

    @Test
    public void testMapMerge(){
        //将新的信息拼接到原来的信息上
        BiFunction<Integer, String, String> function = (k,v) -> v==null ? "new" : v.concat("new");
        map.compute(1, function);
        map.compute(10, function);
        System.out.println(map);
    }

    @Test
    public void testMapCompute(){
        //将新的信息拼接到原来的信息上

        map.merge(1, "new", (v1, v2) -> v1+v2);
        map.merge(10, "new", (v1, v2) -> v1+v2);
        System.out.println(map);
    }

    @Test
    public void testMapGetOrDefault(){
        // Java7以及之前做法
        if(map.containsKey(4)){ // 1
            System.out.println(map.get(4));
        }else{
            System.out.println("NoValue");
        }
        // Java8使用Map.getOrDefault()
        System.out.println(map.getOrDefault(4, "NoValue")); // 2
    }

    public static <E> List<E> mapper(List<E> list, Consumer<E> function){
        for(E element : list){
            function.accept(element);
        }
        return list;
    }

    public static <E,T> List<T> create(List<E> list, Function<E, T> function){
        List<T> newList = new ArrayList<>();
        for(E element : list){
            newList.add(function.apply(element));
        }
        return newList;
    }

}
