package Day06;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Administrator on 2015-10-24.
 */
abstract class Person {
    private String name;

    public Person (String name){
        this.name = name;
    }
    public String getName (){
        return this.name;
    }
    public abstract String getDescription();
}

class Students extends Person{
    private String major;

    public Students(String name,String major){
        super(name);
        this.major = major;
    }

    public String getDescription(){
        return super.getName()+" is a student,major in "+this.major;
    }

    @Override
    public String toString() {
        return getClass().getName()+"[name="+super.getName()+" major="+this.major+"]";
    }
}


public class AbstractTest {
    //参数数量可变的方法
    public static double max (double... values) {
        double d1 = Double.MIN_VALUE;
        for (double v:values){
            if (v>d1) {
                d1 = v;
            }
        }
        return d1;
    }

    public static void main(String[] args) {
        Students stu1 = new Students("li","math");
        System.out.println(stu1.getDescription());

        Person stu2 = new Students("li2","english");
        System.out.println(stu2.getDescription());

        //getClass返回一个对象所属的类,getClass().getName()返回一个对象所属类的名字
        System.out.println(stu1.getClass());
        System.out.println(stu2.getClass());
        System.out.println(stu2.getClass().getName());
        System.out.println(stu2);

        int [] x = {1,2,7,8,9,3,4};
        System.out.println(x.toString());
        System.out.println(Arrays.toString(x));
        int [][] y = new int [3][4];
        for (int i=0;i<3;i++)
            for (int j=0;j<4;j++)
                y[i][j] = i+j;
        System.out.println(Arrays.deepToString(y));

        //泛型数组类型
        ArrayList<Students> stu = new ArrayList<>();
        stu.ensureCapacity(1);
        stu.add(new Students("li3", "math"));
        stu.add(new Students("li4", "english"));
        System.out.println(stu.toString());
        System.out.println(stu.size());
        stu.ensureCapacity(100);
        stu.trimToSize();
        //访问数组列表元素
        System.out.println(stu.get(0).toString());
        //替换数组列表元素
        stu.set(1,new Students("li5","computer"));
        System.out.println(stu.get(1).toString());

        //对象包装器类
        //需要使用equals进行值的相等性比较，不会因值的不同而不同，可得到一个确定的结论。
        Integer int1 = 3;
        Integer int2 = 3;
        if(int1 == int2) {
            System.out.println("3 ==");
        }
        int1 = 1000;
        int2 = 1000;
        if(int1 == int2){
            System.out.println("1000 ==");
        }
        if(int1.equals(int2)){
            System.out.println("1000 equals");
        }

        System.out.println(max(1,2.5,9.88,7.32,5.32323));
    }
}
