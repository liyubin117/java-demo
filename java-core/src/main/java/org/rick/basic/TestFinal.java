package org.rick.basic;

public class TestFinal {
	
	private StringBuilder a=new StringBuilder("a");
	private final StringBuilder b=new StringBuilder("a");
	private final StringBuilder c=new StringBuilder("a");
	
	public void print(){
		//对于可变引用类型，不管有无final，地址都不一样
		System.out.println((b==c)?("b==c"):("b!=c"));
		System.out.println((b==a)?("b==a"):("b!=a"));	
	}

	public static void main(String[] args) {
		TestFinal tf=new TestFinal();
		tf.print();
	}

}
