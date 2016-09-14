package com.lyb;
public class A
{
	
 public static int b = B.a;
 public static A plus =new A("A");
 public static final int finalInt = (int)(Math.random()*100);
 public static B p = new B("A");
 
 public static final String finalStr = "finalStr";
 public static final Integer finalInteger = new Integer(10);
 public static int a = 1;
 public static B c = null;
 
 public A(String from)
 {
  System.out.println("----------- begin A::A ----------------");
  System.out.println("A::A, from="+from);
  System.out.println("A::A, A.b="+A.b);
  System.out.println("A::A, A.finalInt="+A.finalInt);
  System.out.println("A::A, B.a="+B.a);
  System.out.println("A::A, B.plus="+B.plus);
  System.out.println("----------- end A::A ----------------");
 }
 
 public static void main(String[] arg)
 {
  System.out.println("main, A.b="+A.b);
  System.out.println("main, B.t="+B.t);
  System.out.println("main, C.a="+C.a);
 }
}
 
class B
{
 public static int t = A.a;
 public static A plus = new A("B");
 public static int a = 1;
 
 public B(String from)
 {
  System.out.println("----------- begin B::B ----------------");
  System.out.println("B::B, from="+from);
  System.out.println("B::B, B.a="+B.a);
  System.out.println("B::B, A.a="+A.a);
  System.out.println("B::B, A.p="+A.p);
  System.out.println("B::B, A.plus="+A.plus);
  System.out.println("B::B, A.finalInt="+A.finalInt);
  System.out.println("B::B, A.finalInteger="+A.finalInteger);
  System.out.println("B::B, A.finalStr="+A.finalStr);
  System.out.println("----------- end B::B ----------------");
 }
}
 
class C
{
 public static final A a = new A("C");
}