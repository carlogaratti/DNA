package com.carlogaratti.dna.core;

public class StarterForBagLog  {
	public static void main(String[] args) {
		
			Bag bag = new Bag("aaa");
			bag.putKey("velocita").asString("3");
			bag.putKey("velocita").asString("100");
			bag.putKey("nome").asString("Carlo");
			bag.putKey("velocita").asString("-1");
			bag.printLog();
			bag.printEnteredKeys();
			bag.printLogKeys();
	}
}
