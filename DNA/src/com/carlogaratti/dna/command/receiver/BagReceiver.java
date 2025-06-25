package com.carlogaratti.dna.command.receiver;

import com.carlogaratti.dna.core.Bag;

public class BagReceiver extends Receiver {

	private Bag _aBag;
	private String _result;

	@Override
	public void on(Object aBag) {
		_aBag = (Bag) aBag;

	}

	@Override
	public String value() {
		// TODO Auto-generated method stub
		return _result;
	}

	@Override
	public void value(String key) {
		_result = _aBag.get(key).asString();
		value();
	}

}
