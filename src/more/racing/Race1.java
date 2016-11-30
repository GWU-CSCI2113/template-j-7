package more.racing;

//File: SimpleCounter.java (Module 12/10)
//
//Author: Rahul Simha, Tim Wood
//Created: Nov 17, 1998.
//Modified: Nov 6, 2011
//
//A simple simulation without threads.

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import random.UniformRandom;

class NewFrame extends JFrame {

	// For drawing the race results.
	JPanel drawingArea;

	public NewFrame() {
		// Frame properties.
		this.setTitle("Zombie Race");
		this.setResizable(true);
		this.setSize(600, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		Container cPane = this.getContentPane();
		// cPane.setLayout (new BorderLayout());

		// Quit button.
		JPanel p = new JPanel();
		JButton quitb = new JButton("QUIT");
		quitb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				System.exit(0);
			}
		});
		p.add(quitb);

		// Pressing "start" calls race()
		JButton startb = new JButton("START");
		startb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				race();
			}
		});
		p.add(startb);

		// Now add the panel to the frame.
		cPane.add(p, BorderLayout.SOUTH);

		// A JPanel to draw the results.
		drawingArea = new JPanel();
		cPane.add(drawingArea, BorderLayout.CENTER);

		this.setVisible(true);
	}

	void race() {
		Dimension D = drawingArea.getSize();
		drawingArea.getGraphics().clearRect(0, 0, D.width, D.height);


		// Finish-line is at the right end of the canvas.
		int finishLine = D.width;

		// Create two Zombie instances with different ID's.
		Zombie z1 = new Zombie(1, drawingArea);
		Zombie z2 = new Zombie(2, drawingArea);

		// Each Zombie sleeps a random amount of time.
		int z1Nextwakeup = (int) UniformRandom.uniform(300, 600);
		int z2Nextwakeup = (int) UniformRandom.uniform(300, 600);

		// Keep track of the current time.
		int currentTime = 0;

		// Stop when one Zombie crosses the finish line.

		while ((z1.position < finishLine) && (z2.position < finishLine)) {

			// See which one is done first.
			if (z1Nextwakeup < z2Nextwakeup) {
				try {
					// Static method "sleep" in class Thread.
					Thread.sleep(z1Nextwakeup - currentTime);
				} catch (InterruptedException e) {
					System.out.println(e);
				}
				currentTime = z1Nextwakeup;
				z1.move(); // Move a random distance.
				z1Nextwakeup += (int) UniformRandom.uniform(300, 600);
			} else {
				try {
					Thread.sleep(z2Nextwakeup - currentTime);
				} catch (InterruptedException e) {
					System.out.println(e);
				}
				currentTime = z2Nextwakeup;
				z2.move(); // Move a random distance.
				z2Nextwakeup += (int) UniformRandom.uniform(300, 600);
			}
		}
	}

}

class Zombie {

	public int position = 20; // Starting position.
	int ID; // An ID.
	JPanel drawingArea; // The panel on which to draw.

	public Zombie(int ID, JPanel drawingArea) {
		this.ID = ID;
		this.drawingArea = drawingArea;

		// Draw ID on panel.
		Graphics g = drawingArea.getGraphics();
		g.drawString("" + ID, 5, 20 * ID + 8);
	}

	public void move() {
		// Move a random amount.
		int newPosition = position + (int) UniformRandom.uniform(50, 100);

		// Draw new position.
		Graphics g = drawingArea.getGraphics();
		int size = newPosition - position;
		g.fillRect(position, 20 * ID, size, 10);
		position = newPosition;
	}
}

public class Race1 {
	public static void main(String[] argv) {
		NewFrame nf = new NewFrame();
	}
}
