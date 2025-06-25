package com.carlogaratti.dna.chain.starter;

import com.carlogaratti.dna.chain.Chain;
import com.carlogaratti.dna.command.Command;
import com.carlogaratti.dna.command.condition.Condition;
import com.carlogaratti.dna.core.Bag;

public class NextChainCommand extends Command{

	private Bag _bag;
	private Chain _next;
	private Condition _condition;

	public NextChainCommand(Bag aBag) {
		_bag = aBag;
	}

	public void next(Chain aNext) {
		_next = aNext;
		
	}

	@Override
	public void execute() {
		_next.handle(_condition);
		
	}

	public void condition(Condition aCondition) {
		_condition = aCondition;
		
	}

}
