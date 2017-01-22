package com.generic;
//泛型参数的限定
public class GenericDemo2{
    public static void main(String args[]){
        Info<Integer> i = new Info<Integer>() ;        // 使用String为泛型类型
        i.setVar(10) ;                            // 设置内容
        fun(i) ;
        
        Info<String> i2=new Info<String>();
        i2.setVar("200");
        fun(i2);
        
        //String上限不是Integer，无法调用
        //fun2(i2);
        
        fun3(i2);
        //Integer下限不是String，无法调用
        //fun3(i);
       
    }
    public static void fun(Info<?> temp){        // ?可以接收任意的泛型对象
        System.out.println("内容：" + temp) ;
    }
    public static void fun2(Info<? extends Number> temp){    //设置只接收上限为Number类型的对象  
        System.out.println("内容：" + temp) ;
    }
    public static void fun3(Info<? super String> temp){    //设置只接收下限为String类型的对象  
        System.out.println("内容：" + temp) ;
    }
};