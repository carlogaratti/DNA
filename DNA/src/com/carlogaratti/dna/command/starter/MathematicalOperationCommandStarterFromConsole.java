package com.carlogaratti.dna.command.starter;

import java.util.Map;
import java.util.Scanner;

import com.carlogaratti.dna.command.Command;
import com.carlogaratti.dna.command.SumCommand;
import com.carlogaratti.dna.command.builder.CommandBuilder;
import com.carlogaratti.dna.command.get.GetExactValueCommand;
import com.carlogaratti.dna.command.get.GetInputCommand;
import com.carlogaratti.dna.command.get.GetNextPushedValueCommand;
import com.carlogaratti.dna.command.get.GetNextValueFIFOStrategy;
import com.carlogaratti.dna.command.get.GetNextValueLIFOStrategy;
import com.carlogaratti.dna.command.get.GetNextValueStrategy;
import com.carlogaratti.dna.command.receiver.Receiver;
import com.carlogaratti.dna.command.receiver.ReceiverJavaConsoleInput;
import com.carlogaratti.dna.core.Bag;

public class MathematicalOperationCommandStarterFromConsole  {
	public static void main(String[] args) {
		
		/*
		 * CASO PASSATO NEL BAG
		Bag bag = new Bag("bag");
		bag.putKey("one").asString("1");
		bag.putKey("two").asString("2");
		bag.putKey("four").asString("4");
		bag.putKey("six").asString("6");
		*/
		
		/*
		 * CASO PRESO DA INPUT
		 * */
		Bag bag = new Bag("bag");
		bag.putKey("minus").asString("1");
		CommandBuilder commandBuilder = new CommandBuilder();
		commandBuilder.addGetInputConsole(bag);
		Command get = commandBuilder.build();
		System.out.println("one");
		get.pushResultOn("one");
		get.execute();
		System.out.println("two");
		get.pushResultOn("two");
		get.execute();
		System.out.println("four");
		get.pushResultOn("four");
		get.execute();
		System.out.println("one");
		get.pushResultOn("six");
		get.execute();
		
		Command command = new SumCommand(bag);
		command.pullFrom("one");
		command.pullFrom("two");
		command.pushResultOn("result1");
		command.execute();
		
		command.pullFrom("four");
		command.pullFrom("six");
		command.pushResultOn("result2");
		command.execute();
		
		
		
		GetExactValueCommand gex = new GetExactValueCommand(bag);
		gex.on(command);
		gex.pullFrom("result1");
		gex.execute();
		bag.print();
		System.out.println("gex: " + gex.value());;
		
		
		gex.pullFrom("result2");
		gex.execute();
		bag.print();
		System.out.println("gex: " + gex.value());;
		
		
		GetNextPushedValueCommand gexNext = new GetNextPushedValueCommand(bag);
		GetNextValueStrategy lifo = new GetNextValueFIFOStrategy(gexNext);
		gexNext.orderStrategy(lifo);
		gexNext.on((GetInputCommand)get);
		gexNext.pushResultOn("result");
		gexNext.execute();
		System.out.println("gexNext: " + gexNext.value("result"));;
		gexNext.execute();
		System.out.println("gexNext " + gexNext.value("result"));;
		gexNext.execute();
		System.out.println("gexNext " + gexNext.value("result"));;
		gexNext.execute();
		System.out.println("gexNext " + gexNext.value("result"));;
		gexNext.execute();
		System.out.println("gexNext " + gexNext.value("result"));;
		
		//System.out.println("gex: " + gex.value());;
		
		
	}
}
