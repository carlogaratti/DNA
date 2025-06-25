package com.carlogaratti.dna.command.condition;
import java.math.BigDecimal;

public class MathGreaterCondition extends Condition{

	private String _valueUnderCheck;
	private String _valueAgainst;

	public void underCheck(String aValueUnderCheck) {
		_valueUnderCheck = aValueUnderCheck;
		
	}

	public void against(String aValueAgainst) {
		_valueAgainst = aValueAgainst;
		
	}

	public String evaluate() {
		BigDecimal _valueUnderCheckAsBigDecimal = new BigDecimal(_valueUnderCheck);
		BigDecimal __valueAgainstAsBigDecimal = new BigDecimal(_valueAgainst);
		if (_valueUnderCheckAsBigDecimal.compareTo(__valueAgainstAsBigDecimal) < 0) return "FALSE";
		return "TRUE";
		
	}

	@Override
	protected void add(Condition secEqualsZero) {
		// TODO Auto-generated method stub
		
	}

}
