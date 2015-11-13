package com.advancedtopics.app.phonenumber;

import com.advancedtopics.app.phonenumber.PhoneNumberChecker.Country;
import com.advancedtopics.app.phonenumber.PhoneNumberChecker.UkAreaCode;
import com.advancedtopics.app.phonenumber.PhoneNumberChecker.UsAreaCode;

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
		phoneNumber = phoneNumber.replace(" ", "");
		if (phoneNumber.length() != 12)
			return null;

		validNumber = new ValidNumber(phoneNumber);
		boolean isValid = false;
		
		int countryCode1, countryCode2;
		countryCode1 = getNumberAt(0, 1, phoneNumber);
		countryCode2 = getNumberAt(1, 2, phoneNumber);

		if (countryCode1 == 4 && countryCode2 == 4) {
			// UK Country Code
			validNumber.setCountry(Country.UK);
		}
		if (countryCode1 == 0 && countryCode2 == 1) {
			// US Country Code
			validNumber.setCountry(Country.US);
		}
		if (checkAreaCode(validNumber)) {
			if (checkNext3(phoneNumber)) {
				isValid = checkLast4(phoneNumber);
			}
		}
		
		validNumber.setValid(isValid);

		return validNumber;
	}

	private static boolean checkAreaCode(ValidNumber number) {
		int areaCode = getNumberAt(2, 5, number.getPhoneNumber());
		if (areaCode > 0 && areaCode <= 300) {
			if (number.getCountry() == Country.US) {
				validNumber.setUsAreaCode(UsAreaCode.CHICAGO);
				return true;
			}
			if (number.getCountry() == Country.UK) {
				validNumber.setUkAreaCode(UkAreaCode.GLASGOW);
				return true;
			}
		}
		if (areaCode > 301 && areaCode <= 600) {
			if (number.getCountry() == Country.US) {
				validNumber.setUsAreaCode(UsAreaCode.CINCINNATI);
				return true;
			}
			if (number.getCountry() == Country.UK) {
				validNumber.setUkAreaCode(UkAreaCode.EDINBURGH);
				return true;
			}
		}
		if (areaCode > 601 && areaCode <= 900) {
			if (number.getCountry() == Country.US) {
				validNumber.setUsAreaCode(UsAreaCode.NEW_YORK_CITY);
				return true;
			}
			if (number.getCountry() == Country.UK) {
				validNumber.setUkAreaCode(UkAreaCode.LONDON);
				return true;
			}
		}
		return false;
	}

	private static boolean checkNext3(String phoneNumber) {
		int next3 = getNumberAt(5, 8, phoneNumber);
		if (next3 >= 200 && next3 <= 700)
			return true;
		return false;
	}

	private static boolean checkLast4(String phoneNumber) {
		int last4 = getNumberAt(8, 12, phoneNumber);
		if (last4 >= 3000 && last4 <= 9000)
			return true;
		return false;
	}

	private static int getNumberAt(int startingIndex, int endingIndex, String text) {
		int number = Integer.parseInt(text.substring(startingIndex, endingIndex));
		return number;
	}

}

class ValidNumber {
	private String phoneNumber;
	private Country country;
	private UkAreaCode ukAreaCode;
	private UsAreaCode usAreaCode;
	private boolean valid;

	public ValidNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber
	 *            the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the country
	 */
	public Country getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(Country country) {
		this.country = country;
	}

	/**
	 * @return the ukAreaCode
	 */
	public UkAreaCode getUkAreaCode() {
		return ukAreaCode;
	}

	/**
	 * @param ukAreaCode
	 *            the ukAreaCode to set
	 */
	public void setUkAreaCode(UkAreaCode ukAreaCode) {
		this.ukAreaCode = ukAreaCode;
	}

	/**
	 * @return the usAreaCode
	 */
	public UsAreaCode getUsAreaCode() {
		return usAreaCode;
	}

	/**
	 * @param usAreaCode
	 *            the usAreaCode to set
	 */
	public void setUsAreaCode(UsAreaCode usAreaCode) {
		this.usAreaCode = usAreaCode;
	}

	/**
	 * @return the valid
	 */
	public boolean isValid() {
		return valid;
	}

	/**
	 * @param valid the valid to set
	 */
	public void setValid(boolean valid) {
		this.valid = valid;
	}
}
