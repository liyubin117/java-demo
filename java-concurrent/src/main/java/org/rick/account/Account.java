package org.rick.account;

public class Account {
	private double balance;
	private String account_name;
	
	public Account(String account_name,double balance){
		this.account_name=account_name;
		this.balance=balance;
	}
	
	public void setAccountName(String s){
		this.account_name=s;
	}
	public String getAccountName(){
		return this.account_name;
	}
	public void addBalance(double f){
		this.balance=this.balance+f;
	}
	public void minusBalance(double f){
		this.balance=this.balance-f;
	}
	public double getBalance(){
		return this.balance;
	}
	
	public void draw(String name,double d,Account a){
		synchronized(a){
			if(a.getBalance()>=d){
				//System.out.println("account:"+a.getAccountName()+" "+name+"take:"+d);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				a.minusBalance(d);
				System.out.println("account:" + a.getAccountName() + "," + name
						+"take:"+d+","
						+ " rest:" + a.getBalance());
				}else{
				System.out.println("account:"+a.getAccountName()+",rest: "+a.getBalance()
				+","+name+"take："+d
				+",money is not enough to take!");			
			}
		}
	}

}
