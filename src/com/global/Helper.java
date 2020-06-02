package com.global;

import org.jfree.data.xy.XYSeries;

public class Helper {

	public static double logarithmicNormalDistribution(double x,double mu, double sigma)
	{
		double coe = 1/(Math.sqrt(2*Math.PI)*sigma);
		double exp = -(x-mu)*(x-mu)/(2*sigma*sigma);
		return coe * Math.exp(exp);
	}
	
	public static XYSeries getlogarithmicNormalDistribution(double mu, double sigma, String title)
	{
		XYSeries series = new XYSeries(title);
		for(int i=0;i<300;i++)
			series.add(i, logarithmicNormalDistribution(i, mu, sigma));
		return series;
	}
}
