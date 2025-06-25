package com.carlogaratti.dna.command;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;

import com.carlogaratti.dna.command.receiver.Receiver;
import com.carlogaratti.dna.core.Bag;

public class SubstractionCommand extends Command {

	private String _minuendKey;
	private String _substract;

	public SubstractionCommand(Map<String, String> result) {
		super(result);
		// TODO Auto-generated constructor stub
	}
	

	public SubstractionCommand(Bag bagContext) {
		super(bagContext);
	}

	@Override
	public void execute() {
		if (_minuendKey == null)  _minuendKey = _pullFromKey.get(0);
		if (_substract == null)  _substract = _pullFromKey.get(1);
		String minuendAsStringValue = _bag.get(_minuendKey).asString();
		String substractStringValue = _bag.get(_substract).asString();
		BigDecimal minunendo = new BigDecimal(minuendAsStringValue);
		BigDecimal sottraendo = new BigDecimal(substractStringValue);
		BigDecimal result = minunendo.subtract(sottraendo);
		_bag.putKey(_pushOnKey).asString(result.toPlainString());
		

	}


	public  void minuend(String aKey) {
		_minuendKey = aKey;

	}
	
	public void substract(String aKey) {
		_substract = aKey;

	}


	
}
