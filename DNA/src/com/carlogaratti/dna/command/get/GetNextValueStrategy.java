package com.carlogaratti.dna.command.get;

public abstract class GetNextValueStrategy {
	protected GetNextPushedValueCommand _getNextValue;
	
	public GetNextValueStrategy(GetNextPushedValueCommand aGetNextValue) {
		_getNextValue = aGetNextValue;
	}
	public abstract String run();

}
