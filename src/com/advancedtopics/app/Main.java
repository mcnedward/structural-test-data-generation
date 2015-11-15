package com.advancedtopics.app;

import com.advancedtopics.app.fibonacci.FibonacciTest;
import com.advancedtopics.app.phonenumber.PhoneNumberTest;
import com.advancedtopics.app.quadratic.QuadTest;

/**
 * @author Edward McNealy <edwardmcn64@gmail.com> - Nov 14, 2015
 *
 */
public class Main {

	public static void main(String[] args) {
		QuadTest quadTest = new QuadTest();
		quadTest.evaluate("Quadratic Equations");
		quadTest.printTargetGroups();
		System.out.println("\n");

		FibonacciTest fibonacciTest = new FibonacciTest();
		fibonacciTest.evaluate("Fibonacci Sequence");
		fibonacciTest.printTargetGroups();
		System.out.println("\n");

		PhoneNumberTest phoneNumberTest = new PhoneNumberTest();
		phoneNumberTest.evaluate("Phone Number Validation");
		phoneNumberTest.printTargetGroups();
	}

}
