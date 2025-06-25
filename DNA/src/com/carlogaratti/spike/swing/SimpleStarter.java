package com.carlogaratti.spike.swing;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.carlogaratti.dna.command.Command;
import com.carlogaratti.dna.command.CompositeCommand;
import com.carlogaratti.dna.command.receiver.NoReceiver;
import com.carlogaratti.dna.command.receiver.Receiver;
import com.carlogaratti.dna.command.receiver.ReceiverJavaConsoleInput;
import com.carlogaratti.dna.command.receiver.ReceiverJavaConsolePrint;
import com.carlogaratti.dna.command.receiver.ReceiverJavaSwingPrint;


public class SimpleStarter  {
	
	
	public static void main(String[] args) {
		//personalInfo();
	//	multiply();
	//	multiplyAndSumLiner();
		pomodoro();
	}

	private static void pomodoro() {
		Map<String, Command> commandDictionary = new HashMap<String, Command>();
		CommandAbstractFactory commandFactory = new CommandConcreteFactory();
		
		commandDictionary.put("GET", commandFactory.createGetCommand("waitSec", ""));
		commandDictionary.put("MULTIPLY", commandFactory.createMultiplyCommand("primoFattore*secondoFattore*terzoFattore", "primoFattore:secondoFattore:terzoFattore"));
		commandDictionary.put("SUM", commandFactory.createSumCommand("primoFattore+secondoFattore+terzoFattore", "primoFattore:secondoFattore:terzoFattore"));
		commandDictionary.put("LOG", commandFactory.createLogCommand("", "primoFattore:secondoFattore"));
		commandDictionary.put("PRINT", commandFactory.createPrintCommand("", "waitSec"));
		commandDictionary.put("WAIT", commandFactory.createWaitCommand("", "waitSec"));
		commandDictionary.put("PLAYSOUND", commandFactory.createPlaySoundCommand("", ""));
		commandDictionary.put("COMPOSITE", commandFactory.createCompositeCommand());
		
		Scanner scannerInput = new Scanner(System.in);
		
		Command comandoget = commandDictionary.get("GET");
		Receiver inputReceiver = new ReceiverJavaConsoleInput();
		inputReceiver.on(scannerInput);
		comandoget.receiver(inputReceiver);
		
		System.out.print("every time wait for (sec): ");
		comandoget.execute();
		
		PrintStream printStream = new PrintStream(System.out);
		Receiver outputReceiverForConsole = new ReceiverJavaConsolePrint();
		Command comandoPrint = commandDictionary.get("PRINT");
		outputReceiverForConsole.on(printStream);
		comandoPrint.receiver(outputReceiverForConsole);
		comandoPrint.execute();
		
		Command comandoWait = commandDictionary.get("WAIT");
		comandoWait.receiver(new NoReceiver());
		
		Command comandoPlaySound = commandDictionary.get("PLAYSOUND");
		comandoPlaySound.receiver(new NoReceiver());
		
		comandoPlaySound.execute();
		comandoWait.execute();
		comandoPlaySound.execute();
		comandoWait.execute();
		comandoPlaySound.execute();
		comandoWait.execute();
		comandoPlaySound.execute();
		comandoWait.execute();
		comandoPlaySound.execute();
		comandoWait.execute();
		comandoPlaySound.execute();
		comandoWait.execute();
		comandoPlaySound.execute();
		comandoWait.execute();
		comandoPlaySound.execute();
		comandoWait.execute();
		comandoPlaySound.execute();
		comandoWait.execute();
		comandoPlaySound.execute();
		comandoWait.execute();
		
		
		
	}

	private static void printdate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
		  LocalTime localTime = LocalTime.now();
		  System.out.println(dtf.format(localTime));  
		
	}

	private static void multiplyAndSumLiner() {
		
		Map<String, Command> commandDictionary = new HashMap<String, Command>();
		CommandAbstractFactory commandFactory = new CommandConcreteFactory();
		
		commandDictionary.put("GET", commandFactory.createGetCommand("primoFattore:secondoFattore:terzoFattore:plus", ""));
		commandDictionary.put("MULTIPLY", commandFactory.createMultiplyCommand("primoFattore*secondoFattore*terzoFattore", "primoFattore:secondoFattore:terzoFattore"));
		commandDictionary.put("SUM", commandFactory.createSumCommand("primoFattore+secondoFattore+terzoFattore", "primoFattore:secondoFattore:terzoFattore"));
		commandDictionary.put("LOG", commandFactory.createLogCommand("", "primoFattore:secondoFattore"));
		commandDictionary.put("PRINT", commandFactory.createPrintCommand("", "primoFattore+secondoFattore+terzoFattore:primoFattore*secondoFattore*terzoFattore"));
		commandDictionary.put("COMPOSITE", commandFactory.createCompositeCommand());
		
		Scanner scannerInput = new Scanner(System.in);
		
		Command comandoget = commandDictionary.get("GET");
		Receiver inputReceiver = new ReceiverJavaConsoleInput();
		inputReceiver.on(scannerInput);
		comandoget.receiver(inputReceiver);
		
		System.out.print("primo numero: ");
		comandoget.execute();
		System.out.print("secondo numero: ");
		comandoget.execute();
		System.out.print("terzo numero: ");
		comandoget.execute();

		PopUp popUp = new PopUp();
		Command comandoPrint = commandDictionary.get("PRINT");
		Receiver outputReceiverForSwing = new ReceiverJavaSwingPrint();
		outputReceiverForSwing.on(popUp);
		comandoPrint.receiver(outputReceiverForSwing);
		comandoPrint.execute();
		
		
		Command comandoMultiply = commandDictionary.get("MULTIPLY");
		Receiver noReceiver = new NoReceiver();
		comandoMultiply.receiver(noReceiver);
		//comandoMultiply.execute();

		
		Command comandoSum = commandDictionary.get("SUM");
		Receiver noReceiverForSum = new NoReceiver();
		comandoSum.receiver(noReceiverForSum);
		//comandoSum.execute();
		
		Command compositeCommand = commandDictionary.get("COMPOSITE");
		compositeCommand.add(comandoSum);
		compositeCommand.add(comandoMultiply);
		compositeCommand.execute();
		
		PrintStream printStream = new PrintStream(System.out);
		Receiver outputReceiverForConsole = new ReceiverJavaConsolePrint();
		outputReceiverForConsole.on(printStream);
		comandoPrint.receiver(outputReceiverForConsole);
		comandoPrint.execute();
		
		
		comandoPrint.receiver(outputReceiverForSwing);
		comandoPrint.execute();
		
		
		//System.out.println(comandoSum._context);
		//comandoSum.show();
		
		
		/*
		comandoMultiply.resetKeys();
		comandoMultiply.pullFrom("plus");
		comandoMultiply.pullFrom("primoFattore+secondoFattore+terzoFattore");
		comandoMultiply.pushOn("plus*primoFattore+secondoFattore+terzoFattore");
		comandoMultiply.execute();
		System.out.println(comandoMultiply._context);
		comandoMultiply.show();
		*/
	}

	private static void multiply() {
		Map<String, Receiver> widgetTranslatorAdapters = new HashMap<String, Receiver>();
		ReceiverAbstractFactory awtWidgetAbstractFactory = new AWTWidgetAbstractFactory();
		Map<String, Command> commandDictionary = new HashMap<String, Command>();
		CommandAbstractFactory commandFactory = new CommandConcreteFactory();
		widgetTranslatorAdapters.put("noReceiver",  awtWidgetAbstractFactory.createNoReceiver());
		widgetTranslatorAdapters.put("scannerInput",  awtWidgetAbstractFactory.createScannerTransaltorAdapter());
		commandDictionary.put("GET", commandFactory.createGetCommand("primoFattore:secondoFattore:terzoFattore", ""));
		commandDictionary.put("MULTIPLY", commandFactory.createMultiplyCommand("result", "primoFattore:secondoFattore:terzoFattore"));
		commandDictionary.put("LOG", commandFactory.createLogCommand("", "primoFattore:secondoFattore"));
		
		Scanner scannerInput = new Scanner(System.in);
		
		Map<String, Map<String, Object>> configurations = new HashMap<String, Map<String,Object>>();
		
		Map<String, Object> confInputElemnt = new HashMap<String, Object>();
		confInputElemnt.put("element",scannerInput);
		confInputElemnt.put("type","scannerInput");
		confInputElemnt.put("command","GET");
		configurations.put("get", confInputElemnt);
		
		Map<String, Object> confLog = new HashMap<String, Object>();
		confLog.put("element",new NoReceiver());
		confLog.put("type","noReceiver");
		confLog.put("command","LOG");
		configurations.put("log", confLog);
		
		Map<String, Object> confMultiply = new HashMap<String, Object>();
		confMultiply.put("element",new NoReceiver());
		confMultiply.put("type","noReceiver");
		confMultiply.put("command","MULTIPLY");
		configurations.put("multiply", confMultiply);
		
		
		
		List<String> operationsToExecute = new ArrayList<>();
		operationsToExecute.add("get");
		
		CompositeCommand compositeCommand = commandFactory.createCompositeCommand();
		for (String each : operationsToExecute) {
			Map<String, Object> configurationForElementProperties = configurations.get(each);
			String comandoAsString = (String) configurationForElementProperties.get("command");
			Command comando = commandDictionary.get(comandoAsString);
			Object element  = configurationForElementProperties.get("element");
			String windowElementType = (String) configurationForElementProperties.get("type");
			Receiver receiver = widgetTranslatorAdapters.get(windowElementType);
			receiver.on(element);
			comando.receiver(receiver);
			compositeCommand.add(comando);
		}
		System.out.print("primo numero: ");
		compositeCommand.execute();
		System.out.print("secondo numero: ");
		compositeCommand.execute();
		System.out.print("terzo numero: ");
		compositeCommand.execute();
		
		compositeCommand.deleteAll();
		operationsToExecute.removeAll(operationsToExecute);
		operationsToExecute.add("multiply");
		for (String each : operationsToExecute) {
			Map<String, Object> configurationForElementProperties = configurations.get(each);
			String comandoAsString = (String) configurationForElementProperties.get("command");
			Command comando = commandDictionary.get(comandoAsString);
			Object element  = configurationForElementProperties.get("element");
			String windowElementType = (String) configurationForElementProperties.get("type");
			Receiver receiver = widgetTranslatorAdapters.get(windowElementType);
			receiver.on(element);
			comando.receiver(receiver);
			compositeCommand.add(comando);
		}
		compositeCommand.execute();
		
		
		System.out.println(compositeCommand._context);
		compositeCommand.show();
		
		
	}

	private static void personalInfo() {
		Map<String, Receiver> widgetTranslatorAdapters = new HashMap<String, Receiver>();
		ReceiverAbstractFactory awtWidgetAbstractFactory = new AWTWidgetAbstractFactory();
		Map<String, Command> commandDictionary = new HashMap<String, Command>();
		CommandAbstractFactory commandFactory = new CommandConcreteFactory();
		widgetTranslatorAdapters.put("noReceiver",  awtWidgetAbstractFactory.createNoReceiver());
		widgetTranslatorAdapters.put("scannerInput",  awtWidgetAbstractFactory.createScannerTransaltorAdapter());
		commandDictionary.put("GET", commandFactory.createGetCommand("nome:cognome:age:via:cap", ""));
		commandDictionary.put("LOG", commandFactory.createLogCommand("", "nome:cognome:age:cap:via"));
		
		
		Scanner scannerInput = new Scanner(System.in);
        
   
		
		Map<String, Map<String, Object>> configurations = new HashMap<String, Map<String,Object>>();
		
		Map<String, Object> confInputElemnt = new HashMap<String, Object>();
		confInputElemnt.put("element",scannerInput);
		confInputElemnt.put("type","scannerInput");
		confInputElemnt.put("command","GET");
		configurations.put("get", confInputElemnt);
		
		Map<String, Object> confLog = new HashMap<String, Object>();
		confLog.put("element",new NoReceiver());
		confLog.put("type","noReceiver");
		confLog.put("command","LOG");
		configurations.put("log", confLog);
		
		
		
		List<String> operationsToExecute = new ArrayList<>();
		operationsToExecute.add("get");
		operationsToExecute.add("log");
		
		
		CompositeCommand compositeCommand = commandFactory.createCompositeCommand();
		for (String each : operationsToExecute) {
			Map<String, Object> configurationForElementProperties = configurations.get(each);
			String comandoAsString = (String) configurationForElementProperties.get("command");
			Command comando = commandDictionary.get(comandoAsString);
			Object element  = configurationForElementProperties.get("element");
			String windowElementType = (String) configurationForElementProperties.get("type");
			Receiver receiver = widgetTranslatorAdapters.get(windowElementType);
			receiver.on(element);
			comando.receiver(receiver);
			compositeCommand.add(comando);
		}
		System.out.print("Enter your name: ");
		compositeCommand.execute();
		System.out.print("Enter your surname: ");
		compositeCommand.execute();
		System.out.print("Enter your age: ");
		compositeCommand.execute();
		System.out.print("Enter your via: ");
		compositeCommand.execute();
		System.out.print("Enter your cap: ");
		compositeCommand.execute();
		
		System.out.println(compositeCommand._context);
		compositeCommand.show();
		// FINE  NUOVO
		
		/*
		List<String> operationsToExecute = new ArrayList<>();
		operationsToExecute.add("getInputCommand");
		operationsToExecute.add("LogExecutor");
		operationsToExecute.add("LogExecutor");
		
		
		
		
		CompositeCommand compositeCommand = commandFactory.createCompositeCommand();
		for (String each : operationsToExecute) {
			String comandoAsString = aspectOnCommand.getOrDefault(each, each);
			Map<String, Object> windowElemenValue= aspectOnElement.getOrDefault(each, Map.of("element", new NoReceiver(), "type", "noReceiver"));
			Object windowElement = windowElemenValue.get("element");
			String windowElementType = (String) windowElemenValue.get("type");
			Receiver receiver = widgetTranslatorAdapters.get(windowElementType);
			receiver.widget(windowElement);
			Command comando = commandDictionary.get(comandoAsString);
			comando.receiver(windowElement, receiver);
			compositeCommand.add(comando);
		}
		System.out.println(compositeCommand);
		compositeCommand.execute();
		*/
	}

}
