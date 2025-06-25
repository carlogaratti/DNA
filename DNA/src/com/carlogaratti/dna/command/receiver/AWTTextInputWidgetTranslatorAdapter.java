package com.carlogaratti.dna.command.receiver;

import java.util.Map;

import javax.swing.JTextField;

public class AWTTextInputWidgetTranslatorAdapter extends Receiver{
	
	
	
	private JTextField _widget;
	public void on(Object aWidget) {
		_widget = (JTextField) aWidget;
		
	}
	public String value() {
		return _widget.getText();
		
	}
	@Override
	public void value(String result) {
		// TODO Auto-generated method stub
		
	}
}
