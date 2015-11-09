package com.advancedtopics.app.opt4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.opt4j.core.Objective.Sign;
import org.opt4j.core.Objectives;
import org.opt4j.core.problem.Evaluator;

import com.advancedtopics.app.QuadHolder;
import com.advancedtopics.app.QuadraticRoots;
import com.google.inject.Inject;

public class QuadEvaluator implements Evaluator<List<QuadHolder>> {

	QuadraticRoots quadRoots;

	private static int PENALTY = 4;

	@Inject
	public QuadEvaluator() {
		quadRoots = new QuadraticRoots();
	}

	@Override
	public Objectives evaluate(List<QuadHolder> phenotype) {
		Objectives objectives = new Objectives();
		List<QuadHolder> results = new ArrayList<QuadHolder>();
		
		double fitnessD = 0, fitnessB = 0, fitnessO = 0;
		for (QuadHolder holder : phenotype) {
			Map<String, Object> map = quadRoots.findQuadraticRoots(holder);
			String target = map.get("target").toString();
			
//			double branchDistanceD = calculateBranchDistanceForDiscriminantLessThan0(target, holder);
//			double approachLevelD = calculateApproachLevelForDiscriminantLessThan0();
//			fitnessD = calculateFitnessFunction(approachLevelD, branchDistanceD);
//			System.out.println("Fitness D: " + fitnessD);
//			if (fitnessD == 0) {
//				results.add(holder);
//			}
			
//			double branchDistanceB = calculateBranchDistanceForBLessThan0(target, holder);
//			double approachLevelB = calculateApproachLevelForBLessThan0(target);
//			fitnessB = calculateFitnessFunction(approachLevelB, branchDistanceB);
//			System.out.println("Fitness B: " + fitnessB);
//			if (fitnessB == 0) {
//				results.add(holder);
//			}
//			
			double branchDistanceO = calculateBranchDistanceForOther(target, holder);
			double approachLevelO = calculateApproachLevelForOther(target);
			fitnessO = calculateFitnessFunction(approachLevelO, branchDistanceO);
			System.out.println("Fitness O: " + fitnessO);
			if (fitnessO == 0) {
				results.add(holder);
			}
		}

//		objectives.add("Same X Values", Sign.MIN, sameX);
//		objectives.add("Different X Values", Sign.MIN, differentX);
//		objectives.add("Discriminant Less Than 0", Sign.MIN, discriminantLessThan0);
//		objectives.add("B Less Than 0", Sign.MIN, bLessThan0);
//		objectives.add("Other", Sign.MIN, other);
		
		objectives.add("discriminantLessThan0", Sign.MIN, fitnessD);
		objectives.add("bLessThan0", Sign.MIN, fitnessB);
		objectives.add("Other", Sign.MIN, fitnessO);

		return objectives;
	}
	
	private double calculateFitnessFunction(double approachLevel, double branchDistance) {
		double normalisedBranchDistance = 1 - Math.pow(1.001, (branchDistance * -1));
		double fitness = approachLevel + normalisedBranchDistance;
		return fitness;
	}

	private double calculateBranchDistanceForDiscriminantLessThan0(String target,  QuadHolder holder) {
		double branchDistance = 0;
		if (!target.equals("discriminantLessThan0")) {
			double discriminant = QuadraticRoots.findDiscriminant(holder.getA(), holder.getB(), holder.getC());
			branchDistance = calculateBranchDistance(discriminant, 0);
		}
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
