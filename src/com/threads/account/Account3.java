package com.threads.account;


public class Account3 extends Account {


	public Account3(String account_name, double balance) {
		super(account_name, balance);
	}

	@Override
	public synchronized void draw(String name, double d, Account a) {
			if (a.getBalance() >= d) {

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				a.minusBalance(d);
				System.out.println("account:" + a.getAccountName() + "," + name
						+"take:"+d+","
						+ " rest:" + a.getBalance());
			} else {
				System.out.println("account:"+a.getAccountName()+",rest: "+a.getBalance()
				+","+name+"take："+d
				+",money is not enough to take!");			}
	}

}
