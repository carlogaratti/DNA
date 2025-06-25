package com.carlogaratti.dna.chain.starter;

import java.util.ArrayList;
import java.util.List;

import com.carlogaratti.dna.chain.Chain;
import com.carlogaratti.dna.chain.ConcreteChain;
import com.carlogaratti.dna.command.Command;
import com.carlogaratti.dna.command.CompositeCommand;
import com.carlogaratti.dna.command.DoNotingCommand;
import com.carlogaratti.dna.command.IFConditionalCommand;
import com.carlogaratti.dna.command.PrintCommand;
import com.carlogaratti.dna.command.SetCommand;
import com.carlogaratti.dna.command.SubstractionCommand;
import com.carlogaratti.dna.command.condition.CompositeAllTRUECondition;
import com.carlogaratti.dna.command.condition.CompositeCondition;
import com.carlogaratti.dna.command.condition.Condition;
import com.carlogaratti.dna.command.condition.StringEqualsCondition;
import com.carlogaratti.dna.command.condition.StringNotEqualsCondition;
import com.carlogaratti.dna.command.receiver.Receiver;
import com.carlogaratti.dna.command.receiver.ReceiverJavaConsolePrint;
import com.carlogaratti.dna.core.Bag;

public class PomoStarterWitChainForBoolean {
	public static void main(String[] args) {
		Bag bag = new Bag("PomoStarterWitChainForBoolean");
		bag.putKey("colon").asString(":");
		bag.putKey("newLine").asString("\n");
		bag.putKey("origSecValue").asString("5");
		bag.putKey("sec").asString("5");
		bag.putKey("origMinValue").asString("2");
		bag.putKey("min").asString("2");
		bag.putKey("minus").asString("1");
		Receiver consolePrint = new ReceiverJavaConsolePrint();
		consolePrint.on(System.out);
		PrintCommand showSec = new PrintCommand(bag);
		showSec.pullFrom("sec");
		showSec.receiver(consolePrint);
		PrintCommand showColon = new PrintCommand(bag);
		showColon.pullFrom("colon");
		showColon.receiver(consolePrint);
		PrintCommand showNewLine = new PrintCommand(bag);
		showNewLine.pullFrom("newLine");
		showNewLine.receiver(consolePrint);
		PrintCommand showMin = new PrintCommand(bag);
		showMin.pullFrom("min");
		showMin.receiver(consolePrint);
		SubstractionCommand minusForSec = new SubstractionCommand(bag);
		minusForSec.minuend("sec");
		minusForSec.substract("minus");
		minusForSec.pushResultOn("sec");
		SubstractionCommand minusForMin = new SubstractionCommand(bag);
		minusForMin.minuend("min");
		minusForMin.substract("minus");
		minusForMin.pushResultOn("min");
		
		// CHECK3: MINUTI E SECONDI A ZERO 
		bag.putKey("CHECK3").asString("SECONDI=0 AND MINUTI=0");
		IFConditionalCommand checkMinuteAndSecondAreZero = new IFConditionalCommand(bag);
		checkMinuteAndSecondAreZero.pullFrom("CHECK3");
		StringEqualsCondition checkMinuteAndSecondAreZeroCondition = new StringEqualsCondition();
		checkMinuteAndSecondAreZeroCondition.against("0");
		List<String> valuesUnderCheckForMinuteAndSecondAtZero = new ArrayList<String>();
		checkMinuteAndSecondAreZeroCondition.underCheck(valuesUnderCheckForMinuteAndSecondAtZero);
		checkMinuteAndSecondAreZero.condition(checkMinuteAndSecondAreZeroCondition);
		
		// CHECK2: SECONDI A ZERO AND MINUTI DIVERSO DA ZERO
		
		
		bag.putKey("CHECK2").asString("SECONDI=0 AND MINUTI!=0");
		IFConditionalCommand checkSecondISZeroAndMinutiDifferFromZero = new IFConditionalCommand(bag);
		checkSecondISZeroAndMinutiDifferFromZero.pullFrom("CHECK2");
		//CREO LE CONDIZIONI
		//PRIMA CONDIZIONE SECONDI = 0
		StringEqualsCondition checkSecondISZeroCondition = new StringEqualsCondition();
		checkSecondISZeroCondition.against("0");
		List<String> valuesUnderCheckForSecondAtZero = new ArrayList<String>();
		checkSecondISZeroCondition.underCheck(valuesUnderCheckForSecondAtZero);
		//SECONDA CONDIZIONE MINUTI != 0
		StringNotEqualsCondition checkMinutiDifferentFromZeroCondition = new StringNotEqualsCondition();
		checkMinutiDifferentFromZeroCondition.against("0");
		List<String> valuesUnderCheckForMinutesDifferentFromZero = new ArrayList<String>();
		checkMinutiDifferentFromZeroCondition.underCheck(valuesUnderCheckForMinutesDifferentFromZero);
		// CREO CONDIZIONE COMPOSTA DA PRIMA CONDIZIONE (SECONDI = 0) E SECONDA CONDIZIONE (MINUTI != 0)
		CompositeCondition secondIsZeroAndMinuteDifferentFromZero = new CompositeAllTRUECondition();
		secondIsZeroAndMinuteDifferentFromZero.add(checkSecondISZeroCondition);
		secondIsZeroAndMinuteDifferentFromZero.add(checkMinutiDifferentFromZeroCondition);
		//SETTO CONDIZIONE A CHECK 2
		checkSecondISZeroAndMinutiDifferFromZero.condition(secondIsZeroAndMinuteDifferentFromZero);
		
		
		
		
	
		
		// CHECK1: SECONDI DIVERSO DA ZERO
		bag.putKey("CHECK1").asString("SECONDI!=0");
		IFConditionalCommand checkSecondDifferentFromZero = new IFConditionalCommand(bag);
		checkSecondDifferentFromZero.pullFrom("CHECK1");
		StringNotEqualsCondition checkForSecondDifferentFromZero = new StringNotEqualsCondition();
		checkForSecondDifferentFromZero.against("0");
		List<String> valuesUnderCheckForSecondDifferentFromZero = new ArrayList<String>();
		checkForSecondDifferentFromZero.underCheck(valuesUnderCheckForSecondDifferentFromZero);
		checkSecondDifferentFromZero.condition(checkForSecondDifferentFromZero);
		
		
		Command resetSec = new SetCommand(bag);
		resetSec.pullFrom("origSecValue");
		resetSec.pushResultOn("sec");
		
		Command resetMin = new SetCommand(bag);
		resetMin.pullFrom("origMinValue");
		resetMin.pushResultOn("min");
		
		CompositeCommand compositeForSecondAndMinutesAreTrue = new CompositeCommand(bag);
		compositeForSecondAndMinutesAreTrue.add(resetSec);
		compositeForSecondAndMinutesAreTrue.add(resetMin);
		
		CompositeCommand compositeForSecondisZeroTrue =new CompositeCommand(bag);
		compositeForSecondisZeroTrue.add(resetSec);
		compositeForSecondisZeroTrue.add(minusForMin);
		
		checkSecondISZeroAndMinutiDifferFromZero.commandToExecute(compositeForSecondisZeroTrue);
		checkMinuteAndSecondAreZero.commandToExecute(compositeForSecondAndMinutesAreTrue);
		checkSecondDifferentFromZero.commandToExecute(minusForSec);
		
		while (true) {
				
			
			// CHECK1: SECONDI DIVERSO A ZERO
			valuesUnderCheckForSecondDifferentFromZero.removeAll(valuesUnderCheckForSecondDifferentFromZero);
			valuesUnderCheckForSecondDifferentFromZero.add(bag.get("sec").asString());
			checkForSecondDifferentFromZero.underCheck(valuesUnderCheckForSecondDifferentFromZero);
			checkSecondDifferentFromZero.execute(); //va a sovrascrivere "commandResult"
			// CHECK2: SECONDI A ZERO
			valuesUnderCheckForSecondAtZero.removeAll(valuesUnderCheckForSecondAtZero);
			valuesUnderCheckForSecondAtZero.add(bag.get("sec").asString());
			checkSecondISZeroCondition.underCheck(valuesUnderCheckForSecondAtZero);
			valuesUnderCheckForMinutesDifferentFromZero.removeAll(valuesUnderCheckForMinutesDifferentFromZero);
			valuesUnderCheckForMinutesDifferentFromZero.add(bag.get("min").asString());
			checkMinutiDifferentFromZeroCondition.underCheck(valuesUnderCheckForMinutesDifferentFromZero);
			checkSecondISZeroAndMinutiDifferFromZero.execute(); //va a sovrascrivere "commandResult"
			// CHECK3: MINUTI E SECONDI A ZERO 
			valuesUnderCheckForMinuteAndSecondAtZero.removeAll(valuesUnderCheckForMinuteAndSecondAtZero);
			valuesUnderCheckForMinuteAndSecondAtZero.add(bag.get("sec").asString());
			valuesUnderCheckForMinuteAndSecondAtZero.add(bag.get("min").asString());
			checkMinuteAndSecondAreZeroCondition.underCheck(valuesUnderCheckForMinuteAndSecondAtZero);
			checkMinuteAndSecondAreZero.execute();//va a sovrascrivere "commandResult"
			
			//  ESEGUO COMANDI STEP1
			List<Command> check1Command = bag.get("CHECK1").asList();
			for (Command each : check1Command) {
				System.out.println("comandi per check1: ");
				each.show();
				each.execute();
			}
			// CON CHAIN 
			
						Chain aChain = new ConcreteChain(bag);
						aChain.asTrue(compositeForSecondisZeroTrue);
						aChain.asFalse(new DoNotingCommand());
						Chain end = new EndChain(bag);
						aChain.next(end);
						aChain.handle(secondIsZeroAndMinuteDifferentFromZero);
						
						// FINE CON CHAIN	
			//  ESEGUO COMANDI STEP2
			List<Command> check2Command = bag.get("CHECK2").asList();
			for (Command each : check2Command) {
				System.out.println("comandi per check2: ");
				each.show();
				//each.execute();
				
			}
			
			//  ESEGUO COMANDI STEP3
			List<Command> check3Command = bag.get("CHECK3").asList();
			for (Command each : check3Command) {
				System.out.println("comandi per check3: ");
				each.show();
				each.execute();
			}
			//COMMAND FOR VIEW
			List<Command> commandForView = new ArrayList<Command>();
			commandForView.add(showMin);
			commandForView.add(showColon);
			commandForView.add(showSec);
			commandForView.add(showNewLine);
			for (Command each : commandForView) {
				each.execute();
			}
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
	}
}
