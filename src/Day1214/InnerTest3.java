package Day1214;

import java.awt.*;

/**
 * Created by Administrator on 2015-12-23.
 */
public class InnerTest3 {
    public static void main(String args[]){
        Outer3 out1 = new Outer3();  //外部类实例化对象
        out1.fun(100);

        new Outer3().fun(30);
    }
}

class Outer3{
    private String info = "Hello World!";

    public void fun(final int temp){   //在方法中定义内部类
        class Inner {
            public void print(){
                System.out.println("类中的属性："+info);  //直接访问外部类的私有属性
                System.out.println("方法中的参数："+temp);

            }
        }
        new Inner().print();  //通过内部类的实例化对象调用方法
    }
}
