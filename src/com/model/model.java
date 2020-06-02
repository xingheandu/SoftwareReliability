package com.model;

import java.util.ArrayList;
import java.util.List;

public abstract class model {
	protected int n;								
	protected List<Double> t = new ArrayList<>();	

	public abstract double funcF(double x, int i);	
	public abstract double funcfm(double x, int i);
	public abstract List<Double> getT();
}
