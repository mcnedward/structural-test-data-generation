package com.advancedtopics.app;

import com.advancedtopics.app.fibonacci.FibonnaciTest;
import com.advancedtopics.app.phonenumber.PhoneNumberTest;
import com.advancedtopics.app.quadratic.QuadTest;

/**
 * @author Edward McNealy <edwardmcn64@gmail.com> - Nov 14, 2015
 *
 */
public class Main {

	public static void main(String[] args) {
		QuadTest quadTest = new QuadTest();
		quadTest.evaluate();
		quadTest.printTargetGroups();
		System.out.println("\n");

		FibonnaciTest fibonnaciTest = new FibonnaciTest();
		fibonnaciTest.evaluate();
		fibonnaciTest.printTargetGroups();
		System.out.println("\n");

		PhoneNumberTest phoneNumberTest = new PhoneNumberTest();
		phoneNumberTest.evaluate();
		phoneNumberTest.printTargetGroups();
	}

}
