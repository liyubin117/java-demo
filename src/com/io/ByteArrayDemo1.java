package com.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
//内存中的内容转小写，可以用字节数组流实现
public class ByteArrayDemo1 {

	public static void main(String[] args) throws IOException {
		String s="Hello ByteArray Stream by 李宇彬\n";
		ByteArrayInputStream bis=new ByteArrayInputStream(s.getBytes(Charset.forName("UTF-8")));
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		int temp=0;
		while((temp=bis.read())!=-1){
			char c=(char)temp;
			bos.write(Character.toLowerCase(c));
		}
		String newStr=bos.toString("UTF-8");
		bis.close();
		bos.close();
		System.out.println(newStr);
	}

}
