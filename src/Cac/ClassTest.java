package Cac;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Administrator on 2015-10-15.
 * 自定义类
 * static
 * final
 * clone 封装
 * 显式域初始化
 */
class Employee {
    //静态域，即类域，只属于类
    private static int nextId = 1;

    //显式域初始化
    private int id2 = getId();

    //final用于基本类型域或不可变类的域，且构建对象必须得到初始化
    private String s;

    private int id;
    private Date hireDate;
    private double salary;
    private String name;

    public Employee (String name,double salary,int year,int month,int day,String s){
        setId();
        this.name = name;
        this.salary = salary;
        GregorianCalendar g = new GregorianCalendar(year,month-1,day);
        this.hireDate = g.getTime();
        //id2 = getId();
        this.s = s;
    }

    private void setId(){
        this.id = nextId;
        nextId++;
    }

    public int getId(){
        return this.id;
    }

    public int getId2(){
        return this.id2;
    }


    //静态方法，可以访问本类的静态域，无法操作对象
    public static int getnextId(){
        return nextId;
    }

    public String getName (){
        return this.name;
    }

    //返回的是私有变量的引用，即地址
    public Date getHireDate (){
        return this.hireDate;
    }
    //改进,返回私有变量的值拷贝
    public Date getHireDate2(){ return (Date)hireDate.clone(); }

    public Double getSalary(){
        return this.salary;
    }
//    每个类的方法可以访问本类任何一个对象的私有域
    public boolean salary_equals(Employee e){
        return this.getSalary().equals(e.getSalary());
    }

}

public class ClassTest {
    public static void main(String[] args){
/*如果需要返回一个可变数据域的拷贝，就应该使用clone，如果不使用就会出现如下预料之外的情况，即破坏了封装性。
 进一步分析可知，只要类中返回的私有变量并不是值，而是引用，为了保证封装性，需要使用clone*/
        Employee e1 = new Employee("ha",500.0,2014,7,1,"haha");
        Employee e2 = new Employee("he",500.0,2014,7,1,"hehe");
        double tenYearInMilliSeconds = 10*365.25*24*60*60*1000;

        Date d = e1.getHireDate();
        d.setTime(d.getTime()-(long)tenYearInMilliSeconds);
        //可见两者输出的内容一致，也就是说可以通过其他方式来改变类私有变量，而不只是规定的调用类修改器方法来改变，破坏了封装性
        System.out.println("d getTime:" + d.getTime());
        System.out.println("e1 hireDate getTime:" + e1.getHireDate().getTime());

        //改进
        Date e = e2.getHireDate2();
        e.setTime(e.getTime() - (long) tenYearInMilliSeconds);
        System.out.println("e getTime:" + e.getTime());
        System.out.println("e1 hireDate2 getTime:" + e2.getHireDate2().getTime());

        if( e1.salary_equals(e2) ){
            System.out.println("e1 salary is equal to e2");
        }

        System.out.println("e1的id:"+e1.getId());
        System.out.println("e2的id:"+e2.getId());
        //可以看出，域初始化是作用在构造函数初始化之前的
        System.out.println("e1的id2:"+e1.getId2());

        Employee e3 = new Employee("he",500.0,2014,7,1,"hehe");
        if (e2.equals(e3)){
            System.out.println("e2 is equal to e3");
        }
        if (e2.getSalary().equals(e3.getSalary())){
            System.out.println("e2 salary is equal to e3");
        }

        Double d1 = e2.getSalary();
        Double d2 = e3.getSalary();
        if (d1 == d2) {
            System.out.println("d1 == d2");
        }
        if (d1.equals(d2)){
            System.out.println("d1.equals(d2)");
        }

    }
}
