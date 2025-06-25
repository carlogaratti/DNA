package com.carlogaratti.dna.command;

import java.util.Map;

import com.carlogaratti.dna.command.receiver.Receiver;
import com.carlogaratti.dna.core.Bag;

public class StopPlaySoundCommand extends Command {

	private Receiver _receiver;


	public StopPlaySoundCommand(Map<String, String> result) {
		super(result);
	}
	
	public StopPlaySoundCommand(Bag aBag) {
		super(aBag);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		_receiver.value();
	}

	public void receiver(Receiver aReceiver) {
		_receiver = aReceiver;

	}

}
