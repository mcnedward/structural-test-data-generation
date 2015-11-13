package com.advancedtopics.app.phonenumber;

import org.opt4j.core.Objectives;

import com.advancedtopics.app.phonenumber.opt4j.PhoneNumberCreator;
import com.advancedtopics.app.phonenumber.opt4j.PhoneNumberDecoder;
import com.advancedtopics.app.phonenumber.opt4j.PhoneNumberEvaluator;

/**
 * @author Edward McNealy <edwardmcn64@gmail.com> - Nov 13, 2015
 *
 */
public class PhoneNumberTest {

	public static void main(String[] args) {
		ValidNumber validNumber = PhoneNumberChecker.checkPhoneNumber("01 333 444 5555");
		PhoneNumberChecker.printResults(validNumber);
		
		evaluatePhoneNumber();
	}
	
	private static void evaluatePhoneNumber() {
		StringBuilder builder = new StringBuilder();
		for (int x = 0; x < 100; x++) {
			PhoneNumberCreator creator = new PhoneNumberCreator(100);
			PhoneNumberDecoder decoder = new PhoneNumberDecoder();
			PhoneNumberEvaluator evaluator = new PhoneNumberEvaluator();

			Objectives objectives = evaluator.evaluate(decoder.decode(creator.create()));
			System.out.println(objectives.toString());
//			builder.append("\n" + objectives.toString());
		}
		System.out.println(builder.toString());
	}
	
}
