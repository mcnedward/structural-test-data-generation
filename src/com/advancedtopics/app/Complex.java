package com.advancedtopics.app;

public class Complex {
	double re, im;

	public Complex(double re, double im) {
		this.re = re;
		this.im = im;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof Complex)) {
			return false;
		}
		Complex other = (Complex) obj;
		return (re == other.re) && (im == other.im);
	}

	@Override
	public String toString() {
		if (im == 0.0) {
			return String.format("%g", re);
		}
		if (re == 0.0) {
			return String.format("%gi", im);
		}
		return String.format("%g %c %gi", re, (im < 0.0 ? '-' : '+'), Math.abs(im));
	}
}