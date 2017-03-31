package com.innerclass;


/**
 * 常规/成员内部类
 * Created by Administrator on 2015-12-23.
 */
public class NormalInner {
    public static void main(String args[]){
        Outer2 out1 = new Outer2();  //外部类的方法实例化内部类对象，再直接用外部类对象的方法来访问内部类
        out1.fun();

        Outer2.Inner in = out1.new Inner(); //在外部访问内部类，两种方法
        in =new Outer2().new Inner();
        in.print();
        
        new Outer2().new InnerHello().printHello();
    }
}

class Outer2{
    private String info = "Hello World!";
    private static String staticInfo="Static Info";
    
    //外部类和内部类间可以互相访问private对象
    class Inner {
    	private String InnerInfo = "Inner Info";
        public void print(){
        	//成员内部类可以访问外部类的类变量、实例变量
            System.out.println(info+" from Inner InnerInfo");
            System.out.println(staticInfo+" from Outer staticInfo");
        }
    }
    
    //内部类可能通过外部类.this.外部实例方法调用外部实例方法，外部类.外部静态方法调用外部静态方法
    class InnerHello{
    	public void printHello(){
        	Outer2.this.hello();
        	Outer2.staticHello();
    	}
    }

    public void fun(){
        new Inner().print();
        //外部类可以访问成员内部类对象的private对象
        System.out.println(new Inner().InnerInfo);
    }
    
    //外部类实例方法
    public void hello(){
    	System.out.println("hello liyubin from Outer2 hello()");
    }
    //外部类静态方法
    public static void staticHello(){
    	System.out.println("hello liyubin from Outer2 staticHello()");
    }
}
