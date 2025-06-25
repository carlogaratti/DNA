package com.carlogaratti.progetti.pomoapp;

import java.util.Scanner;

import com.carlogaratti.dna.command.Command;
import com.carlogaratti.dna.command.DoNotingCommand;
import com.carlogaratti.dna.command.BooleanInAndBehaviorSelectorCommand;
import com.carlogaratti.dna.command.IFThenNewElseOldSetCommand;
import com.carlogaratti.dna.command.PlaySoundCommand;
import com.carlogaratti.dna.command.PrintCommand;
import com.carlogaratti.dna.command.StopCommand;
import com.carlogaratti.dna.command.StopPlaySoundCommand;
import com.carlogaratti.dna.command.get.GetInputCommand;
import com.carlogaratti.dna.command.receiver.JAvaClip;
import com.carlogaratti.dna.command.receiver.NoReceiver;
import com.carlogaratti.dna.command.receiver.Receiver;
import com.carlogaratti.dna.command.receiver.ReceiverJavaConsoleInput;
import com.carlogaratti.dna.core.Bag;
import com.carlogaratti.spike.swing.CommandAbstractFactory;
import com.carlogaratti.spike.swing.CommandConcreteFactory;

public class CommandBuilder {
	
	Command _result;
	
	CommandAbstractFactory commandFactory = new CommandConcreteFactory();
	
	public void addWaitCommand(String keyPush, String keyPull) {
		Command comando = commandFactory.createWaitCommand(keyPush, keyPull);
		//comando.receiver(new NoReceiver());
		_result = comando;
		
	}

	public Command build() {
		return _result;
		
	}

	public void addResetCommand(String keyPush, String keyPull) {
		Command comando = commandFactory.createSubstractionCommand(keyPush, keyPull);
	//	comando.receiver(new NoReceiver());
		_result = comando;
	}

	public void addSubstractionCommand(String keyPush, String keyPull) {
		Command comando =  commandFactory.createSubstractionCommand(keyPush, keyPull);
		//comando.receiver(new NoReceiver());
		_result = comando;
		
	}
	
	public void addSumCommand(String keyPush, String keyPull) {
		Command comando =  commandFactory.createSumCommand(keyPush, keyPull);
		//comando.receiver(new NoReceiver());
		_result = comando;
		
	}

	public void addwhenSecondIsZeroCommand(String keyPush, String keyPull, String checkValue, Command nextCommand) {
		Command comando = commandFactory.createIFSetCommand(keyPush, keyPull); //secondiInitialValue
		((IFThenNewElseOldSetCommand)comando).checkValue(checkValue);
		//comando.receiver(new NoReceiver());
		comando.next(nextCommand);
		_result = comando;
		
	}

	public void addPrintCommand(String keyPush, String keyPull, Receiver aReceiver) {
		PrintCommand comando = (PrintCommand) commandFactory.createPrintCommand(keyPush, keyPull);
		comando.receiver(aReceiver);
		_result = comando;
		
	}

	public void addPlaySoundStartCommand(String keyPush, String keyPull, Receiver aReceiver) {
		PlaySoundCommand comando = (PlaySoundCommand) commandFactory.createPlaySoundCommand("",  keyPull);
		comando.receiver(aReceiver);
		_result = comando;
	}

	public void addGetCommand(String keyPush, String keyPull, Receiver aReceiver) {
		GetInputCommand comando =  (GetInputCommand) commandFactory.createGetCommand(keyPush, keyPull);
		comando.receiver(aReceiver);
		_result = comando;
	}

	public void addStopCommand(String keyPush, String keyPull, Receiver aReceiver) {
		StopCommand comando =  (StopCommand) commandFactory.createStopCommand(keyPush, keyPull);
		comando.receiver(aReceiver);
		_result = comando;
	}

	public void addwhenSecondAndMinutesAreZeroCommand(String keyPush, String keyPull, String checkValue, Command aCommand) {
		Command comando = commandFactory.createDoubleIFSetCommand(keyPush, keyPull); //secondiInitialValue
		((BooleanInAndBehaviorSelectorCommand)comando).checkValue(checkValue);
		((BooleanInAndBehaviorSelectorCommand)comando).addForTrue(aCommand);
		((BooleanInAndBehaviorSelectorCommand)comando).addForFalse(new DoNotingCommand(new Bag("doNothingBag")));
		_result = comando;
		
	}

	public void addPlaySoundStopCommand(String keyPush, String keyPull, Receiver aReceiver) {
		StopPlaySoundCommand comando = (StopPlaySoundCommand) commandFactory.createStopPlaySoundCommand(keyPush, keyPull);
		comando.receiver(aReceiver);
		_result = comando;
		
	}

}
