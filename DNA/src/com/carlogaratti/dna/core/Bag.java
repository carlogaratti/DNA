package com.carlogaratti.dna.core;

import java.math.BigDecimal;
import java.security.KeyException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.function.Predicate;

import com.carlogaratti.dna.command.Command;

public class Bag {
	private String _key;
	private String _type = "";
	private Map<String, Map<String, Object>> _map = new HashMap<>();
	private String _basketOf;
	private List<String> _enteredKeysLog = new ArrayList<String>();
	Map<String, Map<String, Object>> keyLog = new  HashMap<String, Map<String,Object>>();

	public Bag(String basketOf) {
		_basketOf = basketOf;
		
	}
	
	

	public void put(String key, Map map) {
		_map .put(key, map);
		
	//	if (key.equals("secondi"))System.out.println("Bag: put [ key=" + key + " ] , [ value=" + map.get("String") + " ]");
		
	}
	
	

	public Bag get(String key) {
		//if (!contains(key)) putKey(key);
		Map<String, Object> map = ((Map<String, Object>) _map .get(key));
		if (map == null) return this;
		_type = (String) map.get("type");
		_key = key;
		return this;
	}


	public Bag putKey(String key) {
		_key = key;
		_enteredKeysLog .add(_key);
		asDefault();
		return this;
	}
	


	public void print() {
		System.out.println(_map);
		//_map.forEach((K,V) -> System.out.print("for _basketOf: " + _basketOf + " Chiave : " + K + ", Valore : " + V));
		
	}
	
	public void printEnteredKeys() {
		System.out.println(_map.keySet());
		//_map.forEach((K,V) -> System.out.print("for _basketOf: " + _basketOf + " Chiave : " + K + ", Valore : " + V));
		
	}
	
	private void asDefault() {
		fillValueAsType("default","String");
	}

	public void asString(String value) {
		fillValueAsType(value,"String");
	}
	
	public void asList(List aList) {
		fillValueAsType(aList,"List");
	}
	
	public void asNumber(String number) {
		fillValueAsType(new BigDecimal(number),"BigDecimal");
	}
	
	public void asMap(Map aMap) {
		fillValueAsType(aMap,"Map");
		
	}
	
	private Object extractValue() {
		return  _map.get(_key).get("value");
		
	}

	public String asString() {
		if (!_type.equals("String")) return "not allowed";
		return String.class.cast(extractValue());
	}


	public BigDecimal asNumber() {
		if (!_type.equals("BigDecimal")) return new BigDecimal("0");
		return BigDecimal.class.cast(extractValue());
	}


	public List asList() {
		if (!_type.equals("List")) return new ArrayList();
		return List.class.cast(extractValue());
	}
	
	public Map asMap() {
		if (!_type.equals("Map")) return new HashMap();
		return Map.class.cast(extractValue());
	}

	
		private boolean contains(String key) {
		return _map.containsKey(key);
	}


	private void fillValueAsType(Object anObject, String aType) {
		Map<String, Object> map = new HashMap<>();
		map.put("type", aType);
		map.put("value", anObject);
		put(_key, map);
		logRawValues(_key, aType, anObject);
		
		
	}



	private void logRawValues(String key, String aType, Object anObject) {
		if (anObject.equals("default")) return;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyymmddHHmmss.SSS");
		Date now = new Date();
	    String strDate = sdf.format(now);
		
		Map<String, Object> elem = new HashMap<String, Object>();
		elem.put("key", key);
		elem.put("type", aType);
		elem.put("value", anObject);
		elem.put("timestamp", Instant.now());
		keyLog.put(UUID.randomUUID().toString(), elem);
		/*
		if (keyLog.containsKey(key)) {
			Map<String, Map<String, Object>> currentValue = keyLog.get(key);
			currentValue.put(UUID.randomUUID().toString(), elem);
		}
		else {
			Map<String, Map<String, Object>> currentValue = new HashMap<String, Map<String,Object>>();
			currentValue.put(UUID.randomUUID().toString(), elem);
			keyLog.put(key, currentValue);
		}
		*/
		
	}
	
	public void printLog() {
		System.out.println(keyLog);
	}



	public void printLogKeys() {
		System.out.println(keyLog.keySet());
		
		
	}
	
	public Map<String, Map<String, Object>> map() {
		return _map;
	}



	public Map<String, Map<String, Object>> logMap() {
		return keyLog;
	}

	



	public Map filterOnType(BagFilterCondition aBagConditionFilter) {
		Map<String, Object> result = new HashMap<String, Object>();
		for (Entry<String, Map<String, Object>> entry : _map.entrySet()) {
		  if (aBagConditionFilter.run(entry.getValue().get("type").toString())) 
			  result.put(entry.getKey(),  entry.getValue().get("value"));
				  
		}
		return result;
	}



	public Map filterOnType(Predicate<String> predicate) {
		Map<String, Object> result = new HashMap<String, Object>();
		for (Entry<String, Map<String, Object>> entry : _map.entrySet()) {
		  if (predicate.test(entry.getValue().get("type").toString())) 
			  result.put(entry.getKey(),  entry.getValue().get("value"));
				  
		}
		return result;
	}




	



	

}
