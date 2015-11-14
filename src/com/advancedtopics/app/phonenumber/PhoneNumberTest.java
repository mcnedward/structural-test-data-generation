package com.advancedtopics.app.phonenumber;

import java.util.ArrayList;
import java.util.List;

import com.advancedtopics.app.TargetGroup;
import com.advancedtopics.app.opt4j.BaseTest;
import com.advancedtopics.app.opt4j.phonenumber.PhoneNumberCreator;
import com.advancedtopics.app.opt4j.phonenumber.PhoneNumberDecoder;
import com.advancedtopics.app.opt4j.phonenumber.PhoneNumberEvaluator;

/**
 * @author Edward McNealy <edwardmcn64@gmail.com> - Nov 13, 2015
 *
 */
public class PhoneNumberTest extends BaseTest {

	private List<TargetGroup> targetGroups;
	
	@Override
	public void evaluate() {
		System.out.println("******************** Test for Phone Number Validation ********************");
		targetGroups = new ArrayList<>();
		for (int x = 0; x < 100; x++) {
			PhoneNumberCreator creator = new PhoneNumberCreator();
			PhoneNumberDecoder decoder = new PhoneNumberDecoder();
			PhoneNumberEvaluator evaluator = new PhoneNumberEvaluator();

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
