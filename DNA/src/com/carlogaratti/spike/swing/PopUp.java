package com.carlogaratti.spike.swing;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class PopUp extends JFrame implements Runnable {

	private JTextField kooltxt = new JTextField("");

	@Override
	public void run() {
		 System.out.println("Thread started");
	      this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	      JPanel mainPanel = new JPanel();
	      mainPanel.add(kooltxt);
	      this.setContentPane(mainPanel);
	      pack();
	      setLocationRelativeTo(null);
	      this.setVisible(true);

	}

	public void print(String result) {
		kooltxt.setText(result);
		
	}

}
