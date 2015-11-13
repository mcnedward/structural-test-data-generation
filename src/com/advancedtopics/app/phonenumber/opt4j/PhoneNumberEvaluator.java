package com.advancedtopics.app.phonenumber.opt4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.advancedtopics.app.Target;
import com.advancedtopics.app.TargetGroup;
import com.advancedtopics.app.phonenumber.PhoneNumberChecker;
import com.advancedtopics.app.phonenumber.ValidNumber;
import com.google.inject.Inject;

public class PhoneNumberEvaluator {

	public static Map<String, Target> TARGETS;
	private Map<String, Target> targetsHit;
	private List<String> targetNames;
	private List<TargetGroup> targetGroups;

	@Inject
	public PhoneNumberEvaluator() {
	}

	public List<TargetGroup> evaluate(String phenotype) {
		createTargets();

		ValidNumber validNumber = PhoneNumberChecker.checkPhoneNumber(phenotype);
		// PhoneNumberChecker.printResults(validNumber);

		calculateFitness();
		// printTargetsHit(validNumber);
		setTargetGroups();
		// System.out.println();
		// printTargetGroups();

		return targetGroups;
	}

	private void createTargets() {
		TARGETS = new HashMap<>();
		targetsHit = new HashMap<>();
		targetNames = new ArrayList<>();
		targetGroups = new ArrayList<>();

		targetNames.add("ukCountryCode");
		targetNames.add("usCountryCode");
		targetNames.add("noCountryCode");
		targetNames.add("areaCode1");
		targetNames.add("usForAreaCode1");
		targetNames.add("ukForAreaCode1");
		targetNames.add("areaCode2");
		targetNames.add("usForAreaCode2");
		targetNames.add("ukForAreaCode2");
		targetNames.add("areaCode3");
		targetNames.add("usForAreaCode3");
		targetNames.add("ukForAreaCode3");
		targetNames.add("checkNext3");
		targetNames.add("checkLast4");

		for (String targetName : targetNames) {
			Target target = new Target(targetName);
			TARGETS.put(targetName, target);
		}
	}

	private void setTargetGroups() {
		// Everything passed for area code 1
		createTargetGroup("Everything Passed for UK", TARGETS.get("ukCountryCode"), TARGETS.get("areaCode1"), TARGETS.get("ukForAreaCode1"),
				TARGETS.get("checkNext3"), TARGETS.get("checkLast4"));
		createTargetGroup("Everything Passed for US", TARGETS.get("usCountryCode"), TARGETS.get("areaCode1"), TARGETS.get("usForAreaCode1"),
				TARGETS.get("checkNext3"), TARGETS.get("checkLast4"));
		// Everything passed for area code 2
		createTargetGroup("Everything Passed for UK", TARGETS.get("ukCountryCode"), TARGETS.get("areaCode2"), TARGETS.get("ukForAreaCode2"),
				TARGETS.get("checkNext3"), TARGETS.get("checkLast4"));
		createTargetGroup("Everything Passed for US", TARGETS.get("usCountryCode"), TARGETS.get("areaCode2"), TARGETS.get("usForAreaCode2"),
				TARGETS.get("checkNext3"), TARGETS.get("checkLast4"));
		// Everything passed for area code 3
		createTargetGroup("Everything Passed for UK", TARGETS.get("ukCountryCode"), TARGETS.get("areaCode3"), TARGETS.get("ukForAreaCode3"),
				TARGETS.get("checkNext3"), TARGETS.get("checkLast4"));
		createTargetGroup("Everything Passed for US", TARGETS.get("usCountryCode"), TARGETS.get("areaCode3"), TARGETS.get("usForAreaCode3"),
				TARGETS.get("checkNext3"), TARGETS.get("checkLast4"));

		// Missing middle 3 digits for area code 1
		createTargetGroup("Everything Passed for UK", TARGETS.get("ukCountryCode"), TARGETS.get("areaCode1"), TARGETS.get("ukForAreaCode1"));
		createTargetGroup("Everything Passed for US", TARGETS.get("usCountryCode"), TARGETS.get("areaCode1"), TARGETS.get("usForAreaCode1"));
		// Missing middle 3 digits for area code 2
		createTargetGroup("Everything Passed for UK", TARGETS.get("ukCountryCode"), TARGETS.get("areaCode2"), TARGETS.get("ukForAreaCode2"));
		createTargetGroup("Everything Passed for US", TARGETS.get("usCountryCode"), TARGETS.get("areaCode2"), TARGETS.get("usForAreaCode2"));
		// Missing middle 3 digits for area code 3
		createTargetGroup("Everything Passed for UK", TARGETS.get("ukCountryCode"), TARGETS.get("areaCode3"), TARGETS.get("ukForAreaCode3"));
		createTargetGroup("Everything Passed for US", TARGETS.get("usCountryCode"), TARGETS.get("areaCode3"), TARGETS.get("usForAreaCode3"));

		// Missing last 4 digits for area code 1
		createTargetGroup("Everything Passed for UK", TARGETS.get("ukCountryCode"), TARGETS.get("areaCode1"), TARGETS.get("ukForAreaCode1"),
				TARGETS.get("checkNext3"));
		createTargetGroup("Everything Passed for US", TARGETS.get("usCountryCode"), TARGETS.get("areaCode1"), TARGETS.get("usForAreaCode1"),
				TARGETS.get("checkNext3"));
		// Missing last 4 digits for area code 2
		createTargetGroup("Everything Passed for UK", TARGETS.get("ukCountryCode"), TARGETS.get("areaCode2"), TARGETS.get("ukForAreaCode2"),
				TARGETS.get("checkNext3"));
		createTargetGroup("Everything Passed for US", TARGETS.get("usCountryCode"), TARGETS.get("areaCode2"), TARGETS.get("usForAreaCode2"),
				TARGETS.get("checkNext3"));
		// Missing last 4 digits for area code 3
		createTargetGroup("Everything Passed for UK", TARGETS.get("ukCountryCode"), TARGETS.get("areaCode3"), TARGETS.get("ukForAreaCode3"),
				TARGETS.get("checkNext3"));
		createTargetGroup("Everything Passed for US", TARGETS.get("usCountryCode"), TARGETS.get("areaCode3"), TARGETS.get("usForAreaCode3"),
				TARGETS.get("checkNext3"));

		// Country code
		createTargetGroup("Only UK Country Code", TARGETS.get("ukCountryCode"));
		createTargetGroup("Only US Country Code", TARGETS.get("usCountryCode"));
		createTargetGroup("No Country Code", TARGETS.get("noCountryCode"));
	}

	private void createTargetGroup(String targetGroupName, Target... targets) {
		TargetGroup targetGroup = new TargetGroup(targetGroupName);
		for (Target target : targets)
			targetGroup.addTarget(target);
		targetGroups.add(targetGroup);
	}

	private void calculateFitness() {
		for (String targetName : targetNames) {
			Target target = TARGETS.get(targetName);
			double branchDistance = target.getBranchDistance();
			double approachDistance = target.getApproachDistance();
			double normalisedBranchDistance = 1 - Math.pow(1.001, (branchDistance * -1));
			double fitness = approachDistance + normalisedBranchDistance;
			target.setFitness(fitness);
		}
	}

	private void findHitTarget(ValidNumber validNumber) {
		for (String targetName : targetNames) {
			Target target = TARGETS.get(targetName);
			if (target.isTargetHit())
				targetsHit.put(targetName, target);
		}
	}

	private void printTargets() {
		for (String targetName : targetNames) {
			Target target = TARGETS.get(targetName);
			System.out.println(target.toString() + " Fitness: " + target.getFitness());
			System.out.println("Target Hit? " + target.isTargetHit());
		}
	}

	private void printTargetsHit(ValidNumber number) {
		for (String targetName : targetNames) {
			if (TARGETS.get(targetName).isTargetHit()) {
				System.out.println("**********");
				System.out.println("Phone Number: " + number + " was hit by target: " + targetName);
				System.out.println("**********");
			}
		}
	}

	private void printTargetGroups() {
		for (TargetGroup targetGroup : targetGroups) {
			System.out.println("**********");
			System.out.println(targetGroup);
			System.out.println("**********");
		}
	}
}
