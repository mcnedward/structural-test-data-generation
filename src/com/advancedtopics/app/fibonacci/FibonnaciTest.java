package com.advancedtopics.app.fibonacci;

import java.util.List;

import com.advancedtopics.app.TargetGroup;
import com.advancedtopics.app.opt4j.BaseTest;
import com.advancedtopics.app.opt4j.fibonacci.FibonaciCreator;
import com.advancedtopics.app.opt4j.fibonacci.FibonnaciDecoder;
import com.advancedtopics.app.opt4j.fibonacci.FibonnaciEvaluator;

/**
 * @author Edward McNealy <edwardmcn64@gmail.com> - Nov 13, 2015
 *
 */
public class FibonnaciTest extends BaseTest {

	@Override
	public void evaluate() {
		System.out.println("******************** Test for Fibonnaci Sequence ********************");
		
		for (int x = 0; x < 100; x++) {
			FibonaciCreator creator = new FibonaciCreator(100);
			FibonnaciDecoder decoder = new FibonnaciDecoder();
			FibonnaciEvaluator evaluator = new FibonnaciEvaluator();

			List<TargetGroup> evaluatedGroups = evaluator.evaluate(decoder.decode(creator.create()));
			addTargetGroups(evaluatedGroups);
			finishedIteration++;
			if (checkForCompleteTargets()) {
				break;
			}
		}
		// printTargetGroups();
		if (!checkForCompleteTargets()) {
			System.out.println("All targets where not hit within " + finishedIteration + "...");
			System.out.println("Running again.");
			evaluate();
		} else {
			System.out.println("All targets hit within " + finishedIteration + " iterations.\n********************");
			System.out.println("********************");
		}
	}

}
