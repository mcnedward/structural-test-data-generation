package com.advancedtopics.app.opt4j.fibonacci;

import java.util.Random;

import org.opt4j.core.genotype.IntegerGenotype;
import org.opt4j.core.problem.Creator;

public class FibonaciCreator implements Creator<IntegerGenotype> {

	private Random random;
	private int populationSize;
	
	public FibonaciCreator(int populationSize) {
		this.populationSize = populationSize;
		random = new Random();
	}

	@Override
	public IntegerGenotype create() {
		IntegerGenotype genotype = new IntegerGenotype(-10, 20);
		genotype.init(random, populationSize);
		return genotype;
	}
}