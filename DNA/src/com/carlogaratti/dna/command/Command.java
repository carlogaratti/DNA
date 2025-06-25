package com.carlogaratti.dna.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import com.carlogaratti.dna.command.receiver.Receiver;
import com.carlogaratti.dna.core.Bag;


public abstract class Command {
	
	public abstract void execute();
	//RECEIVER
	//public abstract void receiver(Receiver aReceiver);
	//ADD
	//public abstract void add(Command aCommand);
	
	// BAG
	protected Bag _bag;
	public Command() {
		
	}
	public Command(Bag aBag) {
		_bag = aBag;
	}
	public   Bag bag(){
		return _bag;
	}
	public void showBag (){
		_bag.print();
	}
	// CONTEXT
	protected Map<String, String> _context;
	public Map<String, String> context (){
		return _context;
	}
	
	public Command(Map<String, String> aContext){
		_context = aContext;
	}
	// PUSH AND PULL
	protected List<String> _pushedKeys = new ArrayList<String>();
	protected String _pushOnKey = "result";
	protected Stack<String> _pushOnKeyStack = new Stack<>();
	
	
	public  void pushResultOn(String aKey) {
		_pushOnKeyStack.add(aKey);
		_pushedKeys.add(aKey);
		_pushOnKey = aKey;
	}
	
	protected List<String> _pullFromKey = new ArrayList<String>();
	public void resetKeys() {
		_pullFromKey.clear();
		_pushOnKeyStack.clear();
	}
	
	protected String _currentPullKey;
	public void pullFrom(String aKey) {
		_pullFromKey.add(aKey);
		_currentPullKey = aKey;
	}
	//SHOW
		public void showPushAndPull() {
			System.out.println("PRINT _pushedKeys: " + _pushedKeys.toString());
			System.out.println("PRINT _pushOnKey: " + _pushOnKey);
			System.out.println("PRINT _pushOnKeyStack: " + _pushOnKeyStack.toString());
			System.out.println("PRINT _currentPullKey: " + _currentPullKey);
			System.out.println("PRINT _pullFromKey: " + _pullFromKey);

			
		}
	//NEXT
	protected Command _next;
	public void next(Command next) {
		_next = next;
	}
	//SHOW
	public void show() {
		System.out.println( getClass().getName() + "@" + Integer.toHexString(hashCode()));
		
	}
	
}
