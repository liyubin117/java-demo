package org.rick.basic;

/**
 * Created by Administrator on 2015-8-28.
 * 对象和类（下） 数组
 */
class Cells{
    int row;
    int col;
    /*    构造方法是在类中定义的方法， 但不同于其他的方法，构造方法的定义有如下两点规则：
    构造方法的名称必须与类名相同。
    构造方法没有返回值，但也不能写void。
    Java语言中的构造方法常常用于实现对对象成员变量的初始化
    当类定义了构造方法后，Java编译器将不再添加默认的构造方法*/
/*    public Cells(int row1,int col1) {
        row = row1;
        col = col1;
    }*/
//    this关键字用在方法体中，用于指代调用该方法的当前对象，简单的说：哪个对象调用方法，this指的就是哪个对象。严格来讲，在方法中需要通过this关键字指明当前对象。
    public Cells(int row,int col){
        this.row = row;
        this.col = col;
    }
    public void printCells(){
        System.out.println("row:"+row+" col:"+col);
    }
}

public class ArrayDemo1 {
    //Java语法规定，一个类中不可以有两个方法签名完全相同的方法，即：一个类中不可以有两个方法的方法名和参数列表都完全相同，但是，如果一个类的两个方法只是方法名相同而参数列表不同，是可以的。
    //Java语言中，允许多个方法的名称相同，但参数列表不同，此种方式称为方法的重载
    public boolean pay(double money){return true;}
    public boolean pay(double money,byte a){return true;}
    public static void main(String args[]){
        Cells c1 = new Cells(9,9);
        c1.printCells();

        int [] arr = new int [3];
        //new Cells[4]实际是分配了4个空间用于存放4个Cells类型的引用，并赋初始值为null，而并非是分配了4个Cells类型的对象。
        Cells [] Cells = new Cells [3];
        Cells[0] = new Cells(1,2);
        Cells[1] = new Cells(3,4);
        Cells[2] = new Cells(5,6);
        //数组本身也是一种数据类型，当然也可以作为数组的元素
        int [][] arr2 = new int[3][];
        arr2[0] = new int [2];
        arr2[1] = new int [3];
        arr2[2] = new int [2];
        arr2[1][1] =100;

        int a = 10, b = 10;
        int[][] arr3 = new int[a][b];
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                arr3[i][j] = i * j;
            }
        }
        for (int i = 1; i < a; i++) {
            for (int j = 1; j < b; j++) {
                System.out.print(i + "*" + j + "=" + arr3[i][j] + "\t");
            }
            System.out.print("\n");
        }
    }
}
