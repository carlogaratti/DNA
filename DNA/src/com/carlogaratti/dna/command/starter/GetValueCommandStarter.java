package com.carlogaratti.dna.command.starter;

import java.util.Scanner;

import com.carlogaratti.dna.command.Command;
import com.carlogaratti.dna.command.builder.CommandBuilder;
import com.carlogaratti.dna.command.get.GetExactValueCommand;
import com.carlogaratti.dna.command.get.GetInputCommand;
import com.carlogaratti.dna.command.receiver.Receiver;
import com.carlogaratti.dna.command.receiver.ReceiverJavaConsoleInput;
import com.carlogaratti.dna.core.Bag;

public class GetValueCommandStarter {
	
	public static void main(String[] args) {
		Bag bag = new Bag("bag");
		CommandBuilder commandBuilder = new CommandBuilder();
		commandBuilder.addGetInputConsole(bag);
		Command getInput = commandBuilder.build();
		
		getInput.pushResultOn("name");
		System.out.print("name");
		getInput.execute();
		
		getInput.pushResultOn("age");
		System.out.print("age");
		getInput.execute();
		
		
		GetExactValueCommand exactValueCommand = new GetExactValueCommand(bag);
		exactValueCommand.on(getInput);
		
		exactValueCommand.pullFrom("age");
		exactValueCommand.execute();
		System.out.println(exactValueCommand.value());
		
		exactValueCommand.pullFrom("name");
		exactValueCommand.execute();
		System.out.println(exactValueCommand.value());
	}

}
