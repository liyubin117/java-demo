package com.lyb;
class data{
         private static final data mInstance = new data();
         private static int mx=0;  //若此处mx不设值，则将自动跳过
         private data()
         {
                 System.out.println("mx=" + mx);
                 mx = getVersion();
         }
         public static data getInstance()
         {
                 return mInstance;
         }
         public int getVersion()
         {
                 int v = 0;
                 if (v == 0)
                 {
                         v = 7;
                 }
                 return v;
         }
         public int getmx()
         {
                 return mx;
         }
 }

public class Test
 {
         public static void getData(StringBuffer sb)
         {
                 sb.append("hello world ");
         }
         public static void main(String args[])
         {
                   System.out.println(data.getInstance().getmx());
         }
 }