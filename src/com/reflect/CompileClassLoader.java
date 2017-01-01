package com.reflect;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
//重写类加载器的findClass方法，使实现执行java文件时自动编译的功能，不用事先javac，直接java即可
public class CompileClassLoader extends ClassLoader {
	private byte[] getBytes(String filename) throws IOException{
		File file=new File(filename);
		long len=file.length();
		byte[] raw=new byte[(int)len];
		FileInputStream fin=new FileInputStream(file);
		int r=fin.read(raw);
		if(r!=len){
			throw new IOException("无法读取全部文件："+r+" != "+len);
		}
		return raw;
	}
	
	private boolean compile(String javaFile) throws IOException, InterruptedException{
		System.out.println("CompileClassLoader:正在编译"+javaFile+"...");
		//调用系统的javac命令
		Process p=Runtime.getRuntime().exec("javac "+javaFile);
		//其他线程都等待这个线程完成
		p.waitFor();
		return p.exitValue()==0;
	}
	

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		Class c=null;
		//将包路径中的.替换成/
		String fileStub=name.replace(".", "/");
		String javaFilename=fileStub+".java";
		String classFilename=fileStub+".class";
		File javaFile=new File(javaFilename);
		File classFile=new File(classFilename);
		//当java源文件存在且class文件不存在时，编译；或java源文件存在且class文件修改日期比源文件早时，重新编译
		if( (javaFile.exists())&&
				((!classFile.exists())||(classFile.lastModified()<javaFile.lastModified())) ){
			try{
				if(!compile(javaFilename)||!classFile.exists()){
					throw new Exception("ClassNotFoundException:"+javaFilename);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		//如果class文件存在，系统负责将该文件转换成Class对象
		if(classFile.exists()){
			try{
				byte[] raw=getBytes(classFilename);
				c=defineClass(name,raw,0,raw.length);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		//如果c为空，说明加载失败，抛出异常
		if(c==null){
			throw new ClassNotFoundException(name);
		}
		return c;
	}

	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if(args.length<1){
			System.out.println("缺少目录类，格式：java CompileClassLoader 类名");
		}else{
			//第一个参数是需要运行的类
			String progClass=args[0];
			//剩下的参数将作为运行目标类时的参数
			String[] progArgs=new String[args.length-1];
			System.arraycopy(args, 1, progArgs, 0, progArgs.length);
			CompileClassLoader ccl=new CompileClassLoader();
			//加载需要运行的类
			Class<?> c=ccl.loadClass(progClass);
			//获取需要运行的类主方法，反射调用
			Method main=c.getMethod("main", (new String[0]).getClass());
			Object argsArray[]={progArgs};
			main.invoke(null, argsArray);
		}
 		String s="com/lyb/HelloWorld.java";
		System.out.println(s.lastIndexOf("/"));
		System.out.println(s.substring(s.length()-s.lastIndexOf("/")-1));
	}
}
