package com.function;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class FunctionDemo {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("a","b","c");
        FunctionDemo.mapper(list, t -> System.out.println(t.toUpperCase()));

        System.out.println(FunctionDemo.create(list, t -> t+"test"));

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
