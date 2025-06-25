package com.carlogaratti.progetti.pomoapp;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.carlogaratti.dna.command.Command;
import com.carlogaratti.dna.command.CompositeCommand;
import com.carlogaratti.dna.command.DoNotingCommand;
import com.carlogaratti.dna.command.receiver.AWTJLabelWidgetTranslatorAdapter;
import com.carlogaratti.dna.command.receiver.JAvaClip;
import com.carlogaratti.dna.command.receiver.Receiver;
import com.carlogaratti.dna.command.receiver.ReceiverJavaConsoleInput;
import com.carlogaratti.dna.core.Bag;
import com.carlogaratti.spike.swing.*;

	public class Starter {
		
		
		final static int FIELD_WIDTH = 10; 
		static Thread worker = null;

		public static void main(String[] args) {
			//SETUP VIEW
			JFrame frm = new JFrame();
			java.awt.Container  container =  frm.getContentPane();
			container.setLayout(new GridLayout(2,3));
			JLabel minuti = new JLabel("minuti");
			JLabel duePunti = new JLabel(":");
			JLabel secondi = new JLabel("secondi");
			JButton startButton = new JButton("Start");
			JButton exit = new JButton("Stop");
			container.add(minuti);
			container.add(duePunti);
			container.add(secondi);
			container.add(startButton);
			container.add(new JLabel(" "));
			container.add(exit);
			frm.setSize(500, 350);  
			frm.setTitle("POMOAPP");
			frm.setLocationRelativeTo(null);  
			frm.setVisible(true);
			
			//SETUP COMANDI
			CommandBuilder commandBuilder = new CommandBuilder();
			Map<String, Command> commandDictionary = new HashMap<>();
			
			commandBuilder.addResetCommand("resetSecond", "secondi:sottraendo"); //OK
			commandDictionary.put("resetCommand", commandBuilder.build());
			
			commandBuilder.addWaitCommand("", "waitSec" ); //OK
			commandDictionary.put("waitCommand", commandBuilder.build());
			
			OnClickStrategy threadStrategy = new TheadOnClickStrategy();
			Receiver threadStopReceiver = new ThreadStopperReceiverAdapter();
			threadStopReceiver.on(threadStrategy);
			commandBuilder.addStopCommand("", "minuti", threadStopReceiver); //ok
			commandDictionary.put("stopCommand", commandBuilder.build());			
			
			commandBuilder.addSubstractionCommand("secondi", "secondi:sottraendo");
			commandDictionary.put("substractionCommandForSecond", commandBuilder.build());
			
			commandBuilder.addSubstractionCommand("minuti", "minuti:sottraendo"); //ok
			commandDictionary.put("substractionCommandForMinutes", commandBuilder.build());
			
			commandBuilder.addwhenSecondIsZeroCommand("secondi", "secondi:resetSecond", "-1", commandDictionary.get("substractionCommandForMinutes"));//ok
			commandDictionary.put("whenSecondIsZero", commandBuilder.build());
			
			Receiver soundReceiver = new  JAvaClip();
			commandBuilder.addPlaySoundStartCommand("", "startLongSound:times", soundReceiver); //ok
			commandDictionary.put("startLongSoundCommand", commandBuilder.build());
			commandBuilder.addPlaySoundStartCommand("", "startShortSound", soundReceiver); //ok
			commandDictionary.put("startShortSoundCommand", commandBuilder.build());
			commandBuilder.addPlaySoundStopCommand("", "" , soundReceiver); //ok
			commandDictionary.put("stopSoundCommand", commandBuilder.build());
			
			CompositeCommand commandsForWhenSecondAndMinutesAreZero = new CompositeCommand(new Bag("composite")); //ok
			commandsForWhenSecondAndMinutesAreZero.add(commandDictionary.get("stopSoundCommand"));
			commandsForWhenSecondAndMinutesAreZero.add(commandDictionary.get("waitCommand"));
			commandsForWhenSecondAndMinutesAreZero.add(commandDictionary.get("startShortSoundCommand"));
			commandsForWhenSecondAndMinutesAreZero.add(commandDictionary.get("stopCommand"));
			commandDictionary.put("commandsForWhenSecondAndMinutesAreZero", commandsForWhenSecondAndMinutesAreZero);
			
			commandBuilder.addwhenSecondAndMinutesAreZeroCommand("", "secondi:minuti", "0", commandDictionary.get("commandsForWhenSecondAndMinutesAreZero")); //OK
			commandDictionary.put("whenSecondAndMinutesAreZero", commandBuilder.build());
			
			Receiver showMinutiReceiver = new AWTJLabelWidgetTranslatorAdapter();
			showMinutiReceiver.on(minuti);
			commandBuilder.addPrintCommand("", "minuti", showMinutiReceiver);  //ok
			commandDictionary.put("showMinutiCommand", commandBuilder.build());
			
			Receiver showSecondiReceiver = new AWTJLabelWidgetTranslatorAdapter();
			showSecondiReceiver.on(secondi);
			commandBuilder.addPrintCommand("", "secondi", showSecondiReceiver); //ok
			commandDictionary.put("showSecondiCommand", commandBuilder.build());
			
			Scanner scannerInput = new Scanner(System.in);
			Receiver inputReceiver = new ReceiverJavaConsoleInput();
			inputReceiver.on(scannerInput);
			commandBuilder.addGetCommand("minuti:secondi:sottraendo:waitSec:startLongSound:times:startShortSound", "", inputReceiver);  //ok
			commandDictionary.put("getCommand", commandBuilder.build());
			
			
			
			//SUBMIT BUTTON SETUP
			CompositeCommand setupCommand = new CompositeCommand(new Bag(""));
			setupCommand.add(commandDictionary.get("resetCommand"));
			setupCommand.add(commandDictionary.get("substractionCommandForSecond"));
			setupCommand.add(commandDictionary.get("substractionCommandForMinutes"));
			setupCommand.add(commandDictionary.get("startLongSoundCommand"));
			commandDictionary.put("setupCommand", setupCommand);
			
			CompositeCommand coreCommand = new CompositeCommand(new Bag(""));
			coreCommand.add(commandDictionary.get("showMinutiCommand"));
			coreCommand.add(commandDictionary.get("showSecondiCommand"));
			coreCommand.add(commandDictionary.get("waitCommand"));
			coreCommand.add(commandDictionary.get("whenSecondAndMinutesAreZero"));
			coreCommand.add(commandDictionary.get("substractionCommandForSecond"));
			coreCommand.add(commandDictionary.get("whenSecondIsZero"));
			
			List<String> inputRequestsForSubmit = new ArrayList<>();
			inputRequestsForSubmit.add("Minuti: ");
			inputRequestsForSubmit.add("Secondi: ");
			inputRequestsForSubmit.add("Sottraendo (1): ");
			inputRequestsForSubmit.add("Ogni quanti secondi: ");
			inputRequestsForSubmit.add("Start Long Sound [long.wav] ");
			inputRequestsForSubmit.add("Start Sound times ");
			inputRequestsForSubmit.add("Start Short Sound [notification.wav] ");
			SubmitListener submitListener = new SubmitListener(inputRequestsForSubmit, commandDictionary.get("getCommand"), setupCommand, coreCommand);
			submitListener.strategy(threadStrategy);
			startButton.addActionListener(submitListener);
			//FINE SUBMIT BUTTON SETUP
			
			//EXIT BUTTON SETUP
			CompositeCommand setupCommandForExit = new CompositeCommand(new Bag(""));
			setupCommandForExit.add(commandDictionary.get("startShortSoundCommand"));
			commandDictionary.put("setupCommandForExit", setupCommand);
			Command coreCommandForExit = new DoNotingCommand(new Bag(""));
			List<String> inputRequestsForexit = new ArrayList<>();
			
			SubmitListener exitListener = new SubmitListener(inputRequestsForexit, commandDictionary.get("getCommand"), setupCommandForExit, coreCommandForExit);
			exitListener.strategy(threadStrategy);
			exit.addActionListener(exitListener);
			//FINE EXIT BUTTON SETUP    
		}

	}


