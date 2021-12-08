package org.rick.datecal;

import java.util.Date;
import java.util.Locale;

public class DateDemo1 {
	public static void main(String[] args){
		Locale.setDefault(new Locale("zh","CN"));
		Date date=new Date();
		System.out.println(date);

        //Long->Date 可接受一个从1970-01-01 00:00:00至今的毫秒数
        System.out.println(new Date(System.currentTimeMillis()));
        //Date->Long
        System.out.println(date.getTime());
	}
}
