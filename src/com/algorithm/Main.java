package com.algorithm;

import com.pubtest.Person;

public class Main {
	//演示代码
	public static void main(String[] args){
		SimpleBinaryTree bt=new SimpleBinaryTree();
		bt.add(1);
		bt.add(10);
		bt.add(8);
		bt.add(-1);
		bt.add(9);
		bt.print();
		System.out.println();
		bt.printPreOrder();
		System.out.println();
		
		BinaryTree t=new BinaryTree();
		t.insert(10, 10.1);
		t.insert(1, 20);
		t.insert(3, 8.5);
		t.insert(15, 20.5);
		t.traverse(2);
		t.delete(10);
		t.traverse(2);
		
		RBTree<Integer> rb=new RBTree<>();
		rb.insert(1);
		rb.insert(10);
		rb.insert(8);
		rb.insert(-1);
		rb.insert(9);
		rb.print();
		rb.remove(10);
		
		GeneralHashFunctionLibrary hfl=new GeneralHashFunctionLibrary();
		System.out.println(hfl.RSHash("liyubin LIYUBIN "));
		
	}
}
