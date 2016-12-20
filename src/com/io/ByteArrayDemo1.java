package com.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
//内存中的内容转小写，可以用字节数组流实现
public class ByteArrayDemo1 {

	public static void main(String[] args) throws IOException {
		String s="Hello ByteArray Stream\n";
		ByteArrayInputStream bis=new ByteArrayInputStream(s.getBytes());
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		int temp=0;
		while((temp=bis.read())!=-1){
			char c=(char)temp;
			bos.write(Character.toLowerCase(c));
		}
		String newStr=bos.toString();
		bis.close();
		bos.close();
		System.out.println(newStr);
	}

}
