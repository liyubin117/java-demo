package com.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

public class Client {
	public static void main(String[] args) throws IOException{
		while(true){
			try{
				Socket s=new Socket(InetAddress.getLocalHost(),10888);
				s.setSoTimeout(500000000);
				BufferedReader br=new BufferedReader(new InputStreamReader(s.getInputStream()));
				String line=br.readLine();
				System.out.println("来自服务器的数据："+line);
				
				br.close();
				s.close();
			}catch(SocketTimeoutException e){
				System.out.println("超过5s，停止接收");
			}
		}

	}
}
