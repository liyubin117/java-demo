package com.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class FileInOutputStreamDemo1 {

	public static void main(String[] args) throws IOException {
		//写入
		File f=new File("file"+File.separator+"test.file");
		//System.out.println(f.delete());
		//当文件不存在时，自动创建新的
		//true表示追加写入
		//使用write(byte b[])写入
		OutputStream os=new FileOutputStream(f,true);
		String s="hello FileOutputStream 100 ";
		byte[] bytes=s.getBytes();
		os.write(bytes);
		//使用write(int b)写入
		s="very good\n";
		bytes=s.getBytes();
		for(int i=0;i<bytes.length;i++){
			os.write(bytes[i]);
		}
		//close会强制刷新缓冲区并将内容写入到文件，而字节流未用到缓冲区，不用close也可保存数据
		//os.close();
		
		//读取
		//预先知道输入流大小
		InputStream is=new FileInputStream(f);
		bytes=new byte[(int)f.length()];
		System.out.println("read bytes count:"+is.read(bytes));
		System.out.println(new String(bytes));
		System.out.println("----------------------------");
		//预先不知道输入流大小
		InputStream is2=new FileInputStream(f);
		int temp=0;
		ArrayList<Byte> alb=new ArrayList<Byte>();
		byte b;
		while((temp=is2.read())!=-1){
			b=(byte)temp;
			alb.add(b);
		}
		bytes=new byte[alb.size()];
		for(int i=0;i<bytes.length;i++){
			bytes[i]=alb.get(i);
		}
		System.out.println(new String(bytes));
		is.close();
	}

}
