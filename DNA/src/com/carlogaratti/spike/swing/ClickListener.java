package com.carlogaratti.spike.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ClickListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("I was clicked!!!");
	}

}
