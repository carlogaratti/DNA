package com.carlogaratti.dna.command;

import java.math.BigDecimal;
import java.util.Map;

import com.carlogaratti.dna.command.receiver.Receiver;
import com.carlogaratti.dna.core.Bag;

public class SumCommand extends Command {

	public SumCommand(Map<String, String> result) {
		super(result);
		// TODO Auto-generated constructor stub
	}
	
	public SumCommand(Bag bagContext) {
		super(bagContext);
	}

	@Override
	public void execute() {
		BigDecimal result = new BigDecimal("0");
		for (String each : _pullFromKey) {
			result = result.add(new BigDecimal(_bag.get(each).asString()));
		}
		_bag.putKey(_pushOnKey).asString(result.toPlainString());
		_pullFromKey.removeAll(_pullFromKey);
	}

}
