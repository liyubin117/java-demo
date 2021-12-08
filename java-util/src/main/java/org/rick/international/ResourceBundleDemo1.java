package org.rick.international;

import java.util.ResourceBundle;

public class ResourceBundleDemo1 {
	public static void main(String[] args){
		//properties文件的key值必须放在每行的第一个位置，否则会找不到，且会作为value值
		ResourceBundle rb=ResourceBundle.getBundle("com/useful/international/Message");
		System.out.println(rb.getString("info"));
		System.out.println(rb.getString("name"));
	}
}
