package com.carlogaratti.dna.command.receiver;

import java.io.PrintStream;

public class ReceiverJavaConsolePrint extends Receiver {

	private PrintStream support;

	@Override
	public void on(Object on) {
		support = (PrintStream) on;

	}

	@Override
	public String value() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void value(String result) {
		support.print(result);

	}

}
