package com.carlogaratti.dna.command.condition;

import java.util.Arrays;
import java.util.Iterator;

import com.carlogaratti.dna.core.Bag;

public class StringEqualsCondition extends Condition{

	

	@Override
	public String evaluate() {
		String result = "FALSE";
		
		if (_valuesUnderCheck.stream().allMatch(el -> el.endsWith(_valueAgainst))) result = "TRUE" ;
		else result =  "FALSE";
		//_valuesUnderCheck.removeAll(_valuesUnderCheck);
		return result;
	}

	@Override
	protected void add(Condition secEqualsZero) {
		// TODO Auto-generated method stub
		
	}

	

}
