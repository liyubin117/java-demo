package com.threads.account;

import java.util.concurrent.locks.ReentrantLock;

public class Account2 extends Account {

	private ReentrantLock lock = new ReentrantLock();

	public Account2(String account_name, double balance) {
		super(account_name, balance);
	}

	@Override
	public void draw(String name, double d, Account a) {
		lock.lock();

		try {
			if (a.getBalance() >= d) {
				System.out.println("account:" + a.getAccountName() + " " + name
						+ "take:" + d);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				a.minusBalance(d);
				System.out.println("account:" + a.getAccountName() + " " + name
						+ " rest:" + a.getBalance());
			} else {
				System.out.println("money is not enough to take!");
			}
		} finally {
			lock.unlock();
		}
	}

}
