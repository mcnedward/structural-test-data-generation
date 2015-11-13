package com.advancedtopics.app.fibonacci;

import org.opt4j.core.Objectives;

import com.advancedtopics.app.fibonacci.opt4j.FibonaciCreator;
import com.advancedtopics.app.fibonacci.opt4j.FibonnaciDecoder;
import com.advancedtopics.app.fibonacci.opt4j.FibonnaciEvaluator;

/**
 * @author Edward McNealy <edwardmcn64@gmail.com> - Nov 13, 2015
 *
 */
public class FibonnaciTest {

	public static void main(String[] args) {
		evaluateFibonnaci();
	}
	
	private static void evaluateFibonnaci() {
		StringBuilder builder = new StringBuilder();
		for (int x = 0; x < 100; x++) {
			FibonaciCreator creator = new FibonaciCreator(100);
			FibonnaciDecoder decoder = new FibonnaciDecoder();
			FibonnaciEvaluator evaluator = new FibonnaciEvaluator();

			Objectives objectives = evaluator.evaluate(decoder.decode(creator.create()));
			System.out.println(objectives.toString());
			builder.append("\n" + objectives.toString());
		}
		System.out.println(builder.toString());
	}
	
}
