package com.threads;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
//并发容器
public class ConcurrentCollection {

	public static void main(String[] args) throws InterruptedException {
		//写时拷贝List，支持容器被修改时，并发迭代读取
		final List<String> list=new CopyOnWriteArrayList<>();
		SCIterator.startIteratorThread(list);
		SCIterator.startModifyThread(list);

		
		//JDK1.8前CopyOnWriteArrayList的迭代器不支持修改操作，也不支持一些依赖迭代器修改方法的操作，比如Collections的sort方法
		CopyOnWriteArrayList<String> list1 = new CopyOnWriteArrayList<>();
	    list1.add("c");
	    list1.add("a");
	    list1.add("b");
	    Collections.sort(list1);
	    System.out.println(list1);
	}

}
