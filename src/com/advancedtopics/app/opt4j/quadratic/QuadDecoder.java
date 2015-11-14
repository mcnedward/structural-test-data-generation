package com.advancedtopics.app.opt4j.quadratic;

import java.util.ArrayList;
import java.util.List;

import org.opt4j.core.genotype.IntegerGenotype;
import org.opt4j.core.problem.Decoder;

import com.advancedtopics.app.quadratic.QuadHolder;

public class QuadDecoder implements Decoder<IntegerGenotype, List<QuadHolder>> {

	@Override
	public List<QuadHolder> decode(IntegerGenotype genotype) {
		List<QuadHolder> quads = new ArrayList<QuadHolder>();
		QuadHolder holder = null;
		int x = 0;
		for (int i = 0; i < genotype.size(); i++) {
			if (x == 0) {
				holder = new QuadHolder();
				holder.setA(genotype.get(i));
				x++;
				continue;
			}
			else if (x == 1) {
				holder.setB(genotype.get(i));
				x++;
				continue;
			}
			else if (x == 2) {
				holder.setC(genotype.get(i));
				quads.add(holder);
				x = 0;
			}
		}
		return quads;
	}
}
