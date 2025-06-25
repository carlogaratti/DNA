package com.carlogaratti.dna.command.condition;

import java.util.ArrayList;
import java.util.List;

import com.carlogaratti.dna.core.Bag;

public class TestStringEqualsCondition  {
	public static void main(String[] args) {
		Bag bag = new Bag("bag");
		//List aList = new ArrayList<String>();
		//aList.add("carlo");
		//aList.add("carlo");
		bag.putKey("nome").asString("carlo");
		bag.putKey("against").asString("carlo");
		StringEqualsCondition sec = new StringEqualsCondition(bag);
		sec.against("against");
		sec.underCheck("nome");
		System.out.println(sec.evaluate());
		bag.print();
		
	}
}
