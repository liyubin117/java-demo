package com.collectionmap;

import java.util.HashSet;
import java.util.TreeSet;

public class SetDemo1 {
	public static void main(String[] args){
		HashSet<String> hss=new HashSet<String>();
		hss.add("C");
		hss.add("A");
		hss.add("A");
		hss.add("B");
		hss.add("E");
		hss.add("D");	
		hss.add("H");
		System.out.println(hss);
		
		TreeSet<String> tss=new TreeSet<String>();
		tss.add("C");
		tss.add("A");
		tss.add("A");
		tss.add("B");		
		tss.add("E");
		tss.add("D");	
		tss.add("H");
		System.out.println(tss);

	}
}
