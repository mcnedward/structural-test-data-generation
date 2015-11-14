package com.advancedtopics.app.opt4j.fibonacci;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.advancedtopics.app.Target;
import com.advancedtopics.app.TargetGroup;
import com.advancedtopics.app.fibonacci.FibonacciSequence;
import com.advancedtopics.app.opt4j.BaseEvaluator;

public class FibonacciEvaluator extends BaseEvaluator<List<Long>> {

	public static Map<String, Target> TARGETS;

	@Override
	public List<TargetGroup> evaluate(List<Long> phenotype) {
		for (Long number : phenotype) {
			createTargets();

			long fib = FibonacciSequence.fib(number);

			calculateFitness();
			updateResults(fib, number);
			// printFitness();
		}
		return targetGroups;
	}

	@Override
	protected void setup() {
		targetNames = new ArrayList<String>();
		targetNames.add("nLessThanOr0");
		targetNames.add("iMod2Not0");

		TARGETS = new HashMap<>();
	}

	@Override
	protected void updateResults(Object testObject, Object phenotype) {
		createTargetGroup("N Less Than or Equal to 0", testObject, phenotype, TARGETS.get("nLessThanOr0"));
		createTargetGroup("i Mod 2 Not 0", testObject, phenotype, TARGETS.get("iMod2Not0"));
	}

	@Override
	public Map<String, Target> getTargets() {
		return TARGETS;
	}
}
