package com.function;

import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo {
    Stream<String> stream;
    Consumer print = x -> System.out.println(x);

    @Before
    public void setUp() {
        stream = Stream.of("I", "love", "you");
    }

    @Test
    public void forEach() {
        stream.forEach(print);
    }

    @Test
    public void filter() {
        stream.filter(x -> x.length() > 1)
                .forEach(print);
    }

    @Test
    public void distinct() {
        stream = Stream.of("I", "love", "you", "love");
        stream.distinct()
                .forEach(print);
    }

    @Test
    public void sorted() {
        stream.sorted()
                .forEach(print);

        // stream具有可消费性，一旦遍历过就失效了，若要再次使用必须再次生成
        stream = Stream.of("I", "love", "you", "love");
        stream.sorted((x, y) -> x.length() - y.length())
                .forEach(print);
    }

    @Test
    public void map() {
        stream.map(x -> x.toUpperCase())
                .forEach(print);
    }

    @Test
    public void flatMap() {
        Stream<List<Integer>> stream = Stream.of(Arrays.asList(1, 2), Arrays.asList(3, 4, 5));
        stream.flatMap(list -> list.stream())
                .forEach(print);
    }

    @Test
    public void reduce() {
        // 找出最长的单词
        Stream<String> stream = Stream.of("I", "love", "you", "too");
        Optional<String> longest = stream.reduce((s1, s2) -> s1.length() >= s2.length() ? s1 : s2);
        //Optional<String> longest = stream.max((s1, s2) -> s1.length()-s2.length());
        System.out.println(longest.get());
    }

    @Test
    public void sum() {
        // 求单词长度之和
        Integer lengthSum = stream.reduce(0, // 初始值
                (sum, str) -> sum + str.length(), // 累加器
                (a, b) -> a + b); // 部分和拼接器，并行执行时才会用到
        // int lengthSum = stream.mapToInt(str -> str.length()).sum();
        System.out.println(lengthSum);
    }

    @Test
    public void collect2List(){
        // 将Stream转换成容器或Map
        List<String> list = stream.collect(Collectors.toList());
        System.out.println(list);
    }

    @Test
    public void collect2Set(){
        // 将Stream转换成容器或Map
         Set<String> set = stream.collect(Collectors.toSet());
        System.out.println(set);
    }

    @Test
    public void collect2Map(){
        // 将Stream转换成容器或Map
         Map<String, Integer> map = stream.collect(Collectors.toMap(Function.identity(), String::length));
        System.out.println(map);
    }
}
