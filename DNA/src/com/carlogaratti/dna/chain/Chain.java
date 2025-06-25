package com.carlogaratti.dna.chain;

import java.util.HashMap;
import java.util.Map;

import com.carlogaratti.dna.command.Command;
import com.carlogaratti.dna.command.condition.Condition;

public class Chain {

	private Chain _next;
	private Map<String, Command> _map= new HashMap<String, Command>();

	public void next(Chain aNext) {
		_next = aNext;
		
	}

	public void handle(Condition aCondition) {
		String evaluation = aCondition.evaluate();
		System.out.println("evaluation: " + evaluation);
		Command command = _map.get(evaluation);
		command.execute();
		_next.handle(aCondition);
	}

	public void asTrue(Command aCommand) {
		_map.put("TRUE", aCommand);
		
	}

	public void asFalse(Command aCommand) {
		_map.put("FALSE", aCommand);
		
	}

}
