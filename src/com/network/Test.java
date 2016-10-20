package com.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;

public class Test {
	public static void main(String[] args) throws IOException{
		InetAddress ia=InetAddress.getByName("www.baidu.com");
		System.out.println(ia.getCanonicalHostName()+" "+ia.getHostAddress()+" "+ia.getHostName());
		System.out.println(ia.isReachable(1000));
		
		SocketAddress ia2=new InetSocketAddress("127.0.0.1",10888);
		
		RandomAccessFile raf=new RandomAccessFile("../../../../file/Test.java","rw");
		

	}
}
