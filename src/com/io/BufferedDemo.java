package com.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class BufferedDemo {

	public static void main(String[] args) throws IOException {
		File f=new File("file"+File.separator+"person.txt");
		//缓冲包装流
		BufferedInputStream bis=new BufferedInputStream(new FileInputStream(f),8990);
		byte[] bytes=new byte[(int) f.length()];
		int i=0,b;
		while((b=bis.read())!=-1){
			bytes[i++]=(byte) b;
		}
		System.out.println(new String(bytes));
		bis.close();
	}

}
