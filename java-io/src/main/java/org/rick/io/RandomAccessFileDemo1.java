package org.rick.io;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileDemo1 {
	public static void main(String[] args) throws IOException{
		//创建文件，并写入数据
		RandomAccessFile raf=new RandomAccessFile("file"+File.separator+"raf.txt","rw");
		int age=25;
		String name="liyubin";
		raf.writeBytes(name);
		//raf.writeBytes(Integer.toString(age));
		//Int型数据占用4个字节
		raf.writeInt(age);
		age=30;
		name="lisi   ";
		raf.writeBytes(name);
		raf.writeInt(age);
		age=21;
		name="wangwu ";
		raf.writeBytes(name);
		raf.writeInt(age);
		raf.close();
		
		//读取任意位置的数据
		RandomAccessFile read=new RandomAccessFile("file"+File.separator+"raf.txt","r");
		read.skipBytes(22);
		byte[] b=new byte[7];
		for(int i=0;i<7;i++){
			b[i]=read.readByte();
		}
		System.out.println("第三个人的名字是："+new String(b));
		System.out.println("第三个人的年龄是："+read.readInt());
		read.seek(11);;
		for(int i=0;i<7;i++){
			b[i]=read.readByte();
		}
		System.out.println("第二个人的名字是："+new String(b));
		System.out.println("第二个人的年龄是："+read.readInt());
		read.seek(0);
		for(int i=0;i<7;i++){
			b[i]=read.readByte();
		}
		System.out.println("第一个人的名字是："+new String(b));
		System.out.println("第一个人的年龄是："+read.readInt());
		read.close();
		
		
		
	}
}
