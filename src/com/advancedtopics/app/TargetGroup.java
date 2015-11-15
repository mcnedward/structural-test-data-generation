package com.advancedtopics.app;

import java.util.ArrayList;
import java.util.List;

import com.advancedtopics.app.phonenumber.PhoneNumber;

/**
 * A TargetGroup is a collection of all of the {@link Target}s that are needed to be hit in order for a certain branch
 * or condition to be reached.
 * <p>
 * They contain a list of all the targets required, a boolean to determine if the targets were complete, and a
 * {@link PhoneNumber} once the targets are complete.
 * </p>
 * 
 * @author Edward McNealy <edwardmcn64@gmail.com> - Nov 13, 2015
 *
 */
public class TargetGroup {

	private String targetGroupName;
	private List<Target> targetsRequired;
	private boolean complete = false;
	private Object testObject;
	private Object input;

	public TargetGroup(String targetGroupName) {
		targetsRequired = new ArrayList<Target>();
		this.targetGroupName = targetGroupName;
	}

	public void addTarget(Target target) {
		targetsRequired.add(target);
	}

	/**
	 * Iterate through each of the {@link Target}s, and check if they were hit. If all targets have been hit, then this
	 * group is complete.
	 * 
	 * @return True if all targets have been hit, false otherwise.
	 */
	public boolean isComplete() {
		if (complete)
			return complete;
		boolean targetFailed = true;
		for (Target target : targetsRequired) {
			if (!target.isTargetHit()) {
				targetFailed = false;
				break;
			}
		}
		return targetFailed;
	}
	
	public double getFitness() {
		double fitness = 0;
		for (Target group : targetsRequired)
			fitness += group.getFitness();
		return fitness;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	/**
	 * @return the targetGroupName
	 */
	public String getTargetGroupName() {
		return targetGroupName;
	}

	/**
	 * @return the targetsRequired
	 */
	public List<Target> getTargetsRequired() {
		return targetsRequired;
	}

	/**
	 * @return the testObject
	 */
	public Object getTestObject() {
		return testObject;
	}

	/**
	 * @param testObject
	 *            the testObject to set
	 */
	public void setTestObject(Object testObject) {
		this.testObject = testObject;
	}

	/**
	 * @return the input
	 */
	public Object getInput() {
		return input;
	}

	/**
	 * @param input the input to set
	 */
	public void setInput(Object input) {
		this.input = input;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Target Group for " + targetGroupName + " [" + targetsRequired.size() + "] Complete? " + isComplete());
		builder.append("\nInput: " + input);
		builder.append("\nResult: " + testObject);
		for (Target target : targetsRequired)
			builder.append("\n***\n" + target + "\n***");
		return builder.toString();
	}
}
