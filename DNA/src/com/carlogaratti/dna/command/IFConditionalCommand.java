package com.carlogaratti.dna.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.carlogaratti.dna.command.condition.Condition;
import com.carlogaratti.dna.command.condition.MathGreaterCondition;
import com.carlogaratti.dna.core.Bag;

public class IFConditionalCommand extends Command{

	private Condition _condition;
	private Map<String, Command> _map = new HashMap<>();
	
	

	public IFConditionalCommand(Bag bag) {
		super(bag);
	}

	public void condition(Condition aCondition) {
		_condition = aCondition;
	}


	@Override
	public void execute() {
		String evaluation = _condition.evaluate();
		List<Command> list = new ArrayList<Command>();
		list.add(_map.get(evaluation));
		_bag.putKey(_currentPullKey).asList(list);
	}

	public void commandToExecute(Command aCommandToExecute) {
		_map.put("TRUE", aCommandToExecute);
		_map.put("FALSE", new DoNotingCommand());
		
		
	}

	

}
