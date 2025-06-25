package com.carlogaratti.progetti.semaforo;

import java.util.Map;
import java.util.Scanner;

import com.carlogaratti.dna.command.Command;
import com.carlogaratti.dna.command.SubstractionCommand;
import com.carlogaratti.dna.command.get.GetInputCommand;
import com.carlogaratti.dna.command.receiver.NoReceiver;
import com.carlogaratti.dna.command.receiver.Receiver;
import com.carlogaratti.dna.command.receiver.ReceiverJavaConsoleInput;
import com.carlogaratti.dna.core.Bag;
import com.carlogaratti.progetti.pomoapp.CommandBuilder;


public class Starter {
	public static void main(String[] args) {
		CommandBuilder commandBuilder = new CommandBuilder();
		Scanner scannerInput = new Scanner(System.in);
		Receiver inputReceiver = new ReceiverJavaConsoleInput();
		inputReceiver.on(scannerInput);
		commandBuilder.addGetCommand("primoIntervallo:minus", "", inputReceiver);
		Command get = commandBuilder.build();
		get.receiver(inputReceiver);
		get.pushResultOn("primoIntervallo");
		System.out.print("primo intervallo");
		get.execute();
		System.out.print("minus");
		get.execute();
		
		commandBuilder.addPrintCommand("", "primoIntervallo", new NoReceiver());
		Command show =commandBuilder.build();
		show.execute();
		commandBuilder.addSubstractionCommand("primoIntervallo", "primoIntervallo:minus");
		Command minus = commandBuilder.build();
		minus.execute();
		show.execute();
		minus.execute();
		show.execute();
		minus.execute();
		show.execute();
		minus.execute();
		show.execute();
		minus.execute();
		show.execute();
	}
}
