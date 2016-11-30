package inner;

//
//Author: Rahul Simha and Tim Wood
//Created: November 2, 2000
//
//Mouse click inner class example

import java.awt.*;
import javax.swing.*;

import java.awt.event.*;

class NewFrameIM extends JFrame {

	// Data.
	JButton quitB; // Quit button.
	JButton helloB, worldB; // Two silly buttons.

	String helloStr = "Hello ";
	String worldStr = "World!";

	// Constructor.
	public NewFrameIM(int width, int height) {
		// Set the title and other frame parameters.
		this.setTitle("Mouse Click");
		this.setResizable(true);
		this.setSize(width, height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// We'll use a flowlayout
		Container cPane = this.getContentPane();
		cPane.setLayout(new FlowLayout());

		// Quit button
		quitB = new JButton("Quit");
		// Local listener:
		class QuitActionListener implements ActionListener {
			public void actionPerformed(ActionEvent a) {
				System.exit(0);
			}
		}

		quitB.addActionListener(new QuitActionListener());
		cPane.add(quitB);


		// Deal with mouse clicks.
		class FrameMouseListener implements MouseListener {

			public void mouseClicked(MouseEvent m) {
				System.out.println("Quit touching me!");
			}

			// Empty methods - to complete interface.
			public void mouseEntered(MouseEvent m) {
			}

			public void mouseExited(MouseEvent m) {
			}

			public void mousePressed(MouseEvent m) {
			}

			public void mouseReleased(MouseEvent m) {
			}
		}

		// Add to frame.
		cPane.addMouseListener(new FrameMouseListener());

		// Show the frame.
		this.setVisible(true);
	}

} // End of class "NewFrame"

public class InnerMice {

	public static void main(String[] argv) {
		NewFrameIM nf = new NewFrameIM(300, 200);
	}

}