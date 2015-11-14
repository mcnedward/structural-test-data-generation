package com.advancedtopics.app.opt4j.phonenumber;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.opt4j.core.genotype.IntegerMapGenotype;
import org.opt4j.core.problem.Creator;

import com.google.inject.Inject;

public class PhoneNumberCreator implements Creator<IntegerMapGenotype<String>> {

	private Random random;

	@Inject
	public PhoneNumberCreator() {
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