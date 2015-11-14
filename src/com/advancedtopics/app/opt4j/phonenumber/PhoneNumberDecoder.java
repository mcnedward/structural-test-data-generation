package com.advancedtopics.app.opt4j.phonenumber;

import java.util.Random;

import org.opt4j.core.genotype.IntegerMapGenotype;
import org.opt4j.core.problem.Decoder;

public class PhoneNumberDecoder implements Decoder<IntegerMapGenotype<String>, String> {

	@Override
	public String decode(IntegerMapGenotype<String> genotype) {
		Random rand = new Random();

		int areaCode = genotype.getValue("areaCode");
		int next3 = genotype.getValue("next3");
		// Last 4 only has 3 digits, so it needs on more
		int last = rand.nextInt(9 - 0 + 1) - 0;
		int last4As3 = genotype.getValue("last4");
		int last4 = (last4As3 * 10) + last;

		String countryCode = getCountryCode();
		String ac = String.valueOf(areaCode);
		String n3 = String.valueOf(next3);
		String l4 = String.valueOf(last4);
		String fullNumber = countryCode + ac + n3 + l4;

		return fullNumber;
	}

	/**
	 * Set the country code. A random number between 1 and 3 is generated, and that is used to determine which country
	 * this phone number will be for, or if the number is 3, then no country code will be set.
	 * 
	 * @return The country code as a string.
	 */
	private String getCountryCode() {
		String countryCode = "";

		Random rand = new Random();
		int randNum = rand.nextInt(3 - 1 + 1) + 1;
		if (randNum == 1) {
			// UK country code
			countryCode = "44";
		}
		if (randNum == 2) {
			// US country code
			countryCode = "01";
		}
		if (randNum == 3) {
			// No country code
			countryCode = "00";
		}

		return countryCode;
	}
}
