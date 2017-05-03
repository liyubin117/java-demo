package com.threads;

//线程本地变量
public class ThreadLocalDemo {
	
	private static ThreadLocal<Integer> seqNum=new ThreadLocal<Integer>(){
		@Override
		public Integer initialValue(){
			return 0;
		}
	};
	
	public int getNextNum(){
		seqNum.set(seqNum.get()+1);
		return seqNum.get();
	}
	
	private static class Client extends Thread{
		private ThreadLocalDemo sn;
		public Client(ThreadLocalDemo sn){
			this.sn=sn;
		}
		public void run(){
			for(int i=0;i<3;i++){
				System.out.println(Thread.currentThread().getName()+":"+sn.getNextNum());
			}
		}
	}

	public static void main(String[] args) {
		ThreadLocalDemo sn=new ThreadLocalDemo();
		//三个线程共享sn
		Client c1=new Client(sn);
		Client c2=new Client(sn);
		Client c3=new Client(sn);
		c1.start();
		c2.start();
		c3.start();
	}

}
