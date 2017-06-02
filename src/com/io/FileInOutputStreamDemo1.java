package com.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class FileInOutputStreamDemo1 {

	public static void main(String[] args) throws IOException {
		//写入
		File f=new File("file"+File.separator+"test.file");
		//System.out.println(f.delete());
		//当文件不存在时，自动创建新的
		//true表示追加写入
		//使用write(byte b[])写入
		OutputStream os=new FileOutputStream(f,false);
		String s="hello FileOutputStream 100 ";
		byte[] bytes=s.getBytes();
		os.write(bytes);
		//使用write(int b)写入
		s="very good\n";
		bytes=s.getBytes();
		for(int i=0;i<bytes.length;i++){
			os.write(bytes[i]);
		}
		//使用字符集
		bytes="李宇彬努力前进！".getBytes(Charset.forName("GBK"));
		os.write(bytes);
		//close会强制刷新缓冲区并将内容写入到文件，而字节流未用到缓冲区，不用close也可保存数据
		//os.close();
		
		//读取
		//字节数组读取
		InputStream is=new FileInputStream(f);
		System.out.println(f.length());
		bytes=new byte[(int)f.length()];
		System.out.println("read bytes count:"+is.read(bytes));
		System.out.println(new String(bytes));
		System.out.println("----------------------------");
		//一个字节一个字节地读
		InputStream is2=new FileInputStream(f);
		int temp=0,i=0;
		while((temp=is2.read())!=-1){
			bytes[i++]=(byte)temp;
		}
		System.out.println(new String(bytes,"GBK"));
		is.close();
		is2.close();
	}

}
