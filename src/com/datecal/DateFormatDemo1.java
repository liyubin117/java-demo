package com.datecal;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormatDemo1 {

	public static void main(String[] args) {
		Locale.setDefault(Locale.CHINA);
		
		DateFormat df1=DateFormat.getDateInstance();
		System.out.println("date:"+df1.format(new Date()));
		
		DateFormat df2=DateFormat.getDateTimeInstance(DateFormat.YEAR_FIELD,
				DateFormat.ERA_FIELD,
				new Locale("de","DE"));
		System.out.println("datetime:"+df2.format(new Date()));
		
		String pat1="yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat sdf=new SimpleDateFormat(pat1);
		System.out.println(sdf.format(new Date()));
		
		String s="2016-12-1 5:10:5";
		Date d=null;
		try {
			d=sdf.parse(s);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(sdf.format(d));
	}

}
