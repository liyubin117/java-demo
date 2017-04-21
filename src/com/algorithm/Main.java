package com.algorithm;

public class Main {
	//演示代码
	public static void main(String[] args){
		BinaryTree bt=new BinaryTree();
		bt.add(1);
		bt.add(10);
		bt.add(8);
		bt.add(-1);
		bt.add(9);
		bt.print();
		
		GeneralHashFunctionLibrary hfl=new GeneralHashFunctionLibrary();
		System.out.println(hfl.RSHash("liyubin LIYUBIN "));
	}
}
