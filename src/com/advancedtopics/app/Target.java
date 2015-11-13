package com.advancedtopics.app;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Edward McNealy <edwardmcn64@gmail.com> - Nov 10, 2015
 *
 */
public class Target {

	private String targetName;
	private List<ICondition> conditions;
	private boolean targetHit;
	private int approachDistance;
	private double fitness;

	public Target() {
		conditions = new ArrayList<ICondition>();
		targetHit = false;
		approachDistance = 0;
	}

	public Target(String targetName) {
		this();
		this.targetName = targetName;
	}

	public Target(String targetName, Condition... conditions) {
		this();
		this.targetName = targetName;
		for (Condition condition : conditions) {
			this.conditions.add(condition);
		}
	}

	public void update(boolean targetHit, int approachDistance) {
		this.targetHit = targetHit;
		this.approachDistance = approachDistance;
	}

	public double getBranchDistance() {
		double branchDistance = 0;

		for (ICondition condition : conditions) {
			branchDistance += condition.calculateBranchDistance();
		}

		return branchDistance;
	}

	public void reset() {
		conditions = new ArrayList<ICondition>();
		targetHit = false;
	}
	
	public List<ICondition> getConditions() {
		return conditions;
	}

	public void addCondition(ICondition condition) {
		conditions.add(condition);
	}

	/**
	 * @return the targetName
	 */
	public String getTargetName() {
		return targetName;
	}

	/**
	 * @param targetName
	 *            the targetName to set
	 */
	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}

	/**
	 * @return the targetHit
	 */
	public boolean isTargetHit() {
		return targetHit;
	}

	/**
	 * @param targetHit
	 *            the targetHit to set
	 */
	public void setTargetHit(boolean targetHit) {
		this.targetHit = targetHit;
	}

	/**
	 * @return the approachDistance
	 */
	public int getApproachDistance() {
		return approachDistance;
	}

	/**
	 * @param approachDistance
	 *            the approachDistance to set
	 */
	public void setApproachDistance(int approachDistance) {
		this.approachDistance = approachDistance;
	}

	/**
	 * @return the fitness
	 */
	public double getFitness() {
		return fitness;
	}

	/**
	 * @param fitness
	 *            the fitness to set
	 */
	public void setFitness(double fitness) {
		this.fitness = fitness;
	}

	@Override
	public String toString() {
		return "Target: " + targetName;
	}
}
