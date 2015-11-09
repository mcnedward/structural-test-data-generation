package com.advancedtopics.app;

import java.util.HashMap;
import java.util.Map;

public class QuadraticRoots {

	public Map<String, Object> findQuadraticRoots(QuadHolder holder) {
		double a = holder.getA();
		double b = holder.getB();
		double c = holder.getC();
//		System.out.println(String.format("%sx^2 + %sx + %s", holder.getA(), holder.getB(), holder.getC()));
		
		Complex[] roots = new Complex[2];
		double d = b * b - 4.0 * a * c; // discriminant
		double aa = a + a;
		String target = "";
		
		if (d < 0.0) {
			double re = -b / aa;
			double im = Math.sqrt(-d) / aa;
			roots[0] = new Complex(re, im);
			roots[1] = new Complex(re, -im);
			target = "discriminantLessThan0";
		} else if (b < 0.0) {
			// Avoid calculating -b - Math.sqrt(d), to avoid any
			// subtractive cancellation when it is near zero.
			double re = (-b + Math.sqrt(d)) / aa;
			roots[0] = new Complex(re, 0.0);
			roots[1] = new Complex(c / (a * re), 0.0);
			target = "bLessThan0";
		} else {
			// Avoid calculating -b + Math.sqrt(d).
			double re = (-b - Math.sqrt(d)) / aa;
			roots[1] = new Complex(re, 0.0);
			roots[0] = new Complex(c / (a * re), 0.0);
			target = "other";
		}
		Map<String, Object> value = new HashMap<String, Object>();
		value.put("complex", roots);
		value.put("target", target);
		return value;
	}
	
	public static double findDiscriminant(double a, double b, double c) {
		return b * b - 4.0 * a * c;
	}
}