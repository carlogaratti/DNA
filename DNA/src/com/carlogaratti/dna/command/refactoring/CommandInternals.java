package com.carlogaratti.dna.command.refactoring;

import com.carlogaratti.dna.command.Command;
import com.carlogaratti.dna.command.get.GetInputCommand;
import com.carlogaratti.dna.core.Bag;

public class CommandInternals {
	
	public static void main(String[] args) {
		Bag bag = new Bag("refactoring");
		Command command = new GetInputCommand(bag);
		command.pushResultOn("result1");
		command.pushResultOn("result2");
		command.execute();
		command.showPushAndPull();
		command.execute();
		command.showPushAndPull();
	}

}
