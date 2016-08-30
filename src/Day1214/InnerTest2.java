package Day1214;

import java.awt.*;

/**
 * Created by Administrator on 2015-12-23.
 */
public class InnerTest2 {
    public static void main(String args[]){
        Outer2 out1 = new Outer2();  //外部类实例化对象
        out1.fun();

        Outer2.Inner in = out1.new Inner(); //在外部访问内部类
        in.print();
    }
}

class Outer2{
    private String info = "Hello World!";

    class Inner {
        public void print(){
            System.out.println(info);
        }
    }

    public void fun(){
        new Inner().print();
    }
}
