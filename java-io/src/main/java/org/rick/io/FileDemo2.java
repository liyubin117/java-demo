package org.rick.io;

import java.io.File;

//打印目录下的所有文件和目录，并标识出来
public class FileDemo2 {

	public static void main(String[] args) {
		File f=new File("c:"+File.separator+"testdir");
		FileDemo2.printR(f);
	}
	
	public static void printR(File f){
		if(f!=null){  //判断是否为空
			if(f.isDirectory()){
				System.out.println(f+"\t"+"dir");
				File[] files=f.listFiles();
				if(files!=null){
					for(File a:files){
						printR(a);
					}
				}
			}else{
				System.out.println(f+"\t"+"file");
			}
		}
	}

}
