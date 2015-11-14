package com.advancedtopics.app.opt4j.phonenumber;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.advancedtopics.app.Target;
import com.advancedtopics.app.TargetGroup;
import com.advancedtopics.app.opt4j.BaseEvaluator;
import com.advancedtopics.app.phonenumber.PhoneNumber;
import com.advancedtopics.app.phonenumber.PhoneNumberChecker;

public class PhoneNumberEvaluator extends BaseEvaluator<String> {

	public static Map<String, Target> TARGETS = new HashMap<>();
	
	@Override
	public List<TargetGroup> evaluate(String phenotype) {
		createTargets();

		PhoneNumber phoneNumber = PhoneNumberChecker.checkPhoneNumber(phenotype);

		calculateFitness();
		setTargetGroups();
		setPhoneNumberForTargetsHit(phoneNumber);

		return targetGroups;
	}

	@Override
	protected void setTargetNames() {
		targetNames.add("ukCountryCode");
		targetNames.add("usCountryCode");
		targetNames.add("noCountryCode");
		targetNames.add("areaCode1");
		targetNames.add("usForAreaCode1");
		targetNames.add("ukForAreaCode1");
		targetNames.add("areaCode2");
		targetNames.add("usForAreaCode2");
		targetNames.add("ukForAreaCode2");
		targetNames.add("areaCode3");
		targetNames.add("usForAreaCode3");
		targetNames.add("ukForAreaCode3");
		targetNames.add("checkNext3");
		targetNames.add("checkLast4");
	}

	/**
	 * Set a {@link TargetGroup} for all of the different possible combinations of {@link Target}s that can be hit.
	 */

	@Override
	protected void setTargetGroups() {
		// Everything passed for area code 1
		createTargetGroup("Everything Passed for UK", TARGETS.get("ukCountryCode"), TARGETS.get("areaCode1"), TARGETS.get("ukForAreaCode1"),
				TARGETS.get("checkNext3"), TARGETS.get("checkLast4"));
		createTargetGroup("Everything Passed for US", TARGETS.get("usCountryCode"), TARGETS.get("areaCode1"), TARGETS.get("usForAreaCode1"),
				TARGETS.get("checkNext3"), TARGETS.get("checkLast4"));
		// Everything passed for area code 2
		createTargetGroup("Everything Passed for UK", TARGETS.get("ukCountryCode"), TARGETS.get("areaCode2"), TARGETS.get("ukForAreaCode2"),
				TARGETS.get("checkNext3"), TARGETS.get("checkLast4"));
		createTargetGroup("Everything Passed for US", TARGETS.get("usCountryCode"), TARGETS.get("areaCode2"), TARGETS.get("usForAreaCode2"),
				TARGETS.get("checkNext3"), TARGETS.get("checkLast4"));
		// Everything passed for area code 3
		createTargetGroup("Everything Passed for UK", TARGETS.get("ukCountryCode"), TARGETS.get("areaCode3"), TARGETS.get("ukForAreaCode3"),
				TARGETS.get("checkNext3"), TARGETS.get("checkLast4"));
		createTargetGroup("Everything Passed for US", TARGETS.get("usCountryCode"), TARGETS.get("areaCode3"), TARGETS.get("usForAreaCode3"),
				TARGETS.get("checkNext3"), TARGETS.get("checkLast4"));

		// Missing middle 3 digits for area code 1
		createTargetGroup("Everything Passed for UK", TARGETS.get("ukCountryCode"), TARGETS.get("areaCode1"), TARGETS.get("ukForAreaCode1"));
		createTargetGroup("Everything Passed for US", TARGETS.get("usCountryCode"), TARGETS.get("areaCode1"), TARGETS.get("usForAreaCode1"));
		// Missing middle 3 digits for area code 2
		createTargetGroup("Everything Passed for UK", TARGETS.get("ukCountryCode"), TARGETS.get("areaCode2"), TARGETS.get("ukForAreaCode2"));
		createTargetGroup("Everything Passed for US", TARGETS.get("usCountryCode"), TARGETS.get("areaCode2"), TARGETS.get("usForAreaCode2"));
		// Missing middle 3 digits for area code 3
		createTargetGroup("Everything Passed for UK", TARGETS.get("ukCountryCode"), TARGETS.get("areaCode3"), TARGETS.get("ukForAreaCode3"));
		createTargetGroup("Everything Passed for US", TARGETS.get("usCountryCode"), TARGETS.get("areaCode3"), TARGETS.get("usForAreaCode3"));

		// Missing last 4 digits for area code 1
		createTargetGroup("Everything Passed for UK", TARGETS.get("ukCountryCode"), TARGETS.get("areaCode1"), TARGETS.get("ukForAreaCode1"),
				TARGETS.get("checkNext3"));
		createTargetGroup("Everything Passed for US", TARGETS.get("usCountryCode"), TARGETS.get("areaCode1"), TARGETS.get("usForAreaCode1"),
				TARGETS.get("checkNext3"));
		// Missing last 4 digits for area code 2
		createTargetGroup("Everything Passed for UK", TARGETS.get("ukCountryCode"), TARGETS.get("areaCode2"), TARGETS.get("ukForAreaCode2"),
				TARGETS.get("checkNext3"));
		createTargetGroup("Everything Passed for US", TARGETS.get("usCountryCode"), TARGETS.get("areaCode2"), TARGETS.get("usForAreaCode2"),
				TARGETS.get("checkNext3"));
		// Missing last 4 digits for area code 3
		createTargetGroup("Everything Passed for UK", TARGETS.get("ukCountryCode"), TARGETS.get("areaCode3"), TARGETS.get("ukForAreaCode3"),
				TARGETS.get("checkNext3"));
		createTargetGroup("Everything Passed for US", TARGETS.get("usCountryCode"), TARGETS.get("areaCode3"), TARGETS.get("usForAreaCode3"),
				TARGETS.get("checkNext3"));

		// Country code
		createTargetGroup("Only UK Country Code", TARGETS.get("ukCountryCode"));
		createTargetGroup("Only US Country Code", TARGETS.get("usCountryCode"));
		createTargetGroup("No Country Code", TARGETS.get("noCountryCode"));
	}
	
	@Override
	public Map<String, Target> getTargets() {
		return TARGETS;
	}

	private void setPhoneNumberForTargetsHit(PhoneNumber phoneNumber) {
		for (TargetGroup targetGroup : targetGroups) {
			if (targetGroup.isComplete()) {
				targetGroup.setValidNumber(phoneNumber);
			}
		}
	}
}
