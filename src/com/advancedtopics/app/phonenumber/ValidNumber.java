package com.advancedtopics.app.phonenumber;

import com.advancedtopics.app.phonenumber.PhoneNumberChecker.Country;
import com.advancedtopics.app.phonenumber.PhoneNumberChecker.UkAreaCode;
import com.advancedtopics.app.phonenumber.PhoneNumberChecker.UsAreaCode;

/**
 * @author Edward McNealy <edwardmcn64@gmail.com> - Nov 13, 2015
 *
 */
public class ValidNumber {
	private String phoneNumber;
	private Country country;
	private UkAreaCode ukAreaCode;
	private UsAreaCode usAreaCode;
	private boolean valid;
	private String invalidReason;

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
	 * @param valid
	 *            the valid to set
	 */
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
	/**
	 * @return the invalidReason
	 */
	public String getInvalidReason() {
		return invalidReason;
	}

	/**
	 * @param invalidReason the invalidReason to set
	 */
	public void setInvalidReason(String invalidReason) {
		this.invalidReason = invalidReason;
	}

	@Override
	public String toString() {
		return "Number: [" + phoneNumber + "] is valid? [" + valid + "]" + (!valid ? " Reason: " + invalidReason : "");
	}
}
