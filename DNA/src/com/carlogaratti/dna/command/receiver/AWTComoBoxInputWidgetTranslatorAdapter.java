package com.carlogaratti.dna.command.receiver;

import javax.swing.JComboBox;

public class AWTComoBoxInputWidgetTranslatorAdapter extends   Receiver{
	private JComboBox<String> _widget;
	
	public void on(Object aWidget) {
		_widget = (JComboBox<String>) aWidget;
		
	}
	public String value() {
		return _widget.getSelectedItem().toString();
		
	}
	@Override
	public void value(String result) {
		// TODO Auto-generated method stub
		
	}
}
