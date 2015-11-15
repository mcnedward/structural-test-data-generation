package com.advancedtopics.app;

/**
 * A Condition is a representation of a boolean value.
 * <p>
 * This consists of the type of condition (e.g. true or false, number comparison...), the operator type (e.g. greater
 * than, less than, equal to...), the first and second values to compare, a percentage that is used to determine the
 * chances of the condition passing, and a boolean to tell if the condition has passed.
 * </p>
 * 
 * @author Edward McNealy <edwardmcn64@gmail.com> - Nov 11, 2015
 *
 */
public class Condition implements ICondition {

	private static int PENALTY = 4;

	public enum ConditionType {
		TRUE_FALSE, NUMERIC
	}

	public enum Operator {
		GREATER_THAN, GREATER_THAN_OR_EQUAL, LESS_THAN, LESS_THAN_OR_EQUAL, EQUALS, NOT_EQUALS
	}

	private String name;
	private ConditionType conditionType;
	private Operator operator;
	private double a;
	private double b;
	private int percentage;
	private boolean passed;

	public Condition() {
	}

	public Condition(Operator operator, double a, double b, boolean passed) {
		this();
		this.conditionType = ConditionType.NUMERIC;
		this.operator = operator;
		this.a = a;
		this.b = b;
		this.passed = passed;
	}

	public Condition(int percentage, boolean passed) {
		this();
		this.percentage = percentage;
		this.passed = passed;
	}

	public Condition(Condition condition, boolean switchCondition) {
		Operator operator;
		if (switchCondition)
			operator = switchCondition(condition.operator);
		else
			operator = condition.operator;
		this.conditionType = condition.conditionType;
		this.operator = operator;
		this.a = condition.a;
		this.b = condition.b;
		if (switchCondition)
			this.passed = !condition.passed;
		else
			this.passed = condition.passed;
	}

	private Operator switchCondition(Operator operatorType) {
		switch (operatorType) {
		case EQUALS:
			return Operator.NOT_EQUALS;
		case LESS_THAN:
			return Operator.GREATER_THAN;
		case GREATER_THAN_OR_EQUAL:
			return Operator.LESS_THAN_OR_EQUAL;
		case GREATER_THAN:
			return Operator.LESS_THAN;
		case LESS_THAN_OR_EQUAL:
			return Operator.GREATER_THAN_OR_EQUAL;
		case NOT_EQUALS:
			return Operator.EQUALS;
		default:
			return null;
		}
	}

	public double calculateBranchDistance() {
		if (conditionType == ConditionType.NUMERIC) {
			return calculateNumeric();
		}
		if (conditionType == ConditionType.TRUE_FALSE) {
			return calculateTrueFalse();
		}
		return 0;
	}

	/**
	 * Calculate the branch distance for a condition.
	 * <p>
	 * 
	 * @return The branch distance.
	 * @see Source: <a href=
	 *      "http://ieeexplore.ieee.org/xpl/login.jsp?tp=&arnumber=732680&url=http%3A%2F%2Fieeexplore.ieee.org%2Fstamp%2Fstamp.jsp%3Ftp%26arnumber%3D732680">
	 *      An automated framework for structural test-data generation</a>
	 * 
	 */
	private double calculateNumeric() {
		if (operator == Operator.GREATER_THAN) {
			if ((b - a) < 0)
				return 0;
			else
				return b - a + PENALTY;
		}
		if (operator == Operator.GREATER_THAN_OR_EQUAL) {
			if ((b - a) <= 0)
				return 0;
			else
				return b - a + PENALTY;
		}
		if (operator == Operator.LESS_THAN) {
			if ((a - b) < 0)
				return 0;
			else
				return a - b + PENALTY;
		}
		if (operator == Operator.LESS_THAN_OR_EQUAL) {
			if ((a - b) <= 0)
				return 0;
			else
				return a - b + PENALTY;
		}
		if (operator == Operator.EQUALS) {
			if (Math.abs(a - b) == 0)
				return 0;
			else
				return Math.abs(a - b) + PENALTY;
		}
		if (operator == Operator.NOT_EQUALS) {
			if (Math.abs(a - b) != 0)
				return 0;
			else
				return Math.abs(a - b) + PENALTY;
		}
		return 0;
	}

	/**
	 * Calculate the branch distance for a true or false condition. The percentage is determined by the odds of the
	 * condition being true, for example if comparing an enum with 3 values, the odds of the desired enum being hit are
	 * 33%.
	 * 
	 * @param percentage
	 *            The odds of the condition being true.
	 * @return The branch distance for this condition.
	 */
	private double calculateTrueFalse() {
		return percentage + PENALTY;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the conditionType
	 */
	public ConditionType getConditionType() {
		return conditionType;
	}

	/**
	 * @param conditionType
	 *            the conditionType to set
	 */
	public void setConditionType(ConditionType conditionType) {
		this.conditionType = conditionType;
	}

	/**
	 * @return the operator
	 */
	public Operator getOperator() {
		return operator;
	}

	/**
	 * @param operator
	 *            the operator to set
	 */
	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	/**
	 * @return the value
	 */
	public double getValue() {
		return a;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(double value) {
		this.a = value;
	}

	/**
	 * @return the goal
	 */
	public double getGoal() {
		return b;
	}

	/**
	 * @param goal
	 *            the goal to set
	 */
	public void setGoal(double goal) {
		this.b = goal;
	}

	/**
	 * @return the passed
	 */
	public boolean isPassed() {
		return passed;
	}

	/**
	 * @param passed
	 *            the passed to set
	 */
	public void setPassed(boolean passed) {
		this.passed = passed;
	}

}
