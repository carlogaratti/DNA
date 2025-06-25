package com.carlogaratti.dna.command.receiver;

import javax.swing.JOptionPane;

public class AWTJOptionPaneWidgetTranslatorAdapter extends Receiver {

	private JOptionPane _on;
	@Override
	public void on(Object on) {
		_on = (JOptionPane) on;

	}

	@Override
	public String value() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void value(String result) {
		_on.showInputDialog(result);

	}

}
