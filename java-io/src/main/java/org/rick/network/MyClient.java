package org.rick.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/*C/S结构聊天室简单实现客户端*/

//主线程：接收System.in输入，并发送到服务器
public class MyClient {
	public static void main(String[] args) throws Exception{
		Socket s=new Socket("127.0.0.1",30000);
		new Thread(new ClientThread(s)).start();
		
		PrintStream ps=new PrintStream(s.getOutputStream());
		String line=null;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while((line=br.readLine())!=null){
			ps.println(line);
		}
	}
}

//子线程：接收服务器输入、并输出到屏幕
class ClientThread implements Runnable{
	private Socket s;
	BufferedReader br=null;
	
	public ClientThread(Socket s) throws IOException{
		this.s=s;
		br=new BufferedReader(new InputStreamReader(s.getInputStream()));
	}
	
	@Override
	public void run() {
		try{
			String content=null;
			while( (content=br.readLine())!=null ){
				System.out.println(content);
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
}
