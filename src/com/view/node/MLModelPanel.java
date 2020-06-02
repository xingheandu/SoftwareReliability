package com.view.node;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.jfree.data.xy.XYSeries;

import com.type.ChartType;
import com.type.ModelType;
import com.view.data.JChart;

/**
 * @author sin
 *	Machine Learing Model Panel
 *	BP和SVM模型的专属ModelPanel
 */
public class MLModelPanel {
	private JTabbedPane tabbedPane;
	private JChart KSTestDiagram;
	private JChart predictDiagram;
	private ModelType modelType;
	
	public MLModelPanel(ModelType type) {
		this.modelType = type;
		tabbedPane = new JTabbedPane();
		initKSTestPanel();
		initPredictDiagram();
		//tabbedPane.add("参数设置", paramPanel);
		tabbedPane.add("预测对比", predictDiagram.getChartPanel());
		tabbedPane.add("K-S检验",KSTestDiagram.getChartPanel());
	}
	
	public JTabbedPane getTabbedPane()
	{
		return tabbedPane;
	}

	private void initPredictDiagram() {
		predictDiagram = new JChart("Predict Diagram", "x", "y", ChartType.PloygonalLine);
	}

	private void initKSTestPanel() {
		KSTestDiagram = new JChart("K-S Test", "X", "Y"	, ChartType.PloygonalLine);
		KSTestDiagram.addDefaultSeries(1);
	}
	
	public void addPredictDiagramSeries(XYSeries series)
	{
		predictDiagram.addDataSeries(series);
	}
	
	public void addKSTestDiagram(XYSeries series)
	{
		KSTestDiagram.addDataSeries(series);
	}
}
