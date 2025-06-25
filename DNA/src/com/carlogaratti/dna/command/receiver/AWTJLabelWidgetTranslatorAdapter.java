package com.carlogaratti.dna.command.receiver;

import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class AWTJLabelWidgetTranslatorAdapter extends Receiver {
	private JLabel _widget;
	@Override
	public void on(Object aWidget) {
		_widget = (JLabel) aWidget;
		

	}

	@Override
	public String value() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public void value(String result) {
		//System.out.println("AWTJLabelWidgetTranslatorAdapter: " + result);
		
		_widget.setText(result);
		
		
	}

}
