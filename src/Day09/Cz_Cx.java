package Day09;

/**
 * Created by Administrator on 2015-9-6.
 * 重载： 是指在一个类中定义多个方法名相同但参数列表不同的方法，在编译时，根据参数的个数和类型来决定绑定哪个方法。
 * 重写： 是指在子类中定义和父类完全相同的方法，在程序运行时，根据对象的类型（而不是引用类型）而调用不同的方法。
 *
 */
class Super {
    public void f() {
        System.out.println ("super.f()");
    }
}
class Sub extends Super {
    public void f() {
        System.out.println ("sub.f()");
    }
}
class Goo {
    public void g(Super obj) {
        System.out.println ("g(Super)");
        obj.f();
    }
    public void g(Sub obj) {
        System.out.println ("g(Sub) ");
        obj.f();
    }
}
public class Cz_Cx{
    public static void main(String[] args){
        Super obj = new Sub();
        Goo goo = new Goo();
      /*  重载遵循所谓“编译期绑定”，即在编译时根据参数变量的类型判断应该调用哪个方法， 因为变量obj为Super类型引用， 所以，Goo的g(Super)被调用，先输出g(Super)。
        重写遵循所谓“运行期绑定”，即在运行的时候，根据引用变量所指向的实际对象的类型来调用方法，因为obj实际指向的是子类Sub的对象，因此，子类重写后的f方法被调用，即sub.f()。*/
        goo.g(obj);
    }
}

