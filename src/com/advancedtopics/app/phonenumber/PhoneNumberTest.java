package com.advancedtopics.app.phonenumber;

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

	@Override
	public void evaluate() {
		System.out.println("******************** Test for Phone Number Validation ********************");

		for (int x = 0; x < 100; x++) {
			PhoneNumberCreator creator = new PhoneNumberCreator();
			PhoneNumberDecoder decoder = new PhoneNumberDecoder();
			PhoneNumberEvaluator evaluator = new PhoneNumberEvaluator();

			List<TargetGroup> evaluatedGroups = evaluator.evaluate(decoder.decode(creator.create()));
			addTargetGroups(evaluatedGroups);
			finishedIteration = x++;
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
			System.out.println("All targets hit within " + finishedIteration + " iterations.");
			System.out.println("********************");
		}
	}
}
