package com.io;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;

public class FileDemo {

	public static void main(String[] args) throws IOException {
		File f=new File("file/../file/file.txt");
		File f2=new File("pom.xml");
		File f3=new File("file/Emp.txt");
		File dir=new File("file/dir");
		File dir2=new File("file/dir1/dir2");
		
		//名称及路径
		System.out.println("文件名："+f.getName());
		System.out.println("是否绝对路径："+f.isAbsolute());
		System.out.println("路径是："+f.getPath());
		System.out.println("当前路径是："+System.getProperty("user.dir"));
		System.out.println("绝对路径是："+f.getAbsolutePath());
		System.out.println("标准绝对路径是："+f.getCanonicalPath());
		System.out.println("标准文件是："+f.getCanonicalFile());
		System.out.println("父目录路径："+f2.getParent());
		System.out.println("有效的父目录路径："+f2.getCanonicalFile().getParent());
		
		//文件基本信息
		System.out.println("是否存在："+f2.exists());
		System.out.println("是否是目录："+f2.isDirectory());
		System.out.println("是否是文件："+f2.isFile());
		System.out.println("文件字节数："+f2.length());
		//从计算机纪元（1970年1月1日）时开始的毫秒数
		System.out.println("最后修改时间："+f2.lastModified());
		
		//权限信息
		System.out.println("是否隐藏："+f2.isHidden());
		System.out.println("是否可执行："+f2.canExecute());
		System.out.println("是否可读："+f2.canRead());
		System.out.println("设置只读："+f2.setReadOnly());
		System.out.println("是否可写："+f2.canWrite());
		System.out.println("修改读权限："+f2.setReadable(true, true));
		System.out.println("修改写权限："+f2.setWritable(true, true));
		System.out.println("是否可写："+f2.canWrite());
		System.out.println("修改可执行权限："+f3.setExecutable(true, true));
		
		//文件操作
		//创建
		System.out.println("创建文件："+f.createNewFile());
		File temp=File.createTempFile("upload_", ".tmp", new File("file"));
		System.out.println("创建临时文件："+temp);
		//删除
		System.out.println("删除文件："+temp.delete());
		//重命名
		System.out.println("重命名文件："+f.renameTo(new File("file/newFile.txt")));
		
		//目录操作
		//创建目录
		System.out.println("创建目录："+dir.mkdir());
		System.out.println("强制创建目录(自动创建中间目录)："+dir2.mkdirs());
		
		//遍历
		System.out.println("子目录和文件："+Arrays.toString(f.getParentFile().list()));
		System.out.println("txt后缀的文件："+Arrays.toString(f.getParentFile().listFiles(new FilenameFilter()
			{		
				@Override
				public boolean accept(File dir, String name) {
					if(name.endsWith(".txt")){
						return true;
					}
					return false;
				}
			})));

	}

}
