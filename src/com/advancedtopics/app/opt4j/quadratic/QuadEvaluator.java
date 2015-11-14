package com.advancedtopics.app.opt4j.quadratic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.advancedtopics.app.Target;
import com.advancedtopics.app.TargetGroup;
import com.advancedtopics.app.opt4j.BaseEvaluator;
import com.advancedtopics.app.quadratic.QuadHolder;
import com.advancedtopics.app.quadratic.QuadraticRoots;

public class QuadEvaluator extends BaseEvaluator<List<QuadHolder>> {

	public static Map<String, Target> TARGETS = new HashMap<>();

	@Override
	public List<TargetGroup> evaluate(List<QuadHolder> phenotype) {
		for (QuadHolder holder : phenotype) {
			createTargets();
			QuadraticRoots.findQuadraticRoots(holder);

			calculateFitness();
			setTargetGroups();
		}
		return targetGroups;
	}

	@Override
	protected void setTargetNames() {
		targetNames.add("disriminantLessThan0");
		targetNames.add("bLessThan0");
		targetNames.add("other");
	}
	
	@Override
	protected void setTargetGroups() {
		createTargetGroup("Discriminant Less Than 0", TARGETS.get("disriminantLessThan0"));
		createTargetGroup("b Less than 0", TARGETS.get("bLessThan0"));
		createTargetGroup("Other", TARGETS.get("other"));
	}
	
	@Override
	public Map<String, Target> getTargets() {
		return TARGETS;
	}
}
