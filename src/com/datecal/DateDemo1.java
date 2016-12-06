package com.datecal;

import java.util.Date;
import java.util.Locale;

public class DateDemo1 {
	public static void main(String[] args){
		Locale.setDefault(new Locale("zh","CN"));
		Date date=new Date();
		System.out.println(date);
	}
}
