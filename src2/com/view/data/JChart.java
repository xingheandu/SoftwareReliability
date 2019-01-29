package com.view.data;

import java.awt.Color;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.type.ChartType;
/*
 * BY SIN
 * 使用方法：
 * dc = new DrawChart()
 * dc.add()
 * dc.getChartPanel()
 * 
 */
public class JChart {
	private JFreeChart chart;
	private XYSeriesCollection dataset = new XYSeriesCollection();
	private String chartTitle, xLabel, yLabel;
	private ChartType chartType;
	public JChart(String chartTitle, String xLabel, String yLabel, ChartType type)
	{
		this.chartTitle = chartTitle;
		this.xLabel = xLabel;
		this.yLabel = yLabel;
		this.chartType = type;
	}
	
	public void addDataSeries(XYSeries series)
	{
		dataset.addSeries(series);
	}
	
	public JPanel getChartPanel()
	{
		chart = ChartFactory.createXYLineChart(chartTitle, xLabel, yLabel,dataset);
				
		if(chartType == ChartType.Points)
		{
			XYLineAndShapeRenderer render = new XYLineAndShapeRenderer();
			render.setSeriesLinesVisible(0, false);
			((XYPlot) chart.getPlot()).setRenderer(render);
		}
		if(chartType == ChartType.PloygonalLine)
		{
			XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) chart.getXYPlot().getRenderer();
			renderer.setBaseShapesVisible(true);
		}
		ChartPanel chartPanel = new ChartPanel(chart);
		return chartPanel;
	}
	
	public void addDefaultSeries(int i)
	{
		XYSeries series = new XYSeries("y=x");
		series.add(0, 0);
		series.add(i,i);
		this.dataset.addSeries(series);
	}
	
}
