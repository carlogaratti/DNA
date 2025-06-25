package com.carlogaratti.spike.swing;

import java.util.List;
import java.util.Map;

public abstract class Builder {

	public abstract  void add(String aCommand, List<String> operationsToExecute, Map<String, String> _commandOn, Map<String, Map<String,Object>> _windowElements);

}
