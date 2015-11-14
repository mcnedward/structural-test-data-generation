package com.advancedtopics.app.fibonacci;

import java.util.ArrayList;
import java.util.List;

import com.advancedtopics.app.TargetGroup;
import com.advancedtopics.app.opt4j.BaseTest;
import com.advancedtopics.app.opt4j.fibonacci.FibonacciDecoder;
import com.advancedtopics.app.opt4j.fibonacci.FibonacciEvaluator;
import com.advancedtopics.app.opt4j.fibonacci.FibonaciCreator;

/**
 * @author Edward McNealy <edwardmcn64@gmail.com> - Nov 13, 2015
 *
 */
public class FibonacciTest extends BaseTest {

	private List<TargetGroup> targetGroups;

	@Override
	public void evaluate() {
		System.out.println("******************** Test for Fibonacci Sequence ********************");
		targetGroups = new ArrayList<>();
		for (int x = 0; x < 100; x++) {
			FibonaciCreator creator = new FibonaciCreator(100);
			FibonacciDecoder decoder = new FibonacciDecoder();
			FibonacciEvaluator evaluator = new FibonacciEvaluator();

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
			System.out.println("All targets hit within " + finishedIteration + " iterations.\n********************");
			findFitness(targetGroups);
			System.out.println("********************");
		}
	}

}
