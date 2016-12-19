package com.threads;

public class NoVisibility {
	private static int number=0;
	private static boolean ready=false;
	
	private static class ReaderThread extends Thread{
		public void run(){
			while(!ready){
				Thread.yield();
			}
			System.out.println(number);
		}
	}

	public static void main(String[] args) {
		//while(true){
			ReaderThread rt=new ReaderThread();
			rt.start();
			number=10;
			ready=true;
		//}
		
	}

}
