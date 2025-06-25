package com.carlogaratti.spike.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import com.carlogaratti.dna.command.Command;
import com.carlogaratti.project.bank_statement.Item;

public class MuliplyClickOnEvent implements ActionListener {

	private static Map<String, String> _aspectOnCommand ;
	private static Map<String, Map<String, Object>> _aspectOnWidget ;
	private Map<String, Command> _commands = new HashMap<String, Command>();

	public MuliplyClickOnEvent(Map<String, Map<String,Object>> aspectOnWidget, Map<String, String> aspectOnCommand, List<String> operationsToExecute) {
		_aspectOnCommand = aspectOnCommand;
		_aspectOnWidget = aspectOnWidget;
		CommandBuilderForMultipy commandBuilder = new CommandBuilderForMultipy();
		commandBuilder.add("MultiplySubmit", operationsToExecute, _aspectOnCommand, _aspectOnWidget);
		_commands = commandBuilder.build();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Command multiplyCommand = _commands.get("MultiplySubmit");
		multiplyCommand.execute();
	}

}
