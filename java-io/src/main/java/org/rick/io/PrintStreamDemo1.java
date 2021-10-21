package org.rick.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

//字节输出流会简化输出操作，包装了OutputStream
public class PrintStreamDemo1 {

	public static void main(String[] args) throws FileNotFoundException {
		File f=new File("file"+File.separator+"testPrintStream.file");
		PrintStream ps=new PrintStream(new FileOutputStream(f));
		//简化输出
		ps.print("hello ");
		ps.println("PrintStream!");
		ps.println("byte[]:"+new String(new byte[]{'a','b','c'}));
		ps.println("int:"+1);
		//格式化输出
		ps.printf("姓名：%s，年龄：%d，性别：%c","李宇彬",25,'M');
		ps.close();
	}

}
