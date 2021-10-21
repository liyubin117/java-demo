package org.rick.io;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

//管道IO流，可用于线程间通信
public class PipedDemo1 {
	public static void main(String[] args) throws IOException {
		Send s=new Send();
		Receive r=new Receive();
		s.getPos().connect(r.getPis());
		new Thread(s).start();
		new Thread(r).start();
	}
}

class Send implements Runnable{
	private PipedOutputStream pos=null;
	
	public Send(){
		this.pos=new PipedOutputStream();
	}
	@Override
	public void run() {
		String s="Hello Piped Stream";
		try {
			pos.write(s.getBytes());
			pos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public PipedOutputStream getPos(){
		return this.pos;
	}
}

class Receive implements Runnable{
	private PipedInputStream pis;
	
	public Receive(){
		this.pis=new PipedInputStream();
	}
	@Override
	public void run() {
		byte[] bytes=new byte[1024];
		int len=0;
		try {
			len=pis.read(bytes);
			pis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("接收的内容为："+new String(bytes));
		
	}
	public PipedInputStream getPis(){
		return this.pis;
	}
	
}
