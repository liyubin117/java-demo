package com.algorithm;

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
		
		RBTree<Integer> rb=new RBTree<>();
		rb.insert(1);
		rb.insert(10);
		rb.insert(8);
		rb.insert(-1);
		rb.insert(9);
		rb.print();
		
		GeneralHashFunctionLibrary hfl=new GeneralHashFunctionLibrary();
		System.out.println(hfl.RSHash("liyubin LIYUBIN "));
	}
}
