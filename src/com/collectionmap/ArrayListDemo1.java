package com.collectionmap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ArrayListDemo1 {

	public static void main(String[] args) {
		//初始化
		//空List
		List<String> ls=new ArrayList<String>();
		//以Collection对象初始化List
		List<Integer> listi=new ArrayList<Integer>(Arrays.asList(new Integer[]{1,3,5,7,9}));
		
		//增加
		//在最后一个位置追加
		ls.add("hello");
		ls.add(",");
		ls.add("liyubin");
		ls.add(",");
		//在n位置前增加元素
		ls.add(2, "great");
		
		//遍历
		Iterator<String> i=ls.iterator();
		System.out.print("Iterator遍历:");
		while(i.hasNext()){
			System.out.print(i.next()+" ");
		}
		System.out.println();
		//实现了Iterable接口的对象才能进行foreach循环
		System.out.print("for each遍历:");
		for(String s:ls){
			System.out.print(s+" ");
		}
		System.out.println();
		System.out.print("toString遍历:");
		System.out.println(ls);
		//ListIterator遍历）
		ListIterator<String> li=ls.listIterator(ls.size());
		System.out.print("ListIterator可反向遍历：");
		while(li.hasPrevious()){
			System.out.print(li.previous()+" ");
		}
		System.out.println();
		
		//查找
		System.out.println("ls对象的第3个元素是"+ls.get(2));
		System.out.println("ls对象的第1个逗号的索引是"+ls.indexOf(","));
		
		//删除
		//根据对象删除
		ls.remove(",");
		System.out.println("ls对象删除第一个逗号后变为"+ls.toString());
		//根据索引删除
		ls.remove(ls.indexOf(","));
		System.out.println("ls对象删除第二个逗号后变为"+ls.toString());
		
		//修改
		ls.set(1, "very good");
		System.out.println("ls对象修改后变为"+ls.toString());
		
		//toArray转换为普通定长数组
		System.out.print("toArray get遍历:");
		Object[] obj=ls.toArray();
		for(int n=0;n<obj.length;n++){
			System.out.print(obj[n]+" ");
		}
		System.out.println();
		
		System.out.print("size get遍历:");
		for(int a=0;a<ls.size();a++){
			System.out.print(ls.get(a)+" ");
		}
		System.out.println();


	}

}
