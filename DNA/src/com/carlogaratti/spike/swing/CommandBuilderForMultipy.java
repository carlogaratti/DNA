package com.carlogaratti.spike.swing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.carlogaratti.dna.command.Command;
import com.carlogaratti.dna.command.CompositeCommand;
import com.carlogaratti.dna.command.receiver.NoReceiver;
import com.carlogaratti.dna.command.receiver.Receiver;

public class CommandBuilderForMultipy extends Builder {
	private Map<String, Command> commandDictionary = new HashMap<String, Command>();
	CommandConcreteFactory commandFactory = new CommandConcreteFactory();
	ReceiverAbstractFactory awtWidgetAbstractFactory = new AWTWidgetAbstractFactory();
	Map<String, Receiver> widgetTranslatorAdapters = new HashMap<String, Receiver>();
	
	CommandBuilderForMultipy(){
		//COMANDI FOGLIA
		commandDictionary.put("GETforRBYes", commandFactory.createGetCommand("needToLog",""));
		commandDictionary.put("GETforRBNo", commandFactory.createGetCommand("needToLog",""));
		commandDictionary.put("GETForTF", commandFactory.createGetCommand("moltiplicando", ""));
		commandDictionary.put("GETforCombo", commandFactory.createGetCommand("moltiplicatore", ""));
		commandDictionary.put("LogExecutor", commandFactory.createLogCommand("", "needToLog"));
		commandDictionary.put("multiplyOp", commandFactory.createMultiplyCommand("showResult", "moltiplicatore:moltiplicando"));
		commandDictionary.put("showResultJLabel", commandFactory.createPrintCommand("", "showResult"));
		commandDictionary.put("showResultJPane", commandFactory.createPrintCommand("", "showResult"));
		widgetTranslatorAdapters.put("awtTextInput", awtWidgetAbstractFactory.createTextInputWidgetTranslatorAdapter()); 
		widgetTranslatorAdapters.put("awtComoBoxInput", awtWidgetAbstractFactory.createComoBoxInputWidgetTranslatorAdapter() );
		widgetTranslatorAdapters.put("awtRadioInput",  awtWidgetAbstractFactory.createRadioInputWidgetTranslatorAdapter());
		widgetTranslatorAdapters.put("noReceiver",  awtWidgetAbstractFactory.createNoReceiver());
		widgetTranslatorAdapters.put("awtJLabelOutput",  awtWidgetAbstractFactory.createJLabelOutputWidgetTranslatorAdapter());
		widgetTranslatorAdapters.put("awtJPaneOutput", awtWidgetAbstractFactory.createJPaneOutputWidgetTranslatorAdapter());
		
	}
	
	
	
	public void add(String aCommand, List<String> operationsToExecute, Map<String, String> _commandOn, Map<String, Map<String,Object>> _windowElements) {
		//AGGIUNGO I COMANDI A multiplyCommand, CONTA L'ORDINE di operationsToExecute
				CompositeCommand compositeCommand = commandFactory.createCompositeCommand();
				//AGGIUNGO I COMANDI 
				for (String each : operationsToExecute) {
					String comandoAsString = _commandOn.getOrDefault(each, each);
					Map<String, Object> windowElemenValue= _windowElements.getOrDefault(each, Map.of("element", new NoReceiver(), "type", "noReceiver"));
					Object windowElement = windowElemenValue.get("element");
					String windowElementType = (String) windowElemenValue.get("type");
					Receiver receiver = widgetTranslatorAdapters.get(windowElementType);
					receiver.on(windowElement);
					Command comando = commandDictionary.get(comandoAsString);
					comando.receiver(receiver);
					compositeCommand.add(comando);
				}
				commandDictionary.put(aCommand, compositeCommand);
				
	}

	
	Map<String, Command> build(){
		return commandDictionary;
	}

	
}
