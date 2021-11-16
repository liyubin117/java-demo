package org.rick;

import org.junit.Test;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionalInterfaceDemo {
    public static void main(String[] args) {
        new Thread(() -> System.out.println("very simple!")).start();
    }

    @Test
    public void testFunction() {
        Function<String, Boolean> func = x -> true;
        System.out.println(func.apply("hello"));
    }

    @Test
    public void testSupplier() {
        Supplier<String> func = () -> "this is a supplier";
        System.out.println(func.get());
    }

    @Test
    public void testConsumer() {
        Consumer<String> func = (x) -> System.out.println(x);
        func.accept("hello!");
    }

    @Test
    public void testPredicate() {
        Predicate<String> func = (x) -> x.equals("hello");
        System.out.println(func.test("hello"));
        System.out.println(func.test("hello2"));
    }

    @Test
    public void testCustom() {
        Consumer<String> consumer = (x) -> {
            System.out.println("test" + x);
        };
        CustomFunctionalInterface<String> func = (x) -> {
            x.accept("10");
            return "result";
        };
        System.out.println(func.test(consumer));
    }

}

@FunctionalInterface
interface CustomFunctionalInterface<T> {
    T test(Consumer<T> consumer);
}
