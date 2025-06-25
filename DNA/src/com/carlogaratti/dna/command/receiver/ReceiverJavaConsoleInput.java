package com.carlogaratti.dna.command.receiver;

import java.util.Scanner;

import javax.swing.JTextField;

public class ReceiverJavaConsoleInput extends Receiver {

	private Scanner _widget;
	public void on(Object aWidget) {
		_widget = (Scanner) aWidget;
		
	}
	public String value() {
		return _widget.nextLine();
		
	}
	@Override
	public void value(String result) {
		// TODO Auto-generated method stub
		
	}
	

}
