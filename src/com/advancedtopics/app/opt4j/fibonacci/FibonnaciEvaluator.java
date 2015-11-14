package com.advancedtopics.app.opt4j.fibonacci;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.advancedtopics.app.Target;
import com.advancedtopics.app.TargetGroup;
import com.advancedtopics.app.fibonacci.FibonnaciSequence;
import com.advancedtopics.app.opt4j.BaseEvaluator;

public class FibonnaciEvaluator extends BaseEvaluator<List<Long>> {

	public static Map<String, Target> TARGETS = new HashMap<>();

	@Override
	public List<TargetGroup> evaluate(List<Long> phenotype) {
		for (Long number : phenotype) {
			createTargets();
			FibonnaciSequence.fib(number);

			calculateFitness();
			setTargetGroups();
		}
		return targetGroups;
	}

	@Override
	protected void setTargetNames() {
		targetNames = new ArrayList<String>();
		targetNames.add("nLessThanOr0");
		targetNames.add("iMod2Not0");
	}

	@Override
	protected void setTargetGroups() {
		createTargetGroup("N Less Than or Equal to 0", TARGETS.get("nLessThanOr0"));
		createTargetGroup("i Modulus 2 is not 0", TARGETS.get("iMod2Not0"));
	}

	@Override
	public Map<String, Target> getTargets() {
		return TARGETS;
	}
}
