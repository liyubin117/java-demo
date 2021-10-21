package org.rick.math;
//数据类型
import static java.lang.Character.isJavaIdentifierStart;

import java.math.BigInteger;
import java.util.Scanner;

public class Calculate {

    public static void main(String[] args) {
		System.out.println("Hello,World!");
		int x = 1;
		int y = 5;
		int c = x + y;
		System.out.println(c);

		int d, e = 9;
		int E = 10;
		/* System.out.println(d); */
		System.out.println(e);
		System.out.println(E);

		int m = 5 / 3;
		System.out.println(m);

		int a = 2147483647; // int类型整数的上限
		int b = -2147483648; // int类型整数的下限
		a = a + 1;
		b = b - 1;
		System.out.println("a=" + a); // 输出结果： a=-2147483648 溢出，结果错误。
		System.out.println("b=" + b); // 输出结果： b=2147483647溢出，结果错误。

		long al = 10000000000L;
		System.out.println("al=" + al);

		long time = System.currentTimeMillis();
		System.out.println("time=" + time);

		double pi = 3.1415927;
		double r = 8;
		double s = pi * r * r;
		System.out.println("s=" + s);
		// double运算时会出现舍入误差，若需要精确计算，采用BigDecimal类实现
		double test1 = 3;
		double test2 = 2.9;
		System.out.println("test1-test2=" + (test1 - test2));
		// 字符类型char事实上是一个16位无符号整数（都是正数），这个值是对应字符的编码
		char c1 = 'A';
		char c2 = '\u4e2d'; // '4e2d'为'中'所对应的16位Unicode编码的16进制表示形式
		System.out.println(c1);
		System.out.println(c2);
		char c3 = '\n';
		System.out.println(c3);
		boolean b1 = pi < r;
		System.out.println(b1);
		double d1 = 3.1415926535897932384;
		float f2 = (float) d1;
		System.out.println(f2);
		double persent1 = 80 / 100;
		double persent2 = 80.0 / 100;
		System.out.println(persent1);
		System.out.println(persent2);

        //整数除以0会产生异常，浮点型除以0会得到无穷大
		Double d5 = 5.1/0;
		if (Double.isInfinite(d5)){
			System.out.println("Infinite");
		}

		//浮点数值不适用于禁止出现舍入误差的金融计算中。若需要精确计算，则应使用BigDecimal类
		System.out.println(2.0-1.1);

		//char类型用于表示单个字符，用来表示字符常量
		char pi2 = '\u03C0';
		System.out.println(pi2);

		if ( Character.isJavaIdentifierStart('a') ){
			System.out.println("符合java变量第一位字符的要求");
		}
        if ( Character.isJavaIdentifierPart('\u03C0') ){
            System.out.println( "符合java变量的字符要求");
        }

		int a5 = 10;
		int a6= 10;
		System.out.println(2*a5++);
		System.out.println(2*++a6);

        System.out.println("1向左移3位后为："+(1<<3));
        System.out.println("8向右移3位后为："+(8>>3));

        System.out.println("-1的二进制形式为："+Integer.toBinaryString(-1));
        System.out.println("-1向右移3位(0填充高位)后为："+(-1>>>3));
        System.out.println("-1向右移3位(符号位填充高位)后为："+(-1>>3));

        double d6 = 4;
        System.out.println(Math.sqrt(d6));



        //强制类型转换,强制转换浮点型为整型时，直接截断小数部分取整数部分
        double d7 = 5.5123;
        System.out.println( (int)d7 );
        //若想对浮点数进行舍入运算，应使用Math.round(),round()返回的是long类型
        System.out.println( (int)Math.round(d7) );
        //强制类型转换可能会引起精度丢失
        int a7 = 257;
        System.out.println("257的二进制形式为：" + Integer.toBinaryString(a7));
        System.out.println("257强制转换为byte型后为：" + (byte)a7);



		Scanner console = new Scanner(System.in);
		System.out.println("请输入第一个值：");
		int in1 = console.nextInt();
		System.out.println("请输入第二个值：");
		int in2 = console.nextInt();
		int in3 = (in1>in2) ? in1 : in2;
		System.out.println("较大值为："+in3);
		System.out.println("较大值为："+((in1>in2) ? in1 : in2));


        //大数值，可实现任意精度的计算
        BigInteger bi = BigInteger.valueOf(800000000000L);
        BigInteger bi2 = BigInteger.valueOf(8000);
        //大数值加
        System.out.println(bi.add(bi2));
        //大数值乘
        System.out.println(bi.multiply(bi2));


    }
}
