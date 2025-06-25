package com.carlogaratti.dna.command.get;

import java.util.Map;


import com.carlogaratti.dna.command.Command;
import com.carlogaratti.dna.command.receiver.NoReceiver;
import com.carlogaratti.dna.command.receiver.Receiver;
import com.carlogaratti.dna.core.Bag;

public class GetInputCommand extends Command {
	
	Receiver _receiver = new NoReceiver();
	
	public GetInputCommand(Map<String, String> result ) {
		super(result);
	}

	public GetInputCommand(Bag bagContext) {
		super(bagContext);
	}
	
	public String value(String key) {
		return _bag.get(key).asString();
	}

	
	public void receiver(Receiver aReceiver) {
		_receiver = aReceiver;
		
	}
	
	@Override
	public void execute() {
		
		String value = _receiver.value();
		String currentKeyFromStack = _pushedKeys.get(0);
		_pushedKeys.remove(0);
		_bag.putKey(currentKeyFromStack).asString(value);
		
	}

	

	public String circoluarFromFirstIN() {
		//String key = _pushOnKeyStack.pop();
		String key = _pushOnKeyStack.remove(0);
		_pushOnKeyStack.push(key);
		return value(key);
	}
	
	public String circoluarFromLastIN() {
		String key = _pushOnKeyStack.pop();
		_pushOnKeyStack.insertElementAt(key, 0);
		
		return value(key);
	}

	public void reset() {
		_pushedKeys.removeAll(_pushOnKeyStack);
		_pushOnKey ="";
		_pushOnKeyStack.removeAll(_pushOnKeyStack);
		_pullFromKey.removeAll(_pullFromKey);
		//protected Bag _bag;
		_currentPullKey = "";
		
	}
}
