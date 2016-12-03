package guis.buttons;

//adapted from code by Prof. Simha

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


class QuitActionListener implements ActionListener{
	public void actionPerformed(ActionEvent a) {
		System.exit(0);
	}

}

class helloActionListener implements ActionListener{

	JLabel label;
	String msg;

	public helloActionListener(JLabel label) {
		this.label = label;
	}
	public void actionPerformed(ActionEvent a) {

		label.setText(a.getActionCommand());


	}

}

class NewFrame extends JFrame {
	// Note: Frame does not implement ActionListener anymore

	// Data.
	private JButton quitB; // Quit button.
	private JButton helloB, byeB; // Two silly buttons.
	private JLabel msg;



	// Constructor.
	public NewFrame(int width, int height) {
		// Set the title and other frame parameters.
		this.setTitle("Button Test");
		this.setResizable(true);
		this.setSize(width, height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// We'll use a flowlayout
		Container cPane = this.getContentPane();
		cPane.setLayout(new FlowLayout());

		// Quit button
		quitB = new JButton("Quit");
		quitB.setBackground(Color.red);

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

		helloActionListener listener = new helloActionListener(msg);
		helloB.addActionListener(listener);
		byeB.addActionListener(listener);


		// Show the frame.
		this.setVisible(true);
	}

} // End of class "NewFrame"

public class ButtonTest {

	public static void main(String[] argv) {
		NewFrame nf = new NewFrame(300, 200);
	}

}
