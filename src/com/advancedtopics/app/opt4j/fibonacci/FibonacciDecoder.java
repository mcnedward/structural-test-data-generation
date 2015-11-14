package com.advancedtopics.app.opt4j.fibonacci;

import java.util.ArrayList;
import java.util.List;

import org.opt4j.core.genotype.IntegerGenotype;
import org.opt4j.core.problem.Decoder;

public class FibonacciDecoder implements Decoder<IntegerGenotype, List<Long>> {

	@Override
	public List<Long> decode(IntegerGenotype genotype) {
		List<Long> longs = new ArrayList<Long>();
		for (int i = 0; i < genotype.size(); i++) {
			int g = genotype.get(i);
			longs.add((long) g);
		}
		return longs;
	}
}
