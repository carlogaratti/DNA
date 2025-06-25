package com.carlogaratti.dna.command;

import java.math.BigDecimal;

import com.carlogaratti.dna.command.receiver.Receiver;
import com.carlogaratti.dna.core.Bag;

public class IFThenNewElseDefault extends Command {

	private String _aCheckValue;

	public IFThenNewElseDefault(Bag bag) {
		super(bag);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void execute() {
		BigDecimal actualValue = new BigDecimal(_bag.get(_pullFromKey.get(0)).asString());
		BigDecimal newValue = new BigDecimal(_bag.get(_pullFromKey.get(1)).asString());
		BigDecimal aDefualt = new BigDecimal(_bag.get(_pullFromKey.get(2)).asString());
		if (actualValue.equals(new BigDecimal(_aCheckValue))) _bag.putKey(_pushOnKey).asString(newValue.toPlainString());
		else _bag.putKey(_pushOnKey).asString(aDefualt.toPlainString());//altrimenti metto un default
	}

	

	public void checkValue(String aCheckValue) {
		_aCheckValue = aCheckValue;
		
	}

}
