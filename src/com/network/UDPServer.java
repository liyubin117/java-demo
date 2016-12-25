package com.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPServer {

	public static void main(String[] args) throws IOException, UnknownHostException, InterruptedException {
		DatagramSocket ds=new DatagramSocket(9999,InetAddress.getLocalHost());
		byte[] buf="Hello,i am UDP Server!".getBytes();
		DatagramPacket dp=new DatagramPacket(buf,buf.length,InetAddress.getLocalHost(),8999);
		while(true){
			ds.send(dp);
			Thread.sleep(2000);
		}
		
	}

}
