package com.threads.account;

public class DrawThread extends Thread{
	private Account account;
	private double drawBalance;
	
	public DrawThread(String s,Account a,double d){
		super(s);
		this.account=a;
		this.drawBalance=d;
	}
	
	@Override
	public void run(){
		account.draw(super.getName(), this.drawBalance,this.account);
	}
	

	public static void main(String[] args){
		Account a=new Account("123890",5000);
		new DrawThread("李",a,4000).start();
		new DrawThread("叶",a,1001).start();
		
		Account2 b=new Account2("123891",5000);
		new DrawThread("李",b,4000).start();
		new DrawThread("叶",b,1001).start();
	}
}
