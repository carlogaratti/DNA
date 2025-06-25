package com.carlogaratti.progetti.pomoapp;

import com.carlogaratti.dna.command.receiver.Receiver;

public class ThreadStopperReceiverAdapter extends Receiver {

	TheadOnClickStrategy _theadOnClickStrategy;
	
	@Override
	public void on(Object aWidget) {
		_theadOnClickStrategy = (TheadOnClickStrategy) aWidget;

	}

	@Override
	public String value() {
		return null;
		
	}

	@Override
	public void value(String result) {
		System.out.println("stoppo il thread");
		_theadOnClickStrategy.stop();
		System.out.println("stoppato il thread");

	}

}
