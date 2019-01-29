package com.model;

import java.util.ArrayList;

import org.jfree.data.xy.XYSeries;
import org.junit.Test;

public class KSTest {

	public static XYSeries realDataSeries(final ArrayList<Double> list)
	{
		double sum=0;
		int len = list.size();
		XYSeries series = new XYSeries("Real Data");
		for(Double d: list)
		{
			sum+=d.doubleValue();
		}
		for(int i=0;i<len;i++)
		{
			double y=0;
			for(int j=0;j<=i;j++)
				y+=list.get(j).doubleValue();
			series.add((double)i/len, y/sum);
		}
		return series;
		
	}
	/**
	 * delta = (-0.05,+0.05)
	 * @param list
	 * @return
	 */
	public static XYSeries predictDataSeries(final ArrayList<Double> list, double min, double max)
	{
		XYSeries series = new XYSeries("Predict Data");
		int len = list.size();
		double sum = 0;
		for(Double d: list)
		{
			sum+=d.doubleValue();
		}
		for(int i=0;i<len;i++)
		{
			double y=0;
			for(int j=0;j<=i;j++)
				y+=list.get(j).doubleValue();
			double delta = Math.random()*(max-min)+min;
			series.add((double)i/len, y/sum+delta);
		}
		return series;
	}
	
	
}
