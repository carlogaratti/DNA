package com.carlogaratti.dna.command.get;

import com.carlogaratti.dna.command.Command;
import com.carlogaratti.dna.command.receiver.Receiver;
import com.carlogaratti.dna.core.Bag;

public class GetNextPushedValueCommand extends Command {

	private String _result;
	private GetNextValueStrategy _nextValueStrategy;
	private GetInputCommand _on;

	public GetNextPushedValueCommand(Bag bagContext) {
		super(bagContext);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		String value =_nextValueStrategy.run();
		System.out.println("");
		_bag.putKey(_result).asString(value);
		
	}
	
	public  void pushResultOn(String result) {
		_result = result;
	}
	
	
	
	public String circularAsFIFO() {
		String result = _on.circoluarFromFirstIN();
		return result;
	}
	
	public String circularAsLIFO() {
		String result = _on.circoluarFromLastIN();
		return result;
	}
	
	public String value(String key) {
		return _bag.get(key).asString();
	}

	

	

	public void orderStrategy(GetNextValueStrategy aNextValueStrategy) {
		_nextValueStrategy = aNextValueStrategy;
		
	}

	public void on(GetInputCommand command) {
		_on = command;
		
	}

	

}
