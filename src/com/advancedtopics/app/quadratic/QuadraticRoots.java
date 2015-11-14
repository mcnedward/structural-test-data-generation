package com.advancedtopics.app.quadratic;

import com.advancedtopics.app.Complex;
import com.advancedtopics.app.Condition;
import com.advancedtopics.app.Condition.Operator;
import com.advancedtopics.app.JoinedCondition;
import com.advancedtopics.app.opt4j.quadratic.QuadEvaluator;

public class QuadraticRoots {

	public static String findQuadraticRoots(QuadHolder holder) {
		double a = holder.getA();
		double b = holder.getB();
		double c = holder.getC();
		// System.out.println(String.format("%sx^2 + %sx + %s", holder.getA(), holder.getB(), holder.getC()));

		Complex[] roots = new Complex[2];
		double d = b * b - 4.0 * a * c; // discriminant
		double aa = a + a;

		Condition discCond = new Condition(Operator.LESS_THAN, d, 0.0, d < 0.0);
		QuadEvaluator.TARGETS.get("disriminantLessThan0").addCondition(discCond);

		Condition bCond1 = new Condition(Operator.LESS_THAN, b, 0.0, b < 0.0);
		Condition bCond2 = new Condition(discCond, true);
		JoinedCondition bJoinCond = new JoinedCondition(bCond1, bCond2);
		QuadEvaluator.TARGETS.get("bLessThan0").addCondition(bJoinCond);

		Condition oCond1 = new Condition(discCond, true);
		Condition oCond2 = new Condition(bCond1, true);
		JoinedCondition jc = new JoinedCondition(oCond1, oCond2);
		QuadEvaluator.TARGETS.get("other").addCondition(jc);

		if (d < 0.0) {
			double re = -b / aa;
			double im = Math.sqrt(-d) / aa;
			roots[0] = new Complex(re, im);
			roots[1] = new Complex(re, -im);
			QuadEvaluator.TARGETS.get("disriminantLessThan0").setTargetHit(true);
		} else if (b < 0.0) {
			// Avoid calculating -b - Math.sqrt(d), to avoid any
			// subtractive cancellation when it is near zero.
			double re = (-b + Math.sqrt(d)) / aa;
			roots[0] = new Complex(re, 0.0);
			roots[1] = new Complex(c / (a * re), 0.0);
			QuadEvaluator.TARGETS.get("bLessThan0").setTargetHit(true);
		} else {
			// Avoid calculating -b + Math.sqrt(d).
			double re = (-b - Math.sqrt(d)) / aa;
			roots[1] = new Complex(re, 0.0);
			roots[0] = new Complex(c / (a * re), 0.0);
			QuadEvaluator.TARGETS.get("other").setTargetHit(true);
		}
		String result = roots[0] + ", " + roots[1];
		return result;
	}

	public static double findDiscriminant(double a, double b, double c) {
		return b * b - 4.0 * a * c;
	}
	
}