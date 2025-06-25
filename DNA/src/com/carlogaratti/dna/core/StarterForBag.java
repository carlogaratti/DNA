package com.carlogaratti.dna.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.carlogaratti.dna.command.Command;
import com.carlogaratti.dna.command.SetCommand;
import com.carlogaratti.dna.command.SumCommand;

public class StarterForBag {
	public static void main(String[] args) {
		Bag bag = new Bag("bag");
		bag.putKey("velocita").asString("3");
		
		bag.putKey("accelerazione").asNumber("45");
		bag.putKey("bombo").asString("Luca");
	
		
		Command sum = new SumCommand(bag);
		Command set = new SetCommand(bag);
		
		
		List<Command> commandsAsList = new ArrayList<Command>();
		commandsAsList.add(set);
		commandsAsList.add(sum);
		
		Map<String, Command> commandsAsMap = new HashMap<String, Command>();
		commandsAsMap.put("set", set);
		commandsAsMap.put("sum", sum);
		
		Map<String, String> firstRow = new HashMap<String, String>();
		firstRow.put("columnName", "carlo");
		firstRow.put("columnCognome", "garatti");
		firstRow.put("eta", "48");
		
		Map<String, String> secondRow = new HashMap<String, String>();
		secondRow.put("columnName", "luca");
		secondRow.put("columnCognome", "garatti");
		secondRow.put("eta", "10");
		
		bag.putKey("commandsAsList").asList(commandsAsList);
		bag.putKey("commandsAsMap").asMap(commandsAsMap);
		
		// DATABASE
		bag.putKey("firstRow").asMap(firstRow);
		bag.putKey("secondRow").asMap(secondRow);
		
		
		List<Map> clientTable = new ArrayList<Map>();
		clientTable.add(firstRow);
		clientTable.add(secondRow);
		
		bag.putKey("clientTable").asList(clientTable);
		
		
	//	System.out.println(bag.get("velocita").asString());
	//	System.out.println(bag.get("accelerazione").asNumber());
	//	System.out.println(bag.get("commandsAsList").asList());
	//	System.out.println(bag.get("commandsAsMap").asMap());
		System.out.println(bag.get("firstRow").asMap());
		System.out.println(bag.get("secondRow").asMap());
		System.out.println(bag.get("clientTable").asList());
		//bag.print();
	}
}
