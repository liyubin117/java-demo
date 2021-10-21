package org.rick.io;

import java.io.*;

public class BufferedReaderDemo1 {

	public static void main(String[] args) throws IOException {
		BufferedReader buf=new BufferedReader(new InputStreamReader(System.in));
		String str=null;
		System.out.print("请输入内容：");
		try{
			str=buf.readLine();
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("输出内容："+str);
		
		System.out.println("按行读取文件内容：");
		File f=new File("file"+File.separator+"Emp.txt");
		BufferedReader reader=new BufferedReader(new FileReader(f));
		String line=reader.readLine();
		while(line!=null){
			System.out.println(line);
			line=reader.readLine();
		}
		reader.close();
	}

}
