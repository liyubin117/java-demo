package com.collectionmap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayListDemo1 {

	public static void main(String[] args) {
		List<String> ls=new ArrayList<String>();
		ls.add("hello");
		ls.add(",");
		ls.add("liyubin");
		Iterator i=ls.iterator();
		System.out.print("Iterator遍历:");
		while(i.hasNext()){
			System.out.print(i.next()+" ");
		}
		System.out.println();
		System.out.print("for each遍历:");
		for(String s:ls){
			System.out.print(s+" ");
		}
		System.out.println();

		System.out.print("toString遍历:");
		System.out.println(ls);
		
		System.out.println(ls.get(2));
		
		System.out.print("toArray get遍历:");
		Object[] obj=ls.toArray();
		for(int n=0;n<obj.length;n++){
			System.out.print(obj[n]+" ");
		}
		System.out.println();

	}

}
