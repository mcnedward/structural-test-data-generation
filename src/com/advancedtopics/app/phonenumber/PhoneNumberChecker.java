package com.advancedtopics.app.phonenumber;

import com.advancedtopics.app.Condition;
import com.advancedtopics.app.Condition.Operator;
import com.advancedtopics.app.JoinedCondition;
import com.advancedtopics.app.phonenumber.opt4j.PhoneNumberEvaluator;

/**
 * @author Edward McNealy <edwardmcn64@gmail.com> - Nov 13, 2015
 *
 */
public class PhoneNumberChecker {

	protected enum Country {
		UK, US
	};

	protected enum UkAreaCode {
		GLASGOW, EDINBURGH, LONDON
	};

	protected enum UsAreaCode {
		CHICAGO, CINCINNATI, NEW_YORK_CITY
	};

	static ValidNumber validNumber;

	public static ValidNumber checkPhoneNumber(String phoneNumber) {
		validNumber = new ValidNumber(phoneNumber);
		boolean isValid = false;
		
		int countryCode1, countryCode2;
		countryCode1 = getNumberAt(0, 1, phoneNumber);
		countryCode2 = getNumberAt(1, 2, phoneNumber);

		setConditionsForCountryCodes(countryCode1, countryCode2);
		if (countryCode1 == 4 && countryCode2 == 4) {
			// UK Country Code
			validNumber.setCountry(Country.UK);
			PhoneNumberEvaluator.TARGETS.get("ukCountryCode").setTargetHit(true);
		}
		else if (countryCode1 == 0 && countryCode2 == 1) {
			// US Country Code
			validNumber.setCountry(Country.US);
			PhoneNumberEvaluator.TARGETS.get("usCountryCode").setTargetHit(true);
		} else {
			PhoneNumberEvaluator.TARGETS.get("noCountryCode").setTargetHit(true);
			validNumber.setInvalidReason("Country code is invalid");
			validNumber.setValid(isValid);
			return validNumber;
		}
		
		int next3 = getNumberAt(5, 8, phoneNumber);
		Condition next3Cond1 = new Condition(Operator.GREATER_THAN_OR_EQUAL, next3, 200, next3 >= 200);
		Condition next3Cond2 = new Condition(Operator.LESS_THAN_OR_EQUAL, next3, 700, next3 <= 700);
		JoinedCondition next3Joined = new JoinedCondition(next3Cond1, next3Cond2);
		PhoneNumberEvaluator.TARGETS.get("checkNext3").addCondition(next3Joined);
		PhoneNumberEvaluator.TARGETS.get("checkNext3").setApproachDistance(2);
		
		int last4 = getNumberAt(8, 12, phoneNumber);
		Condition last4Cond1 = new Condition(Operator.GREATER_THAN_OR_EQUAL, last4, 3000, last4 >= 3000);
		Condition last4Cond2 = new Condition(Operator.LESS_THAN_OR_EQUAL, last4, 9000, last4 <= 9000);
		JoinedCondition last4Joined = new JoinedCondition(last4Cond1, last4Cond2);
		PhoneNumberEvaluator.TARGETS.get("checkLast4").addCondition(last4Joined);
		PhoneNumberEvaluator.TARGETS.get("checkLast4").setApproachDistance(2);
		
		if (checkAreaCode(validNumber)) {
			if (checkNext3(next3)) {
				isValid = checkLast4(last4);
			}
		}
		validNumber.setValid(isValid);
		return validNumber;
	}
	
	public static void printResults(ValidNumber validNumber) {
		if (validNumber == null) {
			System.out.println("Phone number is too long or short.");
		}
		else if (!validNumber.isValid())
			System.out.println("Number is not valid");
		else {
			if (validNumber.getCountry() == Country.UK) {
				System.out.println("Valid number in the UK in the area code for " + validNumber.getUkAreaCode() + " is: " + validNumber.getPhoneNumber());
			}
			if (validNumber.getCountry() == Country.US) {
				System.out.println("Valid number in the US in the area code for " + validNumber.getUsAreaCode() + " is: " + validNumber.getPhoneNumber());
			}
		}
	}

	private static boolean checkAreaCode(ValidNumber number) {
		int areaCode = getNumberAt(2, 5, number.getPhoneNumber());
		
		Condition areaCode1_1 = new Condition(Operator.GREATER_THAN, areaCode, 0, areaCode > 0);
		Condition areaCode1_2 = new Condition(Operator.LESS_THAN_OR_EQUAL, areaCode, 300, areaCode <= 300);
		JoinedCondition areaCode1 = new JoinedCondition(areaCode1_1, areaCode1_2);
		PhoneNumberEvaluator.TARGETS.get("areaCode1").addCondition(areaCode1);

		setConditionsForCountryAreaCodeCheck(number, 1);
		
		if (areaCode > 0 && areaCode <= 300) {
			PhoneNumberEvaluator.TARGETS.get("areaCode1").setTargetHit(true);
			PhoneNumberEvaluator.TARGETS.get("usForAreaCode1").setApproachDistance(0);
			PhoneNumberEvaluator.TARGETS.get("ukForAreaCode1").setApproachDistance(0);
			PhoneNumberEvaluator.TARGETS.get("checkNext3").setApproachDistance(1);
			PhoneNumberEvaluator.TARGETS.get("checkLast4").setApproachDistance(1);
			
			if (number.getCountry() == Country.US) {
				PhoneNumberEvaluator.TARGETS.get("usForAreaCode1").setTargetHit(true);
				PhoneNumberEvaluator.TARGETS.get("checkNext3").setApproachDistance(0);
				PhoneNumberEvaluator.TARGETS.get("checkLast4").setApproachDistance(0);
				validNumber.setUsAreaCode(UsAreaCode.CHICAGO);
				return true;
			}
			if (number.getCountry() == Country.UK) {
				PhoneNumberEvaluator.TARGETS.get("ukForAreaCode1").setTargetHit(true);
				PhoneNumberEvaluator.TARGETS.get("checkNext3").setApproachDistance(0);
				PhoneNumberEvaluator.TARGETS.get("checkLast4").setApproachDistance(0);
				validNumber.setUkAreaCode(UkAreaCode.GLASGOW);
				return true;
			}
		}
		
		Condition areaCode2_1 = new Condition(Operator.GREATER_THAN, areaCode, 301, areaCode > 301);
		Condition areaCode2_2 = new Condition(Operator.LESS_THAN_OR_EQUAL, areaCode, 600, areaCode <= 600);
		JoinedCondition areaCode2 = new JoinedCondition(areaCode2_1, areaCode2_2);
		PhoneNumberEvaluator.TARGETS.get("areaCode2").addCondition(areaCode2);
		
		setConditionsForCountryAreaCodeCheck(number, 2);
		
		if (areaCode > 301 && areaCode <= 600) {
			PhoneNumberEvaluator.TARGETS.get("areaCode2").setTargetHit(true);
			PhoneNumberEvaluator.TARGETS.get("usForAreaCode2").setApproachDistance(0);
			PhoneNumberEvaluator.TARGETS.get("ukForAreaCode2").setApproachDistance(0);
			PhoneNumberEvaluator.TARGETS.get("checkNext3").setApproachDistance(1);
			PhoneNumberEvaluator.TARGETS.get("checkLast4").setApproachDistance(1);
			
			if (number.getCountry() == Country.US) {
				PhoneNumberEvaluator.TARGETS.get("usForAreaCode2").setTargetHit(true);
				PhoneNumberEvaluator.TARGETS.get("checkNext3").setApproachDistance(0);
				PhoneNumberEvaluator.TARGETS.get("checkLast4").setApproachDistance(0);
				validNumber.setUsAreaCode(UsAreaCode.CINCINNATI);
				return true;
			}
			if (number.getCountry() == Country.UK) {
				PhoneNumberEvaluator.TARGETS.get("ukForAreaCode2").setTargetHit(true);
				PhoneNumberEvaluator.TARGETS.get("checkNext3").setApproachDistance(0);
				PhoneNumberEvaluator.TARGETS.get("checkLast4").setApproachDistance(0);
				validNumber.setUkAreaCode(UkAreaCode.EDINBURGH);
				return true;
			}
		}
		
		Condition areaCode3_1 = new Condition(Operator.GREATER_THAN, areaCode, 601, areaCode > 601);
		Condition areaCode3_2 = new Condition(Operator.LESS_THAN_OR_EQUAL, areaCode, 900, areaCode <= 900);
		JoinedCondition areaCode3 = new JoinedCondition(areaCode3_1, areaCode3_2);
		PhoneNumberEvaluator.TARGETS.get("areaCode3").addCondition(areaCode3);
		if (areaCode > 601 && areaCode <= 900) {
			PhoneNumberEvaluator.TARGETS.get("areaCode3").setTargetHit(true);
			PhoneNumberEvaluator.TARGETS.get("usForAreaCode3").setApproachDistance(0);
			PhoneNumberEvaluator.TARGETS.get("ukForAreaCode3").setApproachDistance(0);
			PhoneNumberEvaluator.TARGETS.get("checkNext3").setApproachDistance(1);
			PhoneNumberEvaluator.TARGETS.get("checkLast4").setApproachDistance(1);
			
			if (number.getCountry() == Country.US) {
				PhoneNumberEvaluator.TARGETS.get("usForAreaCode3").setTargetHit(true);
				PhoneNumberEvaluator.TARGETS.get("checkNext3").setApproachDistance(0);
				PhoneNumberEvaluator.TARGETS.get("checkLast4").setApproachDistance(0);
				validNumber.setUsAreaCode(UsAreaCode.NEW_YORK_CITY);
				return true;
			}
			if (number.getCountry() == Country.UK) {
				PhoneNumberEvaluator.TARGETS.get("ukForAreaCode3").setTargetHit(true);
				PhoneNumberEvaluator.TARGETS.get("checkNext3").setApproachDistance(0);
				PhoneNumberEvaluator.TARGETS.get("checkLast4").setApproachDistance(0);
				validNumber.setUkAreaCode(UkAreaCode.LONDON);
				return true;
			}
		}
		validNumber.setInvalidReason("Area code is out of bounds.");
		return false;
	}

	private static boolean checkNext3(int next3) {
		if (next3 >= 200 && next3 <= 700) {
			PhoneNumberEvaluator.TARGETS.get("checkNext3").setTargetHit(true);
			return true;
		}
		validNumber.setInvalidReason("Middle 3 digits are out of bounds.");
		return false;
	}

	private static boolean checkLast4(int last4) {
		if (last4 >= 3000 && last4 <= 9000) {
			PhoneNumberEvaluator.TARGETS.get("checkLast4").setTargetHit(true);
			return true;
		}
		validNumber.setInvalidReason("Last 4 digits are out of bounds.");
		return false;
	}

	private static int getNumberAt(int startingIndex, int endingIndex, String text) {
		int number = Integer.parseInt(text.substring(startingIndex, endingIndex));
		return number;
	}
	
	private static void setConditionsForCountryCodes(int countryCode1, int countryCode2) {
		Condition ukCond1 = new Condition(Operator.EQUALS, countryCode1, 4, countryCode1 == 4);
		Condition ukCond2 = new Condition(Operator.EQUALS, countryCode2, 4, countryCode2 == 4);
		JoinedCondition ukJoin = new JoinedCondition(ukCond1, ukCond2);
		PhoneNumberEvaluator.TARGETS.get("ukCountryCode").addCondition(ukJoin);
		
		Condition usCond1 = new Condition(Operator.EQUALS, countryCode1, 0, countryCode1 == 0);
		Condition usCond2 = new Condition(Operator.EQUALS, countryCode2, 1, countryCode2 == 1);
		JoinedCondition usJoin = new JoinedCondition(usCond1, usCond2);
		PhoneNumberEvaluator.TARGETS.get("usCountryCode").addCondition(usJoin);
		
		Condition other1 = new Condition(ukCond1, true);
		Condition other2 = new Condition(ukCond2, true);
		Condition other3 = new Condition(usCond1, true);
		Condition other4 = new Condition(usCond2, true);
		JoinedCondition join1 = new JoinedCondition(other1, other2);
		JoinedCondition join2 = new JoinedCondition(other3, other4);
		PhoneNumberEvaluator.TARGETS.get("noCountryCode").addCondition(join1);
		PhoneNumberEvaluator.TARGETS.get("noCountryCode").addCondition(join2);
	}
	
	private static void setConditionsForCountryAreaCodeCheck(ValidNumber number, int index) {
		String us = "usForAreaCode" + index;
		String uk = "ukForAreaCode" + index;
		Condition usCountryCheck = new Condition(33, number.getCountry() == Country.US);
		PhoneNumberEvaluator.TARGETS.get(us).addCondition(usCountryCheck);
		PhoneNumberEvaluator.TARGETS.get(us).setApproachDistance(1);
		
		Condition ukCountryCheck = new Condition(33, number.getCountry() == Country.UK);
		PhoneNumberEvaluator.TARGETS.get(uk).addCondition(ukCountryCheck);
		PhoneNumberEvaluator.TARGETS.get(uk).setApproachDistance(1);
	}

}
