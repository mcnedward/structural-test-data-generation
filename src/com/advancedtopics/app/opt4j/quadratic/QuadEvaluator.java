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
			String quadraticRoots = QuadraticRoots.findQuadraticRoots(holder);

			calculateFitness();
			updateResults(quadraticRoots, holder);
		}
		return targetGroups;
	}

	@Override
	protected void setup() {
		targetNames.add("disriminantLessThan0");
		targetNames.add("bLessThan0");
		targetNames.add("other");
	}

	@Override
	protected void updateResults(Object testObject, Object phenotype) {
		createTargetGroup("Discriminant Less Than 0", testObject, phenotype, TARGETS.get("disriminantLessThan0"));
		createTargetGroup("b Less than 0", testObject, phenotype, TARGETS.get("bLessThan0"));
		createTargetGroup("Other", testObject, phenotype, TARGETS.get("other"));
	}

	@Override
	public Map<String, Target> getTargets() {
		return TARGETS;
	}
}
