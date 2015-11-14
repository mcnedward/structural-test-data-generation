package com.advancedtopics.app.opt4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.advancedtopics.app.Target;
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

	public void findFitness(List<TargetGroup> targetGroups) {
		Collections.sort(targetGroups, new TargetGroupComparator());
		for (TargetGroup group : targetGroups) {
			System.out.println("*****" + group);
			for (Target target : group.getTargetsRequired()) {
				System.out.println("Fitness for - " + target + " [" + target.getFitness() + "]\n*****");
			}
		}
	}
}

class TargetGroupComparator implements Comparator<TargetGroup> {

	@Override
	public int compare(TargetGroup o1, TargetGroup o2) {
		double fitnessO1 = 0, fitnessO2 = 0;
		for (Target target : o1.getTargetsRequired()) {
			fitnessO1 += target.getFitness();
		}
		for (Target target : o2.getTargetsRequired()) {
			fitnessO2 += target.getFitness();
		}
		if (fitnessO1 == fitnessO2)
			return 0;
		else if (fitnessO1 > fitnessO2)
			return 1;
		else
			return -1;
	}

}
