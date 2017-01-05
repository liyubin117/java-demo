package com.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SelectorClient {
	private static int remotePort = 8000;
	// private static int localPort=9999;

	public static void main(String[] args) throws UnknownHostException, IOException {
		// Socket socket=new
		// Socket(InetAddress.getLocalHost(),remotePort,InetAddress.getLocalHost(),localPort);
		Socket socket = new Socket(InetAddress.getLocalHost(), remotePort);
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String receive = null;
		try {
			// while(true){
			receive = in.readLine();
			System.out.println("receive:" + receive);
			// }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				socket = null;
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				in = null;
			}
		}

	}

}
