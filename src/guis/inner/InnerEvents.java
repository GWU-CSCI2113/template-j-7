package inner;

//
// Authors: Rahul Simha and Tim Wood
// Created: November 2, 2000
//
// Two button example - with local classes, v2

import java.awt.*;
import javax.swing.*;

import java.awt.event.*;

class NewFrame extends JFrame {
	// Note: Frame does not implement ActionListener anymore

	// Data.
	JButton quitB; // Quit button.
	JButton helloB, byeB; // Two silly buttons.
	JLabel msg;


	// Constructor.
	public NewFrame(int width, int height) {
		// Set the title and other frame parameters.
		this.setTitle("Inner Events");
		this.setResizable(true);
		this.setSize(width, height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// We'll use a flowlayout
		Container cPane = this.getContentPane();
		cPane.setLayout(new FlowLayout());

		// Quit button
		quitB = new JButton("Quit");
		quitB.setBackground(Color.red);

		// inner class listener:
		class QuitActionListener implements ActionListener {
			public void actionPerformed(ActionEvent a) {
				System.exit(0);
			}
		}

		class InC implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				msg.setText(e.getActionCommand());
			}
		}

		quitB.addActionListener(new QuitActionListener());
		cPane.add(quitB);


		// "Hello" button
		helloB = new JButton("Hello");
		helloB.setBackground(Color.green);
		cPane.add(helloB);

		// "World" button
		byeB = new JButton("Bye");
		byeB.setBackground(Color.red);
		cPane.add(byeB);

		msg = new JLabel("msg...");
		cPane.add(msg);

		helloB.addActionListener(new InC());
		byeB.addActionListener(new InC());

		// Show the frame.
		this.setVisible(true);
	}

} // End of class "NewFrame"

public class InnerEvents {

	public static void main(String[] argv) {
		NewFrame nf = new NewFrame(400, 200);
	}

}
