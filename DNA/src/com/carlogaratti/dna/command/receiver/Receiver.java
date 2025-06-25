package com.carlogaratti.dna.command.receiver;

public abstract  class Receiver {
	public  abstract void on(Object aWidget);
	public abstract String value();
	public abstract void value(String result);

}
