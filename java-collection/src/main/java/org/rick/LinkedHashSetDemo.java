package org.rick;

import java.util.LinkedHashSet;
import java.util.Set;

public class LinkedHashSetDemo {

	public static void main(String[] args) {
		Set<String> set = new LinkedHashSet<>();
		set.add("b");
		set.add("c");
		set.add("a");
		set.add("c");
		System.out.println(set);
	}

}
