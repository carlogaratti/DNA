package com.carlogaratti.dna.command;

import java.util.Map;

import com.carlogaratti.dna.command.receiver.Receiver;
import com.carlogaratti.dna.core.Bag;
import com.carlogaratti.progetti.pomoapp.TheadOnClickStrategy;

public class StopCommand extends Command {

	private Receiver _receiver;

	public StopCommand(Map<String, String> result) {
		super(result);
		// TODO Auto-generated constructor stub
	}
	
	public StopCommand(Bag aBag) {
		super(aBag);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void execute() {
		_receiver.value(null);

	}

	
	public void receiver(Receiver aReceiver) {
		
		_receiver = aReceiver;

	}

	

}
