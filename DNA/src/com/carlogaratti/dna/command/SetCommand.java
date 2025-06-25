package com.carlogaratti.dna.command;

import java.util.Map;

import com.carlogaratti.dna.command.receiver.Receiver;
import com.carlogaratti.dna.core.Bag;

public class SetCommand extends Command {

	public SetCommand(Map<String, String> result) {
		super(result);
		// TODO Auto-generated constructor stub
	}

	public SetCommand(Bag aBag) {
		super(aBag);
	}

	@Override
	public void execute() {
		//String value = _context.get(_pullFromKey.get(0));
		//_context.put(_pushOnKey, value);
		String keyToExtract = _pullFromKey.get(0);
		String value = _bag.get(keyToExtract).asString();
		//System.out.println("SetCommand: keyToExtract,value " + keyToExtract + " ," + value);
		_bag.putKey(_pushOnKey).asString(value);
	}

	


}
