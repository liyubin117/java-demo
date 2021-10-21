package org.rick.math;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class NumberFormatDemo1 {

	public static void main(String[] args) {
		NumberFormat nf=NumberFormat.getInstance();
		double d1=188888.895;
		System.out.println(d1+"的默认格式化形式为："+nf.format(d1));
		
		Locale[] locales=NumberFormat.getAvailableLocales();
		for(Locale l:locales){
			System.out.print(l+"\t");
		}
		System.out.println();
		
		String pattern="####,####.######";
		DecimalFormat df=new DecimalFormat(pattern);
		double d=1234567.8890;
		String s=df.format(d);
		System.out.println(s);
		try {
			System.out.println(df.parseObject(s));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}

}
