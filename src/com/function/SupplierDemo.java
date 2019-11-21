package com.function;

import java.util.Arrays;
import java.util.List;

@FunctionalInterface
interface Supplier<T> {
    T get();
}
 
class  Car {
    //Supplier是jdk1.8的接口，这里和lamda一起使用了
    public static Car create(final Supplier<Car> supplier) {
        return supplier.get();
    }
 
    public static void collide(final Car car) {
        System.out.println("Collided " + car.toString());
    }
 
    public void follow(final Car another) {
        System.out.println("Following the " + another.toString());
    }
 
    public void repair() {
        System.out.println("Repaired " + this.toString());
    }
}

public class SupplierDemo{
    public static void main(String[] args) {
        //构造器引用，语法是Class::new，或者更一般的Class< T >::new实例
        final Car car = Car.create( Car::new );
        final List< Car > cars = Arrays.asList( car );

        //静态方法引用 Class::static_method
        cars.forEach( Car::collide );

        //特定类的无参实例方法引用，与静态方法引用的用法一样
        cars.forEach( Car::repair );

        //特定对象的有参实例方法引用，instance::method
        final Car police = Car.create( Car::new );
        cars.forEach( police::follow );

    }
}