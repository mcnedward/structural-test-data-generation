package com.advancedtopics.app.phonenumber;

import com.advancedtopics.app.phonenumber.PhoneNumberChecker.Country;

/**
 * @author Edward McNealy <edwardmcn64@gmail.com> - Nov 13, 2015
 *
 */
public class PhoneNumberTest {

	public static void main(String[] args) {
		ValidNumber validNumber = PhoneNumberChecker.checkPhoneNumber("01 333 444 5555");
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
	
}
