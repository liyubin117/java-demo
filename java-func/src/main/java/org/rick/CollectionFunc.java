package org.rick;


import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    @Test
    public void testCollectors() {
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> map1 = new HashMap<>();
        map1.put("k1", "v1");
        map1.put("k2", "v2");
        Map<String, String> map2 = new HashMap<>();
        map2.put("k1", "value1");
        map2.put("k2", "value2");
        list.add(map1);
        list.add(map2);
        List<String> result = list.stream().map(x -> x.get("k1")).collect(Collectors.toList());
        System.out.println(result);
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
