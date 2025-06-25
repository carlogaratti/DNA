package com.carlogaratti.dna.command.condition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CompositeAllTRUECondition extends CompositeCondition {
	@Override
	public String evaluate() {
		String result = "TRUE";
		List<String> resultEvaluation = new ArrayList<>();
		for (Condition each : _conditions) {
			resultEvaluation.add(each.evaluate());
		}
		if (resultEvaluation.contains("FALSE"))  result = "FALSE";
		return result;
	}
}
