package com.advancedtopics.app.phonenumber;

import java.util.ArrayList;
import java.util.List;

import com.advancedtopics.app.TargetGroup;
import com.advancedtopics.app.phonenumber.opt4j.PhoneNumberCreator;
import com.advancedtopics.app.phonenumber.opt4j.PhoneNumberDecoder;
import com.advancedtopics.app.phonenumber.opt4j.PhoneNumberEvaluator;

/**
 * @author Edward McNealy <edwardmcn64@gmail.com> - Nov 13, 2015
 *
 */
public class PhoneNumberTest {

	private static List<TargetGroup> targetGroups;
	private static int finishedIteration = 1;

	public static void main(String[] args) {
		evaluatePhoneNumber();
	}

	private static void evaluatePhoneNumber() {
		for (int x = 0; x < 100; x++) {
			PhoneNumberCreator creator = new PhoneNumberCreator(100);
			PhoneNumberDecoder decoder = new PhoneNumberDecoder();
			PhoneNumberEvaluator evaluator = new PhoneNumberEvaluator();

			List<TargetGroup> evaluatedGroups = evaluator.evaluate(decoder.decode(creator.create()));
			addTargetGroups(evaluatedGroups);
			finishedIteration = x++;
			if (checkForCompleteTargets()) {
				break;
			}
		}
		System.out.println("\n\n");
		printTargetGroups();
		if (!checkForCompleteTargets()) {
			System.out.println("All targets where not hit within " + finishedIteration + "...");
			System.out.println("Running again.");
			evaluatePhoneNumber();
		} else
			System.out.println("All targets hit within " + finishedIteration + " iterations.");
	}

	private static boolean checkForCompleteTargets() {
		for (TargetGroup group : targetGroups) {
			if (!group.isComplete())
				return false;
		}
		return true;
	}

	private static void addTargetGroups(List<TargetGroup> evaluatedGroups) {
		if (targetGroups == null) {
			targetGroups = new ArrayList<>();
			for (TargetGroup group : evaluatedGroups) {
				targetGroups.add(group);
			}
		} else {
			for (TargetGroup group : evaluatedGroups) {
				if (group.isComplete()) {
					replaceTargetGroup(group);
				}
			}
		}
	}

	private static void replaceTargetGroup(TargetGroup replacement) {
		for (TargetGroup g : targetGroups) {
			if (g.getTargetGroupName().equals(replacement.getTargetGroupName())) {
				targetGroups.remove(g);
				targetGroups.add(replacement);
				return;
			}
		}
	}

	private static void printTargetGroups() {
		System.out.println("Number of target groups: [" + targetGroups.size() + "]");
		for (TargetGroup targetGroup : targetGroups) {
			System.out.println("**********");
			System.out.println(targetGroup);
			System.out.println("**********");
		}
	}

}
