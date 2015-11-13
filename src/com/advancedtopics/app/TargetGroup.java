package com.advancedtopics.app;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Edward McNealy <edwardmcn64@gmail.com> - Nov 13, 2015
 *
 */
public class TargetGroup {

	private String targetGroupName;
	private List<Target> targetsRequired;
	private boolean complete = false;

	public TargetGroup(String targetGroupName) {
		targetsRequired = new ArrayList<Target>();
		this.targetGroupName = targetGroupName;
	}

	public void addTarget(Target target) {
		targetsRequired.add(target);
	}

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
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Target Group for " + targetGroupName + " [" + targetsRequired.size() + "] Complete? " + isComplete());
		for (Target target : targetsRequired)
			builder.append("\n" + target + " Hit? " + target.isTargetHit());
		return builder.toString();
	}
}
