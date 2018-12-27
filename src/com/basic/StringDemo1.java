package com.basic;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015-9-16.
 * String类型
 */
public class StringDemo1 {
    public static void main(String args[]){
        //提取子串,每一个双引号括起来的字串都是String类型的一个实例，substring的第二个参数是第一个不想提取的字符位置
        System.out.println("Hello".substring(0,2));
        //对于String型对象进行相等性测试时，只能使用equals函数，==比较的是两个String型对象的内存位置是否相同。
        // 只有当两对象指向的是字符串常量时，==才起作用，而当使用+、substring等操作后，即使一样其位置也可能不一样了。为了保证一致性，使用equals函数判断相等性
        String s1 = "hello1";
        String s2 = "hello1";
        if (s1 == s2){
            System.out.println("==相等");
        }
        if (s1.substring(0,5) == s2.substring(0,5)){
            System.out.println("==相等");
        }
        else {
            System.out.println("substring运算后内存位置不同");
        }
        if (s1.substring(0,5).equals(s2.substring(0,5))){
            System.out.println("equals相等");
        }

        //按指定格式输出
        System.out.println(String.format("%s---%s---%s","header","body","..."));

        System.out.println(String.format("%s_format","ball"));

        //代码点与代码单元
        //length方法返回采用UTF-16编码表示的给定字符串所需要的代码单元数量
        //codePoint返回代码点数量
        String s3 = "Hello";
        System.out.println(s3.length());
        System.out.println(s3.codePointCount(0, s3.length()));
        //若需要用许多小段的字符串构建一个字符串，为保证效率，需要使用StringBuilder
        StringBuilder builder1 = new StringBuilder();
        builder1.append("!aaaa");
        builder1.append('b');
        System.out.println(builder1);
        
        char[] temp=new char[200];
        for(int i=0;i<10;i++){
        	temp[i]='人';
        }
        System.out.println(new String(temp,0,10));

        System.out.println("a|b:c:d".split("\\|").length);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("a");
        arrayList.add("b");
        arrayList.remove(0);
        System.out.println(arrayList.get(0));
    }
}
