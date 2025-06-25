package com.carlogaratti.dna.core;

import java.util.Scanner;

import com.carlogaratti.dna.command.Command;
import com.carlogaratti.dna.command.SetCommand;
import com.carlogaratti.dna.command.receiver.Receiver;
import com.carlogaratti.dna.command.receiver.ReceiverJavaConsoleInput;
import com.carlogaratti.progetti.pomoapp.CommandBuilder;

public class StarterForCommandWithBag{
	public static void main(String[] args) {
		
		
		CommandBuilder commandBuilder = new CommandBuilder();
		Receiver getReceiver = new ReceiverJavaConsoleInput();
		Scanner scanner = new Scanner(System.in);
		getReceiver.on(scanner);
		commandBuilder.addGetCommand("input1:input2:altroinput", "", getReceiver);
		Command get = commandBuilder.build();
		get.receiver(getReceiver);
		
		System.out.print("mettimi un input:");
		get.execute();
		
		System.out.print("mettimi un input:");
		get.execute();
		
		System.out.print("mettimi un input:");
		get.execute();
		
		get.showBag();
		
		Command set = new SetCommand(get.bag());
		set.pullFrom("input1");
		set.pushResultOn("altroinput");
		set.execute();
		
		get.showBag();
		
		
		commandBuilder.addSumCommand("altroinput", "input1:input2");
		Command sum = commandBuilder.build();
		sum.execute();
		sum.showBag();
		
		commandBuilder.addSubstractionCommand("altroinput", "input1:input2");
		Command minus = commandBuilder.build();
		minus.execute();
		minus.showBag();
		
		
		
		
	}
}
