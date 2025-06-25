package com.carlogaratti.dna.command;

import java.math.BigDecimal;
import java.util.Map;

import com.carlogaratti.dna.command.receiver.Receiver;
import com.carlogaratti.dna.core.Bag;

public class PrintCommand extends Command {

	private Receiver _receiver;

	public PrintCommand(Map<String, String> result) {
		super(result);
		// TODO Auto-generated constructor stub
	}
	
	public PrintCommand(Bag aBag) {
		super(aBag);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		for (String each : _pullFromKey) {
			_receiver.value(_bag.get(each).asString());
		}

	}


	public void receiver(Receiver aReceiver) {
		_receiver = aReceiver;

	}

	

}
