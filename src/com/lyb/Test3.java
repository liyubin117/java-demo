package com.lyb;

public class Test3 {
    private final boolean f1;

    public Test3(boolean f){
        f1 = f;
        System.out.println(f1);
    }

    public static void main(String[] args) {
        new Test3(true);
    }
}
