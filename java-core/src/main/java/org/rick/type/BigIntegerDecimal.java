package org.rick.type;

import java.math.BigDecimal;
import java.math.BigInteger;

public class BigIntegerDecimal {

	public static void main(String[] args) {
		//out of range
		//long l=111111111111;
		BigInteger bi=new BigInteger("111111111111");
		BigInteger bi2=new BigInteger("8000000");
		System.out.println("大整数加法操作："+bi.add(bi2));	
		System.out.println("大整数减法操作："+bi.subtract(bi2));		
		System.out.println("大整数乘法操作："+bi.multiply(bi2));		
		System.out.println("大整数除法操作："+bi.divide(bi2));
		
		BigInteger[] bis=bi.divideAndRemainder(bi2);
		System.out.println("大整数除法的商："+bis[0]+" 余数："+bis[1]);

		BigDecimal bd=new BigDecimal(100.8985359565);
		BigDecimal bd2=new BigDecimal(20.141592653);
		System.out.println("任意精度小数的加法："+bd.add(bd2));
		System.out.println("任意精度小数的减法："+bd.subtract(bd2));
		System.out.println("任意精度小数的乘法："+bd.multiply(bd2));
		System.out.println("任意精度小数的除法："+bd.divide(bd2,10,BigDecimal.ROUND_UP));
	}

}
