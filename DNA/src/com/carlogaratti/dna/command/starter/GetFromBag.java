package com.carlogaratti.dna.command.starter;

import java.util.Map;

import com.carlogaratti.dna.command.Command;
import com.carlogaratti.dna.command.get.GetExactValueCommand;
import com.carlogaratti.dna.command.get.GetInputCommand;
import com.carlogaratti.dna.command.receiver.BagReceiver;
import com.carlogaratti.dna.command.receiver.Receiver;
import com.carlogaratti.dna.core.Bag;
import com.carlogaratti.project.bank_statement.GetCommand;

public class GetFromBag  {
	public static void main(String[] args) {
		Bag bag = new Bag("bag");
		bag.putKey("qta").asNumber("12");
		bag.putKey("description").asString("banane ciquita");
		bag.putKey("unitPrice").asNumber("12");
		bag.putKey("currency").asString("EUR");
		bag.print();
		
		Bag resultBag = new Bag("resultBag");
		GetInputCommand getFromBagCommand = new GetInputCommand(resultBag);
		
		Receiver bagReceiver = new BagReceiver();
		bagReceiver.on(bag);
		getFromBagCommand.receiver(bagReceiver);
		
		bagReceiver.value("description");
		getFromBagCommand.pushResultOn("description");
		getFromBagCommand.execute();
		
		bagReceiver.value("currency");
		getFromBagCommand.pushResultOn("currency");
		getFromBagCommand.execute();
		
		resultBag.print();
		
		GetExactValueCommand command = new GetExactValueCommand(resultBag);
		
		command.on(getFromBagCommand);
		
		command.pullFrom("description");
		command.execute();	
		String resultFromExactValue1 = command.value();
		System.out.println(resultFromExactValue1);
		
		command.pullFrom("currency");
		command.execute();	
		String resultFromExactValue2 = command.value();
		System.out.println(resultFromExactValue2);
	}

}
