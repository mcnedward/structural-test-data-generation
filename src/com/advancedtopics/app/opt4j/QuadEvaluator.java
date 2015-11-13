package com.advancedtopics.app.opt4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.opt4j.core.Objectives;
import org.opt4j.core.problem.Evaluator;

import com.advancedtopics.app.QuadHolder;
import com.advancedtopics.app.QuadraticRoots;
import com.advancedtopics.app.Target;
import com.google.inject.Inject;

public class QuadEvaluator implements Evaluator<List<QuadHolder>> {

	QuadraticRoots quadRoots;

	private static int PENALTY = 4;

	@Inject
	public QuadEvaluator() {
		quadRoots = new QuadraticRoots();
	}

	public static Map<String, Target> TARGETS;
	private Map<String, QuadHolder> quadHit;
	private List<String> targetNames;

	private void createTargets() {
		TARGETS = new HashMap<String, Target>();
		quadHit = new HashMap<String, QuadHolder>();
		targetNames = new ArrayList<String>();
		targetNames.add("disriminantLessThan0");
		targetNames.add("bLessThan0");
		targetNames.add("other");

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

	private void findHitTarget(QuadHolder quadHolder) {
		for (String targetName : targetNames) {
			Target target = TARGETS.get(targetName);
			if (target.isTargetHit())
				quadHit.put(targetName, quadHolder);
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
				QuadHolder quad = quadHit.get(targetName);
				System.out.println("**********");
				System.out.println("Quadratic Equition: " + quad + " was hit by target: " + targetName);
				System.out.println("**********");
			}
		}
	}

	@Override
	public Objectives evaluate(List<QuadHolder> phenotype) {
		Objectives objectives = new Objectives();

		createTargets();

		for (QuadHolder holder : phenotype) {
			// System.out.println("***** " + holder + " *****");

			resetTargets();
			quadRoots.findQuadraticRoots(holder);

			calculateFitness();
			// printTargets();
			findHitTarget(holder);
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
