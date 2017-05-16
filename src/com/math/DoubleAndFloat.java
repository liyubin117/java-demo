package com.math;

public class DoubleAndFloat {

	public static void main(String[] args) {
    	//java中的浮点数是近似的，默认是double型。
    	//0.2的double实际是0.20000000001，0.2的float型实际是0.200001
        System.out.println("0.2>0.2 "+(0.2>0.2));
        System.out.println("0.2d>0.2 "+(0.2d>0.2));
        System.out.println("0.2d==0.2 "+(0.2d==0.2));
        System.out.println("0.2d<0.2f "+(0.2d<0.2f));
        System.out.println("0.2f>0.2 "+(0.2f>0.2));
	}

}
