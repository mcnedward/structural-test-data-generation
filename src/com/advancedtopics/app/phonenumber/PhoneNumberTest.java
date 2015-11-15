package com.advancedtopics.app.phonenumber;

import java.util.List;

import com.advancedtopics.app.TargetGroup;
import com.advancedtopics.app.opt4j.BaseTest;
import com.advancedtopics.app.opt4j.phonenumber.PhoneNumberCreator;
import com.advancedtopics.app.opt4j.phonenumber.PhoneNumberDecoder;
import com.advancedtopics.app.opt4j.phonenumber.PhoneNumberEvaluator;

/**
 * @author Edward McNealy <edwardmcn64@gmail.com> - Nov 13, 2015
 *
 */
public class PhoneNumberTest extends BaseTest {

	@Override
	protected List<TargetGroup> evaluate() {
		PhoneNumberCreator creator = new PhoneNumberCreator();
		PhoneNumberDecoder decoder = new PhoneNumberDecoder();
		PhoneNumberEvaluator evaluator = new PhoneNumberEvaluator();

		return evaluator.evaluate(decoder.decode(creator.create()));
	}
}
