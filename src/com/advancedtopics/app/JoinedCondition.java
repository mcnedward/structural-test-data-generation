package com.advancedtopics.app;

/**
 * @author Edward McNealy <edwardmcn64@gmail.com> - Nov 11, 2015
 *
 */
public class JoinedCondition {

	private Condition conditionA;
	private Condition conditionB;

	/**
	 * @param conditionA
	 * @param conditionB
	 */
	public JoinedCondition(Condition conditionA, Condition conditionB) {
		this.conditionA = conditionA;
		this.conditionB = conditionB;
	}

	public double calculateBranchDistance() {
		double branchDistance = 0;
		branchDistance += conditionA.calculateBranchDistance();
		branchDistance += conditionB.calculateBranchDistance();
		return branchDistance;
	}

	/**
	 * @return the conditionA
	 */
	public Condition getConditionA() {
		return conditionA;
	}

	/**
	 * @param conditionA
	 *            the conditionA to set
	 */
	public void setConditionA(Condition conditionA) {
		this.conditionA = conditionA;
	}

	/**
	 * @return the conditionB
	 */
	public Condition getConditionB() {
		return conditionB;
	}

	/**
	 * @param conditionB
	 *            the conditionB to set
	 */
	public void setConditionB(Condition conditionB) {
		this.conditionB = conditionB;
	}

}
