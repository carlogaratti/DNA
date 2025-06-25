package com.carlogaratti.progetti.pomoapp;

import java.awt.event.*;
import java.util.List;

import com.carlogaratti.dna.command.Command;

public class SubmitListener  implements ActionListener{

	private OnClickStrategy _onCLickStrategy;
	private Command _setupCommand;
	private Command _coreCommand;
	private Command _getCommand;
	private List<String> _requests;
	
	public SubmitListener(List<String> requests, Command aGetCommand, Command aSetupCommand, Command aCoreCommand) {
		_requests = requests;
		_getCommand = aGetCommand;
		_setupCommand = aSetupCommand;
		_coreCommand = aCoreCommand;
	}

	public void strategy(OnClickStrategy anOnCLickStrategy) {
		_onCLickStrategy = anOnCLickStrategy;
		
	}
	
	public Command setupCommand() {
		return _setupCommand;
	}
	
	public Command coreCommand() {
		return _coreCommand;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//INPUT DA CONSOLE
				for (String each : _requests) {
			System.out.print(each);
			_getCommand.execute();
		}
		//FINE INPUT DA COSOLE
		//RUN 
		_onCLickStrategy.runOn(this);
		
		
		
	}

	

	

}
