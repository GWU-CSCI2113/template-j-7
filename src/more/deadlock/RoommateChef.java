package more.deadlock;

class Fridge {
	public static synchronized void getColdIngredients() {
		System.out.println(Thread.currentThread().getName()
				+ " looking for COLD igredients!");
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()
				+ " found COLD ingredients");
	}

	public static synchronized void makeEggs() {
		System.out.println(Thread.currentThread().getName()
				+ " going to the fridge...");
		Fridge.getColdIngredients();
		System.out.println(Thread.currentThread().getName()
				+ " going to the pantry...");
		Pantry.getDryIngredients();
	}
}

class Pantry {
	public static synchronized void getDryIngredients() {
		System.out.println(Thread.currentThread().getName()
				+ " looking for DRY igredients!");
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()
				+ " found DRY ingredients");
	}

	public static synchronized void makeBiscuits() {
		System.out.println(Thread.currentThread().getName()
				+ " going to the pantry...");
		Pantry.getDryIngredients();

		System.out.println(Thread.currentThread().getName()
				+ " going to the fridge...");
		Fridge.getColdIngredients();
	}

}

public class RoommateChef implements Runnable {

	private int dish;

	public RoommateChef(int dish) {
		this.dish = dish;
	}

	public void run() {
		Thread.yield();

		if(dish == 0)
			Fridge.makeEggs();
		else
			Pantry.makeBiscuits();

	}

	public static void main(String[] args) {

		Thread t1 = new Thread(new RoommateChef(1));
		Thread t2 = new Thread(new RoommateChef(0));

		t1.start();
		t2.start();

	}

}
