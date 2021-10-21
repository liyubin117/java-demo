package org.rick.io;

import java.io.*;
import java.nio.charset.Charset;

public class FileInOutputStreamDemo1 {

	public static void main(String[] args) throws IOException {
		//写入
		File f=new File("file"+File.separator+"test.file");
		//System.out.println(f.delete());
		//当文件不存在时，自动创建新的
		//true表示追加写入
		//使用write(byte b[])写入
		FileOutputStream os=new FileOutputStream(f,false);
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
		
		//使用getFD方法获得文件描述符对象，调用sync方法确保操作系统将数据写入磁盘
		os.getFD().sync();
		os.close();
		
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
