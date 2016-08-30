package Day1214;

import java.awt.*;

/**
 * Created by Administrator on 2015-12-23.
 */
public class InnerTest {
    public static void main(String args[]){
        Outer out1 = new Outer();  //外部类实例化对象
        out1.fun();

        Outer.Inner in = new Outer.Inner(); //外部可直接调用静态内部类
        in.print();
    }
}

class Outer{
    private static String info = "Hello World!";

    static class Inner {
        public void print(){
            System.out.println(info);
        }
    }

    public void fun(){
        new Inner().print();
    }
}
