package com.useful.international;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class InternationalDemo1 {
	public static void main(String[] args){
		Locale zh=new Locale("zh","CN");
		ResourceBundle rb=ResourceBundle.getBundle("com/useful/international/Message", zh);
		System.out.println(rb.getString("info")+rb.getString("comma")+rb.getString("name"));
		
		rb=ResourceBundle.getBundle("com/useful/international/Messageclass", zh);
		System.out.println(rb.getString("info")+rb.getString("comma")+rb.getString("name"));
		
		Locale en=new Locale("en","US");
		ResourceBundle rb2=ResourceBundle.getBundle("com/useful/international/Message", en);
		System.out.println(rb2.getString("info")+rb2.getString("comma")+rb2.getString("name"));
	
		String str2=rb2.getString("position");
		System.out.println("中文："+MessageFormat.format(str2, "hello ",",","liyubin","!"));
		
		String msg = "{0}{1}{2}{3}{4}{5}{6}{7}{8}";  
		Object [] array = new Object[]{"A","B","C","D","E","F","G","H","I",};         
		String value = MessageFormat.format(msg, array); 
		System.out.println(value);  
	}
}
