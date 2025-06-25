package com.carlogaratti.dna.command.starter;

import java.util.Scanner;

import com.carlogaratti.dna.command.Command;
import com.carlogaratti.dna.command.builder.CommandBuilder;
import com.carlogaratti.dna.command.get.GetInputCommand;
import com.carlogaratti.dna.command.get.GetNextPushedValueCommand;
import com.carlogaratti.dna.command.get.GetNextValueLIFOStrategy;
import com.carlogaratti.dna.command.get.GetNextValueStrategy;
import com.carlogaratti.dna.command.receiver.Receiver;
import com.carlogaratti.dna.command.receiver.ReceiverJavaConsoleInput;
import com.carlogaratti.dna.core.Bag;

public class GetCommandStarter  {
	public static void main(String[] args) {
		
		Bag bag = new Bag("bag");
		/*
		Scanner scannerInput = new Scanner(System.in);
		Receiver inputReceiver = new ReceiverJavaConsoleInput();
		inputReceiver.on(scannerInput);
		GetInputCommand getInput = new GetInputCommand(bag);
		getInput.receiver(inputReceiver);
		*/
		CommandBuilder commandBuilder = new CommandBuilder();
		commandBuilder.addGetInputConsole(bag);
		Command getInput = commandBuilder.build();
		getInput.pushResultOn("first");
		System.out.print("primo");
		getInput.execute();
		getInput.showBag();
		//get.resetKeys();
		getInput.pushResultOn("second");
		System.out.print("secondo");
		getInput.execute();
		getInput.showBag();
		getInput.pushResultOn("terzo");
		System.out.print("terzo");
		getInput.execute();
		getInput.showBag();
		
		
		getInput.showBag();
		
		
		GetNextPushedValueCommand getValue = new GetNextPushedValueCommand(bag);
		getValue.on((GetInputCommand)getInput);
		GetNextValueStrategy aStrategy = new GetNextValueLIFOStrategy(getValue);
		getValue.orderStrategy(aStrategy);
		getValue.next(getInput);
		getValue.pushResultOn("result");
		
		System.out.println("---");
		
		getValue.execute();
		System.out.println(getValue.value("result"));
		
		getValue.execute();
		System.out.println(getValue.value("result"));
		
		getValue.execute();
		System.out.println(getValue.value("result"));
		
		getValue.execute();
		System.out.println(getValue.value("result"));
		
		
	/*
		
		System.out.println(get.circoluarFromFirstIN());
		System.out.println(get.circoluarFromFirstIN());
		System.out.println(get.circoluarFromFirstIN());
		
		System.out.println("---");
		
		System.out.println(get.circoluarFromLastIN());
		System.out.println(get.circoluarFromLastIN());
		System.out.println(get.circoluarFromLastIN());
		System.out.println(get.circoluarFromLastIN());
		*/
	}

}
