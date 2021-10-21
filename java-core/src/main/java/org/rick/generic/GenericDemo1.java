package org.rick.generic;
//?的用法
public class GenericDemo1{
    public static void main(String args[]){
        Info<String> i = new Info<String>() ;        // 使用String为泛型类型
        i.setVar("MLDN") ;                            // 设置内容
        fun(i) ;
        //泛型对象进行引用传递的时候，类型必须一致
        //fun2(i);									
        //使用“?”意味着只能接收，不能修改
        //Info<?> i2=new Info<String>();
        //i2.setVar("MLDN");
    }
    public static void fun(Info<?> temp){        // ?可以接收任意的泛型对象
        System.out.println("内容：" + temp) ;
    }
    public static void fun2(Info<Object> temp){      
        System.out.println("内容：" + temp) ;
    }
};