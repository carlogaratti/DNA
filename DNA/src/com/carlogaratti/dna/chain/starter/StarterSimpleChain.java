package com.carlogaratti.dna.chain.starter;

import com.carlogaratti.dna.chain.Chain;

import com.carlogaratti.dna.chain.ConcreteChain;
import com.carlogaratti.dna.command.Command;
import com.carlogaratti.dna.command.CompositeCommand;
import com.carlogaratti.dna.command.LogCommand;
import com.carlogaratti.dna.command.PrintCommand;
import com.carlogaratti.dna.command.SetCommand;
import com.carlogaratti.dna.command.condition.Condition;
import com.carlogaratti.dna.command.condition.MathGreaterCondition;
import com.carlogaratti.dna.command.condition.StringEqualsCondition;
import com.carlogaratti.dna.command.receiver.Receiver;
import com.carlogaratti.dna.command.receiver.ReceiverJavaConsolePrint;
import com.carlogaratti.dna.core.Bag;

public class StarterSimpleChain {
	public static void main(String[] args) {
		Bag bag = new Bag("aBag");
		bag.putKey("resultForTrue").asString("TRUE");
		bag.putKey("resultForFalse").asString("False");
		bag.putKey("nome").asString("CARLO");
		bag.putKey("against").asString("CARLA");
		
		Condition acondition = new StringEqualsCondition(bag);
		acondition.underCheck("nome");
		acondition.against("against");
		Receiver console = new ReceiverJavaConsolePrint();
		console.on(System.out);
		CompositeCommand trueCommand = new CompositeCommand(bag);
		PrintCommand printCommandForTrue = new PrintCommand(bag);
		printCommandForTrue.receiver(console);
		printCommandForTrue.pullFrom("resultForTrue");
		trueCommand.add(printCommandForTrue);
		PrintCommand falseCommand = new PrintCommand(bag);
		falseCommand.receiver(console);
		falseCommand.pullFrom("resultForFalse");
		Chain end = new EndChain(bag);
		Chain first = new ConcreteChain(bag);
		first.asTrue(trueCommand);
		first.asFalse(falseCommand);
		first.next(end);
		first.handle(acondition);
		//bag.print();
	}
}
