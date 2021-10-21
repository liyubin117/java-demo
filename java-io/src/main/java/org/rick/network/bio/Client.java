package org.rick.network.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;
//BIO客户端，可循环设置端口号
public class Client {
	private static final int TIMEOUT=5;
	private static int port=0;
	
	public static void main(String[] args) throws IOException{
		while(true){
			System.out.println("请输入端口号：");
			Scanner scanner=new Scanner(System.in);
			port=scanner.nextInt();
			try{
				Socket s=new Socket();
				s.connect(new InetSocketAddress(InetAddress.getLocalHost(),port), TIMEOUT*1000);
				/*Socket s=new Socket(InetAddress.getLocalHost(),10888);
				s.setSoTimeout(TIMEOUT*1000);*/
				s.setSoTimeout(TIMEOUT*1000);
				BufferedReader br=new BufferedReader(new InputStreamReader(s.getInputStream()));
				String line=br.readLine();
				System.out.println("来自服务器的数据："+line);
				
				br.close();
				s.close();
			}catch(SocketTimeoutException e){
				System.out.println("超过"+TIMEOUT+"s，停止接收");
			}
		}

	}
}
