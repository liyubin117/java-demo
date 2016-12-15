package com.basic;

/**
 * Created by Administrator on 2015-9-8.
 * 访问控制
 * 将容易变化的、具体的实现细节封装起来，外界不可访问，而对外提供可调用的、稳定的功能，这样的意义在于：
 降低代码出错的可能性，更便于维护。
 当内部实现细节改变时，只要保证对外的功能定义不变，其他的模块不需要更改。
 在软件系统中，封装常常需要依靠一些访问控制修饰符来实现。
 private修饰的成员变量和方法仅仅只能在本类中调用，而public修饰的成员变量和方法可以在任何地方调用。
 private修饰的内容是对内实现的封装， 像刚刚案例中的水果， 就可以将它封装起来，因为，如果“公开”它将会增加维护的成本。
 public修饰的内容是对外提供的可以被调用的功能，需要相对稳定
 protected和默认访问控制也是两种访问修饰。其中，使用protected修饰的成员变量和方法可以被子类及同一个包中的类使用。
 而默认访问控制即不书写任何访问控制符，默认访问控制的成员变量和方法可以被同一个包中的类调用。
 对于类的修饰可以使用public和默认方式。 其中，public修饰的类可以被任何一个类使用，而默认访问控制的类只可以被同一个包中的类使用。
*/
class Point {
    private int x;
    private int y;
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public static int distance(Point p) {
        return (p.y-p.x);
    }
}

/*static关键字可以修饰成员变量，它所修饰的成员变量不属于对象的数据结构，而是属于类的变量，通常通过类名来引用static成员。
        当创建对象后，成员变量是存储在堆中的，而static成员变量和类的信息一起存储在方法区, 而不是在堆中，
        一个类的static成员变量只有“一份”（存储在方法区），无论该类创建了多少对象。*/
class Cat {
    private int age;
    private static int numOfCats;
    public Cat(int age) {
        this.age = age;
        System.out.println(++numOfCats+"只猫，年龄："+this.age);
    }
    public Cat(){}

    //final关键字修饰的方法不可以被重写。使一个方法不能被重写的意义在于：防止子类在定义新方法时造成的“不经意”重写
/*    public final void printCat(){
        System.out.println("这是猫");
    }*/
    /*final关键字修饰的类不可以被继承。使一个类不能被继承的意义在于：可以保护类不被继承修改，可以控制滥用继承对系统造成的危害。
    在JDK中的一些基础类库被定义为final的，例如：String、Math、Integer、Double 等等。自己定义的类也可以声明为final的*/
    public  void printCat(){
        System.out.println("这是猫");
    }
}


class Tank extends Cat {
    public void printCat(){
        System.out.println("这也是猫");
    }
}

public class AccessControl{
    //static final 修饰的成员变量称为常量，必须声明同时初始化，并且不可被改变。常量建议所有字母大写。
    static final int NUM = 10;
    private static final int PRI1 = 100;
    public static void main(String[] args)
    {
        Point p1 = new Point(1, 2);
        Point p2 = new Point(3, 5);
        //p1.x = 100;
        int a = p1.distance(p1);
        System.out.println(a);
        int d = p1.distance(p2);
        System.out.println(d);

        Cat cat1 = new Cat(1);
        Cat cat2 = new Cat(5);
        Cat cat3 = new Cat(7);

        /*static方法的作用在于提供一些“工具方法”和“工厂方法”*/
      /*  RandomUtils.nextInt()
        StringUtils.leftPad(String str,  int size,  char padChar);
        Math.sqrt()   Math.sin()   Arrays.sort()*/

        System.out.println(PRI1);
        System.out.println(NUM);


    }
}