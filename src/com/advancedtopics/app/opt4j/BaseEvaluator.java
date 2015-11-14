package com.advancedtopics.app.opt4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.advancedtopics.app.Target;
import com.advancedtopics.app.TargetGroup;

/**
 * @author Edward McNealy <edwardmcn64@gmail.com> - Nov 14, 2015
 *
 */
public abstract class BaseEvaluator<T> {

	protected List<TargetGroup> targetGroups;
	protected List<String> targetNames;

	public BaseEvaluator() {
		createTargets();
	}

	protected abstract List<TargetGroup> evaluate(T phenotype);

	protected abstract void setup();
	
	protected abstract void updateResults(Object testObject, Object phenotype);
	
	public abstract Map<String, Target> getTargets();

	/**
	 * Create all of the {@link Target}s to hit. A list of all the target names is created, then a target is created for
	 * each target name.
	 */
	protected void createTargets() {
		targetGroups = new ArrayList<>();
		targetNames = new ArrayList<>();

		setup();

		for (String targetName : targetNames) {
			Target target = new Target(targetName);
			getTargets().put(targetName, target);
		}
	}

	/**
	 * Create a {@link TargetGroup} for all of the {@link Target}s that are given.
	 * 
	 * @param targetGroupName
	 *            The name of the TargetGroup.
	 * @param targets
	 *            All of the Targets that are required to be reached in order for this TargetGroup to be complete.
	 */
	protected void createTargetGroup(String targetGroupName, Object testObject, Object phenotype, Target... targets) {
		TargetGroup targetGroup = new TargetGroup(targetGroupName);
		for (Target target : targets) {
			targetGroup.addTarget(target);
			targetGroup.setTestObject(testObject);
			targetGroup.setInput(phenotype);
		}
		targetGroups.add(targetGroup);
	}

	/**
	 * Calculate the fitness value for every {@link Target}. This is found by first determining the normalized branch
	 * distance with the formula: 1 - 1.001^-b, where b is the branch distance. This normalized branch distance is then
	 * subtracted from the approach level, the result is the fitness value for that Target.
	 */
	protected void calculateFitness() {
		for (String targetName : targetNames) {
			Target target = getTargets().get(targetName);
			double branchDistance = target.getBranchDistance();
			double approachLevel = target.getApproachDistance();
			double normalisedBranchDistance = 1 - Math.pow(1.001, (branchDistance * -1));
			double fitness = approachLevel + normalisedBranchDistance;
			target.setFitness(fitness);
		}
	}
	
	public void printFitness() {
		System.out.println("***** Fitness Begin *****");
		for (String targetName : targetNames) {
			Target target = getTargets().get(targetName);
			System.out.println(target.toString() + " - Fitness: " + target.getFitness());
		}
		System.out.println("***** Fitness End *****");
	}
}
