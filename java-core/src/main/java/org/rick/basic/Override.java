//在Java语言中，命名冲突问题是用包（package）的概念来解决的,在定义包时，package语句必须写在Java源文件的最开始处
//在命名包名时，包名可以有层次结构，在一个包中可以包含另外一个包，可以按照如下的方法定义package语句:package 包名1.包名2…包名n;
package org.rick.basic;

//import 类的全局限定名（即包名+类名）；

/**
 * Created by Administrator on 2015-9-6.
 * 重写、访问控制、static和final
 *
 *
 */
class Foo {
    public void f(){
        System.out.println("Foo.f()");
    }
}

class Bar extends Foo {
    //重写
    public void f() {
        System.out.println("Bar.g()");
        //在子类重写的方法中，可以通过super关键字调用父类的版本
        super.f();
    }
}

public class Override {
    public static void main(String args[]){
        Bar obj1 = new Bar();
        obj1.f();

        Foo obj2 = new Bar();
        obj2.f();



    }
}
