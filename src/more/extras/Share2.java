package more.extras;

public class Share2{

	// Define as inner classes (only done to prevent conflicts between our different test files)
	class Supplier implements Runnable{
		// number of iterations to run
		private int cnt;
		// number of new brains supplied per iteration
		private int newBrains = 10;

		Supplier(int cnt) {
			this.cnt = cnt;
		}

		public void run() {
			for(int i=0; i < cnt; i++){
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				adjustBrains(newBrains);

			}
			System.out.println("Supplier brains at end: " + brains);
		}

	}
	// Define as inner classes (only done to prevent conflicts between our different test files)
	class Eater implements Runnable{

		// number of iterations to run
		private int cnt;
		// number of brains eaten per iteration
		private int eatenBrains = -10;

		Eater(int cnt) {
			this.cnt = cnt;
		}

		public void run() {
			for(int i=0; i < cnt; i++){
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				adjustBrains(eatenBrains);

			}
			System.out.println("Eater brains at end: " + brains);
		}

	}



	public static int brains;

	public  void adjustBrains(int amt) {
		brains += amt;
		System.out.println("Added " + amt + "... brains = " + brains);
	}

	public Share2() {
		brains = 100;
		int iterations = 10;

		Eater e1 = new Eater(iterations);
		Supplier s1 = new Supplier(iterations);

		Thread tE = new Thread(e1);
		Thread tS = new Thread(s1);

		tE.start();
		tS.start();

	}

	public static void main(String[] argv) {
		new Share2();
	}


}
