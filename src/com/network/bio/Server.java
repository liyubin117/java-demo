package com.network.bio;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Server {
	public static void main(String[] args) throws IOException, IOException, InterruptedException{
		ServerSocket ss=new ServerSocket(10888,10,InetAddress.getLocalHost());
		while(true){
			Thread.currentThread().sleep(3000);
			Socket s=ss.accept();
            System.out.println("received from client...");
			PrintStream ps=new PrintStream(s.getOutputStream());
			ps.println("hello from server!!!");
			
			//自定义输入，无效，待研究
//            System.out.println("请输入发送给客户端的信息：");
//			Scanner scan=new Scanner(System.in);
//			String st=scan.nextLine();
//			new PrintWriter(s.getOutputStream()).println(st);
			
			ps.close();
			s.close();
		}
	}
}
