package com.carlogaratti.dna.command;

import java.util.Map;

import com.carlogaratti.dna.command.receiver.Receiver;


public class LogCommand extends Command {

	public LogCommand(Map<String, String> result) {
		super(result);
	}

	@Override
	public void execute() {
		
		for (String each : _pullFromKey) {
			System.out.println("LogCommand, contenuto di " +each + "=" + _context.getOrDefault(each,"NA"));
		}

	}
	

}
