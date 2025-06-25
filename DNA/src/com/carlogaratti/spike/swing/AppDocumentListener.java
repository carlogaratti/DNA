package com.carlogaratti.spike.swing;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class AppDocumentListener implements DocumentListener {

	private JTextField _textField;

	public AppDocumentListener(JTextField textField) {
		_textField = textField;
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		System.out.println("insertUpdate");
		System.out.println(_textField.getText());

	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		System.out.println("removeUpdate");
		System.out.println(_textField.getText());

	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		System.out.println("changedUpdate");
		System.out.println(_textField.getText());

	}

}
