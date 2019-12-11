package com.function;


public class Concept {
    //不只是一个语法糖，匿名内部类仍然是一个类，只是不需要手动取名而已，编译器会自动为该类取名。lambda表达式会被编译为主类的私有静态方法，再由invokedynamic指令调用执行
    Runnable r1 = () -> { System.out.println(this.getClass()); };
    public static void main(String[] args) {
        new Concept().r1.run();
        new Runnable(){
            @Override
            public void run() {
                System.out.println(this.getClass());
            }

        }.run();
    }
}