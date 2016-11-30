package more.waitnotify;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;

public class WaitTest2 {

	// Only using inner classes to prevent class conflicts between different versions of this file
	class Zombie implements Runnable {
		int x = 0;
		int speed = 2;
		int sightRange = 4;
		Human hum;

		public void groan() {
			System.out.println("Z: I smell brains.... I'm at " + x);
			hum.runAway();
		}

		@Override
		public void run() {
			while (true) {
				if (hum.x > x) {
					x++;

					if (hum.x - x < sightRange) {
						groan();
					}

				}
				try {
					Thread.sleep(1000 / speed);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}

	class Human implements Runnable {
		int x = 5;
		int speed = 10;
		Zombie zomb;
		int tooFar = 8;
		boolean run = true;

		// called by zombie when it gets close to the human
		public synchronized void runAway() {
			this.notify();
			run = true;
			System.out.println("H: Uh Ohs... better run away!");
		}

		public synchronized void taunt() {
			run = false;

			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Done taunt.");

		}

		@Override
		public void run() {
			while (true) {
				System.out.println("H: looping...");

				if (Math.abs(zomb.x - x) > tooFar) {
					// far away from zombie, so stop running and taunt
					taunt();
				}
				if (run) {
					x++;
				}

				try {
					Thread.sleep(1000 / speed);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private DotPanel dp;

	public WaitTest2(int w, int h, int dotSize) {

		/* Create the frame to hold the DotPanel */
		JFrame frame = new JFrame("DotPanel Test");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/* Create and set the size of the panel */
		dp = new DotPanel(w, h, dotSize);

		/* Add the panel to the frame */
		Container cPane = frame.getContentPane();
		cPane.add(dp);
		// pack() will resize the frame to fit the panel--YOU MUST CALL THIS
		frame.pack();

		// Initialize the DotPanel---you cannot do this until after calling
		// pack!!!
		dp.init();
		frame.setVisible(true);


		// this is messy code. You could do better.
		Zombie zomb = new Zombie();
		Human hum = new Human();
		zomb.hum = hum;
		hum.zomb = zomb;

		Thread tz = new Thread(zomb);
		Thread th = new Thread(hum);
		tz.start();
		th.start();

		while (true) {
			// System.out.println("Zombie at " + zomb.x);
			dp.clear();
			dp.setPenColor(Color.red);
			dp.pixel(zomb.x, 0);
			dp.setPenColor(Color.blue);
			dp.pixel(hum.x, 0);
			dp.show(20);
		}

	}

	public static void main(String[] argv) {

		int w = 50; // number of dots wide
		int h = 1; // number of dots tall
		int dotSize = 15; // size of a "dot" in screen pixels.
		WaitTest2 test = new WaitTest2(w, h, dotSize);

	}

}
