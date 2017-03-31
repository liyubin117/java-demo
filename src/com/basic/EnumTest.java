package com.basic;

import java.util.Scanner;

/**
 * Created by Administrator on 2015-11-26.
 */

enum Size
{
    TINY,SMALL("S"),MEDIUM("M"),LARGE("L"),EXTRA_LARGE("XL");

    private String abbreviation;
    private Size(String abbreviation) {this.abbreviation = abbreviation;}
    private Size(){}
    public String getAbbreviation() {return abbreviation;}
}

public class EnumTest {
    public static void main (String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a size(TINY,SMALL,MEDIUM,LARGE,EXTRA_LARGE):");
        String input = in.next().toUpperCase();
        Size size = Enum.valueOf(Size.class,input);
        System.out.println("size="+size);
        System.out.println("abbreviation="+size.getAbbreviation());
        if(size == Size.EXTRA_LARGE){
            System.out.println("Good job!");
        }
    }
}


