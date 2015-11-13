package com.advancedtopics.app.phonenumber.opt4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.opt4j.core.genotype.IntegerMapGenotype;
import org.opt4j.core.problem.Creator;
import org.opt4j.core.start.Constant;

import com.google.inject.Inject;

public class PhoneNumberCreator implements Creator<IntegerMapGenotype<String>> {

	private Random random;
	private final int populationSize;

	@Inject
	public PhoneNumberCreator(@Constant(value = "populationSize") int populationSize) {
		this.populationSize = populationSize;
		random = new Random();
	}

	@Override
	public IntegerMapGenotype<String> create() {
		List<String> keys = new ArrayList<String>();
		keys.add("areaCode");
		keys.add("next3");
		keys.add("last4");
		IntegerMapGenotype<String> genotype = new IntegerMapGenotype<String>(keys, 100, 999);
		genotype.init(random);
		
		return genotype;
	}
}