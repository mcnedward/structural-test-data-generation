package com.advancedtopics.app.quadratic;

public class QuadHolder {

	private double a,b,c;

	public double getA() {
		return a;
	}

	public void setA(double a) {
		this.a = a;
	}

	public double getB() {
		return b;
	}

	public void setB(double b) {
		this.b = b;
	}

	public double getC() {
		return c;
	}

	public void setC(double c) {
		this.c = c;
	}
	
	@Override
	public String toString() {
		return String.format("Quadratic Holder for A: %s; B: %s; C: %s", a, b, c);
	}
	
}
