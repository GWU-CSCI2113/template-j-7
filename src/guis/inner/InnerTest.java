package inner;

/**
 * Inner and outer classes test.
 * 
 * by T. Wood
 * 
 */

public class InnerTest {
	// This is the outer class
	private int x;
	
	/* Class defined INSIDE InnerTest */
	class MyInnerClass {
		private int y;
		
		public MyInnerClass(int yin) {
			y = yin;
		}
		
		public void go() {
			System.out.println("From inside the inner class:");
			System.out.printf("X = %d and Y = %d\n", x, y);
		}
	}
	
	public InnerTest() {
		x = 12345; // set private data member
		MyInnerClass inny = new MyInnerClass(42);
		inny.go();

	}
	

	public static void main(String[] args) {
		InnerTest test = new  InnerTest();

	}

}
