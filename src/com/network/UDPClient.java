package com.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPClient {

	public static void main(String[] args) throws IOException {
		DatagramSocket ds=new DatagramSocket(8999,InetAddress.getLocalHost());
		byte[] buf=new byte[1024];
		DatagramPacket dp=new DatagramPacket(buf,1024);
		String str=null;
		while(true){
			ds.receive(dp);
			str=new String(dp.getData(),0,dp.getLength());
			System.out.println("receive from "+dp.getAddress().getHostName()+":"+dp.getPort()+"："+str);
		}
		
	}

}
