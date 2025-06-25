
	package com.carlogaratti.spike.swing;

	import javax.swing.event.DocumentListener;
	import javax.swing.text.Document;
	import javax.swing.text.JTextComponent;

	import com.carlogaratti.dna.AbstractWriter;
	import com.carlogaratti.dna.Element;
import com.carlogaratti.dna.command.Command;
import com.carlogaratti.dna.command.IFThenNewElseOldSetCommand;
import com.carlogaratti.dna.command.receiver.AWTJLabelWidgetTranslatorAdapter;
import com.carlogaratti.dna.command.receiver.JAvaClip;
import com.carlogaratti.dna.command.receiver.NoReceiver;
import com.carlogaratti.dna.command.receiver.Receiver;
import com.carlogaratti.dna.command.receiver.ReceiverJavaConsoleInput;
import com.carlogaratti.project.bank_statement.Item;
	import com.carlogaratti.project.bank_statement.ItemStringDefault;
	import java.awt.*;
	import javax.swing.*;
import java.awt.Dimension;
	import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.util.ArrayList;
	import java.util.HashMap;
	import java.util.List;
	import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.Flow;

	
	import java.awt.event.*;

	public class PomoStarter {
		
		
		final static int FIELD_WIDTH = 10; 
		private static Map<String, String> aspectOnCommand = new HashMap<String, String>();
		private static Map<String, Map<String,Object>> aspectOnElement = new HashMap<String, Map<String,Object>>();
		static Thread worker = null;
		public static void main(String[] args) {
			//Create a JFrame Object
			JFrame frm = new JFrame();
			java.awt.Container  container =  frm.getContentPane();
			container.setLayout(new GridLayout(2,3));
			
			JLabel minuti = new JLabel("minuti");
			JLabel duePunti = new JLabel(":");
			JLabel secondi = new JLabel("secondi");
		
			//Create button
			JButton startButton = new JButton("Start");
			JAvaClip soundReceiver = new  JAvaClip();
			
			
			
			startButton.addActionListener((ActionEvent e) -> {
				
				CommandAbstractFactory commandFactory = new CommandConcreteFactory();
								
				//INPUT
				Scanner scannerInput = new Scanner(System.in);
				Command comandoget =  commandFactory.createGetCommand("minuti:secondi:sottraendo:waitSec:startsound:times", "");
				Receiver inputReceiver = new ReceiverJavaConsoleInput();
				inputReceiver.on(scannerInput);
				comandoget.receiver(inputReceiver);
				System.out.print("Minuti: ");
				comandoget.execute();
				
				System.out.print("Secondi: ");
				comandoget.execute();
				
				System.out.print("Sottraendo (1): ");
				comandoget.execute();
				
				System.out.print("Ogni quanti secondi: ");
				comandoget.execute();
				
				System.out.print("Start Sound long.wav: ");
				comandoget.execute();
				
				System.out.print("Start Sound times ");
				comandoget.execute();
				//FINE INPUT
				
				//SETUP COMANDI
				Command resetSecondCommand = commandFactory.createSubstractionCommand("resetSecond", "secondi:sottraendo");
				resetSecondCommand.execute();
				
				Command comandoWait = commandFactory.createWaitCommand("", "waitSec");
				comandoWait.receiver(new NoReceiver());
				
				Command decrementSecond =  commandFactory.createSubstractionCommand("secondi", "secondi:sottraendo");
				decrementSecond.receiver(new NoReceiver());
				
				Command comandoSubstractionForMin= commandFactory.createSubstractionCommand("minuti", "minuti:sottraendo");
				comandoSubstractionForMin.receiver(new NoReceiver());
				
				Command whenSecondIsZero = commandFactory.createIFSetCommand("secondi", "secondi:resetSecond"); //secondiInitialValue
				((IFThenNewElseOldSetCommand)whenSecondIsZero).checkValue("-1");
				whenSecondIsZero.receiver(new NoReceiver());
				whenSecondIsZero.next(comandoSubstractionForMin);
				
				
				Command printMinuti = commandFactory.createPrintCommand("", "minuti");
				Receiver showMinutiReceiver = new AWTJLabelWidgetTranslatorAdapter();
				showMinutiReceiver.on(minuti);
				printMinuti.receiver(showMinutiReceiver);
				
				Command printSecondi = commandFactory.createPrintCommand("", "secondi");
				Receiver printSecondiReceiver = new AWTJLabelWidgetTranslatorAdapter();
				printSecondiReceiver.on(secondi);
				printSecondi.receiver(printSecondiReceiver);
				
				
				
				Command playSoundStartCommand = commandFactory.createPlaySoundCommand("", "startsound:times");
				playSoundStartCommand.receiver(soundReceiver);
				
				Map<String, Command> commandDictionary = new HashMap();
				commandDictionary.put("decrementSecond", decrementSecond);
				commandDictionary.put("comandoSubstractionForMin", comandoSubstractionForMin);
				commandDictionary.put("decrementSecond", decrementSecond);
				commandDictionary.put("playSoundStartCommand", playSoundStartCommand);
				commandDictionary.put("printMinuti", printMinuti);
				commandDictionary.put("printSecondi", printSecondi);
				commandDictionary.put("comandoWait", comandoWait);
				commandDictionary.put("whenSecondIsZero", whenSecondIsZero);
				//FINE SETUP COMANDI
				
				//RUN 
				worker = new Thread() {
          			public void run() {
					//INITIAL VALUE
					decrementSecond.execute();
					comandoSubstractionForMin.execute();
					playSoundStartCommand.execute();
					//FINE INITIAL VALUE
					while(true) {
						
						printMinuti.execute();
						printSecondi.execute();
						comandoWait.execute();
						decrementSecond.execute();
						whenSecondIsZero.execute();
						
					}
					
					// FINE RUN 
				
          	}	};
          	
          	worker.start();
          	
				
				/*
				System.out.print("Sound for start (long.wav): ");
				comandoget.execute();
				
				System.out.print("Minuti: ");
				comandoget.execute();
				
				System.out.print("Secondi: ");
				comandoget.execute();
				
				System.out.print("How Many times?:  ");
				comandoget.execute();
				
						
				
				Command comandoPlaySound = commandDictionary.get("STARTPLAYSOUND");
				comandoPlaySound.receiver(soundReceiver);
				
				comandoPlaySound.execute();
				*/
			});
			//Create button
			JButton exit = new JButton("Stop");
			//Set button position
			    
			//GESTIONE EVENTO PER Submit
			exit.addActionListener((ActionEvent e) -> {
				Map<String, Command> commandDictionary = new HashMap<String, Command>();
				CommandAbstractFactory commandFactory = new CommandConcreteFactory();
				
				commandDictionary.put("GET", commandFactory.createGetCommand("start:times", ""));
				commandDictionary.put("MULTIPLY", commandFactory.createMultiplyCommand("primoFattore*secondoFattore*terzoFattore", "primoFattore:secondoFattore:terzoFattore"));
				commandDictionary.put("SUM", commandFactory.createSumCommand("primoFattore+secondoFattore+terzoFattore", "primoFattore:secondoFattore:terzoFattore"));
				commandDictionary.put("LOG", commandFactory.createLogCommand("", "primoFattore:secondoFattore"));
				commandDictionary.put("PRINT", commandFactory.createPrintCommand("", "waitSec"));
				commandDictionary.put("WAIT", commandFactory.createWaitCommand("", "waitSec"));
				commandDictionary.put("STARTPLAYSOUND", commandFactory.createPlaySoundCommand("", "start:times"));
				commandDictionary.put("STOPLAYSOUND", commandFactory.createStopPlaySoundCommand("", ""));
				commandDictionary.put("COMPOSITE", commandFactory.createCompositeCommand());
				
				
				Command comandoStopPlaySound = commandDictionary.get("STOPLAYSOUND");
				comandoStopPlaySound.receiver(soundReceiver);
				comandoStopPlaySound.execute();
				worker.
				/*
				Scanner scannerInput = new Scanner(System.in);
				Command comandoget = commandDictionary.get("GET");
				Receiver inputReceiver = new ReceiverJavaConsoleInput();
				inputReceiver.on(scannerInput);
				comandoget.receiver(inputReceiver);
				
				
				
				System.out.print("Sound for stop (notification.wav): ");
				comandoget.execute();
				
				System.out.print("How Many times?:  ");
				comandoget.execute();
				
				Command comandoStopPlaySound = commandDictionary.get("STOPLAYSOUND");
				comandoStopPlaySound.receiver(soundReceiver);
				comandoStopPlaySound.execute();
				
				Command comandoPlaySound = commandDictionary.get("STARTPLAYSOUND");
				comandoPlaySound.receiver(soundReceiver);
				comandoPlaySound.execute();
				*/
			});
			
			container.add(minuti);
			container.add(duePunti);
			container.add(secondi);
			container.add(startButton);
			container.add(new JLabel(" "));
			container.add(exit);
			//Set the size of the form
			frm.setSize(500, 350);  
			frm.setTitle("POMOAPP");
			//set form in the center of the screen
			frm.setLocationRelativeTo(null);  
			
			//Display the form 
			frm.setVisible(true);
	        
		
			//operationsToExecute.add("showResultJPane");
		}

	}


