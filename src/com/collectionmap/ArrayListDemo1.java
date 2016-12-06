package com.collectionmap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayListDemo1 {

	public static void main(String[] args) {
		List<String> ls=new ArrayList<String>();
		ls.add("hello");
		ls.add("hello");
		ls.add("liyubin");
		Iterator i=ls.iterator();
		while(i.hasNext()){
			System.out.println(i.next());
		}
		for(String s:ls){
			System.out.println(s);
		}
		System.out.println(ls);
	}

}
