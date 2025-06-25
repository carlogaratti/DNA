package com.carlogaratti.dna.command.starter;

import java.util.Map;

import com.carlogaratti.dna.command.Command;
import com.carlogaratti.dna.command.get.GetInputCommand;
import com.carlogaratti.dna.core.Bag;

public class GetInputCommandStarter {
	
	public static void main(String[] args) {
		
		Bag bag = new Bag("aBag");
		Command getInputCommand = new GetInputCommand(bag);
		//getInputCommand.pushResultOn("result");
		getInputCommand.execute();
		getInputCommand.showBag();
	}

}
