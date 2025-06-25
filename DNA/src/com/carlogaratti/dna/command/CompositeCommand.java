package com.carlogaratti.dna.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.carlogaratti.dna.command.receiver.Receiver;
import com.carlogaratti.dna.core.Bag;

public class CompositeCommand extends Command{
	
	private List<Command> _commands = new ArrayList<Command>();

	public CompositeCommand(Map<String, String> result) {
		super(result);
	}
	
	public CompositeCommand(Bag aBag) {
		super(aBag);
	}
	
	public void add(Command aCommand) {
		_commands.add(aCommand);
	}

	@Override
	public void execute() {
		for (Command each : _commands) {
			each.execute();
		}
		
	}

	


	

	@Override
	public void show() {
		super.show();
		System.out.print("contengo  [ ");
		for (Command each : _commands) {
			each.show();
		}
		System.out.print(" ] fine contengo");
	}


	public void deleteAll() {
		_commands.clear();
		
	}

	

}
