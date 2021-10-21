package org.rick.io;
import java.io.File;
import java.io.IOException;

public class FileDemo1 {

	public static void main(String[] args) throws IOException {
		//创建目录
		//File dir=new File("c:/test.dir");
		File dir=new File("c:"+File.separator+"test.dir");
		dir.mkdir();
		
		//创建文件
		//File file=new File("c:/test.dir/test.txt");
		File file=new File("c:"+File.separator+"test.dir"+File.separator+"test.txt");
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//判断是否文件或目录
		if(dir.isDirectory()){
			System.out.println(dir+" is a directory");
		}
		if(file.isFile()){
			System.out.println(file+" is a file");
		}
		
		//打印C盘目录下的文件情况
		File dirc=new File("c:"+File.separator);
		String[] files=dirc.list();
		for(String s:files){
			System.out.print(s+"\t");
		}
		System.out.println();
		File[] fs=dirc.listFiles();
		for(File s:fs){
			System.out.print(s+"\t");
		}
		System.out.println();
		
		//判断文件存在后，删除文件和目录
		if(file.exists()&&dir.exists()){
			file.delete();
			dir.delete();
		}
		
		
		System.out.println("pathSeparator:"+File.pathSeparator);
		System.out.println("separator:"+File.separator);
		
		File dir2=new File("c:/ha/a/b");
		System.out.println(dir2.mkdirs());
		//也可以传入相对路径，默认是在当前工程目录下
		dir2=new File("a.java");
		System.out.println(dir2.createNewFile());
		
	}

}
