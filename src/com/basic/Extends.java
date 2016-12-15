package com.basic;

/**
 * Created by Administrator on 2015-8-29.
 * 在JAVA中，有java程序、虚拟机、操作系统三个层次，其中java程序与虚拟机交互，而虚拟机与操作系统交互。编译好的java字节码文件运行在JVM中。
 程序中无论代码还是数据，都需要存储在内存中，而java程序所需内存均由JVM进行管理分配，开发者只需关心JVM是如何管理内存的，而无需关注某种操作系统是如何管理内存的，这就保证了java程序的平台无关性。
 JVM会将申请的内存从逻辑上划分为三个区域：堆、栈、方法区。
 */
class Cell{
    int row;
    int col;
    public Cell(int row,int col){
        this.row = row;
        this.col = col;
    }
    public void printCell(){
        System.out.println("row:"+row+" col:"+col);
    }
}
class Tfa {
    Cell [] cells;
    public  Tfa (){
        cells = new Cell [4];
    }
   public void moveLeft(){
       System.out.println("after Left, result:");
       for (int i=0;i<4;i++){
           cells[i].col=cells[i].col-1;
           System.out.println("cell["+i+"] row:"+cells[i].row+" col:"+cells[i].col);
       }
   }
    public void moveRight(){
        System.out.println("after Right, result:");
        for (int i=0;i<4;i++){
            cells[i].col=cells[i].col+1;
            System.out.println("cell["+i+"] row:"+cells[i].row+" col:"+cells[i].col);
        }
    }
}

class TfaT extends Tfa{
      /* 使用继承可以实现代码的重用，在java语言中，需要通过extends关键字实现类的继承。继承完成后，子类（Sub class）可以继承父类（Super class）的成员变量及成员方法，
    同时子类也可以定义自己的成员变量和成员方法。届时，子类将具有父类的成员及本类的成员。
    Java语言不支持多重继承，即：一个类只能继承一个父类，但一个父类可以有多个子类
        java规定，子类在构造之前必须先构造父类,若父类没有提供无参的构造方法，则会出现编译错误。
        在子类构造方法中没有写super调用父类构造方法，这时编译器会默认添加super()来调用父类的无参构造方法*/
    public TfaT(int row,int col){
        cells[0] = new Cell(row,col);
        cells[1] = new Cell(row,col+1);
        cells[2] = new Cell(row,col+2);
        cells[3] = new Cell(row+1,col+1);

    }

    public void printTfaT(){
        System.out.println("before move, result:");
        for (int i=0;i<4;i++){
            System.out.println("cell["+i+"] row:"+cells[i].row+" col:"+cells[i].col);
        }
    }
}

public class Extends {
    //new Cell（）所创建的对象存储在堆中，引用类型变量c在栈中分配，为对象在堆中的地址信息
    Cell c = new Cell(1,2);

     /* 当声明好对象之后，对该对象（堆中的Cell）的访问需要依靠引用变量(栈中的c)，那么当一个对象没有任何引用时，该对象被视为废弃的对象，属于被回收的范围，同时该对象中的所有成员变量也随之被回收。
    可以这样认为，成员变量的生命周期为：从对象在堆中创建开始到对象从堆中被回收结束。*/
    //c = null ;

    //内存泄露是指，不再被使用的内存没有被及时的回收，严重的内存泄露会因过多的内存占用而导致程序的崩溃。在程序中应该尽量避免不必要的内存浪费。
    //GC(Garbage Collection)线程判断对象是否可以被回收的依据是该对象是否有引用来指向，因此，当确定该对象不再使用时，应该及时的将其引用设置为null，这样，该对象即不再被引用，属于可回收的范围。
    //。一般情况下，当我们需要GC线程即刻回收无用对象时，可以调用System.gc（）方法

    //栈用于存储程序运行时在方法中声明的所有的局部变量,若变量为值类型，则在栈中存储的就是该变量的值。若变量为引用类型，则在栈中存储的是堆中对象的地址。

    /*一个运行的Java程序从开始到结束会有多次方法的调用。JVM会为每一个方法的调用在栈中分配一个对应的空间，这个空间称为该方法的栈帧。一个栈帧对应一个正在调用中的方法，栈帧中存储了该方法的参数、局部变量等数据。当某一个方法调用完成后，其对应的栈帧将被清除，局部变量即失效。
    成员变量与局部变量的差别如下：
    局部变量：
            1) 定义在方法中；
            2) 没有默认值，必须自行设定初始值；
            3) 方法被调用时，存在栈中，方法调用结束时局部变量从栈中清除；
    成员变量：
            1) 定义在类中，方法外；
            2) 由系统设定默认初始值，可以不显式初始化；
            3) 所在类被实例化后，存在堆中，对象被回收时，成员变量失效；*/

    //方法区用于存放类的信息，Java程序运行时，首先会通过类装载器载入类文件的字节码信息，经过解析后将其装入方法区。类的各种信息（包括方法）都在方法区存储
    //类在实例化对象时，多个对象会拥有各自在堆中的空间，但所有实例对象是共用在方法区中的一份方法定义的。意味着，方法只有一份。


    public static void main(String args[]){
        TfaT TfaTs = new TfaT(1,2);
        TfaTs.printTfaT();
        TfaTs.moveLeft();

        //定义父类型的引用可以指向子类的对象
        Tfa Tfas2 = new TfaT (1,2);
        for (int i=0;i<4;i++){
            System.out.println("cells["+i+"] "+"row:"+Tfas2.cells[i].row+" col:"+Tfas2.cells[i].col);
        }
    }

}
