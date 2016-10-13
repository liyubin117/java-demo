package com.network;

import java.io.IOException;
import java.io.PrintStream;
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
			PrintStream ps=new PrintStream(s.getOutputStream());
			Scanner scan=new Scanner(System.in);
			String st=scan.nextLine();
			ps.println(st);
			ps.close();
			s.close();
		}
	}
}
