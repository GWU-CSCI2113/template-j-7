package more.sharing;

class Fridge {
	public void drinkMilk() {
		System.out.println(Thread.currentThread().getName()
				+ " starting to drink!");
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()
				+ " finished drinking");
	}
}

public class Roommate implements Runnable {

	private Fridge f;

	public Roommate(Fridge f) {
		this.f = f;
	}

	public void run() {
		for(int i = 0; i < 5; i++)
		{
			f.drinkMilk();
			Thread.yield();
		}

	}

	public static void main(String[] args) {
		Fridge f1 = new Fridge();

		Thread t1 = new Thread(new Roommate(f1));
		Thread t2 = new Thread(new Roommate(f1));

		t1.start();
		t2.start();

	}

}
