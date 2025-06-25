package com.carlogaratti.dna.command;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.carlogaratti.dna.command.receiver.Receiver;
import com.carlogaratti.dna.core.Bag;
import com.carlogaratti.project.bank_statement.Item;

public class MultiplyCommand extends Command {
	
	public MultiplyCommand(Map<String, String> aContext) {
		super(aContext);
	}
	
	public MultiplyCommand(Bag bagContext) {
		super(bagContext);
	}

	@Override
	public void execute() {
		BigDecimal result = new BigDecimal("1");
		for (String each : _pullFromKey) {
			
			result = result.multiply(new BigDecimal(_bag.get(each).asString()));
		}
		_bag.putKey(_pushOnKey).asString(result.toPlainString());
		_pullFromKey.removeAll(_pullFromKey);
	}

	
	

}
