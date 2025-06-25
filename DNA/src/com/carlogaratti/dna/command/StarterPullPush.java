package com.carlogaratti.dna.command;

import java.util.Map;

import com.carlogaratti.dna.core.Bag;

public class StarterPullPush {

	public static void main(String[] args) {
		Bag bag = new Bag("one");
		bag.putKey("primo").asString("13");
		bag.putKey("secondo").asString("2");
		bag.printEnteredKeys();
		SubstractionCommand substraction = new SubstractionCommand(bag);
		substraction.minuend("primo");
		substraction.substract("secondo");
		//substraction.pullFrom("primo");
		//substraction.pullFrom("secondo");
		
		substraction.execute();
		bag.print();
		
		
		bag.putKey("primo").asString("100");
		bag.putKey("secondo").asString("80");
		substraction.execute();
		bag.print();
	}

}
