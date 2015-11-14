package com.advancedtopics.app.fibonacci;

import com.advancedtopics.app.Condition;
import com.advancedtopics.app.Condition.Operator;
import com.advancedtopics.app.opt4j.fibonacci.FibonacciEvaluator;

/**
 * @author Edward McNealy <edwardmcn64@gmail.com> - Nov 13, 2015
 *
 */
public class FibonacciSequence {
	/**
	 * O(log(n))
	 */
	public static long fib(long n) {
		Condition nCond = new Condition(Operator.LESS_THAN_OR_EQUAL, n, 0, n <= 0);
		FibonacciEvaluator.TARGETS.get("nLessThanOr0").addCondition(nCond);

		if (n <= 0) {
			FibonacciEvaluator.TARGETS.get("nLessThanOr0").setTargetHit(true);
			return 0;
		}

		long i = (int) (n - 1);
		long a = 1, b = 0, c = 0, d = 1, tmp1, tmp2;

		Condition iCond = new Condition(Operator.NOT_EQUALS, i % 2, 0, i % 2 != 0);
		FibonacciEvaluator.TARGETS.get("iMod2Not0").addCondition(iCond);

		while (i > 0) {
			if (i % 2 != 0) {
				tmp1 = d * b + c * a;
				tmp2 = d * (b + a) + c * b;
				a = tmp1;
				b = tmp2;
				FibonacciEvaluator.TARGETS.get("iMod2Not0").setTargetHit(true);
			}

			tmp1 = (long) (Math.pow(c, 2) + Math.pow(d, 2));
			tmp2 = d * (2 * c + d);

			c = tmp1;
			d = tmp2;

			i = i / 2;
		}
		return a + b;
	}
}
