package org.rick.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*C/S结构聊天室简单实现服务器*/

//主线程：监听
public class MyServer {
	public static List<Socket> socketList=Collections.synchronizedList(new ArrayList<Socket>());
	
	public static void main(String[] args) throws IOException{
		ServerSocket ss=new ServerSocket(30000);
		while(true){
			Socket s=ss.accept();
			socketList.add(s);
			
			new Thread(new ServerThread(s)).start();
		}
	}

}

//子线程：接收客户端输入，并发送到所有客户端
class ServerThread implements Runnable{
	Socket s=null;
	BufferedReader br=null;
	
	public ServerThread(Socket s) throws IOException{
		this.s=s;
		br=new BufferedReader(new InputStreamReader(s.getInputStream()));
	}
	
	@Override
	public void run() {
		try{
			String content=null;
			while((content=readFromClient()) != null){
				for(Socket s:MyServer.socketList){
					PrintStream ps=new PrintStream(s.getOutputStream());
					ps.println(content);
				}
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	private String readFromClient() {
		try{
			return br.readLine();
		}catch(IOException e){
			MyServer.socketList.remove(s);
		}
		return null;
	}
	
}
