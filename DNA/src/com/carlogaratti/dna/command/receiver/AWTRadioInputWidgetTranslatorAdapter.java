package com.carlogaratti.dna.command.receiver;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;

public class AWTRadioInputWidgetTranslatorAdapter extends Receiver {
	private JRadioButton _widget;
	
	public void on(Object aWidget) {
		System.out.println("AWTRadioInputWidgetTranslatorAdapter: " + aWidget);
		_widget = (JRadioButton) aWidget;
		
	}
	public String value() {
		return  _widget.isSelected() ?  "TRUE" : "FALSE";
		
		
	}
	@Override
	public void value(String result) {
		// TODO Auto-generated method stub
		
	}
}
