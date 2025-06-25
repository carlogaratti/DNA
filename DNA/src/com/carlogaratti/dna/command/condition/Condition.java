package com.carlogaratti.dna.command.condition;

import java.util.Arrays;
import java.util.List;

import com.carlogaratti.dna.core.Bag;

public abstract class Condition {
	// BAG
		protected Bag _bag;
		public Condition() {
			
		}
		public Condition(Bag aBag) {
			_bag = aBag;
		}
		public   Bag bag(){
			return _bag;
		}
		public void showBag (){
			_bag.print();
		}
	
	
	protected List<String> _valuesUnderCheck;
	protected String _valueAgainst;

	public void underCheck(List<String>  valuesUnderCheck) {
		/*List<String> asList =  _bag.get(valuesUnderCheck).asList();
		String asString=  _bag.get(valuesUnderCheck).asString();
		asList.add(asString);
		asList.remove("not allowed");
		System.out.println(Arrays.toString(asList.toArray()));
		*/
		_valuesUnderCheck = valuesUnderCheck;
		
	}

	public void against(String aValueAgainst) {
		//_valueAgainst = _bag.get(aValueAgainst).asString();
		_valueAgainst = aValueAgainst;
	}
	
	public abstract String evaluate();

	protected abstract void add(Condition secEqualsZero);
}
