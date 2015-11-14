package com.advancedtopics.app.opt4j;

import java.util.ArrayList;
import java.util.List;

import com.advancedtopics.app.TargetGroup;

/**
 * @author Edward McNealy <edwardmcn64@gmail.com> - Nov 14, 2015
 *
 */
public abstract class BaseTest {

	protected List<TargetGroup> targetGroups;
	protected int finishedIteration = 0;
	
	protected abstract void evaluate();
	
	public void resetTargetGroups() {
		targetGroups = null;
	}
	
	protected boolean checkForCompleteTargets() {
		for (TargetGroup group : targetGroups) {
			if (!group.isComplete())
				return false;
		}
		return true;
	}

	protected void addTargetGroups(List<TargetGroup> evaluatedGroups) {
		if (targetGroups == null) {
			targetGroups = new ArrayList<>();
			for (TargetGroup group : evaluatedGroups) {
				targetGroups.add(group);
			}
		} else {
			for (TargetGroup group : evaluatedGroups) {
				if (group.isComplete()) {
					replaceTargetGroup(group);
				}
			}
		}
	}

	protected void replaceTargetGroup(TargetGroup replacement) {
		for (TargetGroup g : targetGroups) {
			if (g.getTargetGroupName().equals(replacement.getTargetGroupName())) {
				targetGroups.remove(g);
				targetGroups.add(replacement);
				return;
			}
		}
	}

	public void printTargetGroups() {
		System.out.println("Number of target groups: [" + targetGroups.size() + "]");
		for (TargetGroup targetGroup : targetGroups) {
			System.out.println("**********");
			System.out.println(targetGroup);
			System.out.println("**********");
		}
	}
	
}
