package com.advancedtopics.app.fibonacci.opt4j;

import java.util.Random;

import org.opt4j.core.genotype.IntegerGenotype;
import org.opt4j.core.problem.Creator;
import org.opt4j.core.start.Constant;

import com.google.inject.Inject;

public class FibonaciCreator implements Creator<IntegerGenotype> {

	private Random random;
	private final int populationSize;

	@Inject
	public FibonaciCreator(@Constant(value = "populationSize") int populationSize) {
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