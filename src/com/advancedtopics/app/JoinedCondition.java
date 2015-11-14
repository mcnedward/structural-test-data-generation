package com.advancedtopics.app;

/**
 * A JoinedCondition is a boolean representation of the comparison between two different conditions. These conditions
 * can be of type JoinedCondition or {@link Condition}.
 * 
 * @author Edward McNealy <edwardmcn64@gmail.com> - Nov 11, 2015
 *
 */
public class JoinedCondition implements ICondition {

	private ICondition conditionA;
	private ICondition conditionB;

	/**
	 * @param conditionA
	 * @param conditionB
	 */
	public JoinedCondition(ICondition conditionA, ICondition conditionB) {
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
	public ICondition getConditionA() {
		return conditionA;
	}

	/**
	 * @param conditionA
	 *            the conditionA to set
	 */
	public void setConditionA(ICondition conditionA) {
		this.conditionA = conditionA;
	}

	/**
	 * @return the conditionB
	 */
	public ICondition getConditionB() {
		return conditionB;
	}

	/**
	 * @param conditionB
	 *            the conditionB to set
	 */
	public void setConditionB(ICondition conditionB) {
		this.conditionB = conditionB;
	}

}
