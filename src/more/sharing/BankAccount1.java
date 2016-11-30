package more.sharing;

// CS2113
// Zombies more.sharing a bank account
// Author: Tim Wood
// Written: 11/8/2011

public class BankAccount1 {

	private static int balance = 100;

	// find out how much money we have
	public static int getBalance() {
		return balance;
	}

	// remove some money from the account
	public static void withdrawl(int amt) {
		balance = balance - amt;
	}

	public static void makeWithdrawal(int amount){
		int b = BankAccount1.getBalance();
		if (b >= amount) {
			System.out.println(Thread.currentThread().getName() + " START: Balance is $" + b);

			try {
				System.out.println(Thread.currentThread().getName() + " is filling out form...");
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " finished form, about to withdrawl $" + amount);
			BankAccount1.withdrawl(amount);
			System.out.println(Thread.currentThread().getName() + " DONE: Balance is $" + getBalance());
		}
		else{
			System.out.println(Thread.currentThread().getName() + ": not enough in account!");
		}
	}

	public static void main(String[] args) {
		// Initial balanace is $100
		// Number of times to try to remove $10
		int numIterations = 12;

		ZombieBanker1 z1 = new ZombieBanker1(numIterations);
		ZombieBanker1 z2 = new ZombieBanker1(numIterations);

		Thread t1 = new Thread(z1);
		t1.setName("ZombieBanker 1");
		Thread t2 = new Thread(z2);
		t2.setName("                                         ZombieBanker 2");

		t1.start();
		// t2.start(); /// START WITH JUST ONE ZOMBIE
	}

}

class ZombieBanker1 implements Runnable {

	private int iterations;
	public ZombieBanker1(int iterations){
		this.iterations = iterations;
	}

	public void run() {
		for (int i = 0; i < iterations; i++) {
			// try to make withdrawal
			BankAccount1.makeWithdrawal(10);
			// check if we took too much out
			int b = BankAccount1.getBalance();
			if(b < 0) {
				System.out.println(Thread.currentThread().getName() + ": Oh no! Account is overdrawn: $" + b);
			}
			// yield to let someone else run
			Thread.yield();
		}
	}
}

