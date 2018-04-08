package com.jvm;

class SSClass
{
    static
    {
        System.out.println("SSClass");
    }
}    
class SuperClass extends SSClass
{
    static
    {
        System.out.println("SuperClass init!");
    }
 
    public static int value = 123;
 
    public SuperClass()
    {
        System.out.println("init SuperClass");
    }
}
class SubClass extends SuperClass
{
    static
    {
        System.out.println("SubClass init");
    }
 
    static int a;
 
    public SubClass()
    {
        System.out.println("init SubClass");
    }
}

class ConstClass
{
    static
    {
        System.out.println("ConstClass init!");
    }
    public static  final String HELLOWORLD = "hello world";
}

public class ClassLoad
{
    public static void main(String[] args)
    {
    	System.out.println("-------SubClass.value 通过子类引用父类的静态变量，不会引发子类初始化-------");
        System.out.println(SubClass.value);
        
        System.out.println("-------new SuperClass[10] 通过数组定义引用类，不会引发此类初始化------------");
        SuperClass[] sca = new SuperClass[10];
        
        System.out.println("-------ConstClass.HELLOWORLD 引用类的常量，不会引发此类初始化");
        System.out.println(ConstClass.HELLOWORLD);
    }
}
