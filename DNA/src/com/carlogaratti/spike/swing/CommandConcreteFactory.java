package com.carlogaratti.spike.swing;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.carlogaratti.dna.command.Command;
import com.carlogaratti.dna.command.CompositeCommand;
import com.carlogaratti.dna.command.BooleanInAndBehaviorSelectorCommand;
import com.carlogaratti.dna.command.IFThenNewElseDefault;
import com.carlogaratti.dna.command.IFThenNewElseOldSetCommand;
import com.carlogaratti.dna.command.LogCommand;
import com.carlogaratti.dna.command.MultiplyCommand;
import com.carlogaratti.dna.command.PlaySoundCommand;
import com.carlogaratti.dna.command.PrintCommand;
import com.carlogaratti.dna.command.SetCommand;
import com.carlogaratti.dna.command.StopCommand;
import com.carlogaratti.dna.command.StopPlaySoundCommand;
import com.carlogaratti.dna.command.SubstractionCommand;
import com.carlogaratti.dna.command.SumCommand;
import com.carlogaratti.dna.command.WaitCommand;
import com.carlogaratti.dna.command.get.GetInputCommand;
import com.carlogaratti.dna.command.receiver.Receiver;
import com.carlogaratti.dna.core.Bag;

public class CommandConcreteFactory  extends CommandAbstractFactory{
	Map<String, Receiver> _widgetTranslatorAdapters;
	
	private Map<String, String> context =  new HashMap<String, String>();
	Bag bagContext = new Bag("context");
	
	public CommandConcreteFactory() {
		context.put("default", "notDefined");
		bagContext.putKey("default").asString("defaultValue");
		
	}

	public CompositeCommand createCompositeCommand() {
		//return new CompositeCommand(context);
		return new CompositeCommand(bagContext);
	}

	@Override
	protected Command createGetCommand() {
		//return  new  GetCommand(context);
		return  new  GetInputCommand(bagContext);
	}

	@Override
	protected Command createLogExecutor() {
		return new  LogCommand(context);
	}

	@Override
	protected Command createMultiplyCommand() {
		return new  MultiplyCommand(context);
	}


	@Override
	protected Command createPrintCommand() {
	//	return new  PrintCommand(context);
		return new  PrintCommand(bagContext);
	}

	@Override
	protected Command createSumCommand() {
		//return new  SumCommand(context);
		return  new  SumCommand(bagContext);
	}

	@Override
	protected Command createWaitCommand() {
		//return new  WaitCommand(context);
		return new  WaitCommand(bagContext);
	}

	@Override
	protected Command createPlaySoundCommand() {
		//return new PlaySoundCommand(context);
		return new PlaySoundCommand(bagContext);
	}

	@Override
	protected Command createStopPlaySoundCommand() {
		//return new StopPlaySoundCommand(context);
		return new StopPlaySoundCommand(bagContext);
	}

	@Override
	protected Command createSubstractionComman() {
		//return new  SubstractionCommand(context);
		return new  SubstractionCommand(bagContext);
	}
	
	@Override
	protected Command createConditionalSubstractionCommand() {
		return new  IFThenNewElseDefault(context);
	}

	@Override
	protected Command createIFSetCommand() {
		//return new  IFThenNewElseOldSetCommand(context);
		return new  IFThenNewElseOldSetCommand(bagContext);
	}

	@Override
	protected Command createSetCommand() {
		return new  SetCommand(context);
	}

	@Override
	protected Command createStopCommand() {
		//return new  StopCommand(context);
		return new  StopCommand(bagContext);
	}

	

	@Override
	protected Command createDoubleIFSetCommand() {
		//return new DoubleIFSetCommand(context);
		return new BooleanInAndBehaviorSelectorCommand(bagContext);
	}

	

	


	
	
	
	

}
