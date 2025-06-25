package com.carlogaratti.dna.command.starter;

import com.carlogaratti.dna.command.Command;
import com.carlogaratti.dna.command.NextCommand;
import com.carlogaratti.dna.command.NextTrueFalseCommand;
import com.carlogaratti.dna.command.SetCommand;
import com.carlogaratti.dna.command.SumCommand;
import com.carlogaratti.dna.command.builder.CommandBuilder;
import com.carlogaratti.dna.command.condition.Condition;
import com.carlogaratti.dna.command.condition.MathGreaterCondition;
import com.carlogaratti.dna.command.get.GetExactValueCommand;
import com.carlogaratti.dna.core.Bag;

public class ExpressionStarter  {
	public static void main(String[] args) {
		Bag bag = new Bag("bag");
		bag.putKey("TRUE").asString("TRUE");
		bag.putKey("FALSE").asString("FALSE");
		CommandBuilder commandBuilder = new CommandBuilder();
		commandBuilder.addGetInputConsole(bag);
		Command getInput = commandBuilder.build();
		getInput.pushResultOn("first");
		System.out.println("first");
		getInput.execute();
		getInput.pushResultOn("second");
		System.out.println("second");
		getInput.execute();
		SumCommand sum = new SumCommand(bag);
		sum.pullFrom("first");
		sum.pullFrom("second");
		sum.pushResultOn("result");
		sum.execute();
		sum.showBag();
		GetExactValueCommand exactValue = new GetExactValueCommand(bag);
		exactValue.on(sum);
		exactValue.pullFrom("result");
		String valueUnderCheck = exactValue.value();
		NextTrueFalseCommand next = new NextTrueFalseCommand(bag);
		SetCommand commandForTrue = new SetCommand(bag);
		commandForTrue.pullFrom("TRUE");
		commandForTrue.pushResultOn("result");
		SetCommand commandForFalse = new SetCommand(bag);
		commandForFalse.pullFrom("FALSE");
		commandForFalse.pushResultOn("result");
		next.next(commandForTrue);
		next.forFalse(commandForFalse);
		MathGreaterCondition condition = new MathGreaterCondition();
		condition.underCheck(valueUnderCheck);
		condition.against(valueUnderCheck);
		condition.evaluate();
		next.condition(condition);
		
	}
}
