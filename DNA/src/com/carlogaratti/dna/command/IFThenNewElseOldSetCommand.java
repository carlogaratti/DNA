package com.carlogaratti.dna.command;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import com.carlogaratti.dna.command.receiver.Receiver;
import com.carlogaratti.dna.core.Bag;

public class IFThenNewElseOldSetCommand extends Command {

	private String _checkValue;

	public IFThenNewElseOldSetCommand(Map<String, String> result) {
		super(result);
	}
	
	public IFThenNewElseOldSetCommand(Bag aBag) {
		super(aBag);
	}
/*
	@Override
	public void execute() {
		String newValueKey = _pullFromKey.get(1);
		Command setCommand = new SetCommand(_context);
		setCommand.pullFrom(newValueKey);
		Map<String, Command> map = new HashMap<>();
		map.put("equals", _next);
		map.put("!equals", setCommand);
		BigDecimal checkValue = new BigDecimal(_checkValue);
		BigDecimal actualValue = new BigDecimal(_context.get(_pullFromKey.get(0)));
		BigDecimal newValue = new BigDecimal(_context.get(newValueKey));
		if (actualValue.equals(checkValue)) {
			actualValue = newValue;
			//_context.put(_pushOnKey, newValue.toPlainString());
			_next.execute();
		}
		_context.put(_pushOnKey, actualValue.toPlainString());//altrimenti vecchio valore
		

	}
	*/
	@Override
	public void execute() {
		BigDecimal checkValue = new BigDecimal(_checkValue);
		String newValueKey = _pullFromKey.get(1);
		String actualValueKey = _pullFromKey.get(0);
		BigDecimal actualValue = new BigDecimal(_bag.get(actualValueKey).asString());
		
		//Caso True
		Command setCommandForTrue = new SetCommand(_bag);
		setCommandForTrue.pullFrom(newValueKey);
		setCommandForTrue.pushResultOn(actualValueKey);
		
		//Caso False
		Command setCommandForFalse = new SetCommand(_bag);
		setCommandForFalse.pullFrom(actualValueKey);
		setCommandForFalse.pushResultOn(actualValueKey);
		
		Boolean result = actualValue.equals(checkValue);
		
		Map<Boolean, CompositeCommand> map = new HashMap<>();
		CompositeCommand commandsForTrue = new CompositeCommand(_bag);
		commandsForTrue.add(setCommandForTrue);
		commandsForTrue.add(_next);
		CompositeCommand commandsForFalse = new CompositeCommand(_bag);
		commandsForFalse.add(setCommandForFalse);
		map.put(true, commandsForTrue);
		map.put(false, commandsForFalse);
		
		Command command = map.get(result);
		
		command.execute();
		
	}

	

	

	public void checkValue(String aCheckValue) {
		_checkValue = aCheckValue;
		
	}

}
