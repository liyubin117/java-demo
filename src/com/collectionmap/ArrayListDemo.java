package com.collectionmap;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class ArrayListDemo {
	//初始化空List
	private static List<String> ls=new ArrayList<String>();

	@Before
	public void addElement() {
		//在最后一个位置追加
		ls.add("hello");
		ls.add(",");
		ls.add("liyubin");
		ls.add(",");
		//在n位置前增加元素
		ls.add(2, "great");
		//生成生成n个相同元素
		ls.addAll(Collections.nCopies(3, "a"));
	}

	@Test
	public void test1() {
		//以Collection对象初始化List
		List<Integer> list=new ArrayList<>(Arrays.asList(1,3,5,7,9));
		System.out.println(list);
	}

	@Test
	public void test2() {
		//IndexOutOfBoundsException，因为size一开始是0，只有顺序插入元素时会递增size。当index>size时会报越界
		new ArrayList<String>().add(2, "hello");
	}

	@Test
	public void testIteratorRead() {
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
		//ListIterator遍历
		ListIterator<String> li=ls.listIterator(ls.size());
		System.out.print("ListIterator可反向遍历：");
		while(li.hasPrevious()){
			System.out.print(li.previous()+" ");
		}
		System.out.println();

		//查找
		System.out.println("ls对象的第3个元素是"+ls.get(2));
		System.out.println("ls对象的第1个逗号的索引是"+ls.indexOf(","));

		//toArray转换为普通定长数组
		System.out.print("toArray get遍历:");
		Object[] obj=ls.toArray();
		for(int n=0;n<obj.length;n++){
			System.out.print(obj[n]+" ");
		}
		System.out.println();

		System.out.println("Arrays.toString遍历："+Arrays.toString(obj));

		System.out.print("size get遍历:");
		for(int a=0;a<ls.size();a++){
			System.out.print(ls.get(a)+" ");
		}
		System.out.println();
	}

	@Test
	public void testModify() {
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
	}

}
