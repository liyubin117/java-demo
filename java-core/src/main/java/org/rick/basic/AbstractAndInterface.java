package org.rick.basic;

import java.lang.Override;

/**
 * Created by Administrator on 2015-9-8.
 * 抽象类、接口和内部类
 */
/*由abstract修饰的方法为抽象方法，抽象方法即只有方法的定义，没有方法体实现，用一个分号结尾。若将抽象方法包含在类中，
        则该类也应该为抽象的，可以理解为，该类也不完整。抽象类由abstract关键字声明。
抽象类是不能实例化对象的，而一个类不能实例化是没有意义的，所以，需要定义类来继承抽象类。
        而如果一个类继承了抽象类，则其必须重写其抽象方法（变不完整为完整），除非该类也声明为抽象类。
抽象类可包含非抽象方法*/
abstract class Shape {
    private double c=10;
    public String content = "Shape";
    public Shape(double c) {
        System.out.println("Constructor, yes arg");
        this.c = c;
    }
    public Shape(){} {
        System.out.println("Constructor, no arg");
    }
    public abstract double area();
    public void notAbstract() {System.out.println("a non-abstract function");}
    public void func() { System.out.println("func from Shape"); }
}

class Square extends Shape {
    private double c;
    public String content = "Square";
    public Square(double c) {
        super(c);
    }
    @Override
    public double area() {
        return 0.0625*c*c;
    }
    public void getC() {System.out.println(c);}
    @Override
    public void func() { System.out.println("func from Square"); }
}

class Circle extends Shape{
    private double c;
    public Circle(double c) {
//        super(c);
        this.c = c;
    }
    public double area() {
        return 0.0796*c*c;
    }
}

//接口。接口可以看成是特殊的抽象类。即只包含抽象方法和常量的抽象类。可以通过interface关键字来定义接口
interface Runner {
    public static int DEFAULT_SPEED = 100;
    public void run();  //此处可省略abstract，因其默认即abstract
}

/*
与继承不同，一个类可以实现多个接口，实现的接口直接用逗号分隔。当然，该类需要实现这些接口中定义的所有方法；
一个类可以通过implements关键字”实现”接口。一个类实现了某个接口后必须实现该接口中定义的所有方法。
*/
class AmericanCurl implements Runner {
public void run() {
        System.out.println("run...");
        }
}

/*
接口间可以存在继承关系，一个接口可以通过extends关键字继承另外一个接口。子接口继承了父接口中定义的所有方法。
对子接口的实现需要实现父接口和子接口中定义的所有方法
接口不可包含已实现的方法
*/
interface Hunter  extends Runner {
    public void hunt();
}

class ChinaHunter implements Hunter {
    public void run() {}
    public void hunt() {}
}

public class AbstractAndInterface {
    public static void main(String args[]){
        /*抽象类不能实例化
        Shape shape1 = new Shape(1);*/

        System.out.println("----Square----");
        Square square1 = new Square(1000000);
        System.out.println(square1.area());
        square1.getC();

        System.out.println("----Circle----");
        Circle circle1 = new Circle(1);
        System.out.println(circle1.area());
        circle1.notAbstract();

        System.out.println("----父类引用指向子类对象----");
        Shape s = new Square(100);
        s.func();  //子类方法
//        s.getC();  //父类无此方法
        System.out.println(s.content);  //父类值

        System.out.println("--------");
       /* 定义抽象类的意义在于：
        为其子类提供一个公共的类型（父类引用指向子类对象）；
        封装子类中的重复内容（成员变量和方法）;
        定义有抽象方法，子类虽然有不同的实现，但该方法的定义是一致的。(子类需要实现此抽象方法)。*/
        Shape[] sArr = {new Circle(100),  new Square(100),
                new Circle(200), new Square(150) };
        for(int i=0; i<sArr.length; i++) {
            Shape shape = sArr[i];
            System.out.println(shape.area());
        }

    }

}
