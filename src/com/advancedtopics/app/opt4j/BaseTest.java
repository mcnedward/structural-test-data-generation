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
	protected List<TargetGroup> fitnessTargetGroups;
	protected int finishedIteration = 0;

	public void evaluate(String programName) {
		System.out.println("******************** Test for " + programName + " ********************");
		fitnessTargetGroups = new ArrayList<>();
		for (int x = 0; x < 100; x++) {
			List<TargetGroup> evaluatedGroups = evaluate();

			TargetGroup fitnessGroup = findFitnessGroup(evaluatedGroups);
			if (fitnessGroup != null)
				System.out.println("Optimal input with fitness of [" + fitnessGroup.getFitness() + "] was found with TargetGroup of: "
						+ fitnessGroup.getTargetGroupName() + ": " + fitnessGroup.getInput() + "\n*****");
			addTargetGroups(evaluatedGroups);
			fitnessTargetGroups.addAll(evaluatedGroups);
			addTargetGroups(evaluatedGroups);
			finishedIteration++;
		}
		findFitness(fitnessTargetGroups);
		System.out.println("********************");
	}

	protected abstract List<TargetGroup> evaluate();

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
		printTargetGroups(targetGroups);
	}

	public void printTargetGroups(List<TargetGroup> targetGroups) {
		System.out.println("Number of target groups: [" + targetGroups.size() + "]");
		for (TargetGroup targetGroup : targetGroups) {
			System.out.println("**********");
			System.out.println(targetGroup);
			System.out.println("**********");
		}
	}

	public void printDistinctCompleteTargets(List<TargetGroup> targetGroups) {
		List<TargetGroup> distinct = new ArrayList<>();
		for (TargetGroup group : targetGroups) {
			if (group.isComplete()) {
				if (distinct.isEmpty())
					distinct.add(group);
				else {
					boolean skip = false;
					for (TargetGroup g : distinct) {
						if (g.getTargetGroupName().equals(group.getTargetGroupName())) {
							skip = true;
							break;
						}
					}
					if (!skip)
						distinct.add(group);
				}
			}
		}
		System.out.println("DISTINCT");
		for (TargetGroup group : distinct) {
			if (group.isComplete()) {
				System.out.println("**********");
				System.out.println(group);
				System.out.println("**********");
			}
		}
	}

	public void printDistinctIncompleteTargets(List<TargetGroup> targetGroups) {
		List<TargetGroup> distinct = new ArrayList<>();
		for (TargetGroup group : targetGroups) {
			if (!group.isComplete()) {
				if (distinct.isEmpty())
					distinct.add(group);
				else {
					boolean skip = false;
					for (TargetGroup g : distinct) {
						if (g.getTargetGroupName().equals(group.getTargetGroupName())) {
							skip = true;
							break;
						}
					}
					if (!skip)
						distinct.add(group);
				}
			}
		}
		System.out.println("DISTINCT");
		for (TargetGroup group : distinct) {
			if (!group.isComplete()) {
				System.out.println("**********");
				System.out.println(group);
				System.out.println("**********");
			}
		}
	}

	public void findFitness(List<TargetGroup> targetGroups) {
		Collections.sort(targetGroups, new TargetGroupComparator());
		for (TargetGroup group : targetGroups) {
			System.out.println("*****" + group + "\n*****\n");
		}
	}

	protected TargetGroup findFitnessGroup(List<TargetGroup> evaluatedGroup) {
		TargetGroup fitnessGroup = null;
		for (TargetGroup group : evaluatedGroup) {
			if (group.isComplete()) {
				fitnessGroup = group;
				break;
			}
		}
		return fitnessGroup;
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
