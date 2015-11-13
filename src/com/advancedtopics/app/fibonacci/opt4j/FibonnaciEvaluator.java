package com.advancedtopics.app.fibonacci.opt4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.opt4j.core.Objectives;
import org.opt4j.core.problem.Evaluator;

import com.advancedtopics.app.Target;
import com.advancedtopics.app.fibonacci.FibonnaciSequence;
import com.advancedtopics.app.quadratic.QuadHolder;
import com.advancedtopics.app.quadratic.QuadraticRoots;
import com.google.inject.Inject;

public class FibonnaciEvaluator implements Evaluator<List<Long>> {

	QuadraticRoots quadRoots;

	private static int PENALTY = 4;

	@Inject
	public FibonnaciEvaluator() {
		quadRoots = new QuadraticRoots();
	}

	public static Map<String, Target> TARGETS;
	private Map<String, Long> numberHit;
	private List<String> targetNames;

	private void createTargets() {
		TARGETS = new HashMap<String, Target>();
		numberHit = new HashMap<String, Long>();
		
		targetNames = new ArrayList<String>();
		targetNames.add("nLessThanOr0");
		targetNames.add("iMod2Not0");

		for (String targetName : targetNames) {
			Target target = new Target(targetName);
			TARGETS.put(targetName, target);
		}
	}

	private void resetTargets() {
		for (String targetName : targetNames) {
			TARGETS.get(targetName).reset();
		}
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

	private void findHitTarget(Long number) {
		for (String targetName : targetNames) {
			Target target = TARGETS.get(targetName);
			if (target.isTargetHit())
				numberHit.put(targetName, number);
		}
	}

	private void printTargets() {
		for (String targetName : targetNames) {
			Target target = TARGETS.get(targetName);
			System.out.println(target.toString() + " Fitness: " + target.getFitness());
			System.out.println("Target Hit? " + target.isTargetHit());
		}
	}

	private void printTargetsHit() {
		for (String targetName : targetNames) {
			if (TARGETS.get(targetName).isTargetHit()) {
				Long hit = numberHit.get(targetName);
				System.out.println("**********");
				System.out.println("Fibonnaci Numbers: " + hit + " was hit by target: " + targetName);
				System.out.println("**********");
			}
		}
	}

	@Override
	public Objectives evaluate(List<Long> phenotype) {
		Objectives objectives = new Objectives();

		createTargets();

		for (Long number : phenotype) {
			// System.out.println("***** " + holder + " *****");

			resetTargets();
			FibonnaciSequence.fib(number);

			calculateFitness();
			// printTargets();
			findHitTarget(number);
			printTargetsHit();
		}

		// System.out.println("\n********** Number of Quadratric Equations Ran: [" + phenotype.size() + "] **********");
		// System.out.println("\n********** Target - Discriminant Less Than 0: [" + targetD.size() + "] **********");
		// for (QuadHolder holder : targetD)
		// System.out.println(holder);
		// System.out.println("\n********** Target - B Less Than 0: [" + targetB.size() + "] **********");
		// for (QuadHolder holder : targetB)
		// System.out.println(holder);
		// System.out.println("\n********** Target - Other: [" + targetO.size() + "] **********");
		// for (QuadHolder holder : targetO)
		// System.out.println(holder);

//		objectives.add("Target D", Sign.MAX, fitD);
//		objectives.add("Discriminant", Sign.MIN, dis);
//		objectives.add("Target B", Sign.MAX, fitB);
//		objectives.add("B", Sign.MIN, b);
//		objectives.add("Target O", Sign.MAX, fitO);
//		objectives.add("O", Sign.MIN, o);
		return objectives;
	}

	private double calculateFitnessFunction(double approachLevel, double branchDistance) {
		double normalisedBranchDistance = 1 - Math.pow(1.001, (branchDistance * -1));
		double fitness = approachLevel + normalisedBranchDistance;
		return fitness;
	}

	private double calculateBranchDistanceForDiscriminantLessThan0(String target, QuadHolder holder) {
		double branchDistance = 0;
		if (!target.equals("discriminantLessThan0")) {
			double discriminant = QuadraticRoots.findDiscriminant(holder.getA(), holder.getB(), holder.getC());
			branchDistance = calculateBranchDistance(discriminant, 0);
		}
		System.out.println(branchDistance);
		return branchDistance;
	}

	private double calculateApproachLevelForDiscriminantLessThan0() {
		return 0;
	}

	private double calculateBranchDistanceForBLessThan0(String target, QuadHolder holder) {
		double branchDistance = 0;
		if (!target.equals("bLessThan0")) {
			branchDistance = calculateBranchDistance(holder.getB(), 0);
		}
		return branchDistance;
	}

	private double calculateApproachLevelForBLessThan0(String target) {
		double approachLevel = 0;
		if (target.equals("discriminantLessThan0")) {
			approachLevel = 1;
		} else if (!target.equals("bLessThan0")) {
			approachLevel = 2;
		}
		return approachLevel;
	}

	private double calculateBranchDistanceForOther(String target, QuadHolder holder) {
		double branchDistance = 0;
		if (target.equals("discriminantLessThan0")) {
			double discriminant = QuadraticRoots.findDiscriminant(holder.getA(), holder.getB(), holder.getC());
			branchDistance = calculateBranchDistance(0, discriminant);
		} else if (target.equals("bLessThan0")) {
			branchDistance = calculateBranchDistance(0, holder.getB());
		}
		return branchDistance;
	}

	private double calculateApproachLevelForOther(String target) {
		double approachLevel = 0;
		if (target.equals("discriminantLessThan0")) {
			approachLevel = 2;
		} else if (target.equals("bLessThan0")) {
			approachLevel = 1;
		}
		return approachLevel;
	}

	private double calculateBranchDistance(double a, double b) {
		if ((a - b) < 0)
			return 0;
		else
			return (a - b) + PENALTY;
	}
}
