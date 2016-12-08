package com.math;

import java.util.Random;

public class RandomDemo1 {

	public static void main(String[] args) {
		Random ran=new Random();
		//设置此随机数生成器的种子，当种子为固定值时，每次生成的伪随机数是一样的
		ran.setSeed(5000);
		for(int i=0;i<10;i++){
			System.out.print(ran.nextInt(100)+"\t");
		}
	}

}
