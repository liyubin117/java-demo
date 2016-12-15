package com.lyb;

import java.util.ArrayList;
import java.util.Collection;

public class TestCollection {

	public static void main(String[] args) {
		Collection c=new ArrayList();
		c.add("liyubin");
		c.add("yexingping");
		c.add(8);
		System.out.println(c.size());
		c.remove(8);
		System.out.println(c);
	}

}
