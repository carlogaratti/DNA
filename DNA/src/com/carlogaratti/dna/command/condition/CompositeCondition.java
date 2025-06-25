package com.carlogaratti.dna.command.condition;

import java.util.ArrayList;
import java.util.List;

public abstract class CompositeCondition extends Condition {

	protected List<Condition> _conditions = new ArrayList<>();

	

	@Override
	public void add(Condition aCondition) {
		_conditions.add(aCondition);
		
	}

}
