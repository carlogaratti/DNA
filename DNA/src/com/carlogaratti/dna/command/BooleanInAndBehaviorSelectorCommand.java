package com.carlogaratti.dna.command;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.carlogaratti.dna.command.receiver.Receiver;
import com.carlogaratti.dna.core.Bag;

public class BooleanInAndBehaviorSelectorCommand extends Command {

	private String _checkValue;
	private Command _commandForTrue;
	private Command _commandForFalse;

	public BooleanInAndBehaviorSelectorCommand(Map<String, String> result) {
		super(result);
	}
	
	public BooleanInAndBehaviorSelectorCommand(Bag abag) {
		super(abag);
	}

	@Override
	public void execute() {
		BigDecimal checkValue = new BigDecimal(_checkValue);
		
		Boolean result = true;
		for (String each : _pullFromKey) {
			String stringValue = _bag.get(each).asString();
			BigDecimal value =  new BigDecimal(stringValue);
			result = result && value.equals(checkValue);
		}
		
		Command setCommandForTrue = _commandForTrue;
		Command setCommandForFalse = _commandForFalse;
		
		Map<Boolean, Command> map = new HashMap<>();
		map.put(true, setCommandForTrue);
		map.put(false, setCommandForFalse);
		map.get(result).execute();;
		
	}

	

	
	public void addForTrue(Command aCommand) {
		_commandForTrue = aCommand;

	}
	
	public void addForFalse(Command aCommand) {
		_commandForFalse = aCommand;

	}

	public void checkValue(String aCheckValue) {
		_checkValue = aCheckValue;
		
	}



	public void add(Command aCommand) {
		// TODO Auto-generated method stub
		
	}

	

}
