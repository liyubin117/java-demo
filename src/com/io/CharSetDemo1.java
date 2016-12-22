package com.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class CharSetDemo1 {

	public static void main(String[] args) throws IOException {
		//获取当前环境的字符集
		Properties p=System.getProperties();
		System.out.println("当前字符集为："+p.getProperty("file.encoding"));
		System.out.println("系统属性有：");
		p.list(System.out);
		
		//使用不同于当前环境的字符集，发现果然乱码
		//乱码的根本原因是字符编码不统一
		byte[] GBKbytes="李宇彬最棒！！！\n".getBytes("GBK");
		byte[] UTF8bytes="李宇彬最棒！！！\n".getBytes("UTF-8");
		OutputStream os=System.out;
		os.write(GBKbytes);
		File f=new File("file"+File.separator+"testCharSet.file");
		OutputStream fos=new FileOutputStream(f);
		fos.write(GBKbytes);
		fos.write(UTF8bytes);
	}

}
