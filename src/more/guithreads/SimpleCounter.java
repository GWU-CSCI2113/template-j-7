package more.guithreads;

//File: SimpleCounter.java (Module 12/10)
//
//Author: Rahul Simha, Tim Wood
//Created: Nov 17, 1998.
//Modified: Nov 6, 2011
//
//A simple simulation without threads.

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class NewFrame extends JFrame {

	// For drawing the race results.
	private JPanel panel;
	private JLabel counter1;
	private JLabel counter2;

	public NewFrame() {
		// Frame properties.
		this.setTitle("Countdown");
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
		panel = new JPanel();
		cPane.add(panel, BorderLayout.CENTER);

		counter1 = new JLabel("Counter 1");
		counter2 = new JLabel("Counter 2");

		panel.add(counter1);
		panel.add(counter2);


		this.setVisible(true);
	}

	void race() {

		// Keep track of the current time.
		int currentTime = 0;

		// Stop when one Zombie crosses the finish line.


				try {
					// Static method "sleep" in class Thread.
					Thread.sleep(currentTime);
				} catch (InterruptedException e) {
					System.out.println(e);
				}

	}

}


public class SimpleCounter {
	public static void main(String[] argv) {
		NewFrame nf = new NewFrame();
	}
}
