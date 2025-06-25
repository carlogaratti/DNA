package com.carlogaratti.dna.command.condition;

import java.util.Arrays;

public class StringNotEqualsCondition extends Condition {

	@Override
	public String evaluate() {
		String result = "FALSE";
	
		if (_valuesUnderCheck.stream().allMatch(el -> el.endsWith(_valueAgainst))) result = "FALSE" ;
		else result =  "TRUE";
		//_valuesUnderCheck.removeAll(_valuesUnderCheck);
		return result;
	}

	@Override
	protected void add(Condition secEqualsZero) {
		// TODO Auto-generated method stub
		
	}

}
