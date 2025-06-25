package com.carlogaratti.dna.command.condition;

import java.util.ArrayList;
import java.util.List;

public class TestStringEqualsAndAnotherNotEquals {
	public static void main(String[] args) {
		//SEC=0 AND MIN!=0
		Condition secEqualsZero = new StringEqualsCondition();
		List<String> underTestSecEqualsZero = new ArrayList<>();
		underTestSecEqualsZero.add("0");
		secEqualsZero.underCheck(underTestSecEqualsZero);
		secEqualsZero.against("0");
		//System.out.println(secEqualsZero.evaluate());
		
		Condition minNOTEqualsZero = new StringNotEqualsCondition();
		List<String> underTestMinNOTEqualsZero = new ArrayList<>();
		underTestMinNOTEqualsZero.add("2");
		minNOTEqualsZero.underCheck(underTestMinNOTEqualsZero);
		minNOTEqualsZero.against("0");
		//System.out.println(minNOTEqualsZero.evaluate());
		
		Condition composite = new CompositeAllTRUECondition();
		composite.add(secEqualsZero);
		composite.add(minNOTEqualsZero);
		System.out.println(composite.evaluate());
		
	}
}
