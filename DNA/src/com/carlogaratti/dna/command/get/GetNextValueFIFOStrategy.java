package com.carlogaratti.dna.command.get;

public class GetNextValueFIFOStrategy extends GetNextValueStrategy{
	

	public GetNextValueFIFOStrategy(GetNextPushedValueCommand aGetNextValue) {
		super(aGetNextValue);
	}

	@Override
	public String run() {
		return _getNextValue.circularAsFIFO();
	}
}
