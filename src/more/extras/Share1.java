package more.extras;

public class Share1{

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
				// get the current number of brains
				int currentBrains = Share1.brains;
				// update by the new brain supply
				currentBrains += newBrains;
				Share1.brains = currentBrains;
			}
		}

	}
	// Define as inner classes (only done to prevent conflicts between our different test files)
	class Eater implements Runnable{

		// number of iterations to run
		private int cnt;
		// number of brains eaten per iteration
		private int eatenBrains = 10;

		Eater(int cnt) {
			this.cnt = cnt;
		}

		public void run() {
			for(int i=0; i < cnt; i++){
				// get the current number of brains
				int currentBrains = Share1.brains;
				// update by the new brain supply
				currentBrains -= eatenBrains;
				Share1.brains = currentBrains;
			}
		}

	}



	public static int brains;

	public Share1() {
		brains = 100;
		int iterations = 10000;

		Eater e1 = new Eater(iterations);
		Supplier s1 = new Supplier(iterations);

		Thread tE = new Thread(e1);
		Thread tS = new Thread(s1);

		tE.start();
		tS.start();

		System.out.println("Number of brains left at end: " + brains);
	}

	public static void main(String[] argv) {
		new Share1();
	}


}
