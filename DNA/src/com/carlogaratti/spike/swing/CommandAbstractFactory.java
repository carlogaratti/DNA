package com.carlogaratti.spike.swing;

import java.util.HashMap;
import java.util.Map;

import com.carlogaratti.dna.command.Command;
import com.carlogaratti.dna.command.CompositeCommand;

public abstract class CommandAbstractFactory {
	private Map<String, String> context =  new HashMap<String, String>();
	
	public CommandAbstractFactory() {
		context.put("default", "notDefined");
	}	
	
	protected abstract Command createGetCommand();
	protected abstract Command createLogExecutor();
	protected abstract Command createMultiplyCommand();
	protected abstract Command createSumCommand();
	protected abstract Command createPrintCommand();
	
	  
	

	public Command createGetCommand(String pushOn, String pullOn  ) {
		Command result = createGetCommand();
		calculateKeysForWrite(pushOn, result);
		calculateKeysForRead(pullOn, result);
		return result;
	}


	public Command createLogCommand(String pushOn, String pullOn) {
		Command result = createLogExecutor();
		calculateKeysForWrite(pushOn, result);
		calculateKeysForRead(pullOn, result);
		return result;
	}

	public Command createMultiplyCommand(String pushOn, String pullOn) {
		Command result = createMultiplyCommand();
		calculateKeysForWrite(pushOn, result);
		calculateKeysForRead(pullOn, result);
		return result;
	}
	
	
	public  Command createSumCommand(String pushOn, String pullOn) {
		Command result = createSumCommand();
		calculateKeysForWrite(pushOn, result);
		calculateKeysForRead(pullOn, result);
		return result;
	}

	public Command createPrintCommand(String pushOn, String pullOn) {
		Command result = createPrintCommand();
		calculateKeysForWrite(pushOn, result);
		calculateKeysForRead(pullOn, result);
		return result;
	}
	
	public  Command createWaitCommand(String pushOn, String pullOn) {
		Command result = createWaitCommand();
		calculateKeysForWrite(pushOn, result);
		calculateKeysForRead(pullOn, result);
		return result;
	}

	protected abstract Command createWaitCommand();

	public CompositeCommand createCompositeCommand() {
		return new CompositeCommand(context);
	}
	
	
	private void calculateKeysForRead(String keys, Command result) {
		String[] splittedKeys = keys.split(":");
		for (String each : splittedKeys) {
			result.pullFrom(each);
		}
		
	}


	private void calculateKeysForWrite(String keys, Command result) {
		String[] splittedKeys = keys.split(":");
		for (String each : splittedKeys) {
			result.pushResultOn(each);
		}
	}

	public Command createPlaySoundCommand(String pushOn, String pullOn) {
		Command result = createPlaySoundCommand();
		calculateKeysForWrite(pushOn, result);
		calculateKeysForRead(pullOn, result);
		return result;
	}

	protected abstract Command createPlaySoundCommand();

	public  Command createStopPlaySoundCommand(String pushOn, String pullOn) {
		Command result = createStopPlaySoundCommand();
		calculateKeysForWrite(pushOn, result);
		calculateKeysForRead(pullOn, result);
		return result;
	}

	protected abstract Command createStopPlaySoundCommand();

	public Command createSubstractionCommand(String pushOn, String pullOn) {
		Command result = createSubstractionComman();
		calculateKeysForWrite(pushOn, result);
		calculateKeysForRead(pullOn, result);
		return result;
	}
	
	public Command createConditionalSubstractionCommand(String pushOn, String pullOn) {
		Command result = createConditionalSubstractionCommand();
		calculateKeysForWrite(pushOn, result);
		calculateKeysForRead(pullOn, result);
		return result;
	}

	protected abstract Command createConditionalSubstractionCommand();

	protected abstract Command createSubstractionComman();

	public Command createIFSetCommand(String pushOn, String pullOn) {
		Command result = createIFSetCommand();
		calculateKeysForWrite(pushOn, result);
		calculateKeysForRead(pullOn, result);
		return result;
	}

	protected abstract Command createIFSetCommand();

	public Command createSetCommand(String pushOn, String pullOn) {
		Command result = createSetCommand();
		calculateKeysForWrite(pushOn, result);
		calculateKeysForRead(pullOn, result);
		return result;
	}

	protected abstract Command createSetCommand();

	public  Command createStopCommand(String pushOn, String pullOn) {
		Command result = createStopCommand();
		calculateKeysForWrite(pushOn, result);
		calculateKeysForRead(pullOn, result);
		return result;
	}

	protected abstract Command createStopCommand();

	public  Command createDoubleIFSetCommand(String pushOn, String pullOn) {
		Command result = createDoubleIFSetCommand();
		calculateKeysForWrite(pushOn, result);
		calculateKeysForRead(pullOn, result);
		return result;
	}

	protected abstract Command createDoubleIFSetCommand();

	
	
	
}	

