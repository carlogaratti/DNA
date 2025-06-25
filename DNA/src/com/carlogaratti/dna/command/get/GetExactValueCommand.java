package com.carlogaratti.dna.command.get;

import com.carlogaratti.dna.command.Command;
import com.carlogaratti.dna.command.receiver.Receiver;
import com.carlogaratti.dna.core.Bag;

public class GetExactValueCommand extends Command {

	private String _exactValue;
	private Command _on;

	public GetExactValueCommand(Bag aBag) {
		super(aBag);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		_exactValue = _on.bag().get(_currentPullKey).asString();

	}

	
	
	public String value() {
		// TODO Auto-generated method stub
		return _exactValue;
	}
	
	
	public void on(Command aCommand) {
		_on = aCommand;

	}

}
