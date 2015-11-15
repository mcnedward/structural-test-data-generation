package com.advancedtopics.app.fibonacci;

import java.util.List;

import com.advancedtopics.app.TargetGroup;
import com.advancedtopics.app.opt4j.BaseTest;
import com.advancedtopics.app.opt4j.fibonacci.FibonacciDecoder;
import com.advancedtopics.app.opt4j.fibonacci.FibonacciEvaluator;
import com.advancedtopics.app.opt4j.fibonacci.FibonaciCreator;

/**
 * @author Edward McNealy <edwardmcn64@gmail.com> - Nov 13, 2015
 *
 */
public class FibonacciTest extends BaseTest {

	@Override
	protected List<TargetGroup> evaluate() {
		FibonaciCreator creator = new FibonaciCreator(100);
		FibonacciDecoder decoder = new FibonacciDecoder();
		FibonacciEvaluator evaluator = new FibonacciEvaluator();

		return evaluator.evaluate(decoder.decode(creator.create()));
	}

}
