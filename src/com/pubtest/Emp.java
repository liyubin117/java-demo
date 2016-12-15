package com.pubtest;

public class Emp implements Cloneable {
    String name;
    int age;
    double salary;
/*    public static void printEmpInfo(Emp emp){
        System.out.println("-------------------");
        System.out.println("������"+emp.name+" ���䣺"+emp.age+" �Ա�"+emp.gender+" нˮ��"+emp.salary);
    }*/

    public Emp (String name,int age,double salary){
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public Emp clone(){
        try{
            return (Emp)super.clone();
        }catch(CloneNotSupportedException e){
            return null;
        }
    }

    public void printEmpInfo(){
        System.out.println("-------------------");
        System.out.println("������"+name+" ���䣺"+ age+"нˮ��"+salary);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void raiseSalary(double d) {
        this.salary = this.salary+d;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

}

