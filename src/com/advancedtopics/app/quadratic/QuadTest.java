package com.advancedtopics.app.quadratic;

import java.util.List;

import com.advancedtopics.app.TargetGroup;
import com.advancedtopics.app.opt4j.BaseTest;
import com.advancedtopics.app.opt4j.quadratic.QuadCreator;
import com.advancedtopics.app.opt4j.quadratic.QuadDecoder;
import com.advancedtopics.app.opt4j.quadratic.QuadEvaluator;

public class QuadTest extends BaseTest {

	@Override
	protected List<TargetGroup> evaluate() {
		QuadCreator creator = new QuadCreator(100);
		QuadDecoder decoder = new QuadDecoder();
		QuadEvaluator evaluator = new QuadEvaluator();

		return evaluator.evaluate(decoder.decode(creator.create()));
	}

}
