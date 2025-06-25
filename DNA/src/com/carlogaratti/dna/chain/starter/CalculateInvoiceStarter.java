package com.carlogaratti.dna.chain.starter;

import java.util.List;

import com.carlogaratti.dna.chain.Chain;
import com.carlogaratti.dna.chain.OnlyIFCommand;
import com.carlogaratti.dna.command.Command;
import com.carlogaratti.dna.command.MultiplyCommand;
import com.carlogaratti.dna.command.condition.Condition;
import com.carlogaratti.dna.command.condition.StringEqualsCondition;
import com.carlogaratti.dna.core.Bag;

public class CalculateInvoiceStarter {
	
	public static void main(String[] args) {
		Bag bag = new Bag("aBag");
		bag.putKey("CURRENT_CUSTOMER").asString("PEVIANI");
		bag.putKey("MARCH_BILLABLE_DAYS").asString("143");
		bag.putKey("DAILY_RATE_ESSELUNGA").asString("440");
		bag.putKey("DAILY_RATE_PEVIANI").asString("390");
		
		Command multiplyCommandForTrue = new MultiplyCommand(bag);
		multiplyCommandForTrue.pullFrom("MARCH_BILLABLE_DAYS");
		multiplyCommandForTrue.pullFrom("DAILY_RATE_PEVIANI");
		multiplyCommandForTrue.pushResultOn("totalInvoice");
		
		Command multiplyCommandForFalse = new MultiplyCommand(bag);
		multiplyCommandForFalse.pullFrom("MARCH_BILLABLE_DAYS");
		multiplyCommandForFalse.pullFrom("DAILY_RATE_ESSELUNGA");
		multiplyCommandForFalse.pushResultOn("totalInvoice");
		
		Chain chainForBoolean = new OnlyIFCommand(bag);
		chainForBoolean.command(multiplyCommandForTrue);
		chainForBoolean.commandForFalse(multiplyCommandForFalse);
	
		Condition stringEqualsCondition = new StringEqualsCondition();
		stringEqualsCondition.underCheck(bag.get("CURRENT_CUSTOMER").asString());
		stringEqualsCondition.against("ESSELUNGA");
		
		chainForBoolean.handle(stringEqualsCondition);
		
		List<Command> commans = (List<Command>) bag.get("commandResult").asList();
		for (Command each : commans) {
			each.execute();
		}
		bag.print();
	}

}
