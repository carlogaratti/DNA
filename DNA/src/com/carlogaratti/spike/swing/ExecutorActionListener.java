package com.carlogaratti.spike.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JRadioButton;

public class ExecutorActionListener implements ActionListener {

	private JRadioButton _aRadioButton;

	public ExecutorActionListener(JRadioButton aRadioButton) {
		_aRadioButton = aRadioButton;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(_aRadioButton.isSelected()) {
    		
    		commandOn.computeIfAbsent("LOGEXECUTOR", k -> new ArrayList<Object>()).add(name);
    	}

	}

}
