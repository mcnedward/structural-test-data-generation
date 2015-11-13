package com.advancedtopics.app.fibonacci.opt4j;

import org.opt4j.core.problem.ProblemModule;
import org.opt4j.core.start.Constant;

public class QuadModule extends ProblemModule {

	@Constant(value = "populationSize")
	protected int populationSize = 100;

	protected void config() {
		bindProblem(FibonaciCreator.class, FibonnaciDecoder.class, FibonnaciEvaluator.class);
	}

	/**
	 * @return the populationSize
	 */
	public int getPopulationSize() {
		return populationSize;
	}

	/**
	 * @param populationSize
	 *            the populationSize to set
	 */
	public void setPopulationSize(int populationSize) {
		this.populationSize = populationSize;
	}
}