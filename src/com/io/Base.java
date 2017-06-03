package com.io;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

//字节流和字符流的区别在于字节流无编码信息、无法按行读写
//文件的本质就是一堆二进制数，文件编码是二进制与字符的映射
public class Base {

	public static void main(String[] args) throws IOException {
		File f=new File("file"+File.separator+"base.txt");
		OutputStream out=new FileOutputStream(f);
		
		//7B
		DataOutputStream dos=new DataOutputStream(out);
		dos.writeInt(123);
		dos.writeChar('\n');
		
		//31 32 33，分别对应123的ASCII码49 50 51
		out.write(Integer.toString(123).getBytes(Charset.forName("UTF-8")));
		out.close();
		
		
	}

}
