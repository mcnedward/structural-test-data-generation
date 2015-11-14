package com.advancedtopics.app.quadratic;

import java.util.ArrayList;
import java.util.List;

import com.advancedtopics.app.TargetGroup;
import com.advancedtopics.app.opt4j.BaseTest;
import com.advancedtopics.app.opt4j.quadratic.QuadCreator;
import com.advancedtopics.app.opt4j.quadratic.QuadDecoder;
import com.advancedtopics.app.opt4j.quadratic.QuadEvaluator;

public class QuadTest extends BaseTest {
	
	private List<TargetGroup> targetGroups;

	@Override
	public void evaluate() {
		System.out.println("******************** Test for Quadratic Equations ********************");
		targetGroups = new ArrayList<>();
		for (int x = 0; x < 100; x++) {
			QuadCreator creator = new QuadCreator(100);
			QuadDecoder decoder = new QuadDecoder();
			QuadEvaluator evaluator = new QuadEvaluator();

			List<TargetGroup> evaluatedGroups = evaluator.evaluate(decoder.decode(creator.create()));
			targetGroups.addAll(evaluatedGroups);
			addTargetGroups(evaluatedGroups);
			finishedIteration++;
		}
		// printTargetGroups();
		if (!checkForCompleteTargets()) {
			System.out.println("All targets where not hit within " + finishedIteration + "...");
			System.out.println("Running again.");
			evaluate();
		} else {
			System.out.println("All targets hit within " + finishedIteration + " iterations.");
			findFitness(targetGroups);
			System.out.println("********************");
		}
		
	}

}
