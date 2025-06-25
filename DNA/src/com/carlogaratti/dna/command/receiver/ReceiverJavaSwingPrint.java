package com.carlogaratti.dna.command.receiver;

import java.awt.EventQueue;

import com.carlogaratti.spike.swing.PopUp;

public class ReceiverJavaSwingPrint extends Receiver {
	
	private PopUp popUp;

	public ReceiverJavaSwingPrint() {
		popUp = new PopUp();
		 EventQueue.invokeLater(popUp);
	}

	@Override
	public void on(Object on) {
		popUp = (PopUp) on;

	}

	@Override
	public String value() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void value(String result) {
		popUp.print(result);

	}

}
