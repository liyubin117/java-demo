package com.innerclass;

/**
 * Created by Administrator on 2015-12-23.
 */
public class InnerTest {
	public static void main(String args[]) {
		Outer out1 = new Outer(); // 外部类实例化对象
		// out1.fun();

		Outer.Inner in = new Outer().new Inner();
		in.print();

		Outer.StaticInner static_in = new Outer.StaticInner(); // 外部可直接调用静态内部类
		static_in.print();

		out1.fun(3);
	}
}

class Outer {
	private static String info = "Hello World!";

	// 常规/成员内部类
	public class Inner {
		public void print() {
			System.out.println(info + "<常规/成员>");
		}
	}

	// 静态内部类
	public static class StaticInner {
		public void print() {
			System.out.println(info + "<静态>");
		}
	}

	public void fun(final int x) {
		// 局部内部类的
		final int y = 10;
		// 局部内部类
		class Inner2 {
			public void print2() {
				System.out.println(x + "<局部 final>");
				System.out.println(info + "<局部访问外部 private变量>");
			}
		}
		new Inner2().print2();
		new StaticInner().print();
		System.out.println(y);
	}
}
