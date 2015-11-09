package com.advancedtopics.app;

import org.opt4j.core.Objectives;

import com.advancedtopics.app.opt4j.QuadCreator;
import com.advancedtopics.app.opt4j.QuadDecoder;
import com.advancedtopics.app.opt4j.QuadEvaluator;

public class QuadTest {

	public static void main(String[] args) {
		for (int x = 0; x < 100; x++) {
			QuadCreator creator = new QuadCreator(100);
			QuadDecoder decoder = new QuadDecoder();
			QuadEvaluator evaluator = new QuadEvaluator();

			Objectives objectives = evaluator.evaluate(decoder.decode(creator.create()));
			System.out.println(objectives.toString());
		}
	}

}
