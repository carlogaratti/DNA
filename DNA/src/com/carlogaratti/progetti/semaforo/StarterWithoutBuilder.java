package com.carlogaratti.progetti.semaforo;

import java.io.PrintStream;
import java.util.Scanner;

import com.carlogaratti.dna.command.Command;
import com.carlogaratti.dna.command.CompositeCommand;
import com.carlogaratti.dna.command.DoNotingCommand;
import com.carlogaratti.dna.command.BooleanInAndBehaviorSelectorCommand;
import com.carlogaratti.dna.command.IFThenNewElseDefault;
import com.carlogaratti.dna.command.PrintCommand;
import com.carlogaratti.dna.command.SetCommand;
import com.carlogaratti.dna.command.SubstractionCommand;
import com.carlogaratti.dna.command.WaitCommand;
import com.carlogaratti.dna.command.builder.CommandBuilder;
import com.carlogaratti.dna.command.get.GetInputCommand;
import com.carlogaratti.dna.command.get.GetNextPushedValueCommand;
import com.carlogaratti.dna.command.get.GetNextValueLIFOStrategy;
import com.carlogaratti.dna.command.get.GetNextValueStrategy;
import com.carlogaratti.dna.command.receiver.NoReceiver;
import com.carlogaratti.dna.command.receiver.Receiver;
import com.carlogaratti.dna.command.receiver.ReceiverJavaConsoleInput;
import com.carlogaratti.dna.command.receiver.ReceiverJavaConsolePrint;
import com.carlogaratti.dna.core.Bag;


public class StarterWithoutBuilder  {
	public static void main(String[] args) {
		Bag bag = new Bag("bag");
		bag.putKey("minus").asString("1");
		
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
		
		Bag bagForFirstIntervalReset = new Bag("bag");
		commandBuilder.addGetInputConsole(bagForFirstIntervalReset);
		Command getForFirstIntervalReset = commandBuilder.build();
		getForFirstIntervalReset.pushResultOn("firstIntervalReset");
		System.out.print("firstIntervalReset");
		getForFirstIntervalReset.execute();
		getForFirstIntervalReset.showBag();
		
		Bag bagForSecondIntervalReset = new Bag("bag");
		commandBuilder.addGetInputConsole(bagForFirstIntervalReset);
		Command getForSecondIntervalReset = commandBuilder.build();
		getForSecondIntervalReset.pushResultOn("secondIntervalReset");
		System.out.print("secondIntervalReset");
		getForSecondIntervalReset.execute();
		
		String primoIntervallo = getForFirstIntervalReset.bag().get("firstIntervalReset").asString();
		bag.putKey("primoIntervallo").asString(primoIntervallo);
		
		String secondoIntervallo = getForSecondIntervalReset.bag().get("secondIntervalReset").asString();
		bag.putKey("secondoIntervallo").asString(secondoIntervallo);
		
		GetNextPushedValueCommand getJumpValuePrimoIntervallo = new GetNextPushedValueCommand(bag);
		GetNextValueStrategy nextValueLIFOStrategySecond = new GetNextValueLIFOStrategy(getJumpValuePrimoIntervallo);
		getJumpValuePrimoIntervallo.orderStrategy(nextValueLIFOStrategySecond);
		getJumpValuePrimoIntervallo.on((GetInputCommand)getForFirstIntervalReset);
		getJumpValuePrimoIntervallo.pushResultOn("resetPrimoIntervallo");
		
		GetNextPushedValueCommand getJumpValueSecondoIntervallo = new GetNextPushedValueCommand(bag);
		GetNextValueStrategy nextValueLIFOStrategyMinutes = new GetNextValueLIFOStrategy(getJumpValueSecondoIntervallo);
		getJumpValueSecondoIntervallo.orderStrategy(nextValueLIFOStrategyMinutes);
		getJumpValueSecondoIntervallo.on((GetInputCommand)getForSecondIntervalReset);
		getJumpValueSecondoIntervallo.pushResultOn("resetSecondoIntervallo");
		
		Command showSec = new  PrintCommand(bag);
		Receiver consoleReceiver = new ReceiverJavaConsolePrint();
		PrintStream printStream = new   PrintStream(System.out);
		consoleReceiver.on(printStream);
		((PrintCommand)showSec).receiver(consoleReceiver);
		showSec.pullFrom("primoIntervallo");
		
		Command showMin = new  PrintCommand(bag);
		((PrintCommand)showMin).receiver(consoleReceiver);
		showMin.pullFrom("secondoIntervallo");
		
		SubstractionCommand minusForSecond = new SubstractionCommand(bag);
		minusForSecond.minuend("primoIntervallo");
		minusForSecond.substract("minus");
		minusForSecond.pushResultOn("primoIntervallo");
		
		SubstractionCommand minusForMinutes = new SubstractionCommand(bag);
		minusForMinutes.minuend("secondoIntervallo");
		minusForMinutes.substract("minus");
		minusForMinutes.pushResultOn("secondoIntervallo");
		
		bag.putKey("waitSec").asString("1");
		Command wait = new WaitCommand(bag);
		wait.pullFrom("waitSec");
		//wait.receiver(new NoReceiver());
		
		BooleanInAndBehaviorSelectorCommand zeroSecondJunction = new BooleanInAndBehaviorSelectorCommand(bag);
		zeroSecondJunction.checkValue("0");
		zeroSecondJunction.pullFrom("primoIntervallo");
		zeroSecondJunction.pullFrom("primoIntervallo");
		
		Command resetForPrimoIntervallo = new SetCommand(bag);
		//resetForPrimoIntervallo.receiver(new NoReceiver());
		resetForPrimoIntervallo.pullFrom("resetPrimoIntervallo");
		resetForPrimoIntervallo.pushResultOn("primoIntervallo");
		
		CompositeCommand commandForTrue = new CompositeCommand(bag);
		commandForTrue.add(getJumpValuePrimoIntervallo);
		commandForTrue.add(resetForPrimoIntervallo);
		commandForTrue.add(wait);
		commandForTrue.add(minusForMinutes);
		zeroSecondJunction.addForTrue(commandForTrue);
		
		Command commandForFalse = new SetCommand(bag);
		commandForFalse.pullFrom("resetSecond");
		commandForFalse.pushResultOn("reset");
		zeroSecondJunction.addForFalse(new DoNotingCommand(bag));
		
		BooleanInAndBehaviorSelectorCommand zeroSecondAndMinuteJunction = new BooleanInAndBehaviorSelectorCommand(bag);
		zeroSecondAndMinuteJunction.checkValue("0");
		zeroSecondAndMinuteJunction.pullFrom("primoIntervallo");
		zeroSecondAndMinuteJunction.pullFrom("secondoIntervallo");
		zeroSecondAndMinuteJunction.addForFalse(new DoNotingCommand(bag));
		
		Command resetForSecondoIntervallo = new SetCommand(bag);
		//resetForSecondoIntervallo.receiver(new NoReceiver());
		resetForSecondoIntervallo.pullFrom("resetSecondoIntervallo");
		resetForSecondoIntervallo.pushResultOn("secondoIntervallo");
		
		CompositeCommand commandTrueForResetForSecondoIntervallo = new CompositeCommand(bag);
		commandTrueForResetForSecondoIntervallo.add(getJumpValueSecondoIntervallo);
		commandTrueForResetForSecondoIntervallo.add(resetForSecondoIntervallo);
		zeroSecondAndMinuteJunction.addForTrue(commandTrueForResetForSecondoIntervallo);
		
		showMin.execute();System.out.print(":");showSec.execute();
		System.out.println("");
		commandForFalse.showBag();
		while(true) {
			minusForSecond.execute();
			showMin.execute();System.out.print(":");showSec.execute();
			System.out.println("");
			zeroSecondAndMinuteJunction.execute();
			zeroSecondJunction.execute();
			wait.execute();
		}
	}
}
