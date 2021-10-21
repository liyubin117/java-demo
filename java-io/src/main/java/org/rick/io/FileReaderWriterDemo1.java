package org.rick.io;

import java.io.*;
import java.util.ArrayList;

public class FileReaderWriterDemo1 {

	public static void main(String[] args) throws IOException {
		//写入 
		File f=new File("file"+File.separator+"testWriter.file");
		Writer w=new FileWriter(f,true);
		String s="hello FileWriter\n";
		w.write(s);
		//close会强制刷新缓冲区并将内容写入到文件，而字符流用到缓冲区，不用close保存不了数据，也可直接调用flush()保存数据
		//w.close();
		w.flush();
		
		//读取
		//预先知道输入数据的长度
		Reader r=new FileReader(f);
		char[] cs = new char[1024];
		r.read(cs);
		System.out.println(cs);
		System.out.println("-----------------");
		r.close();
		//预先不知道输入数据的长度
		Reader r2=new FileReader(f);
		int temp;
		ArrayList<Character> alc=new ArrayList<Character>();
		while((temp=r2.read())!=-1){
			alc.add((char)temp);
		}
		for(char c:alc){
			System.out.print(c);
		}
		r2.close();
		
	}

}
