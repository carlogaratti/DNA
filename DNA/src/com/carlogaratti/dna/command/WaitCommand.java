package com.carlogaratti.dna.command;

import java.util.Map;

import com.carlogaratti.dna.command.receiver.Receiver;
import com.carlogaratti.dna.core.Bag;

public class WaitCommand extends Command {

	public WaitCommand(Map<String, String> result) {
		super(result);
		// TODO Auto-generated constructor stub
	}
	
	public WaitCommand(Bag aBag) {
		super(aBag);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		String key = _pullFromKey.get(0);
		String secondsAsString = _bag.get(key).asString();
		long secondsAsLong = Long.parseLong(secondsAsString);
		 try {
			Thread.sleep(secondsAsLong*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
