package com.carlogaratti.dna.command.get;

public class GetNextValueLIFOStrategy extends GetNextValueStrategy {


	public GetNextValueLIFOStrategy(GetNextPushedValueCommand aGetNextValue) {
		super (aGetNextValue);
	}

	@Override
	public String run() {
		return _getNextValue.circularAsLIFO();
	}
	
	

}
