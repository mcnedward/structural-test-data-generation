package com.advancedtopics.app.quadratic;

import org.opt4j.core.Objectives;

import com.advancedtopics.app.quadratic.opt4j.QuadCreator;
import com.advancedtopics.app.quadratic.opt4j.QuadDecoder;
import com.advancedtopics.app.quadratic.opt4j.QuadEvaluator;

public class QuadTest {

	public static void main(String[] args) {
		evaluateQuadriticEquations();
	}

	private static void evaluateQuadriticEquations() {
		StringBuilder builder = new StringBuilder();
		for (int x = 0; x < 100; x++) {
			QuadCreator creator = new QuadCreator(100);
			QuadDecoder decoder = new QuadDecoder();
			QuadEvaluator evaluator = new QuadEvaluator();

			Objectives objectives = evaluator.evaluate(decoder.decode(creator.create()));
			System.out.println(objectives.toString());
			builder.append("\n" + objectives.toString());
		}
		System.out.println(builder.toString());
	}

}
