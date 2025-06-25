package com.carlogaratti.dna.command.builder;

import java.util.Scanner;

import com.carlogaratti.dna.command.Command;
import com.carlogaratti.dna.command.get.GetInputCommand;
import com.carlogaratti.dna.command.receiver.AWTTextInputWidgetTranslatorAdapter;
import com.carlogaratti.dna.command.receiver.Receiver;
import com.carlogaratti.dna.command.receiver.ReceiverJavaConsoleInput;
import com.carlogaratti.dna.core.Bag;

public class CommandBuilder {

	private Command _command;
	private Receiver _receiver;

	public void addGetInputConsole(Bag bag) {
		_command = new GetInputCommand(bag);
		Scanner scannerInput = new Scanner(System.in);
		_receiver = new ReceiverJavaConsoleInput();
		_receiver.on(scannerInput);
		((GetInputCommand)_command).receiver(_receiver);
		
	}

	public void addGetInputAWTTextInput(Bag bag) {
		_command = new GetInputCommand(bag);
		_receiver = new AWTTextInputWidgetTranslatorAdapter();
		((GetInputCommand)_command).receiver(_receiver);
	}

	public Command build() {
		return _command;
	}
	
	public Receiver receiver() {
		return _receiver;
	}

}
